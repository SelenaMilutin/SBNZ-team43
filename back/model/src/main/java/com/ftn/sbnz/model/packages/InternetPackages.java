package com.ftn.sbnz.model.packages;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "internetPackages")
@Inheritance(strategy = InheritanceType.JOINED)
public class InternetPackages extends Packages{
    private double internet;
    private double expiration;
}
