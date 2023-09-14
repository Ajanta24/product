package com.example.Product.exception;


	public class TableNotFoundException extends RuntimeException {
		public TableNotFoundException (String exceptionmessage) {
			super(exceptionmessage);
		}
	}

