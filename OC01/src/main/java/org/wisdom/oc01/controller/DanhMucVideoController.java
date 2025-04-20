package org.wisdom.oc01.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wisdom.oc01.dto.RequestResponse;
import org.wisdom.oc01.dto.request.DanhmucVideoDTO;
import org.wisdom.oc01.dto.response.PageResponse;
import org.wisdom.oc01.entity.DanhMucVideo;
import org.wisdom.oc01.exception.ExceptionResponse;
import org.wisdom.oc01.service.DanhMucVideoService;

@RestController
@RequestMapping("/api/danh-muc-video")
@RequiredArgsConstructor
public class DanhMucVideoController {
    @Autowired
    private DanhMucVideoService danhMucVideoService;

    //Tạo danh mục video
    @PostMapping("/create")
    public ResponseEntity<?> createDanhMucVideo(@RequestBody DanhmucVideoDTO danhmucVideoDTO) {
        try {
            danhMucVideoService.createDanhMucVideo(danhmucVideoDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new RequestResponse("Danh mục video đã được tạo thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ExceptionResponse("Lỗi khi tạo danh mục video: " + e.getMessage()));
        }
    }

    //Cập nhật danh mục video
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDanhmucvideo(@RequestBody DanhmucVideoDTO danhmucVideoDTO, @PathVariable int id) {
        try {
            danhMucVideoService.updateDanhMucVideo(id, danhmucVideoDTO);
            return ResponseEntity.ok(new RequestResponse("Cập nhật danh mục video thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ExceptionResponse("Lỗi khi cập nhật danh mục video: " + e.getMessage()));
        }
    }

    //xóa danh mục video
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDanhmucvideo(@PathVariable int id) {
        try {
            danhMucVideoService.deleteDanhMucVideo(id);
            return ResponseEntity.ok(new RequestResponse("Xóa danh mục video thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ExceptionResponse("Lỗi khi xóa danh mục video: " + e.getMessage()));
        }
    }

    @GetMapping("/all")
    // Lấy danh sách danh mục video có phân trang
    public ResponseEntity<?> getAllDanhMucVideo(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        try {
            Page<DanhMucVideo> danhMucVideoPage = danhMucVideoService.getAllDanhMucVideo(page, size);
            Page<DanhmucVideoDTO> dtoPage = danhMucVideoPage.map(danhMucVideoService::todo);
            PageResponse<DanhmucVideoDTO> response = new PageResponse<>(dtoPage);
            return ResponseEntity.ok(new RequestResponse(response, "Lấy danh sách danh mục video thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ExceptionResponse("Lỗi khi lấy danh sách danh mục:" + e.getMessage()));
        }

    }

    // Lấy danh mục video theo Id
    @GetMapping("/{id}")
    public ResponseEntity<?> getDanhMucVideoById(@PathVariable int id) {
        try {
            DanhMucVideo danhMucVideo = danhMucVideoService.getDanhMucVideoById(id);
            DanhmucVideoDTO danhmucVideoDTO = danhMucVideoService.todo(danhMucVideo);
            return ResponseEntity.ok(new RequestResponse(danhmucVideoDTO, "Lấy danh sách danh mục video thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ExceptionResponse("Không tìm thấy danh mục Video: " + e.getMessage()));
        }
    }
}
