package com.leafriend.crawla;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.*;

public class RakutenDocumentParserTest {

    @Test
    public void testParseDocument() throws IOException {

        // Given

        String file = "rakuten-1.html";
        String baseUri = "http://review.travel.rakuten.co.jp/hotel/voice/74664/";

        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream in = classLoader.getResourceAsStream(file);
        Document document = Jsoup.parse(in, "UTF-8", baseUri);
        RakutenDocumentParser parser = new RakutenDocumentParser();

        // When
        List<AnonymousComment> comments = parser.parseDocument(document);

        // Then
        assertThat(comments.size(), is(20));
        AnonymousComment anonymous = comments.get(0);
        assertThat(anonymous, is(not(instanceOf(Comment.class))));
        assertThat(anonymous.getSubject(), is(nullValue()));
        assertThat(anonymous.getBody(), is("駅から近くて便利"));

        Comment comment = (Comment) comments.get(1);
        assertThat(comment.getGender(), is(Gender.MALE));
        assertThat(comment.getAge(), is(40));
        assertThat(comment.getSubject(), is(nullValue()));
        assertThat(comment.getBody(), is("週末出張（東京ビッグサイト）だったので1泊しました。\n気軽に泊れて温泉があり・・・イイ感じでした。\n黒船キャビン内、贅沢を言えば座ってテレビが見れるくらいの高さ（天井）が欲しいかな！"));

    }

}