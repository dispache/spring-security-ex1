package dev.dispache.springsecurityex1.controller;

import dev.dispache.springsecurityex1.dto.LoginUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping()
    public String auth() {
        return "Auth page :)";
    }

    @PostMapping("login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginUserDto loginUser) {
        Map<String, Object> response = new HashMap<>();
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword())
        );
        Map<String, Object> user = new HashMap<>();
        User principal = (User) auth.getPrincipal();
        String role = ((GrantedAuthority) principal.getAuthorities().toArray()[0]).getAuthority();
        user.put("email", principal.getUsername());
        user.put("role", role);
        response.put("user", user);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ExceptionHandler()
    public String handleException(Exception exception) {
        System.out.println(exception);
        return exception.getMessage();
    }
}
