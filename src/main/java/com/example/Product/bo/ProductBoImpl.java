package com.example.Product.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.Product.eo.ProductEo;
import com.example.Product.exception.TableNotFoundException;
import com.example.Product.model.HealthCheck;
import com.example.Product.model.ProductDto;

@Service
@Component
public class ProductBoImpl implements ProductBo{
	@Autowired
	private ProductEo productEo;
	Logger logger = LoggerFactory.getLogger(ProductBoImpl.class);

	public List<ProductDto> getProductDto() {
		logger.info("retrieving product details");
		return productEo.getProductDto();
	}
	@Override
	public ProductDto saveProductDto(ProductDto productDto) {
		logger.info("saving the product details");
		return productEo.saveProductDto(productDto);
	}
	@Override
	public HealthCheck healthcheck() throws TableNotFoundException{
		HealthCheck healthcheck= new HealthCheck();
		healthcheck.setHealthComment("Check table is available in db");
		try {
			if(getProductDto()!=null)
			{
				healthcheck.setHealthStatus("Success");
				healthcheck.setHealthDescription("Table is available");
			}
		}
		catch(BadSqlGrammarException ex)
		{
		healthcheck.setHealthStatus("Failure");
		healthcheck.setHealthDescription("Table is not available");
		}
		return healthcheck;
	}
}
