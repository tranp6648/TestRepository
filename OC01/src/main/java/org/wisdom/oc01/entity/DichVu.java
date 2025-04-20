package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "dich_vu", schema = "thanh_tong_db")
public class DichVu {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_dich_vu")
    private int idDichVu;
    @Basic
    @Column(name = "ten_dich_vu")
    private String tenDichVu;
    @Basic
    @Column(name = "gia")
    private int gia;
    @Basic
    @Column(name = "hinh_anh")
    private String hinhAnh;
    @Basic
    @Column(name = "mo_ta")
    private String moTa;
    @ManyToOne
    @JoinColumn(name = "danh_muc_dich_vu_id", nullable = false)
    private DanhMucDichVu danhMucDichVu;
    @OneToMany(mappedBy = "dichVu", cascade = CascadeType.ALL)
    private List<DichVuCuaXe> dichVuCuaXe;
    @OneToMany(mappedBy = "dichVu", cascade = CascadeType.ALL)
    private List<DatLichDichVu> datLichDichVu;

}
