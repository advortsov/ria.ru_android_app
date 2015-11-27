package ru.ria.adapters;

import java.util.List;

import ru.ria.beans.EntryRubrics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alexander.notes.R;

public class EntryRubricsAdapter extends ArrayAdapter<EntryRubrics> {

	private List<EntryRubrics> list;

	public EntryRubricsAdapter(Context context, List<EntryRubrics> list) {
		super(context, R.layout.rubric_row_layout, R.id.rubric_name, list);
		this.list = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.rubric_row_layout, parent, false);

			ViewHolder viewHolder = new ViewHolder();
			
			viewHolder.titleName = (TextView) convertView.findViewById(R.id.rubric_name);

			convertView.setTag(viewHolder);
		}
		
		ViewHolder holder = (ViewHolder) convertView.getTag();

		EntryRubrics rubric = list.get(position);

		holder.titleName.setText(rubric.getTitle());

		return convertView;
	}

	static class ViewHolder {
		public TextView titleName;
	}

}
