package com.leafriend.crawla;

import java.util.List;

import org.jsoup.nodes.Document;

public interface DocumentParser {

    public List<? extends AnonymousComment> parseDocument(Document document);

    public static int parseAge(final String string) {
        final String numbers;
        if (string.endsWith("代"))
            numbers = string.substring(0, string.length() - 1);
        else
            numbers = string;
        return Integer.parseInt(numbers);
    }

}
