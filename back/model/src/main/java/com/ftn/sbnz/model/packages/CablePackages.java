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
@Table(name = "cablePackages")
@Inheritance(strategy = InheritanceType.JOINED)
public class CablePackages extends Packages{
    private int chanelNumber;
}
