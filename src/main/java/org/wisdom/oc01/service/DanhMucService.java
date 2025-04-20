package org.wisdom.oc01.service;

import org.springframework.data.domain.Page;
import org.wisdom.oc01.dto.request.DanhMucDTO;
import org.wisdom.oc01.entity.DanhMuc;

public interface DanhMucService {
    DanhMuc createDanhMuc(DanhMucDTO dto);

    DanhMuc updateDanhMuc(int id, DanhMucDTO dto);

    void deleteDanhMuc(int id);

    DanhMuc getDanhMucById(int id);

    Page<DanhMuc> getAllDanhMuc(int page, int size);

    DanhMucDTO toDto(DanhMuc sp);
}
