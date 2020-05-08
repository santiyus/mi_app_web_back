package com.miweb.seguridad.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.miweb.seguridad.filtros.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity // habilita la seguridad web
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTAuthorizationFilter filterJWT;

	/**
	 * Se comunica a spring y le dice que va a usar nuestro popio usuario -->
	 * userDetailsService
	 * 
	 * @param auth de objeto AuthenticationManagerBuilder
	 */
	@Autowired
	public void cofigureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	/**
	 * Se configura de forma global los http para utilizar con anotaciones en vez de
	 * poner aqui todas las urls
	 * 
	 * @param http de objeto HttpSecurity
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http
        	//deshabilitar, sirve para meter un token en formularios y no pueda falsificarse
        	.csrf().disable() 
        	
        	// perimitir a la url "/autenticarse" sin autenticarse
        	.authorizeRequests().antMatchers("/autenticarse").permitAll() 
        	
        	//.anyRequest().authenticated() // las demas urls tienen que autenticarse
        	
        	//Agrega manejo de excepciones para excepciones relacionadas con Spring Security
        	.and().exceptionHandling()
        	
        	//funcionalidad relacionada con la sesión HTTP manejada mediante(SessionManagementFiltery Y
        	// SessionAuthenticationStrategyinterfaz), a la que delega el filtro.
        	.and().sessionManagement() 
        	
        	//se le dice a spring que queremos funcionar sin sessiones porque es un API REST
        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
        
        // Las peticiones pasarán por este filtro para validar el token
        http.addFilterBefore(filterJWT, UsernamePasswordAuthenticationFilter.class); 
	}

	/**
	 * Encripta las contraseñas con el BCryptPasswordEncoder
	 * 
	 * @return objeto de encriptado
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Devolverá la clase encargada de manejar las autentificaciones. Solo llama a la función padre. 
	 * Le ponemos la etiqueta @Bean, para que Spring sepa de donde sacar (inyectar) un objeto 
	 * tipo AuthenticationManager pues lo necesita para controlar la seguridad.
	 * 
	 * @return AuthenticationManager 
	 */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
