package com.leafriend.crawla;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RakutenDocumentParser implements DocumentParser {

    @Override
    public List<AnonymousComment> parseDocument(Document document) {

        List<AnonymousComment> comments = new ArrayList<>();

        Elements items = document.getElementsByClass("commentBox");
        for (Element item : items) {

            Element userName = item.getElementsByClass("user").get(0);
            boolean isAnonymous = userName.childNodeSize() == 1;

            String subject = null;

            Element contents = item.getElementsByClass("commentSentence").get(0);
            String body = contents.childNode(0).toString();
            for (int i = 1; i < contents.childNodeSize(); i++)
                body += "\n" + contents.childNode(i).toString();
            body += "\n";
            body = body.replaceAll("<br>\n", "").trim();

            AnonymousComment comment;
            if (isAnonymous) {
                comment = new AnonymousComment(subject, body);
            } else {
                String ageGender = userName.childNodes().get(2).toString();
                ageGender = ageGender.trim().substring(1, ageGender.length() - 3);
                String[] frags = ageGender.split("/");
                int age = DocumentParser.parseAge(frags[0].trim());
                Gender gender = Gender.parse(frags[1].trim());
                comment = new Comment(age, gender, subject, body);
            }
            comments.add(comment);
        }

        return comments;

    }

}