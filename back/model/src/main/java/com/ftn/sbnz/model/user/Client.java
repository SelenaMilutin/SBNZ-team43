package com.ftn.sbnz.model.user;

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
@Table(name = "client")
@Inheritance(strategy = InheritanceType.JOINED)
public class Client extends AppUser{
    private boolean premium;
}
