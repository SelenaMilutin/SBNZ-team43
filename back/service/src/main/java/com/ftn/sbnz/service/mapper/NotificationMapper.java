package com.ftn.sbnz.service.mapper;

import com.ftn.sbnz.service.dto.NotificationDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationMapper {

    public NotificationDTO mapToServiceAreaUnavailableNotification(LocalDateTime timestamp) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setTimestamp(timestamp);
        notificationDTO.setMessage("Service are is currently unavailable");
        return notificationDTO;
    }

}
