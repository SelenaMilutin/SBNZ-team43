package com.ftn.sbnz.model.user;

public interface IAppUserService {

    UserProfileDTO getUserProfile(String username);

    AppUser findById(long l);
}
