package org.wisdom.oc01.config;

/**
 * Class chứa danh sách các URL API được phân quyền truy cập
 * theo từng role trong hệ thống (Anonymous, User, Student, Admin).
 */
public class APIURL {

    // ==========================
    // Anonymous: Không cần token
    // ==========================

    // Các API GET cho người dùng không đăng nhập (public)
    public static final String[] URL_ANONYMOUS_GET = {
            "/api/test/test",
            "/api/danh-muc/all",                // Lấy tất cả danh mục
            "/api/danh-muc/{id}",               // Lấy chi tiết danh mục theo ID
            "/api/danh-muc-dich-vu/all",        // Lấy tất cả danh mục dịch vụ
            "/api/danh-muc-dich-vu/{id}",       // Lấy chi tiết danh mục dịch vụ theo ID
            "/api/san-pham/all",                // Lấy tất cả sản phẩm (phân trang)
            "/api/san-pham/{id}",               // Lấy chi tiết sản phẩm theo ID
            "/api/dich-vu/all",                 // Lấy tất cả dịch vụ
            "/api/dich-vu/{id}",                // Lấy chi tiết dịch vụ theo ID
            "/sanpham/{filename:.+}",           // Lấy ảnh sản phẩm
            "/dichvu/{filename:.+}",             // Lấy ảnh dịch vụ
            "/api/danh-muc-video/{id}",         // lấy danh mục video theo id
            "/api/danh-muc-video/all",           //Lấy tất cả danh mục video
            "/api/video/{id}",                  //lấy video theo id
            "/api/video/all"                    //lấy tất cả video
    };

    // Các API POST cho người dùng không đăng nhập (public)
    public static final String[] URL_ANONYMOUS_POST = {
            "/api/account/login",                // Đăng nhập tài khoản
            "/api/account/change-password",      // Đổi mật khẩu (trong trường hợp người dùng đã xác thực trước đó)
            "/api/account/register/user",        // Đăng ký tài khoản cho người làm việc
            "/api/account/register/student",     // Đăng ký tài khoản cho nhà tuyển dụng
            "/api/account/forgot_password/**",   // Gửi yêu cầu quên mật khẩu
            "/api/account/send-reset-token",     // Gửi token reset mật khẩu qua email
            "/api/account/reset-password",       // Đặt lại mật khẩu bằng token

    };

    // Các API PUT cho người dùng không đăng nhập
    public static final String[] URL_ANONYMOUS_PUT = {
            "/api/account/change-password",      // Đổi mật khẩu khi quên mật khẩu
            "/api/account/reset-password"        // Đặt lại mật khẩu
    };

    // Các API DELETE cho người dùng không đăng nhập (hiện không có)
    public static final String[] URL_ANONYMOUS_DELETE = {};

    // ================
    // Role: USER
    // ================
    public static final String[] URL_USER_GET = {
            // Chưa định nghĩa
    };
    public static final String[] URL_USER_POST = {
            // Chưa định nghĩa
    };
    public static final String[] URL_USER_PUT = {
            // Chưa định nghĩa
    };
    public static final String[] URL_USER_DELETE = {
            // Chưa định nghĩa
    };

    // ================
    // Role: STUDENT
    // ================
    public static final String[] URL_STUDENT_GET = {
            "/api/video/getVideoOfStudent"
    };
    public static final String[] URL_STUDENT_POST = {
            "/api/video/register/{id}",  //đăng ký video

    };
    public static final String[] URL_STUDENT_PUT = {
            // Chưa định nghĩa
    };
    public static final String[] URL_STUDENT_DELETE = {
            // Chưa định nghĩa
    };

    // ================
    // Role: ADMIN
    // ================

    // Các API GET dành riêng cho Admin
    public static final String[] URL_ADMIN_GET = {
            // Chưa định nghĩa, nếu cần Admin có quyền đọc riêng thì thêm vào đây

    };

    // Các API POST dành riêng cho Admin
    public static final String[] URL_ADMIN_POST = {
            "/api/danh-muc/create",             // Tạo danh mục mới
            "/api/danh-muc-dich-vu/create",     // Tạo danh mục dịch vụ mới
            "/api/san-pham/create",             // Tạo sản phẩm mới
            "/api/dich-vu/create",              // Tạo dịch vụ mới
            "/api/danh-muc-video/create",       //Tạo danh mục video mới
            "/api/video/create",                 //Tạo video
            "/api/video/ResponseRegister/{id}" //Phản hồi đănng ký video
    };

    // Các API PUT dành riêng cho Admin
    public static final String[] URL_ADMIN_PUT = {
            "/api/danh-muc/update/{id}",        // Cập nhật danh mục theo ID
            "/api/danh-muc-dich-vu/update/{id}",// Cập nhật danh mục dịch vụ theo ID
            "/api/san-pham/update/{id}",        // Cập nhật sản phẩm theo ID
            "/api/dich-vu/update/{id}",         // Cập nhật dịch vụ theo ID
            "/api/danh-muc-video/update/{id}",  // Cập nhật danh mục video theo ID
            "/api/video/update/{id}"            // Cập nhật video theo ID
    };

    // Các API DELETE dành riêng cho Admin
    public static final String[] URL_ADMIN_DELETE = {
            "/api/danh-muc/delete/{id}",        // Xoá danh mục theo ID
            "/api/danh-muc-dich-vu/delete/{id}",// Xoá danh mục dịch vụ theo ID
            "/api/san-pham/delete/{id}",        // Xoá sản phẩm theo ID
            "/api/dich-vu/delete/{id}",         // Xoá dịch vụ theo ID
            "/api/danh-muc-video/delete/{id}",  //xóa danh mục video theo ID
            "/api/video/delete/{id}"            //xóa video theo ID
    };
}
