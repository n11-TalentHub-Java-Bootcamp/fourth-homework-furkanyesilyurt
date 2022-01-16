package com.furkanyesilyurt.fourthHomework.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "debt")
public class Debt implements Serializable {

    @Id
    @SequenceGenerator(name = "generator", sequenceName = "ID_DEBT_SEQ")
    @GeneratedValue(generator = "generator", strategy = GenerationType.AUTO)
    @Column(name = "debt_id")
    private Long debt_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "debt_user_id_fkey"))
    private User user;

    @Column(name = "main_debt")
    private Double mainDebt;

    @Column(name = "remaining_debt")
    private Double remainingDebt;

    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    @Override
    public String toString() {
        return debt_id == null ? "" : debt_id.toString();
    }

}
