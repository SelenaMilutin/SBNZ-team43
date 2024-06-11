package com.ftn.sbnz.model.servicearea;

import com.ftn.sbnz.model.user.Client;

import java.util.List;

public interface IServiceAreaService {

    void assignServiceAreaToUser(Client appUser);

    void notifyUsersAboutUnavailableServiceArea(ServiceArea serviceArea);

    void notifyClientAboutServiceAreaChange(Client client);

    ServiceAreaDTO setServiceAreaAvailability(Long serviceAreaId, boolean availability);

    void notifyAdminAboutUnavailableServiceAreas();

    void toggleActiveFlagForBackupServiceAreas(boolean active);

    List<ServiceAreaDTO> getAllServiceAreas();

    void moveClientsToAvailableServiceAreasWithCapacityBelow90Percent(ServiceArea serviceArea);

}
