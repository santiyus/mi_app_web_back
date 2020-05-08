package com.miweb.seguridad.servicios;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.miweb.constantes.ConstSecurity;
import com.miweb.modelos.UsuarioJWT;
import com.miweb.modelos.rest.InUsuarioAutenticado;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class ServiciosJWT {

	private static final Log logger = LogFactory.getLog(ServiciosJWT.class);
	
	@Autowired
	private ConstSecurity constSecurity;
	
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Value("${jwt.secret}")
    private String secret;
    
    public String autenticarse (InUsuarioAutenticado pUser) throws Exception {
    	logger.info("Comprobar si el usuario es correcto");
    	try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(pUser.getUser(), pUser.getPass())
            );
        } catch (Exception ex) {
        	logger.error("El usuario es incorrecto");
            throw new Exception("inavalid username/password");
        }
        return constSecurity.PREX_TOKEN + generateToken(pUser.getUser());
    }

	/**
	 * Mediante un token extraemos un usuarioJTW
	 * 
	 * @param pToken String
	 * 
	 * @return UsuarioJTW
	 */
	public UsuarioJWT extraerUsuarioDelToken(String pToken) {
		/**
		 * 1- Descodificar el token
		 * 2- Comprobar su validez
		 * 3- Comprobar la firma
		 * 
		 * */
		return new UsuarioJWT();
	}

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
    	logger.info("Creando Token");

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
    	logger.info("Validando token");
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
	
	
	

}
