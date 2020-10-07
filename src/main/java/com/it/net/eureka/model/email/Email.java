package com.it.net.eureka.model.email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Email {

    @NotNull
    @NotBlank
    private String to;

    @NotNull
    @NotBlank
    private String from;

    @NotNull
    @NotBlank
    private String subject;

    @NotNull
    @NotBlank
    @Size(min = 20, max = 500)
    private String text;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
