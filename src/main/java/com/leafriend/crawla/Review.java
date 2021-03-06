package com.leafriend.crawla;

public class Review extends AnonymousReview {

    private final int age;

    private final Gender gender;

    public Review(final int age, final Gender gender, final String subject,
            final String body) {
        super(subject, body);
        this.age = age;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

}
