package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "dat_lich_san_pham", schema = "thanh_tong_db")
public class DatLichSanPham {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_dat_lich_san_pham")
    private int idDatLichSanPham;

    @ManyToOne
    @JoinColumn(name = "san_pham_id", nullable = false)
    private SanPham sanPham;
    @ManyToOne
    @JoinColumn(name = "dat_lich_id", nullable = false)
    private DatLich datLich;
}
