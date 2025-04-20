package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "danh_muc", schema = "thanh_tong_db")
public class DanhMuc {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_danh_muc")
    private int idDanhMuc;
    @Basic
    @Column(name = "ten_danh_muc")
    private String tenDanhMuc;
    @Basic
    @Column(name = "mo_ta")
    private String moTa;
    @OneToMany(mappedBy = "danhMuc", cascade = CascadeType.ALL)
    private List<SanPham> sanPhamList;

}
