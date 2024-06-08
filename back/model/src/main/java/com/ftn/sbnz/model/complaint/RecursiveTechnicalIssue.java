package com.ftn.sbnz.model.complaint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.definition.type.Position;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecursiveTechnicalIssue {

    @Position(0)
    private String issue;

    @Position(1)
    private String issueConsequence;
}
