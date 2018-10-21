package com.netcracker.hack.web.security;

import java.util.ArrayList;
import java.util.List;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.netcracker.hack.model.People;
import com.netcracker.hack.repository.PeopleRepository;

public class UserAuthenticationService implements UserDetailsService{

	private PeopleRepository userRepository;
	
	
	public UserAuthenticationService(PeopleRepository userRepository){
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<People> users = userRepository.findByName(username);
		
		if(users.size() != 0) {
			List<GrantedAuthority> authorities= new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

			return new User(
					users.get(0).getName(),
					users.get(0).getPassword(),
					authorities);
		}
		
		
		throw new UsernameNotFoundException(" User " + username + " not found!");
	}

}
