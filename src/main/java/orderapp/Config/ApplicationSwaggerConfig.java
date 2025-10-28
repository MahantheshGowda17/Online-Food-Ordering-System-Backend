package orderapp.Config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class ApplicationSwaggerConfig {

	@Bean
	OpenAPI OpenApi() {
		Server localhost=new Server();
		localhost.setUrl("http://localhost:8080");
		localhost.setDescription("Local Environment");
		
		Contact contact=new Contact();
		contact.setEmail("mahantheshgowda17@gmail.com");
		contact.setName("Mahanthesh");
		
		Info info = new Info().title(" Online Food Order")
				.version("1.0").contact(contact)
				.description("This documentation exposes API endpoints"
						+"to manage food application");
		
		return new OpenAPI().info(info).servers(List.of(localhost));
	}
}
