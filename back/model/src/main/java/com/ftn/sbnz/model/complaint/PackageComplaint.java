package com.ftn.sbnz.model.complaint;

import com.ftn.sbnz.model.packages.Packages;
import lombok.*;

import javax.persistence.*;
import static javax.persistence.InheritanceType.JOINED;

@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy=JOINED)
@Table(name = "package_complaint")
public class PackageComplaint extends Complaint {

    @OneToOne
    @JoinColumn(name= "packages_id")
    private Packages packages;
    
}
