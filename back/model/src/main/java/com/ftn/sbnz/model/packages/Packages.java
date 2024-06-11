package com.ftn.sbnz.model.packages;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.InheritanceType.JOINED;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@TableGenerator(name="packages_id_generator", table="primary_keys", pkColumnName="key_pk", pkColumnValue="packages", valueColumnName="value_pk")
@Inheritance(strategy=JOINED)
@ToString
public class Packages implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @ManyToOne
    private Packages parent;
    private String name;
    private double monthlyPrice;
    private PackageType packageType;
    private boolean inOfferFlag;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Packages cloned = (Packages) super.clone();

        // Deep copy the parent if it exists
        if (this.parent != null) {
            cloned.parent = (Packages) this.parent.clone();
        }

        return cloned;
    }
    public Packages copy() {
        // Create a copy of the parent package if it exists
        Packages parentCopy = (this.parent != null) ? this.parent.copy() : null;

        // Create a new Packages object with copied values
        return new Packages(
                this.id,
                parentCopy,
                this.name,
                this.monthlyPrice,
                this.packageType,
                this.inOfferFlag
        );

    }
}
