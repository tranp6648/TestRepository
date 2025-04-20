package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_user", nullable = false)
    private int idUser;

    @Basic
    @Column(name = "first_name", nullable = true, length = 45)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = true, length = 45)
    private String lastName;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;


}
