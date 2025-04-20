package org.wisdom.oc01.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import org.wisdom.oc01.Enum.Status;
import org.wisdom.oc01.dto.request.VideoDTO;
import org.wisdom.oc01.entity.HocVienDangKyVideo;
import org.wisdom.oc01.entity.Video;

public interface VideoService {
    Video createVideo(VideoDTO video, MultipartFile file);

    Video updateVideo(int id, VideoDTO video,MultipartFile file);

    void deleteVideo(int id);

    Video getVideoById(int id);

    VideoDTO todo(Video video);

    Page<Video> getAllVideo(int page, int size);

    HocVienDangKyVideo dangkyVideo(String token, int idVideo);
    HocVienDangKyVideo ResponseRegister(int id, Status status);
    Page<VideoDTO>findVideOfStudent(int page, int size, String token);
}
