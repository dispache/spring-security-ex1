package dev.dispache.springsecurityex1.services;

import dev.dispache.springsecurityex1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {
    User user1 = new User()
            .email("e.digtyarenko3@gmail.com")
            .password("qwerty")
            .age(23)
            .role("admin")
            .build();
    User user2 = new User()
            .email("johnjohnson@gmail.com")
            .password("johnsonpass")
            .build();;
    User user3 = new User()
            .email("marcogray@gmail.com")
            .password("graypass")
            .age(32)
            .build();;
    List<User> users = List.of(user1, user2, user3);

    public UserDetails loadUserByUsername(String email) {
        User user = users.stream().filter(item -> item.getEmail().equals(email)).findFirst().orElse(null);
        if ( user == null ) {
            throw new UsernameNotFoundException("User not found");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}

