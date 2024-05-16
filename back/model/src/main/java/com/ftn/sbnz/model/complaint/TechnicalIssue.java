package com.ftn.sbnz.model.complaint;

import lombok.*;

import javax.persistence.*;
import static javax.persistence.InheritanceType.JOINED;

@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy=JOINED)
@Table(name = "technical_issue")
public class TechnicalIssue extends Complaint {
    

}
