package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "cua_hang", schema = "thanh_tong_db")
public class CuaHang {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cua_hang")
    private int idCuaHang;
    @Basic
    @Column(name = "ten_cua_hang")
    private String tenCuaHang;
    @Basic
    @Column(name = "dia_chi")
    private String diaChi;
    @OneToMany(mappedBy = "cuaHang", cascade = CascadeType.ALL)
    private List<DatLich> datLich;

}
