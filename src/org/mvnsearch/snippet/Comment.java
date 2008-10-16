package org.mvnsearch.snippet;

import java.util.Date;

/**
 * snippet comment
 *
 * @author linux_china@hotmail.com
 */
public class Comment {
    private String author; //author
    private String subject;  //subject
    private String content;  //content
    private Date createdAt; //created timestamp

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
