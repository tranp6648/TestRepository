package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "dat_lich_dich_vu", schema = "thanh_tong_db")
public class DatLichDichVu {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_dat_lich_dich_vu")
    private int idDatLichDichVu;


    @ManyToOne
    @JoinColumn(name = "dat_lich_id", nullable = false)
    private DatLich datLich;
    @ManyToOne
    @JoinColumn(name = "dich_vu_id", nullable = false)
    private DichVu dichVu;
}
