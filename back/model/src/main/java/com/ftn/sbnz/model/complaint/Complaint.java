package com.ftn.sbnz.model.complaint;
import lombok.*;

import javax.persistence.*;
import com.ftn.sbnz.model.user.Client;
import static javax.persistence.InheritanceType.JOINED;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy=JOINED)
@Table(name = "complaint")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
