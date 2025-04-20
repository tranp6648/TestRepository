package org.wisdom.oc01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wisdom.oc01.repository.DanhMucDichVuRepository;

@RequestMapping("/api/test")
@RestController
public class TestAndFixBugController {
    @Autowired
    private DanhMucDichVuRepository danhMucDichVuRepository;

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(danhMucDichVuRepository.findAll());
    }

}
