package uz.pdp.rolepermissionexample.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.rolepermissionexample.entity.Role;
import uz.pdp.rolepermissionexample.entity.User;
import uz.pdp.rolepermissionexample.entity.enums.Permission;
import uz.pdp.rolepermissionexample.entity.enums.RoleName;
import uz.pdp.rolepermissionexample.repository.RoleRepository;
import uz.pdp.rolepermissionexample.repository.UserRepository;
import uz.pdp.rolepermissionexample.utils.AppConstants;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {

        if (initMode.equals("always")) {

            Permission[] values = Permission.values();

            //admin roli bazaga yozildi
            Role admin = roleRepository.save(new Role(
                    AppConstants.ADMIN,
                    new HashSet<>(Arrays.asList(values))
            ));
            Role user = roleRepository.save(new Role(
                    AppConstants.USER,
                    new HashSet<>(Arrays.asList(Permission.READ_CATEGORY,
                            Permission.READ_ONE_CATEGORY))
            ));

            userRepository.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin"),
                    admin,
                    true
            ));
            userRepository.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode("user"),
                    user,
                    true
            ));
        }
    }
}
