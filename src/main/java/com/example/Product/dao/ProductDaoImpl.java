package com.example.Product.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.Product.model.ProductDto;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	Logger logger = LoggerFactory.getLogger(ProductDao.class);
	@Override
	public ProductDto saveProductDto(ProductDto productDto) {
		String query = "insert into productvo values('"+productDto.getProductId()+"','"+productDto.getProductName()+"')";
		jdbcTemplate.update(query);
		logger.info("Creating");
		return productDto;
	}




	@Override
	public List<ProductDto> getProductDto() {
		String sql = "select*from productvo";
		logger.info("Find all the data");
        List<ProductDto> productDto = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<ProductDto>(ProductDto.class));
        return productDto;
	}


}
