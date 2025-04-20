package org.wisdom.oc01.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.wisdom.oc01.Enum.Status;
import org.wisdom.oc01.config.JwtService;
import org.wisdom.oc01.dto.request.VideoDTO;
import org.wisdom.oc01.entity.Account;
import org.wisdom.oc01.entity.DanhMucVideo;
import org.wisdom.oc01.entity.HocVienDangKyVideo;
import org.wisdom.oc01.entity.Video;
import org.wisdom.oc01.exception.ErrorHandler;
import org.wisdom.oc01.generic.GeneralService;
import org.wisdom.oc01.repository.AccountRepository;
import org.wisdom.oc01.repository.DanhMucVideoRepository;
import org.wisdom.oc01.repository.HocVienDangKyVideoRepository;
import org.wisdom.oc01.repository.VideoRepository;
import org.wisdom.oc01.service.VideoService;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private HocVienDangKyVideoRepository hocVienDangKyVideoRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private DanhMucVideoRepository danhMucVideoRepository;
    @Autowired
    private GeneralService generalService;

    //Tạo
    @Override
    public Video createVideo(VideoDTO video,MultipartFile file) {
        Video videoEntity = new Video();
        return saveorUpdateVideo(videoEntity, video,file);
    }

    @Override
    public Video updateVideo(int id, VideoDTO video,MultipartFile file) {
        Video videoDto = videoRepository.findById(id)
                .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "Không tìm thấy Video có ID: " + id));
        return saveorUpdateVideo(videoDto, video,file);
    }

    @Override
    public void deleteVideo(int id) {
        if (!videoRepository.existsById(id)) {
            throw new ErrorHandler(HttpStatus.NOT_FOUND, "video không tồn tại");
        }
        videoRepository.deleteById(id);
    }

    @Override
    public Video getVideoById(int id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "Không tìm thấy video"));
    }

    @Override
    public VideoDTO todo(Video video) {
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setId(video.getIdVideo());
        videoDTO.setTenVideo(video.getTenVideo());
        videoDTO.setDanhMucVideoId(video.getDanhMucVideo().getIdDanhMucVideo());
        videoDTO.setMota(video.getMoTa());
        videoDTO.setCreatedAt(video.getCreatedAt());
        return videoDTO;
    }

    @Override
    public Page<Video> getAllVideo(int page, int size) {
        return videoRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public HocVienDangKyVideo dangkyVideo(String token, int idVideo) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String username = jwtService.extractUsername(token);
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "Không tìm thấy học viên"));
        Video video = videoRepository.findById(idVideo)
                .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "Không tìm thấy video"));
        HocVienDangKyVideo hocVienDangKyVideo = new HocVienDangKyVideo();
        hocVienDangKyVideo.setAccount(account);
        hocVienDangKyVideo.setVideo(video);
        return hocVienDangKyVideoRepository.save(hocVienDangKyVideo);
    }

    //Phản hồi đăng ký video
    @Override
    public HocVienDangKyVideo ResponseRegister(int id, Status status) {
        Optional<HocVienDangKyVideo>optional=hocVienDangKyVideoRepository.findById(id);
        if (optional.isPresent()) {
            throw new RuntimeException("Không tìm thấy đăng ký video với ID: " + id);
        }
        HocVienDangKyVideo hocVienDangKyVideo=optional.get();
        hocVienDangKyVideo.setStatus(status);
        return hocVienDangKyVideoRepository.save(hocVienDangKyVideo);
    }

    @Override
    public Page<VideoDTO> findVideOfStudent(int page, int size, String token) {
        if(token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String username = jwtService.extractUsername(token);
        Account account=accountRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Username: " + username));
        Pageable pageable = PageRequest.of(page, size);
        return videoRepository.findRegisteredVideos(account.getIdAccount(), pageable);
    }

    private Video saveorUpdateVideo(Video video, VideoDTO videoDTO, MultipartFile multipartFile) {
        video.setTenVideo(videoDTO.getTenVideo());
        video.setMoTa(videoDTO.getMota());
        video.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        DanhMucVideo danhMucVideo = danhMucVideoRepository.findById(videoDTO.getDanhMucVideoId())
                .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "Không tìm thấy danh mục video với ID: " + videoDTO.getDanhMucVideoId()));
        video.setDanhMucVideo(danhMucVideo);
        if (multipartFile != null && !multipartFile.isEmpty()) {
            try {
                String filePath = generalService.saveFile(multipartFile, "video/");
                video.setNoiDung(filePath);
            } catch (IOException e) {
                throw new ErrorHandler(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi khi lưu video: " + e.getMessage());
            }
        }
        return videoRepository.save(video);
    }
}
