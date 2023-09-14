package com.example.Product.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class ProductDto {

		private int productId;
		private String productName;
		public ProductDto() {
			super();
		}
		public ProductDto(int productId, String productName) {
			super();
			this.productId = productId;
			this.productName = productName;
		}
	@XmlElement
		public int getProductId() {
			return productId;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}
	@XmlElement
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		
		}

