package com.leafriend.crawla;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JalanDocumentParser implements DocumentParser {

    @Override
    public String extractName(Document document) {
        Element div = document.getElementById("yado_header_hotel_name");
        return div.text();
    }

    @Override
    public int countPages(Document document) {
        String text = document.getElementsByClass("pager_num").get(0).text();
        text = text.substring(1, text.length() - 4);
        return Integer.parseInt(text.split("/")[1]);
    }

    @Override
    public String makeUrl(String url, int page) {
        String[] tokens = url.split("/");
        tokens[5] = page + ".HTML" + tokens[5];
        return tokens[0] + "/" + tokens[1] + "/" + tokens[2] + "/" + tokens[3]
                + "/" + tokens[4] + "/" + tokens[5];
    }

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
