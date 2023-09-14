package com.example.Product.dao;

import java.util.List;

import com.example.Product.model.ProductDto;

public interface ProductDao {
	
	public ProductDto saveProductDto(ProductDto productDto);
	public List<ProductDto> getProductDto();
	}
