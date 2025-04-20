package org.wisdom.oc01.dto.request;


import lombok.Data;

@Data
public class SanPhamDTO {
    private int idSanPham;
    private String tenSanPham;
    private int gia;
    private String hinhAnh;
    private String moTa;
    private int danhMucId;
}
