package com.example.Product.mapper;

import com.example.Product.model.ProductDto;
import com.example.Product.model.ProductVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-23T14:54:57+0530",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public List<ProductVo> toProductVos(List<ProductDto> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductVo> list1 = new ArrayList<ProductVo>( list.size() );
        for ( ProductDto productDto : list ) {
            list1.add( toProductVo( productDto ) );
        }

        return list1;
    }

    @Override
    public List<ProductDto> toProductDtos(List<ProductVo> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDto> list1 = new ArrayList<ProductDto>( list.size() );
        for ( ProductVo productVo : list ) {
            list1.add( toProductDto( productVo ) );
        }

        return list1;
    }

    @Override
    public ProductDto toProductDto(ProductVo product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setProductId( product.getProductId() );
        productDto.setProductName( product.getProductName() );

        return productDto;
    }

    @Override
    public ProductVo toProductVo(ProductDto product1) {
        if ( product1 == null ) {
            return null;
        }

        ProductVo productVo = new ProductVo();

        productVo.setProductId( product1.getProductId() );
        productVo.setProductName( product1.getProductName() );

        return productVo;
    }
}
