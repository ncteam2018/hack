package com.netcracker.hack.auth;

import com.netcracker.hack.model.Role;
import com.netcracker.hack.model.UserAuthData;
import com.netcracker.hack.repository.UserAuthRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserAuthenticationService implements UserDetailsService {

  private UserAuthRepository userAuthRepository;

  public UserAuthenticationService(UserAuthRepository userAuthRepository) {
    this.userAuthRepository = userAuthRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserAuthData userAuth = userAuthRepository.findByLogin(username);

    if (userAuth != null) {
      List<GrantedAuthority> authorities = new ArrayList<>();

      for (Role role : userAuth.getRoles()) {
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()));
      }

      return new User(
          userAuth.getLogin(),
          userAuth.getPassword(),
          authorities);
    }

    throw new UsernameNotFoundException(" User " + username + " not found!");
  }

}
