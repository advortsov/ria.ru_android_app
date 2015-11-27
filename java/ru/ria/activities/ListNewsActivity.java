package ru.ria.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ru.ria.adapters.EntryNewsAdapter;
import com.example.alexander.notes.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ru.ria.beans.EntryNews;
import ru.ria.beans.EntryRubrics;
import ru.ria.beans.News;
import ru.ria.net.ContentGenerator;

/**
 * Activity with list of news entries.
 * Each entry consist news image, title and announce
 */
public class ListNewsActivity extends AppCompatActivity {

    public ListView listNews;
    private EntryNewsAdapter entryNewsAdapter;
    public static String NEWS = "ru.ria.activities.News";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);
        listNews = (ListView) findViewById(R.id.listNews);
        List<EntryNews> serList = (ArrayList<EntryNews>) getIntent().getSerializableExtra(MainActivity.ENTRY_NEWS);
        entryNewsAdapter = new EntryNewsAdapter(ListNewsActivity.this, serList);
        listNews.setAdapter(entryNewsAdapter);
        listNews.setOnItemClickListener(new ListViewClickListener());
    }

    private class DownloadNewsTask extends AsyncTask<String, Void, News> {
        private ProgressDialog pd;
        private String newsRef;
        private ContentGenerator generator;

        public DownloadNewsTask(String newsRef) {
            this.newsRef = newsRef;
        }

        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(ListNewsActivity.this, "Working...", "request to server", true, false);
        }

        protected News doInBackground(String... params) {
            try {
                generator = new ContentGenerator();
                return generator.getCurrentNews(newsRef);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(News result) {
            Intent intentEntryNews = new Intent(ListNewsActivity.this, NewsActivity.class);
            intentEntryNews.putExtra(NEWS, result);
            startActivity(intentEntryNews);
            pd.dismiss();
        }
    }

    class ListViewClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            EntryNews entryNews = (EntryNews) parent.getAdapter().getItem(position);
            entryNews.setNumber(position);
            new DownloadNewsTask(entryNews.getNewsRef()).execute();
        }
    }

}
