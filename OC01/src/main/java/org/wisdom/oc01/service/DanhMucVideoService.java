package org.wisdom.oc01.service;

import org.springframework.data.domain.Page;
import org.wisdom.oc01.dto.request.DanhmucVideoDTO;
import org.wisdom.oc01.entity.DanhMucVideo;

public interface DanhMucVideoService {
    DanhMucVideo createDanhMucVideo(DanhmucVideoDTO video);

    DanhMucVideo updateDanhMucVideo(int id, DanhmucVideoDTO video);

    void deleteDanhMucVideo(int id);

    DanhMucVideo getDanhMucVideoById(int id);

    DanhmucVideoDTO todo(DanhMucVideo danhMucVideo);

    Page<DanhMucVideo> getAllDanhMucVideo(int page, int size);
}
