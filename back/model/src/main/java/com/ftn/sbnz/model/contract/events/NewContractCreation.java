package com.ftn.sbnz.model.contract.events;

import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Role(Role.Type.EVENT)
public class NewContractCreation {
    private String message;
    private Contract oldContract;
    private LocalDateTime creationTime;
}
