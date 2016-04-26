package com.leafriend.crawla;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JalanDocumentParser implements DocumentParser {

    @Override
    public List<Comment> parseDocument(Document document) {

        List<Comment> comments = new ArrayList<>();

        Elements items = document.getElementsByClass("kuchikomi-list-cassette");
        for (Element item : items) {

            Element userName = item.getElementsByClass("user-name").get(0);
            String[] frags = userName.childNodes().get(4).toString().split("/");
            Gender gender = Gender.parse(frags[0].trim());
            int age = DocumentParser.parseAge(frags[1].trim());

            Element contents = userName.parent().nextElementSibling();

            String subject = contents.child(0).text();
            String body = contents.childNode(5).toString();
            Comment comment = new Comment(age, gender, subject, body);
            comments.add(comment);
        }

        return comments;

    }

}
