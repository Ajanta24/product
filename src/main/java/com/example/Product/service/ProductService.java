package com.example.Product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Product.model.HealthCheck;
import com.example.Product.model.ProductVo;
@Service
public interface ProductService {
	 public List<ProductVo> getProductVo();
	 public ProductVo saveProductVo(ProductVo productVo);
	 public HealthCheck healthcheck();
	}


