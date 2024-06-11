package com.ftn.sbnz.model.complaint;
import com.ftn.sbnz.model.packages.Packages;
import lombok.*;

import javax.persistence.*;
import com.ftn.sbnz.model.user.Client;
import org.kie.api.definition.type.Role;

import java.time.LocalDateTime;
import java.util.Date;

import static javax.persistence.InheritanceType.JOINED;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy=JOINED)
@Role(Role.Type.EVENT)
@Table(name = "complaint")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String complaintMsg;

    private String recommendation;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "packages_id")
    private Packages packages;

    private Date timestamp;

    private boolean processed;
}
