package com.leafriend.crawla;

import java.util.List;

import org.jsoup.nodes.Document;

public interface DocumentParser {

    public List<AnonymousComment> parseDocument(Document document);

}
