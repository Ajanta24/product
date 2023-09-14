package com.example.Product.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.Product.model.ProductDto;
import com.example.Product.model.ProductVo;

@Mapper(componentModel = "Spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper{
	 Logger Logger = LoggerFactory.getLogger("mapper is mapping vo to dto");
		ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);
		List<ProductVo> toProductVos(List<ProductDto> list);
		List<ProductDto> toProductDtos(List<ProductVo> list);
		ProductDto toProductDto(ProductVo product);
		ProductVo toProductVo(ProductDto product1);
}