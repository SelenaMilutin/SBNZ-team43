package com.ftn.sbnz.model.complaint;

import com.ftn.sbnz.model.packages.Packages;
import lombok.*;

import javax.persistence.*;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import static javax.persistence.InheritanceType.JOINED;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy=JOINED)
@Table(name = "package_complaint")
@Role(Role.Type.EVENT)
@Timestamp("submitTime")
@Expires("48h00m")
public class PackageComplaint extends Complaint {

    @OneToOne
    @JoinColumn(name= "packages_id")
    private Packages packages;
    
    private Date submitTime;
}
