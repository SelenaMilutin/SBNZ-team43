package com.ftn.sbnz.model.servicearea;

import com.ftn.sbnz.model.user.Client;

public interface IServiceAreaService {

    void assignServiceAreaToUser(Client appUser);

    void notifyUsersAboutUnavailableServiceArea(ServiceArea serviceArea);

    void setServiceAreaAvailability(Long serviceAreaId, boolean availability);

}
