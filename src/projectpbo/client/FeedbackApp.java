package projectpbo.client;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

import projectpbo.controller.Controller;
import projectpbo.database.Database;
import projectpbo.view.FeedbackFrame;

public class FeedbackApp {

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = Database.connect();
            if (conn == null) {
                System.out.println("Gagal konek ke database.");
                return;
            }

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Controller controller = new Controller();
                        FeedbackFrame feedbackFrame = new FeedbackFrame(controller);
                        feedbackFrame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error menutup koneksi: " + e.getMessage());
                }
            }
        }
    }
}
