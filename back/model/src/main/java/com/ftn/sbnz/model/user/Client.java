package com.ftn.sbnz.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "client")
@Inheritance(strategy = InheritanceType.JOINED)
public class Client extends AppUser{
}
