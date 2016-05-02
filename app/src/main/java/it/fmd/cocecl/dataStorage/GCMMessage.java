package it.fmd.cocecl.dataStorage;

import java.io.Serializable;

public class GCMMessage implements Serializable {
    String id, title, message, createdAt;

    public GCMMessage() {
    }

    public GCMMessage(String id, String title, String message, String createdAt) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
