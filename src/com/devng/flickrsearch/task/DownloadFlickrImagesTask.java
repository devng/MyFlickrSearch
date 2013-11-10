package com.devng.flickrsearch.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.devng.flickrsearch.common.Enums;
import com.devng.flickrsearch.common.Helpers;
import com.devng.flickrsearch.model.FlickrImgRef;

public class DownloadFlickrImagesTask extends AsyncTask<FlickrImgRef, Pair<Bitmap, FlickrImgRef>, Void> {

	private static final String LOG_TAG = DownloadFlickrImagesTask.class.getSimpleName();

	private final DownloadFlickrImagesHandler handler;

	private final Enums.ImageSize imageSize;

	public DownloadFlickrImagesTask(DownloadFlickrImagesHandler handler, Enums.ImageSize imageSize) {
		this.handler = handler;
		this.imageSize = imageSize;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Void doInBackground(FlickrImgRef... imgRefs) {
		for (int i = 0; i < imgRefs.length; i++) {
			String imgUrl = Helpers.constructFlickrImgUrl(imgRefs[i], this.imageSize);
			Bitmap bitmap = this.downloadBitmap(imgUrl);
			publishProgress(new Pair<Bitmap, FlickrImgRef>(bitmap, imgRefs[i]));
		}
		return null;
	}

	private Bitmap downloadBitmap(String url) {
		Log.d(LOG_TAG, "Downloading image from url: " + url);
		byte[] response = Helpers.doGet(url);
		if (response == null || response.length == 0) {
			return null;
		}
		Options opts = new Options();
		// opts.inSampleSize = 2;
		Bitmap bitmap = BitmapFactory.decodeByteArray(response, 0, response.length, opts);
		return bitmap;
	}

	@Override
	protected void onProgressUpdate(Pair<Bitmap, FlickrImgRef>... pairs) {
		super.onProgressUpdate(pairs);
		if (pairs != null) {
			for (Pair<Bitmap, FlickrImgRef> pair : pairs) {
				handler.onDownloadedFlickrImage(pair);
			}
		}
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		handler.onDownloadFinish();
	}
}
