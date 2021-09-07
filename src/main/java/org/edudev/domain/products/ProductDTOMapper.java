package org.edudev.domain.products;

import org.edudev.arch.dtos.EntityDTOMapper;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class ProductDTOMapper extends EntityDTOMapper<Product, ProductDTO> {

    @Inject
    public ProductDTOMapper(final Products products) {
        super(
                ProductDTO::new,
                (dto) -> dto.update(products.findById(dto.getId()))
        );
    }
}
