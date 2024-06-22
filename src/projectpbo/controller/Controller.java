package projectpbo.controller;


import projectpbo.model.Model;
import projectpbo.model.ModelUser;

import java.util.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Controller {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public class SessionManager {
        private static String currentUsername;

        public static String getCurrentUsername() {
            return currentUsername;
        }

        public static void setCurrentUsername(String username) {
            currentUsername = username;
        }

        public static void clearSession() {
            currentUsername = null;
        }
    }

    public boolean registerUser(ModelUser user) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            oos.writeUTF("REGISTER_USER");
            oos.writeObject(user);
            oos.flush();

            return ois.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validateUser(ModelUser user) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            oos.writeUTF("VALIDATE_USER");
            oos.writeObject(user);
            oos.flush();

            String username = ois.readUTF();
            if (username != null && !username.isEmpty()) {
                SessionManager.setCurrentUsername(username);
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addFeedback(Model feedback) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            oos.writeUTF("ADD_FEEDBACK");
            oos.writeObject(feedback);
            oos.flush();

            return ois.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Model> getFeedbackEntries() {
        List<Model> feedbackEntries = null;
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            oos.writeUTF("GET_FEEDBACK");
            oos.flush();

            feedbackEntries = (List<Model>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return feedbackEntries;
    }
}
