package org.wisdom.oc01.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.wisdom.oc01.dto.request.DanhmucVideoDTO;
import org.wisdom.oc01.entity.DanhMucVideo;
import org.wisdom.oc01.repository.DanhMucVideoRepository;
import org.wisdom.oc01.service.DanhMucVideoService;

import java.util.Optional;

@Service
public class DanhMucVideoServiceImpl implements DanhMucVideoService {
    @Autowired
    private DanhMucVideoRepository danhMucVideoRepository;

    //thêm
    @Override
    public DanhMucVideo createDanhMucVideo(DanhmucVideoDTO video) {
        try {
            DanhMucVideo danhMucVideo = new DanhMucVideo();
            danhMucVideo.setTenDanhMuc(video.getTendanhmuc());
            danhMucVideo.setTenDanhMuc(video.getTendanhmuc());
            return danhMucVideoRepository.save(danhMucVideo);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //Cập nhật
    @Override
    public DanhMucVideo updateDanhMucVideo(int id, DanhmucVideoDTO video) {
        try {
            Optional<DanhMucVideo> optional = danhMucVideoRepository.findById(id);
            if (optional.isPresent()) {
                DanhMucVideo danhMucVideo = optional.get();
                danhMucVideo.setTenDanhMuc(video.getTendanhmuc());
                return danhMucVideoRepository.save(danhMucVideo);
            } else {
                throw new RuntimeException("Không tìm thấy danh mục video với id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //Xóa
    @Override
    public void deleteDanhMucVideo(int id) {
        try {
            if (!danhMucVideoRepository.existsById(id)) {
                throw new RuntimeException("Không tìm thấy danh mục video với id: " + id);
            }
            danhMucVideoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //Tìm theo Id
    @Override
    public DanhMucVideo getDanhMucVideoById(int id) {
        try {
            return danhMucVideoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục video với id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Không thể lấy danh mục.");
        }
    }

    @Override
    public DanhmucVideoDTO todo(DanhMucVideo danhMucVideo) {
        DanhmucVideoDTO danhMucVideoDTO = new DanhmucVideoDTO();
        danhMucVideoDTO.setId(danhMucVideo.getIdDanhMucVideo());
        danhMucVideoDTO.setTendanhmuc(danhMucVideo.getTenDanhMuc());
        return danhMucVideoDTO;
    }

    @Override
    public Page<DanhMucVideo> getAllDanhMucVideo(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("idDanhMucVideo").descending());
            return danhMucVideoRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Không thể lấy danh sách danh mục.");
        }
    }
}
