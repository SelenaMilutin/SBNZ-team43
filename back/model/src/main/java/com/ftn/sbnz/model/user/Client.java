package com.ftn.sbnz.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ftn.sbnz.model.servicearea.ServiceArea;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "client")
@Inheritance(strategy = InheritanceType.JOINED)
public class Client extends AppUser{

    @ManyToOne
    @JoinColumn( name = "service_area_id")
    private ServiceArea serviceArea;

}
