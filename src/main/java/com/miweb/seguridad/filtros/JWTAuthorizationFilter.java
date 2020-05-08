package com.miweb.seguridad.filtros;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.miweb.constantes.ConstSecurity;
import com.miweb.seguridad.servicios.ServiciosJWT;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private static final Log logger = LogFactory.getLog(JWTAuthorizationFilter.class);

	@Autowired
	private ConstSecurity constSecurity;

	@Autowired
	private ServiciosJWT serviciosJWT;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) 
	    		throws ServletException, IOException {
		 
		 	logger.info(">>> ENTRADA AL FILTRO DE AUTENTIFICACION");
	        String authorizationHeader = httpServletRequest.getHeader(constSecurity.HEADER_PARAM_TOKEN);

	        String token = null;
	        String userName = null;

	        if (authorizationHeader != null && authorizationHeader.startsWith(constSecurity.PREX_TOKEN)) {
	            token = authorizationHeader.substring(7);
	            userName = serviciosJWT.extractUsername(token);
	        }

	        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

	            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

	            if (serviciosJWT.validateToken(token, userDetails)) {

	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
	                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                usernamePasswordAuthenticationToken
	                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            }
	        }
	        filterChain.doFilter(httpServletRequest, httpServletResponse);
	    }

}
