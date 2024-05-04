package com.springbootapi.springbootapi.init;

import com.springbootapi.springbootapi.model.Role;
import com.springbootapi.springbootapi.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Component
@RequiredArgsConstructor
public class ApplicationStartRunner implements CommandLineRunner {
    private final RoleRepository roleRepository;
    @Override
    public void run(String ...args) throws Exception {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
        if (roleUser == null)
        {
            roleUser = new Role(1L, "123", "ROLE_USER");
            roleRepository.save(roleUser);
        }
        if (roleAdmin == null)
        {
            roleAdmin = new Role(2L, "456", "ROLE_ADMIN");
            roleRepository.save(roleAdmin);
        }
    }
}
