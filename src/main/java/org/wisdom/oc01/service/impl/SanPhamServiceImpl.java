package org.wisdom.oc01.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.wisdom.oc01.dto.request.SanPhamDTO;
import org.wisdom.oc01.entity.DanhMuc;
import org.wisdom.oc01.entity.SanPham;
import org.wisdom.oc01.exception.ErrorHandler;
import org.wisdom.oc01.generic.GeneralService;
import org.wisdom.oc01.repository.DanhMucRepository;
import org.wisdom.oc01.repository.SanPhamRepository;
import org.wisdom.oc01.service.SanPhamService;

import java.io.IOException;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class SanPhamServiceImpl implements SanPhamService {

    private final SanPhamRepository sanPhamRepository;
    private final DanhMucRepository danhMucRepository;
    private final GeneralService generalService;

    @Override
    public SanPham createSanPham(SanPhamDTO dto, MultipartFile file) {
        SanPham sp = new SanPham();
        return saveOrUpdateSanPham(sp, dto, file);
    }

    @Override
    public SanPham updateSanPham(int id, SanPhamDTO dto, MultipartFile file) {
        SanPham sp = sanPhamRepository.findById(id)
                .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "Không tìm thấy sản phẩm có ID: " + id));
        return saveOrUpdateSanPham(sp, dto, file);
    }

    public SanPhamDTO toDto(SanPham sp) {
        SanPhamDTO dto = new SanPhamDTO();
        dto.setIdSanPham(sp.getIdSanPham());
        dto.setTenSanPham(sp.getTenSanPham());
        dto.setGia(sp.getGia());
        dto.setHinhAnh(sp.getHinhAnh());
        dto.setMoTa(sp.getMoTa());
        dto.setDanhMucId(sp.getDanhMuc().getIdDanhMuc()); // hoặc sp.getDanhMucId() nếu là field primitive
        return dto;
    }

    @Override
    public ResponseEntity<byte[]> getSanPhamImage(String filename) {
        try {
            // 1. Lấy đường dẫn file ảnh
            Path filePath = generalService.getFullPathFromLink("/sanpham/" + filename);

            // 2. Kiểm tra file tồn tại
            generalService.validateFileExists(filePath);

            // 3. Lấy dữ liệu file và MIME type
            GeneralService.FileData fileData = generalService.getFileData(filePath);

            // 4. Trả về ảnh đúng MIME
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(fileData.getMimeType()))
                    .body(fileData.getContent());

        } catch (IOException e) {
            throw new ErrorHandler(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi khi đọc ảnh: " + e.getMessage());
        }
    }

    private SanPham saveOrUpdateSanPham(SanPham sp, SanPhamDTO dto, MultipartFile file) {
        sp.setTenSanPham(dto.getTenSanPham());
        sp.setGia(dto.getGia());
        sp.setMoTa(dto.getMoTa());

        // ✅ Gán danh mục
        DanhMuc danhMuc = danhMucRepository.findById(dto.getDanhMucId())
                .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "Không tìm thấy danh mục với ID: " + dto.getDanhMucId()));
        sp.setDanhMuc(danhMuc);

        // ✅ Lưu hình ảnh nếu có
        if (file != null && !file.isEmpty()) {
            try {
                String filePath = generalService.saveFile(file, "sanpham/");
                sp.setHinhAnh(filePath);
            } catch (IOException e) {
                throw new ErrorHandler(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi khi lưu hình ảnh: " + e.getMessage());
            }
        }

        return sanPhamRepository.save(sp);
    }

    @Override
    public void deleteSanPham(int id) {
        if (!sanPhamRepository.existsById(id)) {
            throw new ErrorHandler(HttpStatus.NOT_FOUND, "Sản phẩm không tồn tại");
        }
        sanPhamRepository.deleteById(id);
    }

    @Override
    public SanPham getSanPhamById(int id) {
        return sanPhamRepository.findById(id)
                .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "Không tìm thấy sản phẩm"));
    }

    @Override
    public Page<SanPham> getAllSanPham(int page, int size) {
        return sanPhamRepository.findAll(PageRequest.of(page, size));
    }
}
