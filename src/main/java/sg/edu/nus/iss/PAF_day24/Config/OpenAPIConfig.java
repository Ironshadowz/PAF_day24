package sg.edu.nus.iss.PAF_day24.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig 
{
    @Bean
    public OpenAPI openAPI()
    {
        return new OpenAPI().info(new Info()
                            .title("My PAF day 24 lecture")
                            .description("BankAccount API transaction example")
                            .version("version 1.0"));
    }                       
}
