package com.leafriend.crawla;

import java.util.List;

import org.jsoup.nodes.Document;

public interface DocumentParser {

    public List<? extends AnonymousComment> parseDocument(Document document);

}
