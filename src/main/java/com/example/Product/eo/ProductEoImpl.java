package com.example.Product.eo;


	import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Component;


import com.example.Product.dao.ProductDao;
import com.example.Product.model.ProductDto;
	
	@Component
	
	public class ProductEoImpl implements ProductEo{
		@Autowired
		private ProductDao productDao;
		Logger logger = LoggerFactory.getLogger(ProductEo.class);
		@Override
		public List<ProductDto> getProductDto() {
			logger.info("Retrieving data");
			return productDao.getProductDto();
		}
		@Override
		public ProductDto saveProductDto(ProductDto productDto) {
			logger.info("Save product");
			//return productDao.saveProductDto(productDto);
			

		        logger.info("Save person");

		        //return personDao.savePersonDto(personDto);

		        try {

		            // Create a JAXBContext for PersonDto

		            JAXBContext context = JAXBContext.newInstance(ProductDto.class);

		 

		            // Marshalling (Java object to XML)

		            Marshaller marshaller = context.createMarshaller();

		            StringWriter writer = new StringWriter();

		            marshaller.marshal(productDto, writer);

		            String xml = writer.toString();

		 

		            // Unmarshalling (XML to Java object)

		            Unmarshaller unmarshaller = context.createUnmarshaller();

		            ProductDto unmarshalledPersonDto = (ProductDto) unmarshaller.unmarshal(new StringReader(xml));

		 

		            // Save the unmarshalledPersonDto to the database

		            ProductDto savedPersonDto = productDao.saveProductDto(unmarshalledPersonDto);

		 

		            return savedPersonDto;

		        } catch (JAXBException e) {

		            // Handle JAXBException

		            logger.error("Error while marshalling/unmarshalling PersonDto: " + e.getMessage());

		            return null; // Or throw an exception

		        }

		    
		}
		@Override
		public Object healthcheck() {
			// TODO Auto-generated method stub
			return null;
		}

	}

