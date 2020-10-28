package lightning.webds.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lightning.webds.handler.AuthSuccessHandler;
import lightning.webds.handler.DriverSocketHandler;
import lightning.webds.handler.WaitingSocketHandler;
import lightning.webds.service.UserService;
import lightning.webds.UserInfo;
import java.util.List;

import javax.swing.JApplet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class HttpSecurityConfig extends WebSecurityConfigurerAdapter {
	public static final String user_role = "USER";
	public static final String admin_role = "ADMIN";

	private AuthSuccessHandler getSuccessHandler(){
		return new AuthSuccessHandler();
	}
	private UserInfo getUserDetailsService(){
		return new UserInfo();
	}

	// password is not required as login is handled by google
	private PasswordEncoder getPasswordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
	
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/admin/*").access("hasRole('" + admin_role + "')")
				.antMatchers("/").access("hasRole('" + admin_role + "') or hasRole('" + user_role + "')")
				.antMatchers("/user/*").access("hasRole('" + admin_role + "') or hasRole('" + user_role + "')")
				.and()
			.formLogin()
				.loginPage("/login")
				.successHandler(getSuccessHandler())
				.permitAll()
				.and()
			.logout()
				.permitAll()
			.and().csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(getUserDetailsService()).passwordEncoder(getPasswordEncoder());
	}
}