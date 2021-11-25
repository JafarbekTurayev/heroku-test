package uz.pdp.rolepermissionexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.rolepermissionexample.entity.Role;
import uz.pdp.rolepermissionexample.entity.User;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
