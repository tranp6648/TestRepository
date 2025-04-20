package org.wisdom.oc01.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.wisdom.oc01.dto.request.DichVuDTO;
import org.wisdom.oc01.entity.DichVu;

public interface DichVuService {

    DichVu updateDichVu(int id, DichVuDTO dto, MultipartFile file);

    void deleteDichVu(int id);

    DichVu getDichVuById(int id);

    Page<DichVu> getAllDichVu(int page, int size);

    DichVu createDichVu(DichVuDTO dto, MultipartFile file);


    DichVuDTO toDto(DichVu dv);

    ResponseEntity<byte[]> getDichVuImage(String filename);
}
