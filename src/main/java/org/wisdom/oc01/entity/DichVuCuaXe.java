package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "dich_vu_cua_xe", schema = "thanh_tong_db", catalog = "")
public class DichVuCuaXe {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_dich_vu_cua_xe")
    private int idDichVuCuaXe;
    @ManyToOne
    @JoinColumn(name = "thong_tin_xe_id", nullable = false)
    private ThongTinXe thongTinXe;
    @ManyToOne
    @JoinColumn(name = "dich_vu_id", nullable = false)
    private DichVu dichVu;
}
