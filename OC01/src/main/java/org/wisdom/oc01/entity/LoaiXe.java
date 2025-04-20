package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "loai_xe", schema = "thanh_tong_db")
public class LoaiXe {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_loai_xe")
    private int idLoaiXe;
    @Basic
    @Column(name = "ten_loai_xe")
    private String tenLoaiXe;

    @OneToMany(mappedBy = "loaiXe", cascade = CascadeType.ALL)
    private List<ThongTinXe> thongTinXe;
}
