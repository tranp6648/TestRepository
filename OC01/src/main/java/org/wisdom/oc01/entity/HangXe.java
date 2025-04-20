package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "hang_xe", schema = "thanh_tong_db")
public class HangXe {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_hang_xe")
    private int idHangXe;
    @Basic
    @Column(name = "ten_hang_xe")
    private String tenHangXe;

    @OneToMany(mappedBy = "hangXe", cascade = CascadeType.ALL)
    private List<ThongTinXe> thongTinXe;

}
