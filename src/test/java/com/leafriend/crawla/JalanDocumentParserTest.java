package com.leafriend.crawla;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.*;

public class JalanDocumentParserTest {

    private Document document;

    private JalanDocumentParser parser;

    @Before
    public void setUp() throws IOException {

        String file = "jalan-1.html";
        String baseUri = "http://www.jalan.net/yad319550/kuchikomi/?yadNo=319550&dateUndecided=1&screenId=UWW3701&idx=0&smlCd=137102&distCd=01";

        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream in = classLoader.getResourceAsStream(file);
        document = Jsoup.parse(in, "UTF-8", baseUri);
        parser = new JalanDocumentParser();

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
        assertThat(pageCount, is(2));

    }

    @Test
    public void testNextPage() {

        // Given
        String url = "http://www.jalan.net/yad321110/kuchikomi/?screenId=UWW3001&yadNo=321110&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=241402&distCd=01";
        String made;

        // When
        made = parser.makeUrl(url, 2);
        // Then
        assertThat(made, is(
                "http://www.jalan.net/yad321110/kuchikomi/2.HTML?contHideFlg=1&yadNo=321110&rootCd=7701&dateUndecided=1&minPrice=0&maxPrice=999999&screenId=UWW3701&idx=30&smlCd=241402&callbackHistFlg=1&distCd=01"));

        // When
        made = parser.makeUrl(url, 3);
        // Then
        assertThat(made, is(
                "http://www.jalan.net/yad321110/kuchikomi/3.HTML?contHideFlg=1&yadNo=321110&rootCd=7701&dateUndecided=1&minPrice=0&maxPrice=999999&screenId=UWW3701&idx=60&smlCd=241402&callbackHistFlg=1&distCd=01"));

        // When
        made = parser.makeUrl(url, 4);

        // Then
        assertThat(made, is(
                "http://www.jalan.net/yad321110/kuchikomi/4.HTML?contHideFlg=1&yadNo=321110&rootCd=7701&dateUndecided=1&minPrice=0&maxPrice=999999&screenId=UWW3701&idx=90&smlCd=241402&callbackHistFlg=1&distCd=01"));

    }

    @Test
    public void testParseDocument() throws IOException {

        // When
        List<Review> comments = parser.parseDocument(document);

        // Then
        assertThat(comments.size(), is(30));
        Review comment = comments.get(1);
        assertThat(comment.getGender(), is(Gender.MALE));
        assertThat(comment.getAge(), is(20));
        assertThat(comment.getSubject(), is("予想以上に良かったです"));
        assertThat(comment.getBody(), is(
                "値段的にどうかなと心配でしたが、意外に清潔感やサービスが充実してました。\n特にお風呂は店名に使われているだけのことはあり、大人数で入れるものがいくつもあり、温度もちょうどよく気持ち良かったです。"));

    }

}
