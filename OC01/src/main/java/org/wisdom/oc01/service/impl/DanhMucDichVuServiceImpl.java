package org.wisdom.oc01.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.wisdom.oc01.dto.request.DanhMucDichVuDTO;
import org.wisdom.oc01.entity.DanhMucDichVu;
import org.wisdom.oc01.repository.DanhMucDichVuRepository;
import org.wisdom.oc01.service.DanhMucDichVuService;

import java.util.Optional;

@Service
public class DanhMucDichVuServiceImpl implements DanhMucDichVuService {

    @Autowired
    private DanhMucDichVuRepository danhMucDichVuRepository;

    @Override
    public DanhMucDichVu createDanhMucDichVu(DanhMucDichVuDTO dto) {
        try {
            DanhMucDichVu danhMucDichVu = new DanhMucDichVu();
            danhMucDichVu.setTenDanhMucDv(dto.getTenDanhMucDv());
            danhMucDichVu.setMoTa(dto.getMoTa());
            return danhMucDichVuRepository.save(danhMucDichVu);
        } catch (Exception e) {
            throw new RuntimeException("Không thể thêm danh mục.");
        }
    }

    @Override
    public DanhMucDichVu updateDanhMucDichVu(int id, DanhMucDichVuDTO dto) {
        try {
            Optional<DanhMucDichVu> optional = danhMucDichVuRepository.findById(id);
            if (optional.isPresent()) {
                DanhMucDichVu danhMucDichVu = optional.get();
                danhMucDichVu.setTenDanhMucDv(dto.getTenDanhMucDv());
                danhMucDichVu.setMoTa(dto.getMoTa());
                return danhMucDichVuRepository.save(danhMucDichVu);
            } else {
                throw new RuntimeException("Không tìm thấy danh mục với id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Không thể cập nhật danh mục.");
        }
    }

    @Override
    public void deleteDanhMucDichVu(int id) {
        try {
            if (!danhMucDichVuRepository.existsById(id)) {
                throw new RuntimeException("Không tìm thấy danh mục với id: " + id);
            }
            danhMucDichVuRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Không thể xóa danh mục.");
        }
    }

    @Override
    public DanhMucDichVu getDanhMucDichVuById(int id) {
        try {
            return danhMucDichVuRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục với id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Không thể lấy danh mục.");
        }
    }

    @Override
    public Page<DanhMucDichVu> getAllDanhMucDichVu(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("idDanhMucDichVu").descending());
            return danhMucDichVuRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Không thể lấy danh sách danh mục.");
        }
    }

    @Override
    public DanhMucDichVuDTO toDto(DanhMucDichVu sp) {
        DanhMucDichVuDTO dto = new DanhMucDichVuDTO();
        dto.setId(sp.getIdDanhMucDichVu());
        dto.setTenDanhMucDv(sp.getTenDanhMucDv());
        dto.setMoTa(sp.getMoTa());
        return dto;
    }
}
