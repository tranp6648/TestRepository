package org.wisdom.oc01.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wisdom.oc01.dto.RequestResponse;
import org.wisdom.oc01.dto.request.SanPhamDTO;
import org.wisdom.oc01.dto.response.PageResponse;
import org.wisdom.oc01.entity.SanPham;
import org.wisdom.oc01.exception.ErrorHandler;
import org.wisdom.oc01.service.SanPhamService;

@RestController
@RequestMapping("/api/san-pham")
@RequiredArgsConstructor
public class SanPhamController {

    private final SanPhamService sanPhamService;

    // ✅ Tạo sản phẩm mới
    @PostMapping("/create")
    public ResponseEntity<RequestResponse> createSanPham(
            @ModelAttribute SanPhamDTO dto,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            sanPhamService.createSanPham(dto, file);
            return ResponseEntity.ok(new RequestResponse(dto, "Sản phẩm đã được tạo thành công"));
        } catch (ErrorHandler e) {
            return ResponseEntity.status(e.getStatus()).body(new RequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResponse("Đã xảy ra lỗi hệ thống"));
        }
    }

    // ✅ Cập nhật sản phẩm
    @PutMapping("/update/{id}")
    public ResponseEntity<RequestResponse> updateSanPham(
            @PathVariable int id,
            @ModelAttribute SanPhamDTO dto,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            sanPhamService.updateSanPham(id, dto, file);
            return ResponseEntity.ok(new RequestResponse(dto, "Sản phẩm đã được cập nhật thành công"));
        } catch (ErrorHandler e) {
            return ResponseEntity.status(e.getStatus()).body(new RequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResponse("Đã xảy ra lỗi hệ thống"));
        }
    }

    // ✅ Xoá sản phẩm
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RequestResponse> deleteSanPham(@PathVariable int id) {
        try {
            sanPhamService.deleteSanPham(id);
            return ResponseEntity.ok(new RequestResponse("Sản phẩm đã được xoá thành công"));
        } catch (ErrorHandler e) {
            return ResponseEntity.status(e.getStatus()).body(new RequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResponse("Đã xảy ra lỗi hệ thống"));
        }
    }

    // ✅ Lấy sản phẩm theo ID
    @GetMapping("/{id}")
    public ResponseEntity<RequestResponse> getSanPhamById(@PathVariable int id) {
        try {
            SanPham sp = sanPhamService.getSanPhamById(id);
            SanPhamDTO dto = sanPhamService.toDto(sp);
            return ResponseEntity.ok(new RequestResponse(dto, "Lấy thông tin sản phẩm thành công"));
        } catch (ErrorHandler e) {
            return ResponseEntity.status(e.getStatus()).body(new RequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResponse("Đã xảy ra lỗi hệ thống"));
        }
    }


    // ✅ Lấy tất cả sản phẩm (phân trang)
    @GetMapping("all")
    public ResponseEntity<RequestResponse> getAllSanPham(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<SanPham> sanPhamPage = sanPhamService.getAllSanPham(page, size);
            Page<SanPhamDTO> dtoPage = sanPhamPage.map(sanPhamService::toDto);
            PageResponse<SanPhamDTO> response = new PageResponse<>(dtoPage); // Use DTOs in the response
            return ResponseEntity.ok(new RequestResponse(response, "Lấy danh sách sản phẩm thành công"));
        } catch (ErrorHandler e) {
            return ResponseEntity.status(e.getStatus()).body(new RequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResponse("Đã xảy ra lỗi hệ thống"));
        }
    }
}
