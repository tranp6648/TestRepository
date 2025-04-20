package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tuyen_dung", schema = "thanh_tong_db")
public class TuyenDung {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_tuyen_dung")
    private int idTuyenDung;
    @Basic
    @Column(name = "noi_dung_tuyen_dung")
    private String noiDungTuyenDung;
    @Basic
    @Column(name = "bat_dau")
    private Timestamp batDau;
    @Basic
    @Column(name = "han_chot")
    private Timestamp hanChot;
    @OneToMany(mappedBy = "tuyenDung", cascade = CascadeType.ALL)
    private List<NopHoSo> nopHoSo;
}
