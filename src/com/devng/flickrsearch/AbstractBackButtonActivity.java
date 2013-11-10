package com.devng.flickrsearch;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import roboguice.activity.RoboActivity;

/**
 * Activities which extend this class will have a back button on the left side of the menu bar.
 */
public abstract class AbstractBackButtonActivity extends RoboActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			setupActionBar();
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				this.onBackPressed();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
