package com.devng.flickrsearch.task;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import android.net.http.AndroidHttpClient;
import android.util.Log;

import com.devng.flickrsearch.Config;
import com.devng.flickrsearch.model.FlickrImgRef;
import com.google.gson.Gson;

class TaskHelper {

	private static final String LOG_TAG = TaskHelper.class.getSimpleName();

	private TaskHelper() {
	}

	public static byte[] doGet(final String url) {
		AndroidHttpClient client = AndroidHttpClient.newInstance(Config.HTTP_USER_AGENT);
		try {
			HttpGet getRequest = new HttpGet(url);
			HttpResponse response = client.execute(getRequest);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				Log.e(LOG_TAG, "Got a non OK ("
						+ response.getStatusLine().getStatusCode()
						+ ")response for " + url);
				return null;
			}
			return EntityUtils.toByteArray(response.getEntity());
		} catch (IOException e) {
			Log.e(LOG_TAG, "Could not open " + url, e);
		} finally {
			client.close();
		}
		return null;
	}

	public static <T> T fromJson(final String jsonString, final Class<T> type) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, type);
	}

    /**
     * helper method, to construct the url from the json object.
     */
    public static String constructFlickrImgUrl(final FlickrImgRef ref, final String imageSize) {
        StringBuilder sb = new StringBuilder();

        sb.append("http://farm");
        sb.append(ref.getFarm());
        sb.append(".static.flickr.com/");
        sb.append(ref.getServer());
        sb.append("/");
        sb.append(ref.getId());
        sb.append("_");
        sb.append(ref.getSecret());
        sb.append(imageSize);
        sb.append(".jpg");

        return sb.toString();
    }
}
