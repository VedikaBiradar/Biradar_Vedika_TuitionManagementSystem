package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy;

import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity.Role;
import com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testFindByName() {
        // creating new role
        Role role = new Role();
        role.setName("ROLE_USER");
        roleRepository.save(role);

        //find the role by using findbyname
        Role found = roleRepository.findByName("ROLE_USER");

        //checking role correctly there or not
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("ROLE_USER");
    }

    @Test
    public void testSaveRole() {
        // given role
        String roleName = "ROLE_ADMIN";

        // Check if the role already exists
        Role existingRole = roleRepository.findByName(roleName);
        if (existingRole == null) {
            Role role1 = new Role();
            role1.setName(roleName);

            // getting saved role
            Role savedRole = roleRepository.save(role1);

            // checking role already present or not
            Optional<Role> found = roleRepository.findById(savedRole.getId());
            assertThat(found).isPresent();
            assertThat(found.get().getName()).isEqualTo(roleName);
        } else {
            // Handle the case where the role already exists
            System.out.println("Role with name " + roleName + " already exists.");

        }
    }

    @Test
    public void testRoleNotFound() {
        // role found or not
        Role found = roleRepository.findByName("ROLE_NON_EXISTENT");

        // if not found then successfully null and true
        assertThat(found).isNull();
    }
}
