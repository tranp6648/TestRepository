package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "san_pham", schema = "thanh_tong_db", catalog = "")
public class SanPham {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_san_pham")
    private int idSanPham;
    @Basic
    @Column(name = "ten_san_pham")
    private String tenSanPham;
    @Basic
    @Column(name = "hinh_anh")
    private String hinhAnh;
    @Basic
    @Column(name = "gia")
    private int gia;
    @Basic
    @Column(name = "mo_ta")
    private String moTa;
    @ManyToOne
    @JoinColumn(name = "danh_muc_id", nullable = false)
    private DanhMuc danhMuc;
}
