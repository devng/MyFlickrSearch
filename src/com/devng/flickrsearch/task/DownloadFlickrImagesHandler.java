package com.devng.flickrsearch.task;

import android.graphics.Bitmap;
import android.util.Pair;

import com.devng.flickrsearch.model.FlickrImgRef;

public interface DownloadFlickrImagesHandler {

	void handleDownloadedFlickrImage(Pair<Bitmap, FlickrImgRef> pair);
	
	void onFinish();
}
