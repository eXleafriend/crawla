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

    private Document document;

    private RakutenDocumentParser parser;

    @Before
    public void setUp() throws IOException {

        String file = "rakuten-1.html";
        String baseUri = "http://review.travel.rakuten.co.jp/hotel/voice/74664/";

        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream in = classLoader.getResourceAsStream(file);
        document = Jsoup.parse(in, "UTF-8", baseUri);
        parser = new RakutenDocumentParser();

    }

    @Test
    public void testExtractName() {

        // When
        String name = parser.extractName(document);

        // Then
        assertThat(name, is("お台場　大江戸温泉物語"));

    }

    @Test
    public void testCountPages() {

        // When
        int pageCount = parser.countPages(document);

        // Then
        assertThat(pageCount, is(9));

    }

    @Test
    public void testNextPage() {

        // Given
        String url = "http://travel.rakuten.co.jp/HOTEL/137490/review.html";
        String made;

        // When
        made = parser.makeUrl(url, 2);
        // Then
        assertThat(made, is(
                "http://review.travel.rakuten.co.jp/hotel/voice/137490/?f_next=20"));

    }

    @Test
    public void testParseDocument() throws IOException {

        // When
        List<AnonymousReview> comments = parser.parseDocument(document);

        // Then
        assertThat(comments.size(), is(20));
        AnonymousReview anonymous = comments.get(0);
        assertThat(anonymous, is(not(instanceOf(Review.class))));
        assertThat(anonymous.getSubject(), is(nullValue()));
        assertThat(anonymous.getBody(), is("駅から近くて便利"));

        Review comment = (Review) comments.get(1);
        assertThat(comment.getGender(), is(Gender.MALE));
        assertThat(comment.getAge(), is(40));
        assertThat(comment.getSubject(), is(nullValue()));
        assertThat(comment.getBody(), is(
                "週末出張（東京ビッグサイト）だったので1泊しました。\n気軽に泊れて温泉があり・・・イイ感じでした。\n黒船キャビン内、贅沢を言えば座ってテレビが見れるくらいの高さ（天井）が欲しいかな！"));

    }

}
