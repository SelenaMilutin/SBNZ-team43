package com.ftn.sbnz.model.complaint;

import com.ftn.sbnz.model.packages.Packages;
import lombok.*;

import javax.persistence.*;
import static javax.persistence.InheritanceType.JOINED;
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy=JOINED)
@Table(name = "package_complaint")
public class PackageComplaint extends Complaint {

//     TODO
//     @OneToOne
//     @JoinColumn(name = "package_id")
//     private Packages package;
    
}
