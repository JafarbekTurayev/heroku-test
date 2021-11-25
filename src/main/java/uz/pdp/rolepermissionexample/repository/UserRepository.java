package uz.pdp.rolepermissionexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.rolepermissionexample.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserName(String username);
}
