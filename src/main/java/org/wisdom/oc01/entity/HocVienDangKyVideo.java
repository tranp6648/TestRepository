package org.wisdom.oc01.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.wisdom.oc01.Enum.Status;

@Setter
@Getter
@Entity
@Table(name = "hoc_vien_dang_ky_video", schema = "thanh_tong_db", catalog = "")
public class HocVienDangKyVideo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_hoc_vien_dang_ky_video")
    private int idHocVienDangKyVideo;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;
    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private Status status=Status.Pending;
}
