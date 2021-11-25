package uz.pdp.rolepermissionexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.rolepermissionexample.entity.Category;
import uz.pdp.rolepermissionexample.entity.Role;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByNameContainingIgnoreCase(String name);
}
