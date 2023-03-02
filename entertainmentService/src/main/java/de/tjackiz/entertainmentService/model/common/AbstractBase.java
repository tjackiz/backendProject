package de.tjackiz.entertainmentService.model.common;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public abstract class AbstractBase {

    private UUID id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date timestamp;

    private String createdUser;

    private String lastEditor;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getLastEditor() {
        return lastEditor;
    }

    public void setLastEditor(String lastEditor) {
        this.lastEditor = lastEditor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBase that = (AbstractBase) o;
        return Objects.equals(id, that.id) && Objects.equals(createdDate, that.createdDate) && Objects.equals(timestamp, that.timestamp) && Objects.equals(createdUser, that.createdUser) && Objects.equals(lastEditor, that.lastEditor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdDate, timestamp, createdUser, lastEditor);
    }

    @Override
    public String toString() {
        return "AbstractBase{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", timestamp=" + timestamp +
                ", createdUser='" + createdUser + '\'' +
                ", lastEditor='" + lastEditor + '\'' +
                '}';
    }
}
