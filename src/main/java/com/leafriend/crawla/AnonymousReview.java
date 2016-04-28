package com.leafriend.crawla;

public class AnonymousReview {

    private final String subject;

    private final String body;

    public AnonymousReview(String subject, String body) {
        super();
        this.subject = subject;
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

}
