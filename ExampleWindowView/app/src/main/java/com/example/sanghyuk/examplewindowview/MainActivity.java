package com.example.sanghyuk.examplewindowview;

import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

// 참고 : http://itmir.tistory.com/548
public class MainActivity extends AppCompatActivity {
	public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE= 5469;
//Random value

	public void testPermission() {
		if (!Settings.canDrawOverlays(this)) {
			Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
					Uri.parse("package:" + getPackageName()));
			startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
			if (Settings.canDrawOverlays(this)) {
				// You have permission
			} else {
				finish();
			}
		}
	}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		testPermission();
	}
	public void mStart(View v) {
		startService(new Intent(this, AlwaysTopServiceTouch.class));
	}

	public void mStop(View v) {
		stopService(new Intent(this, AlwaysTopServiceTouch.class));
	}
}
