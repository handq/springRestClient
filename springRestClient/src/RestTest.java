import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


public class RestTest {
	
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
//get方式***********************************************************************************************************

//		//参数直接放在URL中
//		String message = restTemplate.getForObject("http://handq.com:8080/rs/users", String.class );
//		System.out.println(message);
//		
//		
//		//参数使用MAP传递
//		Map<String ,Object> urlVariables = new HashMap<String ,Object>();
//		urlVariables.put("name", "zhaoshijie");
//		urlVariables.put("id", 80);
//		String message2 = restTemplate.getForObject("http://localhost:8080/yongbarservice/appstore/appgoods/restTemplate", String.class, urlVariables);
		
		
//delete方式***********************************************************************************************************		
		
		//delete方法（注意：delete方法没有返回值，说明，id=0这个参数在服务器端可以不定义该参数，直接使用request获取）
//		restTemplate.delete("http://handq.com:8080/rs/users/1000");

//post方法***************************************************************************************************************
		//1、以实体的方式传参
//		User u = new User();
//		u.setAge(100);
//		u.setId("122");
//		u.setName("hand");
//		String s = putWorkLogOperation(u); 
//		System.out.println("返回值："+s);
		//2、直接使用URL传递参数
//		restTemplate.postForObject("http://handq.com:8080/rest/users/saveUser?name=zhaoshijie&id=80&age=123",null, String.class );
//put方法***************************************************************************************************************
		//1、以实体的方式传参		
//		User u = new User();
//		u.setAge(100);
//		u.setId("122");
//		u.setName("hand");
//		String s = putWorkLogOperation(u); 
//		System.out.println("返回值："+s);
		//2、直接使用URL传递参数
		restTemplate.put("http://handq.com:8080/rest/users/updateUser?name=zhaoshijie&age=23&id=80" ,null);
		System.out.println("complete");
//delete***************************************************************************************************************
//		restTemplate.delete("http://handq.com:8080/rs/users/1000");
		
	}
	
	//post或者put  以实体的方式传参
    public static String putWorkLogOperation(final User workLogOperation){
    	RestTemplate restTemplate = new RestTemplate();
    	//需要这个转换器
    	restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        User wlo = null;
        HttpEntity<?> requestEntity = new HttpEntity<Object>(workLogOperation, requestHeaders);
        try {
            ResponseEntity<User> responseEntity = restTemplate.exchange("http://10.144.33.194:8080/rest/users/putUser", HttpMethod.PUT, requestEntity, User.class);
            System.out.println(responseEntity.getStatusCode());      
            if(responseEntity.getStatusCode().value() != 200) {
                throw new Exception("response code: " + responseEntity.getStatusCode().value()); 
            }
            wlo = responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }     
        return wlo.getName();
    }
}
