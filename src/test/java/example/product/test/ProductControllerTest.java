package example.product.test;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import com.example.Product.model.HealthCheck;
import com.example.Product.model.ProductVo;
import com.example.Product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)

@SpringBootTest
class ProductControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ProductService productService;
	ObjectMapper obj = new ObjectMapper();
    
       @Test
        void saveProductVo() throws Exception{
        	ProductVo productVos=new ProductVo(1,"Ajanta");
            String json = obj.writeValueAsString(productVos);
            when(productService.saveProductVo(any())).thenReturn(productVos);
            mockMvc.perform(post("/productdetails/post").content(json)
            		    .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk());
        }
       @Test
        void getProductVo()  throws Exception{
    	   List<ProductVo> listProductVo=new ArrayList<>();
	         ProductVo productVo=new ProductVo();
	         productVo.setProductId(1);
	         productVo.setProductName("Ajanta");
	         
	         ProductVo productVo1=new ProductVo();
	         productVo1.setProductId(2);
	         productVo1.setProductName("Vani");
	         listProductVo.add(productVo);
	         listProductVo.add(productVo1);
	         when(productService.getProductVo()).thenReturn(listProductVo);
	         mockMvc.perform(get("/productdetails/get"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$", hasSize(2)));    
      }
       @Test
       void healthCheck()  throws Exception{
    	   HealthCheck healthCheck=new HealthCheck("Check table is available in db","Success","Table is available");
    	   String json=obj.writeValueAsString(healthCheck);
    	   when( productService.healthcheck()).thenReturn(healthCheck);
    	   mockMvc.perform(get("/productdetails/healthcheck").content(json)
       		    .contentType(MediaType.APPLICATION_JSON_VALUE)
                   .accept(MediaType.APPLICATION_JSON))
                   .andDo(print())
                   .andExpect(status().isOk());
       }
	}
