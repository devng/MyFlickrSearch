package com.devng.flickrsearch.common;

import android.net.http.AndroidHttpClient;
import android.util.Log;

import com.devng.flickrsearch.model.FlickrImgRef;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * A number of static functions which make my life easier.
 */
public class Helpers {

	private static final String LOG_TAG = Helpers.class.getSimpleName();

	private Helpers() {
	}

	public static byte[] doGet(final String url) {
		Log.d(LOG_TAG, "Requesting " + url);
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


	public static String encodeUrlString(String input) {
		try {
			return URLEncoder.encode(input, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.e(LOG_TAG, "Unsupported Encoding");
			return null;
		}
	}

	/**
	 * helper method, to construct the url from the json object.
	 */
	public static String constructFlickrImgUrl(final FlickrImgRef ref, final Enums.ImageSize imageSize) {
		StringBuilder sb = new StringBuilder();

		sb.append("http://farm");
		sb.append(ref.getFarm());
		sb.append(".static.flickr.com/");
		sb.append(ref.getServer());
		sb.append("/");
		sb.append(ref.getId());
		sb.append("_");
		sb.append(ref.getSecret());
		sb.append(imageSize.getSuffix());
		sb.append(".jpg");

		return sb.toString();
	}
}
