package uz.pdp.rolepermissionexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.rolepermissionexample.payload.ApiResponse;
import uz.pdp.rolepermissionexample.repository.RoleRepository;
import uz.pdp.rolepermissionexample.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

//    public ApiResponse registerUser(RegisterDto registerDto) {
//        if (userRepository.existsByUsername(registerDto.getUsername()))
//            return new ApiResponse("Username already exists!",false);
//
//        if (!registerDto.getPassword().equals(registerDto.getPrePassword()))
//            return new ApiResponse("Passwors are not compatible!",false);
//        User user = new User(
//                registerDto.getFullName(),
//                registerDto.getUsername(),
//                passwordEncoder.encode(registerDto.getPassword()),
//                roleRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("Role not found!","name",AppConstants.USER)),
//                true
//                );
//        userRepository.save(user);
//        return new ApiResponse("User saved!", true);
//    }

    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(()-> new UsernameNotFoundException(username));
    }
}
