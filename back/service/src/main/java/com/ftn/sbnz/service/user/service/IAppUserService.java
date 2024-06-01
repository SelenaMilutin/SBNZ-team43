package com.ftn.sbnz.service.user.service;

import com.ftn.sbnz.service.user.dto.UserProfileDTO;

public interface IAppUserService {
    UserProfileDTO getUserProfile(String username);
}
