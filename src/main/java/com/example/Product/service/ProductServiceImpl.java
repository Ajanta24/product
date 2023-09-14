package com.example.Product.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.Product.bo.ProductBo;
import com.example.Product.mapper.ProductMapper;
import com.example.Product.model.HealthCheck;
import com.example.Product.model.ProductVo;

@Component
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductBo productBo;
	@Autowired
	private ProductMapper productMapper;
	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Override
	public List<ProductVo> getProductVo() {
		logger.info("Retrieving data");
		return productMapper.toProductVos(productBo.getProductDto());
	}

	@Override
	public ProductVo saveProductVo(ProductVo product) {

		logger.info("Saving Product");
		return productMapper.toProductVo(productBo.saveProductDto(productMapper.toProductDto(product)));
	}

	@Override
	public HealthCheck healthcheck() {
		logger.info("Healthcheck in progress");
		return productBo.healthcheck();
	}

}
