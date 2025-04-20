package org.wisdom.oc01.service;

import org.springframework.data.domain.Page;
import org.wisdom.oc01.dto.request.DanhMucDichVuDTO;
import org.wisdom.oc01.entity.DanhMucDichVu;

public interface DanhMucDichVuService {
    DanhMucDichVu createDanhMucDichVu(DanhMucDichVuDTO dto);

    DanhMucDichVu updateDanhMucDichVu(int id, DanhMucDichVuDTO dto);

    void deleteDanhMucDichVu(int id);

    DanhMucDichVu getDanhMucDichVuById(int id);

    Page<DanhMucDichVu> getAllDanhMucDichVu(int page, int size);

    DanhMucDichVuDTO toDto(DanhMucDichVu sp);
}
