package com.zeraki.zerakibackend;

import com.zeraki.zerakibackend.app.dto.AccountType;
import com.zeraki.zerakibackend.app.role.RoleService;
import com.zeraki.zerakibackend.app.role.UserRole;
import com.zeraki.zerakibackend.app.security.AuthService;
import com.zeraki.zerakibackend.app.user.AppUser;
import com.zeraki.zerakibackend.app.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class ZerakiBackendApplication implements ApplicationRunner {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthService authService;

	public static void main(String[] args) {
		SpringApplication.run(ZerakiBackendApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	@Bean
	public BCryptPasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (!userService.existByAuthUsername("admin@zeraki.com")) {
			AppUser user = new AppUser();
			user.setAccountType(AccountType.ADMIN);
			user.setAuthUsername("admin@zeraki.com");
			user.setEmail("admin@zeraki.com");
			user.setFirstname("admin");
			user.setLastname("super");

			user.setPassword(authService.hashPassword("!zeraki@2022"));

			user = userService.save(user);

			UserRole role = new UserRole();

			role.setUserId(user.getId());
			role.setRole("ROLE_ADMIN");
			roleService.addUserRole(role);

		}

	}

}
