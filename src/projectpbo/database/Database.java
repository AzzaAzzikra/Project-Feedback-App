package projectpbo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    private static Connection conn = null;

    public static Connection connect() {
        try {
            if (conn == null || conn.isClosed()) {
                String url = "jdbc:sqlite:projectPBO";
                conn = DriverManager.getConnection(url);
                if(conn != null) {
                    logger.info("Terkoneksi ke SQLite.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error konek ke database: " + e.getMessage());
        }
        return conn;
    }

    public static void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                logger.info("Koneksi database ditutup.");
            }
        } catch (SQLException e) {
            logger.error("Gagal menutup koneksi: {}", e.getMessage());
        }
    }
}
