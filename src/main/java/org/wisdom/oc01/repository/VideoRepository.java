package org.wisdom.oc01.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.wisdom.oc01.dto.request.VideoDTO;
import org.wisdom.oc01.entity.Video;
import org.wisdom.oc01.generic.IRepository;

@Repository
public interface VideoRepository extends IRepository<Video, Integer> {
    @Query("SELECT new org.wisdom.oc01.dto.request.VideoDTO(" +
            "a.idVideo, a.tenVideo, a.moTa, a.danhMucVideo.idDanhMucVideo, a.createdAt, b.status) " +
            "FROM Video a " +
            "LEFT JOIN HocVienDangKyVideo b ON a.idVideo = b.video.idVideo AND b.account.idAccount = :id")
    Page<VideoDTO> findRegisteredVideos(@Param("id") Integer id, Pageable pageable);


}
