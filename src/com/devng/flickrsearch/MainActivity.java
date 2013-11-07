package com.devng.flickrsearch;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.inject.Inject;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class MainActivity extends RoboActivity {

	private static final String LOG_TAG = MainActivity.class.getSimpleName();

    // TODO enhance this with a service to get more buzz words
	private static final String[] WORDS = {"Android", "Kitties", "Lion", "Giraffe", "Java", "eGym", "Star Wars", "Star Trek", "Apple", "42", "München", "Würzburg", "Stara Zagora", "NSA", "Buzz"};

	private final Random rand = new Random();

    @InjectView(R.id.searchEdit)
	private EditText searchEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	public void onPickRandom(View v) {
		Log.d(LOG_TAG, "onPickRandom");
		String word = WORDS[rand.nextInt(WORDS.length)];
		searchEdit.setText(word);
	}

	public void onSearch(View v) {
		Log.d(LOG_TAG, "onSearch");

		String text = searchEdit.getText().toString();
		if (text == null || text.length() == 0) {
			Toast.makeText(this, getResources().getString(R.string.textTooShort), Toast.LENGTH_LONG).show();
			return;
		}

		Intent intent = new Intent(this, ImageViewerActivity.class);
		intent.putExtra(Config.EXTRA_KEY_SEARCH_TERM, text);
		startActivity(intent);
	}

}
