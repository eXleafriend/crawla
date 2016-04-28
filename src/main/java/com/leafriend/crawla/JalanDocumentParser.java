package com.leafriend.crawla;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JalanDocumentParser implements DocumentParser {

    @Override
    public List<Review> parseDocument(Document document) {

        List<Review> reviews = new ArrayList<>();

        Elements items = document.getElementsByClass("kuchikomi-list-cassette");
        for (Element item : items) {

            Element userName = item.getElementsByClass("user-name").get(0);
            String[] frags = userName.childNodes().get(4).toString().split("/");
            Gender gender = Gender.parse(frags[0].trim());
            int age = DocumentParser.parseAge(frags[1].trim());

            Element contents = userName.parent().nextElementSibling();

            String subject = contents.child(0).text();
            String body = contents.childNode(5).toString();
            for (int i = 6; i < contents.childNodeSize(); i++)
                body += "\n" + contents.childNode(i).toString();
            body = body.replaceAll("<br>\n", "").trim();
            Review review = new Review(age, gender, subject, body);
            reviews.add(review);
        }

        return reviews;

    }

}
