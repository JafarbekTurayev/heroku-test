package uz.pdp.rolepermissionexample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.rolepermissionexample.entity.enums.Permission;
import uz.pdp.rolepermissionexample.entity.enums.RoleName;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    //huquqlar ro'yxati
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<Permission> permissions;

    public Role(String name, Set<Permission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }
}
