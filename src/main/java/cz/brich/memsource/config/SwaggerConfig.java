package cz.brich.memsource.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        Contact contact = new Contact();
        contact = contact.name("Milan Brich");

        return new OpenAPI()
                .info(new Info()
                        .title("Memsource Service REST API")
                        .contact(contact)
                        .version("1.0.0"))
                .externalDocs(new ExternalDocumentation()
                        .url("TODO"));
    }
}
