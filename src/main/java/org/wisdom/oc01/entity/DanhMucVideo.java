package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "danh_muc_video", schema = "thanh_tong_db")
public class DanhMucVideo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_danh_muc_video")
    private int idDanhMucVideo;
    @Basic
    @Column(name = "ten_danh_muc")
    private String tenDanhMuc;

    @OneToMany(mappedBy = "danhMucVideo", cascade = CascadeType.ALL)
    private List<Video> videos;

}
