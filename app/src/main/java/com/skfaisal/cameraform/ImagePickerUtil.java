package com.skfaisal.cameraform;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;


import com.faisal.basemodule.GetContext;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ImagePickerUtil {


    public static Uri GetSingleImageFromCameraFragment(Fragment fragment, int requestCode) {

        Uri uri = null;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(Objects.requireNonNull(fragment.getActivity()).getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(fragment.getActivity());
                if (photoFile != null) {
                    Log.d("chklog", "Path:" + Environment.getExternalStorageDirectory());
                    Uri photoURI = FileProvider.getUriForFile(fragment.getActivity(),
                            "com.nybsys.sentra_ptt.fileprovider",
                            photoFile);
                    uri = photoURI;
                    //  Bitmap bitmap = BitmapFactory.decodeFile(photoFile.getPath());
                   /* takePictureIntent.putExtra("crop", "true");
                    takePictureIntent.putExtra("aspectX", aspectX);
                    takePictureIntent.putExtra("aspectY", aspectY);
                    takePictureIntent.putExtra("outputX", outputX);
                    takePictureIntent.putExtra("outputY", outputY);
                    takePictureIntent.putExtra("scale", scale);
                    takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);*/
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    fragment.startActivityForResult(takePictureIntent, requestCode);
                    return uri;
                }
            } catch (Exception ex) {
                // Error occurred while creating the File
                Log.d("chklog", "error:" + ex.getMessage());
                Toast.makeText(GetContext.getApplicationUsingReflection(), "something went wrong!! please try again", Toast.LENGTH_SHORT).show();
            }
            // Continue only if the File was successfully created

        }
        return uri;
    }
    public static Uri GetSingleImageFromCamera(Activity activity, int requestCode) {

        Uri uri = null;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(Objects.requireNonNull(activity).getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(activity);
                if (photoFile != null) {
                    Log.d("chklog", "Path:" + Environment.getExternalStorageDirectory());
                    Uri photoURI = FileProvider.getUriForFile(activity,
                            activity.getResources().getString(R.string.fileautority_path),
                            photoFile);
                    uri = photoURI;
                    //  Bitmap bitmap = BitmapFactory.decodeFile(photoFile.getPath());
                   /* takePictureIntent.putExtra("crop", "true");
                    takePictureIntent.putExtra("aspectX", aspectX);
                    takePictureIntent.putExtra("aspectY", aspectY);
                    takePictureIntent.putExtra("outputX", outputX);
                    takePictureIntent.putExtra("outputY", outputY);
                    takePictureIntent.putExtra("scale", scale);
                    takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);*/
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    activity.startActivityForResult(takePictureIntent, requestCode);
                    return uri;
                }
            } catch (Exception ex) {
                // Error occurred while creating the File
                Log.d("chklog", "error:" + ex.getMessage());
                Toast.makeText(GetContext.getApplicationUsingReflection(), "something went wrong!! please try again", Toast.LENGTH_SHORT).show();
            }
            // Continue only if the File was successfully created

        }
        return uri;
    }

    private static File createImageFile(Activity activity) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
    }

}
