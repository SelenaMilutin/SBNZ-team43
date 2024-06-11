package com.ftn.sbnz.model.contract;

import com.ftn.sbnz.model.packages.Packages;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.*;

import com.ftn.sbnz.model.user.Client;
import org.kie.api.definition.type.Position;
import org.kie.api.definition.type.Role;
import org.springframework.context.event.EventListener;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Role(Role.Type.EVENT)
@Entity
@Table(name = "contract")
public class Contract {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime expirationDate;

    private boolean activeFlag;

    @ManyToOne
    private Client client;

    @OneToOne
    @JoinColumn(name = "packages_id")
    private Packages packages;

    private double discount; // ako je ostvario neki popust

    private boolean proccessedFlag = false;

    public boolean isInFirstHalf(){

        long durationInMinutes = ChronoUnit.MINUTES.between(this.startDate, this.expirationDate);
        LocalDateTime middleDateTime = this.startDate.plusMinutes(durationInMinutes / 2);
        return middleDateTime.isBefore(LocalDateTime.now());
    }

    public double calculateDebt(double percentage){
//        long months = (Duration.between(this.expirationDate, LocalDateTime.now())).toDays() / 30L;
        long months = ChronoUnit.MONTHS.between(LocalDateTime.now(), this.expirationDate);
//        System.out.println(months);
//        System.out.println(percentage);
        return  months * this.packages.getMonthlyPrice() * percentage;
    }
}
