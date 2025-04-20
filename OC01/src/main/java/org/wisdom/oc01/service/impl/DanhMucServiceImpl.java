package org.wisdom.oc01.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.wisdom.oc01.dto.request.DanhMucDTO;
import org.wisdom.oc01.entity.DanhMuc;
import org.wisdom.oc01.repository.DanhMucRepository;
import org.wisdom.oc01.service.DanhMucService;

import java.util.Optional;

@Service
public class DanhMucServiceImpl implements DanhMucService {

    @Autowired
    private DanhMucRepository danhMucRepository;


    // Thêm
    @Override
    public DanhMuc createDanhMuc(DanhMucDTO dto) {
        try {
            DanhMuc danhMuc = new DanhMuc();
            danhMuc.setTenDanhMuc(dto.getTenDanhMuc());
            danhMuc.setMoTa(dto.getMoTa());
            return danhMucRepository.save(danhMuc);
        } catch (Exception e) {
            throw new RuntimeException("Không thể thêm danh mục.");
        }
    }

    // Sửa
    @Override
    public DanhMuc updateDanhMuc(int id, DanhMucDTO dto) {
        try {
            Optional<DanhMuc> optional = danhMucRepository.findById(id);
            if (optional.isPresent()) {
                DanhMuc danhMuc = optional.get();
                danhMuc.setTenDanhMuc(dto.getTenDanhMuc());
                danhMuc.setMoTa(dto.getMoTa());
                return danhMucRepository.save(danhMuc);
            } else {
                throw new RuntimeException("Không tìm thấy danh mục với id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Không thể cập nhật danh mục.");
        }
    }

    // Xóa
    @Override
    public void deleteDanhMuc(int id) {
        try {
            if (!danhMucRepository.existsById(id)) {
                throw new RuntimeException("Không tìm thấy danh mục với id: " + id);
            }
            danhMucRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Không thể xóa danh mục.");
        }
    }

    // Tìm theo ID
    @Override
    public DanhMuc getDanhMucById(int id) {
        try {
            return danhMucRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục với id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Không thể lấy danh mục.");
        }
    }

    // Tìm tất cả có phân trang
    @Override
    public Page<DanhMuc> getAllDanhMuc(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("idDanhMuc").descending());
            return danhMucRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Không thể lấy danh sách danh mục.");
        }
    }

    @Override
    public DanhMucDTO toDto(DanhMuc sp) {
        DanhMucDTO dto = new DanhMucDTO();
        dto.setId(sp.getIdDanhMuc());
        dto.setTenDanhMuc(sp.getTenDanhMuc());
        dto.setMoTa(sp.getMoTa());

        return dto;
    }

}
