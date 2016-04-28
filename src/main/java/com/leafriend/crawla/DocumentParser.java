package com.leafriend.crawla;

import java.util.List;

import org.jsoup.nodes.Document;

public interface DocumentParser {

    public String extractName(Document document);

    public int countPages(Document document);

    public List<? extends AnonymousReview> parseDocument(Document document);

    public String makeUrl(String url, int page);

    public static int parseAge(final String string) {
        final String numbers;
        if (string.endsWith("代"))
            numbers = string.substring(0, string.length() - 1);
        else
            numbers = string;
        return Integer.parseInt(numbers);
    }

}
