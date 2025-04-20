package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "danh_muc_dich_vu", schema = "thanh_tong_db")
public class DanhMucDichVu {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_danh_muc_dich_vu")
    private int idDanhMucDichVu;
    @Basic
    @Column(name = "ten_danh_muc_dv")
    private String tenDanhMucDv;
    @Basic
    @Column(name = "mo_ta")
    private String moTa;
    @OneToMany(mappedBy = "danhMucDichVu", cascade = CascadeType.ALL)
    private List<DichVu> dichVuList;

}
