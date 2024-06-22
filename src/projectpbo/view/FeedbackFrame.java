package projectpbo.view;

import projectpbo.controller.Controller;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import projectpbo.controller.Controller.SessionManager;

public class FeedbackFrame extends JFrame {
    private Controller controller;
    private Login loginForm;
    private Register registerForm;
    private Main feedbackForm;
    private AdminPanel adminPanel;
    private CardLayout cardLayout;

    public FeedbackFrame(Controller controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        loginForm = new Login(controller, this);
        registerForm = new Register(controller, this);
        feedbackForm = new Main();
        adminPanel = new AdminPanel(controller);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Feedback Application");
        setMinimumSize(new Dimension(400, 300));
        setPreferredSize(new Dimension(430, 360));
        setResizable(false);
        setLocationRelativeTo(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Exit...");
            }
        });

        add(loginForm, "loginForm");
        add(registerForm, "registerForm");
        add(feedbackForm, "feedbackForm");
        add(adminPanel, "adminPanel");

        pack();
        showLogin();
    }

    public void showLogin() {
        cardLayout.show(getContentPane(), "loginForm");
    }

    public void showRegister() {
        cardLayout.show(getContentPane(), "registerForm");
    }

    public void showFeedbackForm() {
        cardLayout.show(getContentPane(), "feedbackForm");
    }
    
    public void showAdminPanel() {
        cardLayout.show(getContentPane(), "adminPanel");
    }
    
    public void logoutAndRedirect() {
        SessionManager.setCurrentUsername(null);
        showLogin();
        adminPanel.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Controller controller = new Controller();
            FeedbackFrame frame = new FeedbackFrame(controller);
            frame.setVisible(true);
        });
    }
}
