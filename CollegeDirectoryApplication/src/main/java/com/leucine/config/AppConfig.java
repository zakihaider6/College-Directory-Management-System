package com.leucine.config;




import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
@Configuration
public class AppConfig {
@Bean
public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {
	http.cors(cors -> {
		cors.configurationSource(new CorsConfigurationSource() {
		@Override
		public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
		CorsConfiguration cfg = new CorsConfiguration();
		cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
		cfg.setAllowedMethods(Collections.singletonList("*"));
		cfg.setAllowCredentials(true);
		cfg.setAllowedHeaders(Collections.singletonList("*"));
		cfg.setExposedHeaders(Arrays.asList("Authorization"));
		return cfg;
		}
		});
		}).authorizeHttpRequests(auth ->{
			auth
			.requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
			
//			.requestMatchers(HttpMethod.POST,"/admin/**").permitAll()

			
			.requestMatchers(HttpMethod.PUT,"/student/**").hasRole("STUDENT")
			.requestMatchers(HttpMethod.GET,"/student/**").hasRole("STUDENT")
			.requestMatchers(HttpMethod.DELETE,"/student/**").hasRole("STUDENT")
			.requestMatchers(HttpMethod.POST,"/student/**").hasRole("STUDENT")
			.requestMatchers(HttpMethod.PATCH,"/student/**").hasRole("STUDENT")
			
			.requestMatchers(HttpMethod.PUT,"/admin/**").hasRole("ADMINISTRATOR")
			.requestMatchers(HttpMethod.GET,"/admin/**").hasRole("ADMINISTRATOR")
			.requestMatchers(HttpMethod.DELETE,"/admin/**").hasRole("ADMINISTRATOR")
			.requestMatchers(HttpMethod.POST,"/admin/**").hasRole("ADMINISTRATOR")
			.requestMatchers(HttpMethod.PATCH,"/admin/**").hasRole("ADMINISTRATOR")
			
			.requestMatchers(HttpMethod.PUT,"/faculty/**").hasRole("FACULTY_MEMBER")
			.requestMatchers(HttpMethod.GET,"/faculty/**").hasRole("FACULTY_MEMBER")
			.requestMatchers(HttpMethod.DELETE,"/faculty/**").hasRole("FACULTY_MEMBER")
			.requestMatchers(HttpMethod.POST,"/faculty/**").hasRole("FACULTY_MEMBER")
			.requestMatchers(HttpMethod.PATCH,"/faculty/**").hasRole("FACULTY_MEMBER")
	
			.anyRequest().authenticated();
		})
		.csrf(csrf -> csrf.disable())
		.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults());
		return http.build();
}
@Bean
public PasswordEncoder passwordEncoder() {
	
		return new BCryptPasswordEncoder();
	}


}
