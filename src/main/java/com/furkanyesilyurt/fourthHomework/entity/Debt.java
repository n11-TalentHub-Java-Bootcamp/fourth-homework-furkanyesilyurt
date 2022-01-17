package com.furkanyesilyurt.fourthHomework.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.furkanyesilyurt.fourthHomework.enums.DebtType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    private Double mainDebt;

    @Column(name = "remaining_debt")
    private Double remainingDebt;

    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    @Column(name = "debtType")
    private DebtType debtType;

    @Column(name = "debt") //for delay debt
    private Long debt;

    //, columnDefinition = "character varying (255)"

    public Debt() {
    }

    public Debt(Long debt_id, User user, Double mainDebt, Double remainingDebt, Date expiryDate, DebtType debtType) {
        this.debt_id = debt_id;
        this.user = user;
        this.mainDebt = mainDebt;
        this.remainingDebt = remainingDebt;
        this.expiryDate = expiryDate;
        this.debtType = debtType;
    }

    public Long getDebt_id() {
        return debt_id;
    }

    public void setDebt_id(Long debt_id) {
        this.debt_id = debt_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getMainDebt() {
        return mainDebt;
    }

    public void setMainDebt(Double mainDebt) {
        this.mainDebt = mainDebt;
    }

    public Double getRemainingDebt() {
        return remainingDebt;
    }

    public void setRemainingDebt(Double remainingDebt) {
        this.remainingDebt = remainingDebt;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public DebtType getDebtType() {
        return debtType;
    }

    public void setDebtType(DebtType debtType) {
        this.debtType = debtType;
    }

    @Override
    public String toString() {
        return debt_id == null ? "" : debt_id.toString();
    }

}
