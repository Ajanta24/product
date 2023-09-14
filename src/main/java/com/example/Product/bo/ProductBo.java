package com.example.Product.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Product.model.HealthCheck;
import com.example.Product.model.ProductDto;

public interface ProductBo {
	public List<ProductDto> getProductDto();
	public HealthCheck healthcheck();
	public ProductDto saveProductDto(ProductDto productDto);
}
