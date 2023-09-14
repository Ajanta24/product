package com.example.Product.model;

//import javax.xml.bind.annotation.XmlElement;


//@XmlRootElement
public class ProductVo {
	
		//@NotNull
		private int productId;
		//@NotNull
		private String productName;
	private String productCityName;
		public ProductVo() {
			super();
		}
		public ProductVo(int productId, String productName) {
			super();
			this.productId = productId;
			this.productName = productName;
		}
//		@XmlElement
		public int getProductId() {
			return productId;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}
//		@XmlElement
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}

	}


