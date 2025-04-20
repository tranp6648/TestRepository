package org.wisdom.oc01.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.wisdom.oc01.dto.request.SanPhamDTO;
import org.wisdom.oc01.entity.SanPham;

public interface SanPhamService {
    SanPham createSanPham(SanPhamDTO dto, MultipartFile file);

    SanPham updateSanPham(int id, SanPhamDTO dto, MultipartFile file);


    void deleteSanPham(int id);

    SanPham getSanPhamById(int id);

    Page<SanPham> getAllSanPham(int page, int size);

    SanPhamDTO toDto(SanPham sp);

    ResponseEntity<byte[]> getSanPhamImage(String filename);
}
