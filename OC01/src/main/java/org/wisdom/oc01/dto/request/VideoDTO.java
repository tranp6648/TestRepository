package org.wisdom.oc01.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.wisdom.oc01.Enum.Status;

import java.sql.Timestamp;

@Data
@Getter
@Setter
public class VideoDTO {
    private int id;
    private String tenVideo;
    private String mota;
    private int danhMucVideoId;
    private Timestamp createdAt;
    private Status status;

    public VideoDTO() {
    }

    public VideoDTO(int id, String tenVideo, String mota, int danhMucVideoId, Timestamp createdAt,Status status) {
        this.id = id;
        this.tenVideo = tenVideo;
        this.mota = mota;
        this.danhMucVideoId = danhMucVideoId;
        this.createdAt = createdAt;
        this.status = status;
    }
}
