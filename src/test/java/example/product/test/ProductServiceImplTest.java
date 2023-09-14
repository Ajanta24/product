package example.product.test;
import com.example.Product.model.HealthCheck;
import static org.hamcrest.Matchers.hasSize;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.Product.bo.ProductBo;
import com.example.Product.mapper.ProductMapper;
import com.example.Product.model.ProductVo;

import com.fasterxml.jackson.databind.ObjectMapper;
@AutoConfigureMockMvc
@SpringBootTest
class ProductServiceImplTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ProductBo productBo;
	@MockBean
	private ProductMapper productMapper;
	ObjectMapper obj = new ObjectMapper();
    
	 @Test
	  void saveProductVoTest() throws Exception{
          ProductVo productVos=new ProductVo(1,"Ajanta");
          String json=obj.writeValueAsString(productVos);
          when(productMapper.toProductVo(productBo.saveProductDto(productMapper.toProductDto(productVos))))
          .thenReturn(productVos);
          mockMvc.perform(post("/productdetails/post").content(json)
      		    .contentType(MediaType.APPLICATION_JSON_VALUE)
                  .accept(MediaType.APPLICATION_JSON))
                  .andDo(print())
                  .andExpect(status().isOk());
	    }
	    @Test
	    void getProductVoTest() throws Exception{
	         List<ProductVo> listProductVo=new ArrayList<>();
	         ProductVo productVo=new ProductVo();
	         productVo.setProductId(1);
	         productVo.setProductName("Ajanta");
	         ProductVo productVo1=new ProductVo();
	         productVo1.setProductId(2);
	         productVo1.setProductName("Vani");
	         listProductVo.add(productVo);
	         listProductVo.add(productVo1);
	         when(productMapper.toProductVos(productBo.getProductDto()))
	         .thenReturn(listProductVo);
	          mockMvc.perform(get("/productdetails/get"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$", hasSize(2)));
	    }
	    @Test
	        void healthCheck()  throws Exception{
	    	   HealthCheck healthCheck=new HealthCheck("Check table is available in db","Success","Table is available");
	    	   String json=obj.writeValueAsString(healthCheck);
	    	   when(productBo.healthcheck()).thenReturn(healthCheck);
	    	   mockMvc.perform(get("/productdetails/healthcheck").content(json)
	       		    .contentType(MediaType.APPLICATION_JSON_VALUE)
	                   .accept(MediaType.APPLICATION_JSON))
	                   .andDo(print())
	                   .andExpect(status().isOk());
	       }
	}

	