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

    @Test
    public void testParseDocument() throws IOException {

        // Given

        String file = "jalan-1.html";
        String baseUri = "http://www.jalan.net/yad319550/kuchikomi/?yadNo=319550&dateUndecided=1&screenId=UWW3701&idx=0&smlCd=137102&distCd=01";

        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream in = classLoader.getResourceAsStream(file);
        Document document = Jsoup.parse(in, "UTF-8", baseUri);
        JalanDocumentParser parser = new JalanDocumentParser();

        // When
        List<Comment> comments = parser.parseDocument(document);

        // Then
        assertThat(comments.size(), is(30));
        Comment comment = comments.get(1);
        assertThat(comment.getGender(), is(Gender.MALE));
        assertThat(comment.getAge(), is(20));
        assertThat(comment.getSubject(), is("予想以上に良かったです"));
        assertThat(comment.getBody(), is("値段的にどうかなと心配でしたが、意外に清潔感やサービスが充実してました。\n特にお風呂は店名に使われているだけのことはあり、大人数で入れるものがいくつもあり、温度もちょうどよく気持ち良かったです。"));

    }

}
