package com.ftn.sbnz.service.mapper;

import com.ftn.sbnz.model.user.Client;
import com.ftn.sbnz.model.user.Role;
import com.ftn.sbnz.service.auth.dto.CreateUserDTO;
import com.ftn.sbnz.service.user.dto.UserProfileDTO;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper {

    public Client mapCreateUserDTOToClient(CreateUserDTO dto) {
        Client userEntity = new Client();
        userEntity.setName(dto.getName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setPhone(dto.getPhone());
        userEntity.setAddress(dto.getAddress());
        userEntity.setEmail(dto.getEmail());
        userEntity.setRole(Role.CLIENT);
        userEntity.setActiveFlag(true);
        userEntity.setServiceArea(null);
        userEntity.setPreviousServiceArea(null);
        return userEntity;
    }

    public UserProfileDTO mapUserToUserProfileDTO(Client user) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setName(user.getName());
        userProfileDTO.setLastName(user.getLastName());
        userProfileDTO.setPhone(user.getPhone());
        userProfileDTO.setAddress(user.getAddress());
        userProfileDTO.setEmail(user.getEmail());
        userProfileDTO.setBlockedFlag(user.isBlockedFlag());
        if (user.isServiceAreaSet()) {
            userProfileDTO.setServiceAreaId(user.getServiceArea().getId().toString());
            userProfileDTO.setServiceAreaAvailable(user.getServiceArea().isAvailableFlag());
            userProfileDTO.setServiceAreaCurrentCapacity(user.getServiceArea().getCurrentCapacity());
            userProfileDTO.setServiceAreaMaximumCapacity(user.getServiceArea().getMaximumCapacity());
        }
        return userProfileDTO;
    }

}
