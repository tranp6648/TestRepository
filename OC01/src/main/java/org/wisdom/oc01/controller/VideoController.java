package org.wisdom.oc01.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wisdom.oc01.Enum.Status;
import org.wisdom.oc01.dto.RequestResponse;
import org.wisdom.oc01.dto.request.VideoDTO;
import org.wisdom.oc01.dto.response.PageResponse;
import org.wisdom.oc01.entity.Video;
import org.wisdom.oc01.exception.ErrorHandler;
import org.wisdom.oc01.exception.ExceptionResponse;
import org.wisdom.oc01.service.VideoService;

@RestController
@RequestMapping("/api/video")
@RequiredArgsConstructor
public class VideoController {
    @Autowired
    private VideoService videoService;

    //Tạo video
    @PostMapping("/create")
    public ResponseEntity<?> createVideo(@ModelAttribute VideoDTO videoDTO,@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            videoService.createVideo(videoDTO,file);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new RequestResponse("video đã được tạo thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ExceptionResponse("Lỗi khi tạo  video: " + e.getMessage()));
        }
    }
    //lấy video đã đăng ký hoặc chưa đăng ký
    @GetMapping("getVideoOfStudent")
    public ResponseEntity<?>getVideoOfStudent(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestHeader("Authorization") String token){
        try {
            Page<VideoDTO>dtoPage=videoService.findVideOfStudent(page,size,token);
            return ResponseEntity.ok(new RequestResponse(dtoPage,"Danh sách video"));
        }catch (ErrorHandler e) {
            return ResponseEntity.status(e.getStatus()).body(new RequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResponse("Đã xảy ra lỗi hệ thống"));
        }
    }
    //cập nhật video
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVideo(@PathVariable int id, @ModelAttribute VideoDTO videoDTO,@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            videoService.updateVideo(id, videoDTO,file);
            return ResponseEntity.ok(new RequestResponse("Dịch vụ đã được cập nhật thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ExceptionResponse("Lỗi khi cập nhật video: " + e.getMessage()));
        }
    }

    //Xóa video
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable int id) {
        try {
            videoService.deleteVideo(id);
            return ResponseEntity.ok(new RequestResponse("Video đã được xoá thành công"));
        } catch (ErrorHandler e) {
            return ResponseEntity.status(e.getStatus()).body(new RequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResponse("Đã xảy ra lỗi hệ thống"));
        }
    }


    //Phản hồi đăng ký video
    @PutMapping("/ResponseRegister/{id}")
    public ResponseEntity<?> ResponseRegisterVideo(@PathVariable int id, @RequestBody Status status) {
        try {
            videoService.ResponseRegister(id, status);
            return ResponseEntity.ok(new RequestResponse("Phản hồi thành công"));
        } catch (ErrorHandler e) {
            return ResponseEntity.status(e.getStatus()).body(new RequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResponse("Đã xảy ra lỗi hệ thống"));
        }
    }

    //Đăng ký video
    @PostMapping("/register/{id}")
    public ResponseEntity<?> registerVideo(@PathVariable int id, @RequestHeader("Authorization") String authHeader) {
        try {
            videoService.dangkyVideo(authHeader, id);
            return ResponseEntity.ok(new RequestResponse("Đăng ký video thành công"));
        } catch (ErrorHandler e) {
            return ResponseEntity.status(e.getStatus()).body(new RequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResponse("Đã xảy ra lỗi hệ thống"));
        }
    }

    //lấy video theo id
    @GetMapping("/{id}")
    public ResponseEntity<RequestResponse> getVideoById(@PathVariable int id) {
        try {
            Video video = videoService.getVideoById(id);
            VideoDTO dto = videoService.todo(video);
            return ResponseEntity.ok(new RequestResponse(dto, "Lấy thông tin video thành công"));
        } catch (ErrorHandler e) {
            return ResponseEntity.status(e.getStatus()).body(new RequestResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestResponse("Đã xảy ra lỗi hệ thống"));
        }
    }

    // Lấy danh sách  video có phân trang
    @GetMapping("/all")
    public ResponseEntity<?> getAllVideos(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        try {
            Page<Video> videoPage = videoService.getAllVideo(page, size);
            Page<VideoDTO> dtoPage = videoPage.map(videoService::todo);
            PageResponse<VideoDTO> response = new PageResponse<>(dtoPage);
            return ResponseEntity.ok(new RequestResponse(response, "Lấy danh sách video thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ExceptionResponse("Lỗi khi lấy danh sách video:" + e.getMessage()));
        }
    }
}
