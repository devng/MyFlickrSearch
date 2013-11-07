package com.devng.flickrsearch;

import android.os.Build;

public class Config {

    private Config() {}

    public static final String HTTP_USER_AGENT = "MyFickrSearch/1.0 Android " + Build.VERSION.RELEASE;

    // to get a temp key like this
    // see http://www.flickr.com/services/api/explore/flickr.photos.search
    // see http://www.flickr.com/services/api/flickr.photos.search.html
    public static final String API_KEY = "11d10320bc04eba9bb2eb6d7bd399aab";

    public static final String API_URL = "http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key="
            + API_KEY + "&format=json&nojsoncallback=1&sort=relevance&per_page=15&text=";

    public static final String EXTRA_KEY_SEARCH_TERM = "searchTerm";

    /**
     * Add this '_m' at the end for Flickr image url to get a small image
     *
     * @see http://www.flickr.com/services/api/misc.urls.html
     */
    public static final String IMAGE_SIZE_SMALL = "_m";
}
