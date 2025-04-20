package org.wisdom.oc01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wisdom.oc01.dto.RequestResponse;
import org.wisdom.oc01.dto.request.DanhMucDichVuDTO;
import org.wisdom.oc01.dto.response.PageResponse;
import org.wisdom.oc01.entity.DanhMucDichVu;
import org.wisdom.oc01.exception.ExceptionResponse;
import org.wisdom.oc01.service.DanhMucDichVuService;

@RequestMapping("api/danh-muc-dich-vu")
@RestController
public class DanhMucDichVuController {
    @Autowired
    private DanhMucDichVuService danhMucDichVuService;

    // Tạo danh mục
    @PostMapping("/create")
    public ResponseEntity<?> createDanhMucDichVu(@RequestBody DanhMucDichVuDTO danhMucDichVuDTO) {
        try {
            danhMucDichVuService.createDanhMucDichVu(danhMucDichVuDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new RequestResponse("Danh mục đã được tạo thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ExceptionResponse("Lỗi khi tạo danh mục: " + e.getMessage()));
        }
    }

    // Cập nhật danh mục
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDanhMucDichVu(@PathVariable int id, @RequestBody DanhMucDichVuDTO danhMucDichVuDTO) {
        try {
            danhMucDichVuService.updateDanhMucDichVu(id, danhMucDichVuDTO);
            return ResponseEntity.ok(new RequestResponse("Cập nhật danh mục thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ExceptionResponse("Lỗi khi cập nhật danh mục: " + e.getMessage()));
        }
    }

    // Xóa danh mục
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDanhMucDichVu(@PathVariable int id) {
        try {
            danhMucDichVuService.deleteDanhMucDichVu(id);
            return ResponseEntity.ok(new RequestResponse("Xóa danh mục thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ExceptionResponse("Lỗi khi xóa danh mục: " + e.getMessage()));
        }
    }

    // Lấy danh mục theo ID
// Lấy danh mục theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getDanhMucDichVuById(@PathVariable int id) {
        try {
            DanhMucDichVu danhMucDichVu = danhMucDichVuService.getDanhMucDichVuById(id);
            DanhMucDichVuDTO dto = danhMucDichVuService.toDto(danhMucDichVu); // Use 'danhMucDichVu' instead of 'sp'
            return ResponseEntity.ok(new RequestResponse(dto, "Lấy danh sách danh mục dịch vụ thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ExceptionResponse("Không tìm thấy danh mục: " + e.getMessage()));
        }
    }


    // Lấy danh sách danh mục có phân trang
// Lấy danh sách danh mục có phân trang
    @GetMapping("/all")
    public ResponseEntity<?> getAllDanhMucDichVu(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Page<DanhMucDichVu> danhMucDichVuPage = danhMucDichVuService.getAllDanhMucDichVu(page, size);
            Page<DanhMucDichVuDTO> dtoPage = danhMucDichVuPage.map(danhMucDichVuService::toDto); // Map to the correct DTO
            PageResponse<DanhMucDichVuDTO> response = new PageResponse<>(dtoPage); // Use DTOs in the response
            return ResponseEntity.ok(new RequestResponse(response, "Lấy danh sách danh mục dịch vụ thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ExceptionResponse("Lỗi khi lấy danh sách danh mục: " + e.getMessage()));
        }
    }

}
