package com.furkanyesilyurt.fourthHomework.entity;

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
@Table(name = "user_app")
public class User implements Serializable {

    @Id
    @SequenceGenerator(name = "generator", sequenceName = "ID_USER_SEQ")
    @GeneratedValue(generator = "generator", strategy = GenerationType.AUTO)
    @Column(name = "user_id",nullable = false)
    private Long user_id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birthday")
    private Date birthday;

    @Override
    public String toString() {
        return user_id == null ? "" : user_id.toString();
    }

}
