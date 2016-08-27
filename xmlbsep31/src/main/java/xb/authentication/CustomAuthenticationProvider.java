package xb.authentication;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import xb.controller.api.SpisakAkataController;
import xb.database.DatabaseConnection;
import xb.manager.ObjectManager;
import xb.model.Korisnici;
import xb.model.TipKorisnik;
import xb.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication arg0) throws AuthenticationException {
		String username = (String)arg0.getPrincipal();
		String credentials = (String)arg0.getCredentials();

		//TODO
		ObjectManager<Korisnici> objects = new ObjectManager<Korisnici>(CustomAuthenticationProvider.class.getClassLoader().getResource("Schemas/Korisnici.xsd"));
		Korisnici ks = (Korisnici)objects.readFromDB(DatabaseConnection.USERS_DOC_ID);
		ks.getListaKorisnika().size();
		ArrayList<TipKorisnik> korisnici = (ArrayList<TipKorisnik>)ks.getListaKorisnika();
		TipKorisnik korisnik = new TipKorisnik();
		
		for(TipKorisnik tk: korisnici)
			if(tk.getKorisnickoIme().equals(username))
				korisnik = tk;
		
		validateUser(korisnik, credentials);
		List<GrantedAuthority> authorities = loadAuthorities(korisnik);

		return new UsernamePasswordAuthenticationToken(username, credentials, authorities);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return arg0.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	/**private boolean validatePassword(String credentials, TipKorisnik korisnik) {
		boolean retVal = false;
		//TODO
		//ovde treba unhash-ovati lozinku da bi se izvrsila provera!
		if (StringUtils.isNotBlank(korisnik.getLozinka()))
			retVal = (PasswordEncoder.getEncodedPassword(credentials, korisnik.getKorisnickoIme())).equals(korisnik.getLozinka());
		return retVal;
	}*/
	
	
	private boolean validatePassword(String credentials, TipKorisnik korisnik) {
		boolean retVal = false;
		//TODO
		if (StringUtils.isNotBlank(korisnik.getLozinka()))
			retVal = (PasswordEncoder.getEncodedPassword(credentials, korisnik.getKorisnickoIme())).equals(korisnik.getLozinka());
		return retVal;
	}
	
	
	private void validateUser(TipKorisnik korisnik, String credentials) {
		if (korisnik == null)
			throw new UsernameNotFoundException("Nevalidno korisnicko ime!");
		if (!validatePassword(credentials, korisnik))
			throw new BadCredentialsException("Neuspesno logovanje!");
	}
	
	
	private List<GrantedAuthority> loadAuthorities(TipKorisnik korisnik) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		//TODO
		/** za svakog korisnika treba vratiti njegovu ulogu i dodati u listu */
		authorities.add(new SimpleGrantedAuthority(korisnik.getUloga()));
		return authorities;
	}
	
}
