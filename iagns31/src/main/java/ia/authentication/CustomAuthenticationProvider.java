package ia.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication arg0) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public Authentication authenticate(Authentication arg0) throws AuthenticationException {
//		String username = (String)arg0.getPrincipal();
//		String credentials = (String)arg0.getCredentials();
//		
//		//TODO
//		ObjectManager<Korisnici> objects = new ObjectManager<Korisnici>(FirstController.class.getClassLoader().getResource("Schemas/Korisnici.xsd"));
//		ArrayList<Korisnici> korisnici = objects.executeQuery(QueryGenerator.getFilesInColl(DatabaseConnection.USERS_COL_ID));
//		ArrayList<TipKorisnik> tks = (ArrayList<TipKorisnik>)korisnici.get(0).getListaKorisnika();
//		TipKorisnik korisnik = new TipKorisnik();
//		
//		for(TipKorisnik tk: tks)
//			if(tk.getKorisnickoIme().equals(username))
//				korisnik = tk;
//		
//		validateUser(korisnik, credentials);
//		List<GrantedAuthority> authorities = loadAuthorities(korisnik);
//
//		return new UsernamePasswordAuthenticationToken(username, credentials, authorities);
//	}
//
//	@Override
//	public boolean supports(Class<?> arg0) {
//		return arg0.equals(UsernamePasswordAuthenticationToken.class);
//	}
//
//	
//	private boolean validatePassword(String credentials, TipKorisnik korisnik) {
//		boolean retVal = false;
//		//TODO
//		if (StringUtils.isNotBlank(korisnik.getLozinka()))
//			retVal = (PasswordEncoder.getEncodedPassword(credentials, korisnik.getKorisnickoIme())).equals(korisnik.getLozinka());
//		return retVal;
//	}
//	
//	
//	private void validateUser(TipKorisnik korisnik, String credentials) {
//		if (korisnik == null)
//			throw new UsernameNotFoundException("Nevalidno korisnicko ime!");
//		if (!validatePassword(credentials, korisnik))
//			throw new BadCredentialsException("Neuspesno logovanje!");
//	}
//	
//	
//	private List<GrantedAuthority> loadAuthorities(TipKorisnik korisnik) {
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		//TODO
//		/** za svakog korisnika treba vratiti njegovu ulogu i dodati u listu */
//		authorities.add(new SimpleGrantedAuthority(korisnik.getUloga()));
//		return authorities;
//	}
	
}
