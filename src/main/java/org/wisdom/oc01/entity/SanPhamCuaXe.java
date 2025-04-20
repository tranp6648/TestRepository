package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "san_pham_cua_xe", schema = "thanh_tong_db", catalog = "")
public class SanPhamCuaXe {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_san_pham_cua_xe")
    private int idSanPhamCuaXe;
    @ManyToOne
    @JoinColumn(name = "thong_tin_xe_id", nullable = false)
    private ThongTinXe thongTinXe;
    @ManyToOne
    @JoinColumn(name = "san_pham_id", nullable = false)
    private SanPham sanPham;
}
