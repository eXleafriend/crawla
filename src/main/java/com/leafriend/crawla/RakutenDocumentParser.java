package com.leafriend.crawla;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class RakutenDocumentParser implements DocumentParser {

    @Override
    public String extractName(Document document) {
        Element div = document.getElementById("RthNameArea");
        return div.text();
    }

    @Override
    public int countPages(Document document) {
        Element pages = document.getElementsByClass("pagingNumber").get(0);
        List<Node> children = pages.childNodes();
        children = pages.child(0).childNodes();
        String text = ((Element) children.get(children.size() - 2)).text();
        // text = text.substring(1, text.length() - 4);
        return Integer.parseInt(text);

    }

    @Override
    public String makeUrl(String url, int page) {
        String[] tokens = url.split("/");
        return "http://review.travel.rakuten.co.jp/hotel/voice/" + tokens[4]
                + "/?f_next=" + ((page - 1) * 20);
    }

    @Override
    public List<AnonymousReview> parseDocument(Document document) {

        List<AnonymousReview> reviews = new ArrayList<>();

        Elements items = document.getElementsByClass("commentBox");
        for (Element item : items) {

            Element userName = item.getElementsByClass("user").get(0);
            boolean isAnonymous = userName.childNodeSize() == 1;

            String subject = null;

            Element contents = item.getElementsByClass("commentSentence")
                    .get(0);
            String body = contents.childNode(0).toString();
            for (int i = 1; i < contents.childNodeSize(); i++)
                body += "\n" + contents.childNode(i).toString();
            body += "\n";
            body = body.replaceAll("<br>\n", "").trim();

            AnonymousReview review;
            if (isAnonymous) {
                review = new AnonymousReview(subject, body);
            } else {
                String ageGender = userName.childNodes().get(2).toString();
                ageGender = ageGender.trim().substring(1,
                        ageGender.length() - 3);
                String[] frags = ageGender.split("/");
                int age = DocumentParser.parseAge(frags[0].trim());
                Gender gender = Gender.parse(frags[1].trim());
                review = new Review(age, gender, subject, body);
            }
            reviews.add(review);
        }

        return reviews;

    }

}
