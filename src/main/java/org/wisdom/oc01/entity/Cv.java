package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Cv {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cv")
    private int idCv;
    @Basic
    @Column(name = "ten_cv")
    private String tenCv;
    @Basic
    @Column(name = "path")
    private String path;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
    private List<NopHoSo> nopHoSo;
}
