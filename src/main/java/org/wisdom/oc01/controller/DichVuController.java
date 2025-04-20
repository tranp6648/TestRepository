package org.wisdom.oc01.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wisdom.oc01.dto.RequestResponse;
import org.wisdom.oc01.dto.request.DichVuDTO;
import org.wisdom.oc01.dto.response.PageResponse;
import org.wisdom.oc01.entity.DichVu;
import org.wisdom.oc01.exception.ErrorHandler;
import org.wisdom.oc01.service.DichVuService;

@RestController
@RequestMapping("/api/dich-vu")
@RequiredArgsConstructor
public class DichVuController {

    private final DichVuService dichVuService;

    // ✅ Tạo dịch vụ mới
    @PostMapping("/create")
    public ResponseEntity<RequestResponse> createDichVu(
            @ModelAttribute DichVuDTO dto,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            dichVuService.createDichVu(dto, file);
            return ResponseEntity.ok(new RequestResponse(dto, "Dịch vụ đã được tạo thành công"));
        } catch (ErrorHandler e) {
            return ResponseEntity.status(e.getStatus()).body(new RequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResponse("Đã xảy ra lỗi hệ thống"));
        }
    }

    // ✅ Cập nhật dịch vụ
    @PutMapping("/update/{id}")
    public ResponseEntity<RequestResponse> updateDichVu(
            @PathVariable int id,
            @ModelAttribute DichVuDTO dto,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            dichVuService.updateDichVu(id, dto, file);
            return ResponseEntity.ok(new RequestResponse(dto, "Dịch vụ đã được cập nhật thành công"));
        } catch (ErrorHandler e) {
            return ResponseEntity.status(e.getStatus()).body(new RequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResponse("Đã xảy ra lỗi hệ thống"));
        }
    }

    // ✅ Xoá dịch vụ
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RequestResponse> deleteDichVu(@PathVariable int id) {
        try {
            dichVuService.deleteDichVu(id);
            return ResponseEntity.ok(new RequestResponse("Dịch vụ đã được xoá thành công"));
        } catch (ErrorHandler e) {
            return ResponseEntity.status(e.getStatus()).body(new RequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResponse("Đã xảy ra lỗi hệ thống"));
        }
    }

    // ✅ Lấy dịch vụ theo ID
    @GetMapping("/{id}")
    public ResponseEntity<RequestResponse> getDichVuById(@PathVariable int id) {
        try {
            DichVu dv = dichVuService.getDichVuById(id);
            DichVuDTO dto = dichVuService.toDto(dv);
            return ResponseEntity.ok(new RequestResponse(dto, "Lấy thông tin dịch vụ thành công"));
        } catch (ErrorHandler e) {
            return ResponseEntity.status(e.getStatus()).body(new RequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResponse("Đã xảy ra lỗi hệ thống"));
        }
    }

    // ✅ Lấy tất cả dịch vụ (phân trang)
    @GetMapping("/all")
    public ResponseEntity<RequestResponse> getAllDichVu(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<DichVu> dichVuPage = dichVuService.getAllDichVu(page, size);
            Page<DichVuDTO> dtoPage = dichVuPage.map(dichVuService::toDto);
            PageResponse<DichVuDTO> response = new PageResponse<>(dtoPage);
            return ResponseEntity.ok(new RequestResponse(response, "Lấy danh sách dịch vụ thành công"));
        } catch (ErrorHandler e) {
            return ResponseEntity.status(e.getStatus()).body(new RequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResponse("Đã xảy ra lỗi hệ thống"));
        }
    }
}
