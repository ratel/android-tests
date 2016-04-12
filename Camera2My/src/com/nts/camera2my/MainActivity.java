package com.nts.camera2my;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import com.nts.android.camera.AutoFitTextureView;
import com.nts.android.camera.FotoMaker2;
import com.nts.android.logs.LoggerLogAndroidFactory;
import com.nts.cash.log.LogsWriter;
import com.nts.cash.log.OuterLogs;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
	private static final String TAG = "Camera2BasicFragment";
	static {
		com.nts.cash.log.OuterLogs.initialisation(new LoggerLogAndroidFactory());
//		LoggerSQLiteLogAndroidFactory.setEnabled(false);com.nts.cash.log.OuterLogs.initialisation(new LoggerSQLiteLogAndroidFactory());
	}

	static private final LogsWriter log= OuterLogs.createLogsWriter(MainActivity.class);              // Ëמדדונ.
	
	private Button btMakeFoto;
	private AutoFitTextureView mTextureView;

	FotoMaker2 fotoMaker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mTextureView= (AutoFitTextureView)findViewById(R.id.tvCameraPreviewID);

//		mTextureView = new AutoFitTextureView(this);	FrameLayout f= (FrameLayout)findViewById(R.id.cameraPreviewID);		f.addView(mTextureView);

		btMakeFoto= (Button)findViewById(R.id.btMakeFotoID);
		btMakeFoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				takePicture();
			}
		});
		createFotoMaker();
		log.d("_", "Start");
	}

	private void createFotoMaker() {
		fotoMaker= new FotoMaker2(this, mTextureView);
	}
	@Override
	public void onResume() {
		super.onResume();
        // When the screen is turned off and turned back on, the SurfaceTexture is already
        // available, and "onSurfaceTextureAvailable" will not be called. In that case, we can open
        // a camera and start preview from here (otherwise, we wait until the surface is ready in
        // the SurfaceTextureListener).
//		mTextureView= (AutoFitTextureView)findViewById(R.id.tvCameraPreviewID);
		fotoMaker.onResume();
		
	}
	@Override
	public void onPause() {
		fotoMaker.onPause();
		super.onPause();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    private void takePicture() {
    	fotoMaker.takePicture();
    	
//    	mFile= FotoMakerParent.createImageFileName();        lockFocus();
    }
}
