package org.edudev.domain.products;

import org.edudev.arch.db.MongoConfig;
import org.edudev.arch.repositoriesImpl.GenericRepositoryMongoImpl;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class ProductsMongoImpl extends GenericRepositoryMongoImpl<Product> implements Products {

    @Inject
    public ProductsMongoImpl(final MongoConfig mongoConfig) { super(Product.class, mongoConfig); }
}
