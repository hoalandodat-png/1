import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    // Cấu hình kết nối
    private static final String DB_URL   = "jdbc:postgresql://localhost:5432/rikkei_erp_db";
    private static final String USER     = "postgres";
    private static final String PASSWORD = "your_password"; // đổi theo máy của bạn

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("  KIỂM TRA KẾT NỐI POSTGRESQL - RIKKEI ERP DB");
        System.out.println("==================================================");

        // try-with-resources → tự động đóng Connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {

            System.out.println("[THÀNH CÔNG] Kết nối CSDL PostgreSQL thành công!");
            System.out.println("--------------------------------------------------");

            // Lấy thông tin từ DatabaseMetaData
            DatabaseMetaData meta = conn.getMetaData();

            System.out.println("Tên hệ quản trị CSDL : " + meta.getDatabaseProductName());
            System.out.println("Phiên bản CSDL       : " + meta.getDatabaseProductVersion());
            System.out.println("Tên JDBC Driver      : " + meta.getDriverName());
            System.out.println("Phiên bản Driver     : " + meta.getDriverVersion());

            System.out.println("--------------------------------------------------");

        } catch (SQLException e) {

            System.out.println("[THẤT BẠI] Kết nối CSDL thất bại!");
            System.out.println("--------------------------------------------------");
            System.out.println("Mã lỗi SQLState : " + e.getSQLState());
            System.out.println("Thông báo lỗi   : " + e.getMessage());
            System.out.println("--------------------------------------------------");

            // Gợi ý sửa lỗi theo SQLState
            String state = e.getSQLState();

            if (state == null) {
                System.out.println("Gợi ý: Không thể kết nối tới server. Kiểm tra PostgreSQL có đang chạy không.");
            } else if (state.equals("28P01")) {
                System.out.println("Gợi ý: Sai tên đăng nhập hoặc mật khẩu. Kiểm tra USER và PASSWORD.");
            } else if (state.equals("3D000")) {
                System.out.println("Gợi ý: Database không tồn tại. Hãy tạo database 'rikkei_erp_db'.");
            } else if (state.equals("08001") || state.equals("08006")) {
                System.out.println("Gợi ý: Không thể kết nối tới server. Kiểm tra host và port trong DB_URL.");
            } else {
                System.out.println("Gợi ý: Lỗi không xác định. Liên hệ quản trị viên CSDL.");
            }
        }

        System.out.println("==================================================");
        System.out.println("               KẾT THÚC KIỂM TRA");
        System.out.println("==================================================");
    }
}