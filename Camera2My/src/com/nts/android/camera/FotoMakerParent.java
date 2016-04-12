package com.nts.android.camera;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nts.cash.log.LogsWriter;
import com.nts.cash.log.OuterLogs;

import android.os.Environment;

public abstract class FotoMakerParent {
	static private final LogsWriter log= OuterLogs.createLogsWriter(FotoMakerParent.class);              // ������.
    public static File getDir() {
//      File sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
  	final String DIR_NAME= "catch";

  	if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			log.t("", "SD-����� �� ��������: " + Environment.getExternalStorageState());
			return null;
		}
		
      File sdDir= Environment.getExternalStorageDirectory();
      sdDir= new File(sdDir.getAbsolutePath() + "/" + DIR_NAME);
	    // ������� �������
      sdDir.mkdirs();
      return new File(sdDir, "CameraAPI");
  }
	static public File createImageFileName() {
		/*File mediaStorageDir = new File("/sdcard/", "JCG Camera");
		if (!mediaStorageDir.exists()) {
			//if you cannot make this folder return
			if (!mediaStorageDir.mkdirs()) {
				return null;
			}
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File mediaFile;
		mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
		*/
		
	       File pictureFileDir = getDir();
	       
	        // ���� ����� �� ���������� � ��� �� ��������� �� ������� ��������� �� ����.
	        if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
	            log.d("_", "���������� ������� ����� ��� ���������� �����������.");
//	            Toast.makeText(context, "���������� ������� ����� ��� ���������� �����������.", Toast.LENGTH_LONG).show();
	            return null;
	        }
	 
	        // ���������� ��� ��� ����
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.hh-mm-ss");
	        String date = dateFormat.format(new Date());
	        String namePhoto = "Photo_" + date;
	        String photoFile = namePhoto + ".jpg";
	 
	        String photoFilename = pictureFileDir.getPath() + File.separator + photoFile;
	        File pictureFile = new File(photoFilename);
	        
		
		return pictureFile;
	}
}
