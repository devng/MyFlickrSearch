package com.devng.flickrsearch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.devng.flickrsearch.common.Config;
import com.devng.flickrsearch.common.Enums;
import com.devng.flickrsearch.model.FlickrImgRef;
import com.devng.flickrsearch.task.DownloadFlickrImagesHandler;
import com.devng.flickrsearch.task.DownloadFlickrImagesTask;

import roboguice.inject.InjectView;

public class ImageViewerActivity extends AbstractBackButtonActivity
		implements DownloadFlickrImagesHandler {

	@InjectView(R.id.progressBar2)
	private ProgressBar progressBar;

	@InjectView(R.id.imageView2)
	private ImageView imageView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_viewer);

		// get the search term and start the search on the server side via a task
		Intent sender = getIntent();
		FlickrImgRef imgRef = (FlickrImgRef) sender.getExtras().get(Config.EXTRA_KEY_FLICKR_REF);
		assert imgRef != null; // sanity check
		DownloadFlickrImagesTask task = new DownloadFlickrImagesTask(this, Enums.ImageSize.MEDIUM);
		task.execute(imgRef);

	}


	@Override
	public void onDownloadedFlickrImage(Pair<Bitmap, FlickrImgRef> pair) {
		imageView.setImageBitmap(pair.first);
		progressBar.setVisibility(View.GONE);
		imageView.setVisibility(View.VISIBLE);
	}


	@Override
	public void onDownloadFinish() {
	}
}
