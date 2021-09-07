package org.edudev.arch.repositoriesImpl;

import dev.morphia.Datastore;
import dev.morphia.query.FindOptions;
import dev.morphia.query.experimental.filters.Filter;
import org.bson.BsonInt32;
import org.bson.Document;
import org.edudev.arch.db.MongoConfig;
import org.edudev.arch.domain.DomainEntity;
import org.edudev.arch.domain.Page;
import org.edudev.arch.domain.Query;
import org.edudev.arch.domain.Sort;
import org.edudev.arch.repositories.GenericRepository;
import org.edudev.domain.products.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.MongoClients.create;
import static dev.morphia.Morphia.createDatastore;
import static dev.morphia.query.experimental.filters.Filters.*;
import static java.util.Objects.requireNonNull;
import static org.edudev.arch.domain.SortOrder.DESC;

public class GenericRepositoryMongoImpl<T extends DomainEntity> implements GenericRepository<T> {

    private final Class<T> entityClass;

    private final Datastore datastore;

    public GenericRepositoryMongoImpl(final Class<T> entityClass, final MongoConfig mongoConfig) {
        this.entityClass = entityClass;
        this.datastore = createDatastore(create(mongoConfig.getUrl()), requireNonNull(mongoConfig.getUrl().getDatabase()));
        this.datastore.getMapper().map(Product.class);
        this.datastore.ensureIndexes();
    }


    @Override
    public void insert(final T entity) {
        datastore.insert(entity);
    }

    @Override
    public void update(final T entity) {
        datastore.merge(entity);
    }

    @Override
    public void remove(final T entity) {
        datastore.delete(entity);
    }

    @Override
    public Optional<T> findById(final String id) {
        return datastore.find(entityClass)
                .filter(eq("_id", id))
                .stream()
                .findFirst();
    }

    @Override
    public Collection<T> list(final Query query, final Sort sort, final Page page) {
        return getFilteredDocument(query)
                .iterator(
                        new FindOptions()
                                .sort(getSortedDocument(sort))
                                .skip(page.getFirst())
                                .limit(page.getLast())
                ).toList();

    }

    private dev.morphia.query.Query<T> getFilteredDocument(final Query query) {
        List<Filter> filters = new ArrayList<>();

        if (!query.getQuery().isEmpty()) {
            filters.add(
                    or(
                            eq("name", query.getQuery()),
                            eq("description", query.getQuery())
                    )
            );
        }

        if (!query.getMinPrice().isEmpty()) {
            filters.add(
                    gte("price", query.getMinPrice())
            );
        }


        if(!query.getMaxPrice().isEmpty()) {
            filters.add(
                    lte("price", query.getMaxPrice())
            );
        }

        return query.isEmpty() ? datastore.find(entityClass) : datastore.find(entityClass).filter(
                filters.toArray(new Filter[0])
        );
    }

    private Document getSortedDocument(final Sort sort) {
        return new Document(sort.getField(), new BsonInt32(sort.getType() == DESC ? -1 : 1));
    }

}
