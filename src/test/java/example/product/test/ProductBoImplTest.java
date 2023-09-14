package example.product.test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.example.Product.eo.ProductEo;
import com.example.Product.model.HealthCheck;
import com.example.Product.model.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
@AutoConfigureMockMvc
@SpringBootTest
public class ProductBoImplTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ProductEo productEo;

	ObjectMapper obj = new ObjectMapper();

	 @Test
	  void saveProductDtoTest() throws Exception{
          ProductDto productDto=new ProductDto(1,"Ajanta");
          String json=obj.writeValueAsString(productDto);
          when(productEo.saveProductDto(any())).thenReturn(productDto);
          mockMvc.perform(post("/productdetails/post").content(json)
      		    .contentType(MediaType.APPLICATION_JSON_VALUE)
                  .accept(MediaType.APPLICATION_JSON))
                  .andDo(print())
                  .andExpect(status().isOk());
	    }
	    @Test
	     void getProductDtoTest() throws Exception{
	         List<ProductDto> listProductDto=new ArrayList<>();
	         ProductDto productDto=new ProductDto();
	         productDto.setProductId(1);
	         productDto.setProductName("Ajanta");
	         ProductDto productDto1=new ProductDto();
	         productDto1.setProductId(2);
	         productDto1.setProductName("Vani");
	         listProductDto.add(productDto);
	         listProductDto.add(productDto1);
	         when( productEo.getProductDto()).thenReturn(listProductDto);
	          mockMvc.perform(get("/productdetails/get"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$", hasSize(2)));
	    }
	   @Test
	       public void healthCheck()  throws Exception{
	    	   HealthCheck healthCheck=new HealthCheck("Check table is available in db","Success","Table is available");
	    	   String json=obj.writeValueAsString(healthCheck);
	    	   when(productEo.healthcheck()).thenReturn(healthCheck);
	    	   mockMvc.perform(get("/productdetails/healthcheck").content(json)
	       		    .contentType(MediaType.APPLICATION_JSON_VALUE)
	                   .accept(MediaType.APPLICATION_JSON))
	                   .andDo(print())
	                   .andExpect(status().isOk());
	       }
}
