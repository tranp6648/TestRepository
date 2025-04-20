package org.wisdom.oc01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wisdom.oc01.dto.RequestResponse;
import org.wisdom.oc01.dto.request.DanhMucDTO;
import org.wisdom.oc01.dto.response.PageResponse;
import org.wisdom.oc01.entity.DanhMuc;
import org.wisdom.oc01.exception.ExceptionResponse;
import org.wisdom.oc01.service.DanhMucService;

@RestController
@RequestMapping("/api/danh-muc")
public class DanhMucController {

    @Autowired
    private DanhMucService danhMucService;

    // Tạo danh mục
    @PostMapping("/create")
    public ResponseEntity<?> createDanhMuc(@RequestBody DanhMucDTO danhMucDTO) {
        try {
            danhMucService.createDanhMuc(danhMucDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new RequestResponse("Danh mục đã được tạo thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ExceptionResponse("Lỗi khi tạo danh mục: " + e.getMessage()));
        }
    }

    // Cập nhật danh mục
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDanhMuc(@PathVariable int id, @RequestBody DanhMucDTO danhMucDTO) {
        try {
            danhMucService.updateDanhMuc(id, danhMucDTO);
            return ResponseEntity.ok(new RequestResponse("Cập nhật danh mục thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ExceptionResponse("Lỗi khi cập nhật danh mục: " + e.getMessage()));
        }
    }

    // Xóa danh mục
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDanhMuc(@PathVariable int id) {
        try {
            danhMucService.deleteDanhMuc(id);
            return ResponseEntity.ok(new RequestResponse("Xóa danh mục thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ExceptionResponse("Lỗi khi xóa danh mục: " + e.getMessage()));
        }
    }

    // Lấy danh mục theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getDanhMucById(@PathVariable int id) {
        try {
            DanhMuc danhMuc = danhMucService.getDanhMucById(id);
            DanhMucDTO dto = danhMucService.toDto(danhMuc); // Convert the entity to DTO
            return ResponseEntity.ok(new RequestResponse(dto, "Lấy danh sách danh mục thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ExceptionResponse("Không tìm thấy danh mục: " + e.getMessage()));
        }
    }


    // Lấy danh sách danh mục có phân trang
    @GetMapping("/all")
    public ResponseEntity<?> getAllDanhMuc(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Page<DanhMuc> danhMucPage = danhMucService.getAllDanhMuc(page, size);
            Page<DanhMucDTO> dtoPage = danhMucPage.map(danhMucService::toDto); // Map entities to DTOs
            PageResponse<DanhMucDTO> response = new PageResponse<>(dtoPage); // Use DTOs in the response
            return ResponseEntity.ok(new RequestResponse(response, "Lấy danh sách danh mụcthành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ExceptionResponse("Lỗi khi lấy danh sách danh mục: " + e.getMessage()));
        }
    }


}
