package com.example.Product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Product.model.HealthCheck;
import com.example.Product.model.ProductVo;
import com.example.Product.service.ProductService;
import com.example.Product.service.ProductServiceImpl;

@RestController         
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	@GetMapping("/get")
	
	public ResponseEntity<List<ProductVo>> getProductVo(){
	List<ProductVo> productVo=productService.getProductVo();
	//if(productVo==null || productVo.isEmpty())
		//return new ResponseEntity(" No Data found", HttpStatus.NOT_FOUND);
	logger.info("Data of product details is retriving");
	return ResponseEntity.ok(productVo);
} 
	@PostMapping(value="/post",produces="application/json")
	public ResponseEntity<ProductVo> saveProductVo(@Validated @RequestBody ProductVo productVo){
		ProductVo productVos=productService.saveProductVo(productVo);
		if(productVos==null)
			return new ResponseEntity("Insertion Error", HttpStatus.BAD_REQUEST);
		logger.info("Data is saving");
		return ResponseEntity.status(HttpStatus.OK).body(productVos);
	}
	@GetMapping("/healthcheck")
	public ResponseEntity<HealthCheck> healthcheck(){
		HealthCheck healthcheck= productService.healthcheck();
		logger.info("Data is saving in db");
		return ResponseEntity.status(HttpStatus.OK).body(healthcheck);
	}	
}
