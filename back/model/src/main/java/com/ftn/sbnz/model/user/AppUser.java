package com.ftn.sbnz.model.user;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.InheritanceType.JOINED;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@TableGenerator(name="appUser_id_generator", table="primary_keys", pkColumnName="key_pk", pkColumnValue="appUser", valueColumnName="value_pk")
@Inheritance(strategy=JOINED)
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String password;
//    @Lob
//    private String profileImage;
    private String address;
    private boolean blockedFlag;
    private boolean activeFlag;
    private Role role;
}
