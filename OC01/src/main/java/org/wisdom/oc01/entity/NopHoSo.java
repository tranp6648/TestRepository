package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "nop_ho_so", schema = "thanh_tong_db")
public class NopHoSo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_nop_ho_so")
    private int idNopHoSo;
    @ManyToOne
    @JoinColumn(name = "tuyen_dung_id", nullable = false)
    private TuyenDung tuyenDung;
    @ManyToOne
    @JoinColumn(name = "cv_id", nullable = false)
    private Cv cv;
}
