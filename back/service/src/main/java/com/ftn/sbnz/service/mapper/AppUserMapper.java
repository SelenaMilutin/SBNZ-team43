package com.ftn.sbnz.service.mapper;

import com.ftn.sbnz.model.user.AppUser;
import com.ftn.sbnz.model.user.Role;
import com.ftn.sbnz.service.auth.dto.CreateUserDTO;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper {

    public AppUser mapCreateUserDTOToUserEntity(CreateUserDTO dto) {
        AppUser userEntity = new AppUser();
        userEntity.setName(dto.getName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setPhone(dto.getPhone());
        userEntity.setAddress(dto.getAddress());
        userEntity.setEmail(dto.getEmail());
        userEntity.setRole(Role.CLIENT);
        userEntity.setActiveFlag(true);
        return userEntity;
    }

}
