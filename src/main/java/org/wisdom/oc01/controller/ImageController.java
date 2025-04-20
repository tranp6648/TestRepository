package org.wisdom.oc01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.wisdom.oc01.exception.ErrorHandler;
import org.wisdom.oc01.service.DichVuService;
import org.wisdom.oc01.service.SanPhamService;

@RestController
public class ImageController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private DichVuService dichVuService;

    // ✅ Lấy ảnh sản phẩm (trả đúng định dạng MIME)
    @GetMapping("/sanpham/{filename:.+}")
    public ResponseEntity<byte[]> getSanPhamImage(@PathVariable String filename) {
        try {
            return sanPhamService.getSanPhamImage(filename);
        } catch (ErrorHandler e) {
            return ResponseEntity.status(e.getStatus()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    // ✅ Lấy ảnh dịch vụ (trả đúng định dạng MIME)
    @GetMapping("/dichvu/{filename:.+}")
    public ResponseEntity<byte[]> getDichVuImage(@PathVariable String filename) {
        try {
            return dichVuService.getDichVuImage(filename);
        } catch (ErrorHandler e) {
            return ResponseEntity.status(e.getStatus()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
