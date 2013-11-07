package com.devng.flickrsearch;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.devng.flickrsearch.model.FlickrImgRef;

public class ImageViewerListAdapter extends BaseAdapter{

	private List<Pair<Bitmap, FlickrImgRef>> data;

	private Activity parentActivity;

	public ImageViewerListAdapter(Activity parentActivity) {
		this.data = new ArrayList<Pair<Bitmap, FlickrImgRef>>();
		this.parentActivity = parentActivity;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int index) {
		return this.data.get(index);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	public void addItem(Pair<Bitmap, FlickrImgRef> item) {
		this.data.add(item);
		this.notifyDataSetChanged();
	}

    /** A reference holder class so we do not call findViewById that often */
	private class ViewHolder {
		TextView flickrName;
		ImageView imageView;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater layoutInflater = parentActivity.getLayoutInflater();
			convertView = layoutInflater.inflate(R.layout.flickr_list_item, null);

			ViewHolder holder = new ViewHolder();
			holder.flickrName = ((TextView) convertView.findViewById(R.id.flickrName));
			holder.imageView = ((ImageView) convertView.findViewById(R.id.flickrImage));

			convertView.setTag(holder);
		}

		ViewHolder holder = (ViewHolder) convertView.getTag();
		Pair<Bitmap, FlickrImgRef> pair = data.get(position);

		holder.flickrName.setText(pair.second.getTitle());
		holder.imageView.setImageBitmap(pair.first);
		return convertView;
	}

}
