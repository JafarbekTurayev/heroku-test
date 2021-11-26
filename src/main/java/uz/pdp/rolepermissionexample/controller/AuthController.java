package uz.pdp.rolepermissionexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uz.pdp.rolepermissionexample.entity.User;
import uz.pdp.rolepermissionexample.payload.LoginDTO;
import uz.pdp.rolepermissionexample.repository.UserRepository;
import uz.pdp.rolepermissionexample.security.CurrentUser;
import uz.pdp.rolepermissionexample.security.JwtProvider;
import uz.pdp.rolepermissionexample.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthService authService;
    @Autowired
    AuthenticationManager authenticationManager;


//    @PostMapping("/register")

    @GetMapping("/test")
    public HttpEntity<?> getTest() {
        return ResponseEntity.ok("Test");
    }

    @GetMapping("/me")
    public HttpEntity<?> getMe(@CurrentUser User user) {
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword()));

        User user = (User) authenticate.getPrincipal();

        String token = jwtProvider.generateToken(user.getUsername(), user.getRole());
        return ResponseEntity.ok(token);
    }
}
