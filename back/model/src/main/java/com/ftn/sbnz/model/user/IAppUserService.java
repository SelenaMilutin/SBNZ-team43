package com.ftn.sbnz.model.user;

public interface IAppUserService {
    UserProfileDTO getUserProfile(String username);

    void createDiscount(int numberOfPreviousContracts, Client client);

}
