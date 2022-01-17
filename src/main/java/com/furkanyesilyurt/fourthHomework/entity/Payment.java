package com.furkanyesilyurt.fourthHomework.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","debt"})
public class Payment implements Serializable {

    @Id
    @SequenceGenerator(name = "generator", sequenceName = "ID_PAYMENT_SEQ")
    @GeneratedValue(generator = "generator", strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "debt_id", foreignKey = @ForeignKey(name = "payment_debt_id_fkey"), referencedColumnName = "debt_id")
    private Debt debt;

    @Column(name = "payment_date")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    @Column(name = "user_id")
    private Long userId;

    @Override
    public String toString() {
        return id == null ? "" : id.toString();
    }

}
