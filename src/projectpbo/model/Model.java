package projectpbo.model;

import java.io.Serializable;

public class Model implements Serializable {
    private int id;
    private String username;
    private String feedback;

    public Model(String username, String feedback) {
        this.username = username;
        this.feedback = feedback;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
