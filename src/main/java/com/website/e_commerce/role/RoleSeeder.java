package com.website.e_commerce.role;

import com.website.e_commerce.user.RoleEnum;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    private void loadRoles(){
        RoleEnum[] roleNames = new RoleEnum[]{RoleEnum.USER,RoleEnum.ARCHITECT};
        Map<RoleEnum, String> roleEnumStringMap = Map.of(
                RoleEnum.USER, "default user",
                RoleEnum.ARCHITECT, "Architect"
        );
        Arrays.stream(roleNames).forEach(roleName -> {
            Optional<RoleEntity> optionalRole = roleRepository.findByName(roleName);
            optionalRole.ifPresentOrElse(System.out::println,() -> {
                RoleEntity roleToCreate = new RoleEntity();
                roleToCreate.setName(roleName);
                        roleRepository.save(roleToCreate);
            });


        });


    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        this.loadRoles();
    }
}
