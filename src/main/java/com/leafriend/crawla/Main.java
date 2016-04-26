package com.leafriend.crawla;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

    private static ClassLoader classLoader = Main.class.getClassLoader();

    public static List<? extends AnonymousComment> parse(DocumentParser parser,
            String baseUri, String file) throws IOException {

        InputStream in = classLoader.getResourceAsStream(file);
        Document document = Jsoup.parse(in, "UTF-8", baseUri);
        List<? extends AnonymousComment> comments = parser
                .parseDocument(document);
        return comments;

    }

}
