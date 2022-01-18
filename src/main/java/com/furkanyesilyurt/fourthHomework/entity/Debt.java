package com.furkanyesilyurt.fourthHomework.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.furkanyesilyurt.fourthHomework.enums.DebtType;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "debt")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","user"})
public class Debt implements Serializable {

    @Id
    @SequenceGenerator(name = "generator", sequenceName = "ID_DEBT_SEQ")
    @GeneratedValue(generator = "generator", strategy = GenerationType.AUTO)
    @Column(name = "debt_id")
    private Long debt_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "debt_user_id_fkey"), referencedColumnName = "user_id")
    private User user;

    @Column(name = "main_debt")
    @Digits(integer = 10, fraction = 2)
    private Double mainDebt;

    @Column(name = "remaining_debt")
    @Digits(integer = 10, fraction = 2)
    private Double remainingDebt;

    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    @Column(name = "debtType")
    private DebtType debtType;

    @Column(name = "debt") //for delay debt id
    private Long debt;

    @Transient
    @Column(name = "transient_delay_debt")
    @Digits(integer = 10, fraction = 2)
    private Double delayDebt;

    @Override
    public String toString() {
        return debt_id == null ? "" : debt_id.toString();
    }

}
