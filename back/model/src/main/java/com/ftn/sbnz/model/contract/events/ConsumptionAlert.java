package com.ftn.sbnz.model.contract.events;

import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.user.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Role(Role.Type.EVENT)
public class ConsumptionAlert {
    private String message;
    private Contract contract;
}
