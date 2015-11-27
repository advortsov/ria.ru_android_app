package ru.ria.net;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.ria.beans.EntryNews;
import ru.ria.beans.EntryRubrics;
import ru.ria.beans.News;

/**
 * This class generates needed lists of content for application.
 */
public class ContentGenerator {

    public List<EntryRubrics> getEntryRubrics() throws IOException {
        String sectionRef;
        String title;

        Document doc = Jsoup.connect("http://ria.ru/")
                .userAgent("Mozilla")
                .get();

        List<EntryRubrics> entryGenresList = new ArrayList<>();

        Elements newsElements = doc.select(".navigator_top_item");

        for (Element element : newsElements) {
            sectionRef = element.select("a").attr("href");
            title = element.select(".navigator_top_item_title").text();
            entryGenresList.add(new EntryRubrics(sectionRef, title));
        }

        return entryGenresList;
    }

    public List<EntryNews> getEntryNews(String sectionRef) throws IOException {
        String imgRef;
        String newsRef;
        String title;
        String announce;
        String date;
        String url;

        if (!sectionRef.contains("http://ria.ru")){
            url = "http://ria.ru" + sectionRef;
        } else {
            url = sectionRef;
        }

        List<EntryNews> entryNewsList = new ArrayList<>();

        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla")
                .get();

        Elements entryNewsElements;
        Elements listItems;

        if (sectionRef.contains("religion")){

            entryNewsElements = doc.select(".rubric_bytheme_item_block_wrap");
            listItems = entryNewsElements.select(".rubric_bytheme_item_block");

            for (Element element : listItems) {
                imgRef = element.select("img").attr("src");
                newsRef = element.select(".rubric_bytheme_item_block_inner.photo a").attr("href");
                title = element.select(".rubric_bytheme_item_block_inner.photo a").text();
                announce = ""; // there are no announces
                date = element.select(".rubric_bytheme_item_block_inner.photo span").text();

                entryNewsList.add(new EntryNews(imgRef, newsRef, title, announce, date));
            }

            return entryNewsList;

        } else if (sectionRef.contains("radio")){

            entryNewsElements = doc.select(".actual_view_items");
            listItems = entryNewsElements.select(".actual_view_item.clearfix");

            for (Element element : listItems) {
                imgRef = element.select("img").attr("src");
                newsRef = element.select(".actual_view_item_img a").attr("href");
                title = element.select(".actual_view_item_title a").text();
                announce = element.select(".actual_view_item_announce a").text();
                date = ""; // there is no date

                entryNewsList.add(new EntryNews(imgRef, newsRef, title, announce, date));
            }

            return entryNewsList;

        } else if (sectionRef.contains("science")) {

            entryNewsElements = doc.select(".rubric_bytheme_item_block_wrap");
            listItems = entryNewsElements.select(".rubric_bytheme_item_block");

            for (Element element : listItems) {
                imgRef = element.select("img").attr("src");
                newsRef = element.select("a").attr("href");
                title = element.select(".rubric_bytheme_item_block_inner_title").text();
                announce = element.select(".actual_view_item_announce a").text(); // there are no announces
                date = element.select(".rubric_bytheme_item_block_inner.photo span").text();
                entryNewsList.add(new EntryNews(imgRef, newsRef, title, announce, date));
            }

            return entryNewsList;

        }  else if (sectionRef.equals("/")) { // main menu

            entryNewsElements = doc.select(".day_news_date.day_news_page");
            listItems = entryNewsElements.select(".day_news_item");
            listItems.addAll( entryNewsElements.select(".day_news_items_row .day_news_item.day_news_item_3rd") );

            for (Element element : listItems) {
                imgRef = element.select(" .day_news_item_img img").attr("src");
                newsRef = element.select(" .day_news_item_text .day_news_item_title a").attr("href");
                title = element.select(" .day_news_item_text .day_news_item_title h3").text();
                announce = element.select(" .day_news_item_text .day_news_item_announce a").text();
                date = ""; // there is no date
                entryNewsList.add(new EntryNews(imgRef, newsRef, title, announce, date));
            }

            return entryNewsList;

        } else {

            entryNewsElements = doc.select(".list .list_item");
            listItems = entryNewsElements.select(".list_item_content");

            for (Element element : listItems) {
                imgRef = element.select(".list_item_img img").attr("src");
                newsRef = element.select(".list_item_text .list_item_title a").attr("href");
                title = element.select(".list_item_text .list_item_title a").text();
                announce = element.select(".list_item_text .list_item_announce").text();
                date = element.select(".list_item_text .list_item_date").text();

                entryNewsList.add(new EntryNews(imgRef, newsRef, title, announce, date));
            }

            return entryNewsList;
        }

    }

    public News getCurrentNews(String newsRef) throws IOException {
        String imgRef;
        String title;
        String text;
        String date;
        String url = "http://ria.ru" + newsRef;

        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla")
                .get();

        imgRef = doc.select(".article .article_full " +
                ".article_full_content .article_full_text.clearfix " +
                ".article_illustration " +
                ".slideshow_item.slideshow_item_sel.inject img").attr("src");

        title = doc.select(".article .article_header " +
                ".article_header_title ").text();

        text = doc.select(".article .article_full " +
                ".article_full_content .article_full_text.clearfix p").text();

        date = doc.select(".article .article_header " +
                ".article_header_info .article_header_date").attr("datetime");

        return new News(imgRef, title, text, date);
    }
}
