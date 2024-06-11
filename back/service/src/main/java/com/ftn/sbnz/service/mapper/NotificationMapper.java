package com.ftn.sbnz.service.mapper;

import com.ftn.sbnz.model.packages.Packages;
import com.ftn.sbnz.model.servicearea.ServiceArea;
import com.ftn.sbnz.service.dto.NotificationDTO;
import org.springframework.stereotype.Component;

import javax.management.Notification;
import java.time.LocalDateTime;

@Component
public class NotificationMapper {

    public NotificationDTO mapToServiceAreaUnavailableNotification(LocalDateTime timestamp) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setTimestamp(timestamp);
        notificationDTO.setMessage("Service area is currently unavailable");
        return notificationDTO;
    }

    public NotificationDTO mapToMoreThanPercentAreasUnavailableNotification(LocalDateTime timestamp, int percent) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setTimestamp(timestamp);
        notificationDTO.setMessage(String.format("More than %s percent of service areas are currently unavailable", percent));
        return notificationDTO;
    }

    public NotificationDTO mapToServiceAreaChanged(LocalDateTime timestamp, ServiceArea serviceArea) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setTimestamp(timestamp);
        if (serviceArea == null) {
            notificationDTO.setMessage("Your services have been removed from service area");
        } else {
            notificationDTO.setMessage("Your services have been moved to a new service area with id = " + serviceArea.getId().toString());
        }
        return notificationDTO;
    }

    public NotificationDTO mapToNotificationDTO(LocalDateTime timestamp, String message) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setTimestamp(timestamp);
        notificationDTO.setMessage(message);
        return notificationDTO;

    }

    public NotificationDTO mapToRecomendPackageNotification(Packages packages, LocalDateTime now, Packages oldPackage) {
        return new NotificationDTO(now, "We heard your complaint for " + oldPackage.getName() + ". We recommend the package " + packages.getName());
    }
}
