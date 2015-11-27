package ru.ria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexander.notes.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import ru.ria.beans.EntryNews;

public class EntryNewsAdapter extends ArrayAdapter<EntryNews> {

	private List<EntryNews> list;
	Context context1;

	public EntryNewsAdapter(Context context, List<EntryNews> list) {
		super(context, R.layout.news_row_layout, R.id.news_name, list);
		this.list = list;
		this.context1 = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.news_row_layout,
					parent, false);

			ViewHolder viewHolder = new ViewHolder();
			
			viewHolder.title = (TextView) convertView.findViewById(R.id.news_name);
			viewHolder.announce = (TextView) convertView.findViewById(R.id.news_annotation);
			viewHolder.date = (TextView) convertView.findViewById(R.id.news_date);
			viewHolder.imageNews = (ImageView) convertView.findViewById(R.id.image_of_news);

			convertView.setTag(viewHolder);
		}
		
		ViewHolder holder = (ViewHolder) convertView.getTag();

		EntryNews news = list.get(position);

		holder.title.setText(news.getTitle());
		holder.announce.setText(news.getAnnounce());
		holder.date.setText(news.getDate());

//		ImageLoader imageLoader = ImageLoader.getInstance(); // Получили экземпляр
//		imageLoader.init(ImageLoaderConfiguration.createDefault(this)); // Проинициализировали конфигом по умолчанию
//
//		imageLoader.displayImage(news.getImgRef(), imageView); // Запустили асинхронный показ картинки
//
//		holder.imageNews.setImageDrawable(imageLoader.loadImage(news.getImgRef(),   );

		ImageLoader.getInstance().displayImage(news.getImgRef(), holder.imageNews);

		return convertView;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public EntryNews getItem(int position) {
		return list.get(position);
	}

	static class ViewHolder {
		public TextView title;
		public TextView announce;
		public TextView date;
		public ImageView imageNews;
	}

}
