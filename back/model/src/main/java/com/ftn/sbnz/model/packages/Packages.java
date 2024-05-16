package com.ftn.sbnz.model.packages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.InheritanceType.JOINED;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@TableGenerator(name="packages_id_generator", table="primary_keys", pkColumnName="key_pk", pkColumnValue="packages", valueColumnName="value_pk")
@Inheritance(strategy=JOINED)
public class Packages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @ManyToOne
    private Packages parent;
    private String name;
    private double monthlyPrice;
    private PackageType packageType;
    private boolean inOfferFlag;

}
