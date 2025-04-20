package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "dat_lich", schema = "thanh_tong_db")
public class DatLich {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_dat_lich")
    private int idDatLich;
    @Basic
    @Column(name = "thoi_gian_dat_lich")
    private Timestamp thoiGianDatLich;
    @Basic
    @Column(name = "ghi_chu")
    private String ghiChu;
    @ManyToOne
    @JoinColumn(name = "cua_hang_id", nullable = false)
    private CuaHang cuaHang;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @OneToMany(mappedBy = "datLich", cascade = CascadeType.ALL)
    private List<DatLichSanPham> datLichSanPhamList;
    @OneToMany(mappedBy = "datLich", cascade = CascadeType.ALL)
    private List<DatLichDichVu> datLichDichVuList;

}
