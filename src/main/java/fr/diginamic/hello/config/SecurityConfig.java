package fr.diginamic.hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import fr.diginamic.hello.dto.UserMapper;
import fr.diginamic.hello.repository.UserRepository;



@Configuration
@EnableMethodSecurity
public class SecurityConfig  {

	@Bean
	PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	UserDetailsService userDetailsService(UserRepository userRepository) {
		return username -> UserMapper.toUserDetails(userRepository.findByUserName(username));
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecu) throws Exception {
		httpSecu.authorizeHttpRequests(request -> request
				.requestMatchers("/","login").permitAll()
				.requestMatchers("/logout").authenticated()
				.requestMatchers("/townList").authenticated()
				.requestMatchers("/deleteTown/**").hasRole("ADMIN")
				.anyRequest().denyAll()
				)
		.httpBasic(Customizer.withDefaults()).formLogin(Customizer.withDefaults());
		return httpSecu.build();
	}
}
