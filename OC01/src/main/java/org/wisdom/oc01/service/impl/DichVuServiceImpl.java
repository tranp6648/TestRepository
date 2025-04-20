package org.wisdom.oc01.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.wisdom.oc01.dto.request.DichVuDTO;
import org.wisdom.oc01.entity.DanhMuc;
import org.wisdom.oc01.entity.DanhMucDichVu;
import org.wisdom.oc01.entity.DichVu;
import org.wisdom.oc01.exception.ErrorHandler;
import org.wisdom.oc01.generic.GeneralService;
import org.wisdom.oc01.repository.DanhMucDichVuRepository;
import org.wisdom.oc01.repository.DichVuRepository;
import org.wisdom.oc01.service.DichVuService;

import java.io.IOException;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class DichVuServiceImpl implements DichVuService {

    private final DichVuRepository dichVuRepository;
    private final GeneralService generalService;
    private final DanhMucDichVuRepository danhMucDichVuRepository;

    @Override
    public DichVu createDichVu(DichVuDTO dto, MultipartFile file) {
        DichVu dv = new DichVu();
        return saveOrUpdateDichVu(dv, dto, file);
    }

    @Override
    public DichVu updateDichVu(int id, DichVuDTO dto, MultipartFile file) {
        DichVu dv = dichVuRepository.findById(id)
                .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "Không tìm thấy dịch vụ có ID: " + id));
        return saveOrUpdateDichVu(dv, dto, file);
    }

    private DichVu saveOrUpdateDichVu(DichVu dv, DichVuDTO dto, MultipartFile file) {
        dv.setTenDichVu(dto.getTenDichVu());
        dv.setGia(dto.getGia());
        dv.setMoTa(dto.getMoTa());
        // ✅ Gán danh mục
        DanhMucDichVu danhMucDichVu = danhMucDichVuRepository.findById(dto.getDanhMucDichVuId())
                .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "Không tìm thấy danh mục với ID: " + dto.getDanhMucDichVuId()));
        dv.setDanhMucDichVu(danhMucDichVu);
        if (file != null && !file.isEmpty()) {
            try {
                String filePath = generalService.saveFile(file, "dichvu/");
                dv.setHinhAnh(filePath);
            } catch (IOException e) {
                throw new ErrorHandler(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi khi lưu hình ảnh: " + e.getMessage());
            }
        }

        return dichVuRepository.save(dv);
    }

    @Override
    public DichVuDTO toDto(DichVu dv) {
        DichVuDTO dto = new DichVuDTO();
        dto.setId(dv.getIdDichVu());
        dto.setTenDichVu(dv.getTenDichVu());
        dto.setGia(dv.getGia());
        dto.setHinhAnh(dv.getHinhAnh());
        dto.setMoTa(dv.getMoTa());
        dto.setDanhMucDichVuId(dv.getDanhMucDichVu().getIdDanhMucDichVu());
        return dto;
    }

    @Override
    public ResponseEntity<byte[]> getDichVuImage(String filename) {
        try {
            Path filePath = generalService.getFullPathFromLink("/dichvu/" + filename);
            generalService.validateFileExists(filePath);
            GeneralService.FileData fileData = generalService.getFileData(filePath);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(fileData.getMimeType()))
                    .body(fileData.getContent());
        } catch (IOException e) {
            throw new ErrorHandler(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi khi đọc ảnh: " + e.getMessage());
        }
    }


    @Override
    public void deleteDichVu(int id) {
        if (!dichVuRepository.existsById(id)) {
            throw new ErrorHandler(HttpStatus.NOT_FOUND, "Dịch vụ không tồn tại");
        }
        dichVuRepository.deleteById(id);
    }

    @Override
    public DichVu getDichVuById(int id) {
        return dichVuRepository.findById(id)
                .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "Không tìm thấy dịch vụ"));
    }

    @Override
    public Page<DichVu> getAllDichVu(int page, int size) {
        return dichVuRepository.findAll(PageRequest.of(page, size));
    }
}
