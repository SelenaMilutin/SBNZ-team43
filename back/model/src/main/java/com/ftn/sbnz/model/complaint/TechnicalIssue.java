package com.ftn.sbnz.model.complaint;

import lombok.*;

import javax.persistence.*;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import static javax.persistence.InheritanceType.JOINED;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy=JOINED)
@Table(name = "technical_issue")
@Role(Role.Type.EVENT)
@Timestamp("submitTime")
@Expires("48h00m")
public class TechnicalIssue extends Complaint implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Date submitTime;

}
