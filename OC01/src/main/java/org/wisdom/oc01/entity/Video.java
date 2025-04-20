package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
public class Video {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_video")
    private int idVideo;
    @Basic
    @Column(name = "ten_video")
    private String tenVideo;
    @Basic
    @Column(name = "noi_dung")
    private String noiDung;
    @Basic
    @Column(name = "mo_ta")
    private String moTa;
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "danh_muc_video_id", nullable = false)
    private DanhMucVideo danhMucVideo;

}
