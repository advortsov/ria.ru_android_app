package ru.ria.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ru.ria.adapters.EntryRubricsAdapter;
import com.example.alexander.notes.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.Serializable;
import java.util.List;

import ru.ria.net.ContentGenerator;
import ru.ria.beans.EntryNews;
import ru.ria.beans.EntryRubrics;

/**
 * Main activity with rubrics list.
 * Each entry consist rubric name.
 */
public class MainActivity extends AppCompatActivity {

    private ListView listRubrics;
    public static String ENTRY_NEWS = "ru.ria.activities.EntryNews";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listRubrics = (ListView) findViewById(R.id.listRubrics);
        new DownloadEntryGenresListTask(this).execute();
        listRubrics.setOnItemClickListener(new ListViewClickListener());

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
    }

    private class DownloadEntryGenresListTask extends AsyncTask<Void, Void, List<EntryRubrics>> {
        private ProgressDialog pd;
        private ContentGenerator generator;
        Context context;

        public DownloadEntryGenresListTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(MainActivity.this, "Working...", "request to server", true, false);
        }

        @Override
        protected List<EntryRubrics> doInBackground(Void... params) {
            try {
                generator = new ContentGenerator();
                return generator.getEntryRubrics();
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<EntryRubrics> result) {
            EntryRubricsAdapter entryRubricsAdapter =
                    new EntryRubricsAdapter(context, result);
            listRubrics.setAdapter(entryRubricsAdapter);
            pd.dismiss();
        }
    }

    private class DownloadEntryNewsListTask extends AsyncTask<String, Void, List<EntryNews>> {
        private ProgressDialog pd;
        private String ref;
        private ContentGenerator generator;

        public DownloadEntryNewsListTask(String ref) {
            this.ref = ref;
        }

        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(MainActivity.this, "Working...", "request to server", true, false);
        }

        protected List<EntryNews> doInBackground(String... params) {
            try {
                generator = new ContentGenerator();
                return generator.getEntryNews(ref);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<EntryNews> result) {
            Intent intentEntryNews = new Intent(MainActivity.this, ListNewsActivity.class);
            intentEntryNews.putExtra(ENTRY_NEWS, (Serializable)result);
            startActivity(intentEntryNews);
            pd.dismiss();
        }
    }

    class ListViewClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            EntryRubrics rubric = (EntryRubrics) parent.getAdapter().getItem(position);
            rubric.setNumber(position);
            new DownloadEntryNewsListTask(rubric.getSectionRef()).execute();
        }
    }

}
