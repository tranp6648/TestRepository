package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "thong_tin_xe", schema = "thanh_tong_db")
public class ThongTinXe {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_thong_tin_xe")
    private int idThongTinXe;
    @Basic
    @Column(name = "ten_xe")
    private String tenXe;
    @Basic
    @Column(name = "nam_san_xuat")
    private int namSanXuat;
    @Basic
    @Column(name = "mo_ta")
    private String moTa;
    @ManyToOne
    @JoinColumn(name = "loai_xe_id", nullable = false)
    private LoaiXe loaiXe;
    @ManyToOne
    @JoinColumn(name = "hang_xe_id", nullable = false)
    private HangXe hangXe;
    @OneToMany(mappedBy = "thongTinXe", cascade = CascadeType.ALL)
    private List<DichVuCuaXe> dichVuCuaXe;
    @OneToMany(mappedBy = "thongTinXe", cascade = CascadeType.ALL)
    private List<SanPhamCuaXe> sanPhamCuaXe;
}
