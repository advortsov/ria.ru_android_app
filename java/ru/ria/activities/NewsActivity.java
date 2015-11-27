package ru.ria.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alexander.notes.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import ru.ria.beans.EntryNews;
import ru.ria.beans.News;

/**
 * Activity displays current news.
 * Those news consist news image, title, date and text
 */
public class NewsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ImageView newsPic = (ImageView) findViewById(R.id.news_curr_pic);
        TextView newsTitle = (TextView) findViewById(R.id.news_curr_title);
        TextView newsDate = (TextView) findViewById(R.id.news_curr_date);
        TextView newsText = (TextView) findViewById(R.id.news_curr_text);

        News serNews = (News) getIntent().getSerializableExtra(ListNewsActivity.NEWS);

        newsTitle.setText(serNews.getTitle());
        newsDate.setText(serNews.getDate());
        newsText.setText(serNews.getText());

        ImageLoader.getInstance().displayImage(serNews.getImgRef(), newsPic);
    }
}
