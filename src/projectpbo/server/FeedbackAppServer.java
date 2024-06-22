package projectpbo.server;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import projectpbo.model.Model;
import projectpbo.database.Database;
import projectpbo.model.ModelUser;

public class FeedbackAppServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server dimulai pada port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {

            String action = ois.readUTF();
            switch (action) {
                case "ADD_FEEDBACK":
                    Model feedbackModel = (Model) ois.readObject();
                    boolean addResult = addFeedbackToDatabase(feedbackModel);
                    oos.writeBoolean(addResult);
                    oos.flush();
                    break;

                case "GET_FEEDBACK":
                    List<Model> feedbackEntries = getFeedbackFromDatabase();
                    oos.writeObject(feedbackEntries);
                    oos.flush();
                    break;

                case "REGISTER_USER":
                    ModelUser newUser = (ModelUser) ois.readObject();
                    boolean registerResult = registerUserInDatabase(newUser);
                    oos.writeBoolean(registerResult);
                    oos.flush();
                    break;

                case "VALIDATE_USER":
                    ModelUser user = (ModelUser) ois.readObject();
                    boolean validateResult = validateUserCredentials(user);
                    if (validateResult) {
                        oos.writeUTF(user.getUsername());
                        oos.flush();
                    } else {
                        oos.writeUTF("");
                        oos.flush();
                    }
                    break;

                default:
                    oos.writeUTF("UNKNOWN_ACTION");
                    oos.flush();
                    break;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean addFeedbackToDatabase(Model feedback) {
        String sql = "INSERT INTO Feedbacks(username, feedback) VALUES(?, ?)";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, feedback.getUsername());
            pstmt.setString(2, feedback.getFeedback());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<Model> getFeedbackFromDatabase() {
        List<Model> feedbackEntries = new ArrayList<>();
        String sql = "SELECT username, feedback FROM Feedbacks";

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String username = rs.getString("username");
                String feedback = rs.getString("feedback");
                feedbackEntries.add(new Model(username, feedback));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedbackEntries;
    }

    private boolean registerUserInDatabase(ModelUser user) {
        String sql = "INSERT INTO Users(username, password) VALUES(?, ?)";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean validateUserCredentials(ModelUser user) {
        String sql = "SELECT username FROM Users WHERE username = ? AND password = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
