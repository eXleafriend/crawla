package com.leafriend.crawla;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.function.Consumer;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

    private static ClassLoader classLoader = Main.class.getClassLoader();

    public static void main(String[] args)
            throws ClientProtocolException, IOException {

        String[] xxx = {	
                "大江戸温泉物語 伊勢志摩", "http://www.jalan.net/yad321110/kuchikomi/?screenId=UWW3001&yadNo=321110&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=241402&distCd=01", "http://travel.rakuten.co.jp/HOTEL/137490/review.html", 
                "大江戸温泉物語 ホテルレオマの森", "http://www.jalan.net/yad316332/kuchikomi/?screenId=UWW3001&yadNo=316332&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=370507&distCd=01", "http://travel.rakuten.co.jp/HOTEL/28098/review.html", 
                "大江戸温泉物語 那須塩原温泉 かもしか荘", "http://www.jalan.net/yad385164/kuchikomi/?screenId=UWW3001&yadNo=385164&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=080602&distCd=01", "http://travel.rakuten.co.jp/HOTEL/74518/review.html", 
                "大江戸温泉物語 会津神指温泉 あいづ", "http://www.jalan.net/yad300184/kuchikomi/?screenId=UWW3001&yadNo=300184&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=071712&distCd=01", "http://travel.rakuten.co.jp/HOTEL/68662/review.html", 
                "大江戸温泉物語 鬼怒川温泉 ホテル鬼怒川御苑", "http://www.jalan.net/yad324785/kuchikomi/?screenId=UWW3001&yadNo=324785&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=080902&distCd=01", "http://travel.rakuten.co.jp/HOTEL/8842/review.html", 
                "大江戸温泉物語 君津の森", "http://www.jalan.net/yad369140/kuchikomi/?screenId=UWW3001&yadNo=369140&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=122002&distCd=01", "http://travel.rakuten.co.jp/HOTEL/74520/review.html", 
                "大江戸温泉物語 石和温泉 ホテル新光", "http://www.jalan.net/yad370872/kuchikomi/?screenId=UWW3001&yadNo=370872&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=150205&distCd=01", "http://travel.rakuten.co.jp/HOTEL/59642/review.html", 
                "大江戸温泉物語 那須塩原温泉 ホテルニュー塩原", "http://www.jalan.net/yad341037/kuchikomi/?screenId=UWW3001&yadNo=341037&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=080602&distCd=01", "http://travel.rakuten.co.jp/HOTEL/2477/review.html", 
                "大江戸温泉物語 松島温泉 ホテル壮観", "http://www.jalan.net/yad320167/kuchikomi/?screenId=UWW3001&yadNo=320167&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=040502&distCd=01", "http://travel.rakuten.co.jp/HOTEL/4665/review.html", 
                "大江戸温泉物語 熱海温泉 あたみ", "http://www.jalan.net/yad385072/kuchikomi/?screenId=UWW3001&yadNo=385072&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=210202&distCd=01", "http://travel.rakuten.co.jp/HOTEL/136832/review.html", 
                "大江戸温泉物語 あわら温泉 あわら", "http://www.jalan.net/yad370018/kuchikomi/?screenId=UWW3001&yadNo=370018&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=200202&distCd=01", "http://travel.rakuten.co.jp/HOTEL/130020/review.html", 
                "大江戸温泉物語 伊香保温泉 伊香保", "http://www.jalan.net/yad341042/kuchikomi/?screenId=UWW3001&yadNo=341042&stayMonth=&stayYear=&stayDay=&maxPrice=999999&dateUndecided=1&rootCd=7701&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=091102&distCd=01", "http://travel.rakuten.co.jp/HOTEL/68663/review.html", 
                "大江戸温泉物語 城崎温泉 きのさき", "http://www.jalan.net/yad335624/kuchikomi/?screenId=UWW3001&yadNo=335624&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=281102&distCd=01", "http://travel.rakuten.co.jp/HOTEL/130498/review.html", 
                "大江戸温泉物語 箕面温泉 箕面観光ホテル", "http://www.jalan.net/yad397608/kuchikomi/?screenId=UWW3001&yadNo=397608&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=271405&distCd=01", "http://travel.rakuten.co.jp/HOTEL/29844/review.html", 
                "大江戸温泉物語 山代温泉 加賀の本陣 山下家", "http://www.jalan.net/yad328758/kuchikomi/?screenId=UWW3001&yadNo=328758&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=192605&distCd=01", "http://travel.rakuten.co.jp/HOTEL/5566/review.html", 
                "大江戸温泉物語 鬼怒川温泉 鬼怒川観光ホテル", "http://www.jalan.net/yad343637/kuchikomi/?screenId=UWW3001&yadNo=343637&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=080902&distCd=01", "http://travel.rakuten.co.jp/HOTEL/67986/review.html", 
                "大江戸温泉物語 土肥温泉 土肥マリンホテル", "http://www.jalan.net/yad341781/kuchikomi/?screenId=UWW3001&yadNo=341781&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=211402&distCd=01", "http://travel.rakuten.co.jp/HOTEL/145058/review.html", 
                "大江戸温泉物語 下呂温泉 下呂", "http://www.jalan.net/yad391082/kuchikomi/?screenId=UWW3001&yadNo=391082&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=220502&distCd=01", "http://travel.rakuten.co.jp/HOTEL/68666/review.html", 
                "大江戸温泉物語 天下泰平の湯", "http://www.jalan.net/yad394587/kuchikomi/?screenId=UWW3001&yadNo=394587&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=212602&distCd=01", "http://travel.rakuten.co.jp/HOTEL/140994/review.html", 
                "大江戸温泉物語 伊東温泉 伊東ホテルニュー岡部", "http://www.jalan.net/yad313622/kuchikomi/?screenId=UWW3001&yadNo=313622&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=210402&distCd=01", "http://travel.rakuten.co.jp/HOTEL/9501/review.html", 
                "大江戸温泉物語 日光霧降温泉 日光霧降", "http://www.jalan.net/yad376230/kuchikomi/?screenId=UWW3001&yadNo=376230&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=080802&distCd=01", "http://travel.rakuten.co.jp/HOTEL/68664/review.html", 
                "大江戸温泉物語 会津東山温泉 東山グランドホテル", "http://www.jalan.net/yad384702/kuchikomi/?screenId=UWW3001&yadNo=384702&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=071712&distCd=01", "http://travel.rakuten.co.jp/HOTEL/136832/review.html", 
                "大江戸温泉物語 加賀片山津温泉 ながやま", "http://www.jalan.net/yad395541/kuchikomi/?screenId=UWW3001&yadNo=395541&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=192605&distCd=01", "http://travel.rakuten.co.jp/HOTEL/74519/review.html", 
                "大江戸温泉物語 鹿教湯温泉 鹿教湯桜館", "http://www.jalan.net/yad303718/kuchikomi/?screenId=UWW3001&yadNo=303718&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=160802&distCd=01", "", 
                "大江戸温泉物語 鹿教湯温泉 鹿教湯藤館", "http://www.jalan.net/yad386190/kuchikomi/?screenId=UWW3001&yadNo=386190&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=160802&distCd=01", "http://travel.rakuten.co.jp/HOTEL/68665/review.html", 
                "大江戸温泉物語 浦安温泉 浦安万華郷", "http://www.jalan.net/yad304280/kuchikomi/?screenId=UWW3001&yadNo=304280&stayMonth=&stayYear=&stayDay=&maxPrice=999999&rootCd=7701&dateUndecided=1&contHideFlg=1&minPrice=0&callbackHistFlg=1&smlCd=120508&distCd=01", "http://travel.rakuten.co.jp/HOTEL/80492/review.html", 
        };

        for (int i = 0; i < xxx.length; i += 3) {

            parseAndWrite(i / 3, xxx[i], xxx[i + 1]);
            parseAndWrite(i / 3, xxx[i], xxx[i + 1]);
            if (xxx[i + 2].length() > 0)
                parseAndWrite(i / 3, xxx[i], xxx[i + 2]);
        }

    }

    public static void parseAndWrite(int index, String name, String url)
            throws ClientProtocolException, IOException {

        System.out.println(name + ": " + url);

        DocumentParser parser;
        String prefix;
        String encoding;
        if (url.startsWith("http://www.jalan.net/")) {
            parser = new JalanDocumentParser();
            prefix = "Jalan";
            encoding = "Windows-31J";
        } else if (url.startsWith("http://travel.rakuten.co.jp/")) {
            parser = new RakutenDocumentParser();
            prefix = "Rakuten";
            encoding = "UTF-8";
        } else {
            throw new IllegalArgumentException("Unknown Service: " + url);
        }

        Consumer<InputStream> responseConsumer = (in) -> {
            try {

                Document document = Jsoup.parse(in, encoding, url);

                int pages = parser.countPages(document);
                // String name = parser.extractName(document);
                String file = name + "-" + prefix + ".log";
                try (BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(file),
                                "UTF-8"))) {

                    write(parser, prefix, document, writer);

                    for (int page = 2; page <= pages; page++) {
                        String _url = parser.makeUrl(url, page);
                        requestAndParse(_url, _in -> {

                            try {

                                Document _doc = Jsoup.parse(_in, encoding,
                                        _url);
                                write(parser, prefix, _doc, writer);

                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                throw new RuntimeException("Not handled", e);
                            }

                        });
                    }

                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new RuntimeException("Not handled", e);
            }
        };

        requestAndParse(url, responseConsumer);

    }

    private static void requestAndParse(String url,
            Consumer<InputStream> responseConsumer)
            throws IOException, ClientProtocolException {
        System.out.println(url);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            // System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            InputStream in = entity.getContent();

            responseConsumer.accept(in);

        }
    }

    private static void write(DocumentParser parser, String prefix,
            Document document, BufferedWriter writer) throws IOException {

        List<? extends AnonymousReview> reviews = parser
                .parseDocument(document);

        for (AnonymousReview review : reviews) {
            writer.write(prefix);
            writer.write("\t");
            if (review instanceof Review) {
                Review r = (Review) review;
                writer.write(String.valueOf(r.getGender()));
                writer.write("\t");
                writer.write(String.valueOf(r.getAge()));
                writer.write("\t");
            } else {
                writer.write("\t");
                writer.write("\t");
            }
            writer.write(
                    review.getSubject() == null ? "" : review.getSubject());
            writer.write("\t");
            writer.write(review.getBody().replaceAll("\n", " "));
            writer.write("\n");
            writer.flush();
        }
    }

}
