package com.miweb.seguridad.servicios;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.miweb.jpa.servicios.ServiciosJpa;
import com.miweb.modelos.UsuarioJWT;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ServiciosJpa serviciosJpa;

	private static final Log logger = LogFactory.getLog(UserDetailsServiceImpl.class);

	/**
	 * Transforma el usuario en user
	 * 
	 * @param pUsername String
	 * @return User
	 * @exception UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Empieza la comprobacion de del usuario y creacion de token " + username);
//		UsuarioJWT u = new UsuarioJWT(1, "admin", "admin", "ADMIN");
		UsuarioJWT u;
		try {
			u = serviciosJpa.consultarUsuarioJWTPorNombre(username);

			if (u.getNombre().equals(username)) 
				return this.userBuilder(u.getNombre(), new BCryptPasswordEncoder().encode(u.getPwd()), u.getRol());
			else 
				throw new UsernameNotFoundException("Usuario no encontrado");
			
		} catch (Exception e) {
			logger.error("La consulta de comprobacion ha fallado.");
			return null;
		}
	}

	/**
	 * Crea el objeto User
	 * 
	 * @param pUsername String
	 * @param pPwd      String
	 * @param pRoles    Array String
	 * 
	 * @return User
	 */
	private User userBuilder(String pUsername, String pPwd, String... pRoles) {
		logger.info("Creacion de UserDetails");
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		List<GrantedAuthority> autorias = new ArrayList<>();
		for (String rol : pRoles) {
			autorias.add(new SimpleGrantedAuthority("ROLE_" + rol));
		}

		return new User(pUsername, pPwd, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, autorias);
	}

}
