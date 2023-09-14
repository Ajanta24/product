package example.product.test;

import static org.hamcrest.Matchers.hasSize;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Product.model.ProductDto;

import com.fasterxml.jackson.databind.ObjectMapper;


@AutoConfiguration
@SpringBootTest
class ProductDaoImplTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	JdbcTemplate jdbcTemplate;
	
	ObjectMapper obj = new ObjectMapper();
    
	 @Test
	  void saveProductDto() throws Exception{
           ProductDto productDto=new ProductDto(1,"Ajanta");
           String json=obj.writeValueAsString(productDto);
           when(jdbcTemplate.update("insert into productvo values('"+productDto.getProductId()+"','"+productDto.getProductName()+"')")).thenReturn(1);
           mockMvc.perform(post("/productdetails/post")
        		   .content(json)
        		   .contentType(MediaType.APPLICATION_JSON_VALUE)
                   .accept(MediaType.APPLICATION_JSON))
                   .andDo(print())
                  .andExpect(status().isOk()); 
	    }
	 @Test
    void getProductDto() throws Exception{
		 List<ProductDto> listProductDto =new ArrayList<>();
         ProductDto productDto=new ProductDto();
         productDto.setProductId(1);
         productDto.setProductName("Ajanta");
         ProductDto productDto1=new ProductDto();
         productDto1.setProductId(2);
         productDto1.setProductName("Vani");
         listProductDto.add(productDto);
         listProductDto.add(productDto1);
           String sql = "select*from productvo";
         when(jdbcTemplate.query(sql,
                  new BeanPropertyRowMapper<ProductDto>(ProductDto.class))).thenReturn(listProductDto);
         mockMvc.perform(get("/productdetails/get")
        		  .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk());     
	    }
	}