package megalog;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@OpenAPIDefinition(info = @Info(title = "HRMS SYSTEM", version = "1.0.0", description = "HRMS Information"))
@EnableWebMvc
public class HrmsApplication{
	public static void main(String[] args) {
		SpringApplication.run(HrmsApplication.class, args);
	}

}
