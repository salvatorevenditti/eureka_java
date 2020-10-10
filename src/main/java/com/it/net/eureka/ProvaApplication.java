package com.it.net.eureka;

import com.it.net.eureka.utils.Costants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class ProvaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ProvaApplication.class, args);
	}

	@Bean
	public static Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.it.net.eureka.controller")).build();
	}

	@Bean
	public static LinkDiscoverers discovers() {
		List<LinkDiscoverer> plugins = new ArrayList<>();
		plugins.add(new CollectionJsonLinkDiscoverer());
		return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
	}

	@Bean
	public SimpleMailMessage emailTemplate() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(Costants.STR_TEST);
		message.setFrom(Costants.STR_TEST);
		message.setSubject(Costants.STR_TEST);
		message.setText(Costants.STR_TEST);
		return message;
	}
}
