package org.wisdom.oc01.dto.request;

import lombok.Data;

@Data
public class DichVuDTO {
    private int id;
    private String tenDichVu;
    private int gia;
    private String hinhAnh;
    private String moTa;
    private int danhMucDichVuId;
}
