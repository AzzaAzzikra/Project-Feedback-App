package projectpbo;


import projectpbo.client.FeedbackApp;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectPBO {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Set look and feel based on system
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(ProjectPBO.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Launch your Feedback application
                FeedbackApp.main(args);
            }
        });
    }
}