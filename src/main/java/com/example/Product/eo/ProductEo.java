package com.example.Product.eo;

import java.util.List;

import com.example.Product.model.ProductDto;



public interface ProductEo {
	public List<ProductDto> getProductDto();
	public ProductDto saveProductDto(ProductDto productDto);
	public Object healthcheck();
}
