package com.netcracker.hack.auth;

import com.netcracker.hack.model.Profile;
import com.netcracker.hack.model.Role;
import com.netcracker.hack.repository.ProfileRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserAuthenticationService implements UserDetailsService {

    private ProfileRepository profileRepository;


    public UserAuthenticationService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Profile profile = profileRepository.findByLogin(username);

        if (profile != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();

            for (Role role : profile.getRoles())
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()));

            return new User(
                    profile.getLogin(),
                    profile.getPassword(),
                    authorities);
        }

        throw new UsernameNotFoundException(" User " + username + " not found!");
    }

}
