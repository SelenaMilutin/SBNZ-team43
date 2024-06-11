package com.ftn.sbnz.model.packages.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Position;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransitionPackage {
    @Position(0)
    private String name;

    @Position(1)
    private String parentName;

    @Position(2)
    private String packageType;
}
