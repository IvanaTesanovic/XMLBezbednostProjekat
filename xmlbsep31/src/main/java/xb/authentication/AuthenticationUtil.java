package xb.authentication;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthenticationUtil {

	/**
	 * Checks if user is logged in.
	 * 
	 * @param authentication
	 * @return <code>TRUE</code> if user is logged in. <code>FALSE</code> otherwise.
	 */
	public static boolean isAuthenticated (Authentication authentication){
		return !(authentication instanceof AnonymousAuthenticationToken);
	}

	/**
	 * Private constructor.
	 */
	private AuthenticationUtil() {
		// Prevents initialization.
	}
	
}
