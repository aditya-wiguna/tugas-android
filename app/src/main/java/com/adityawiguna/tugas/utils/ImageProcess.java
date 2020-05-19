package com.adityawiguna.tugas.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;

public class ImageProcess {
    public Bitmap automaticRotateImage(Bitmap img, String pathImg){
        Bitmap bitmap = null;

        try {
            ExifInterface exifInterface = new ExifInterface(pathImg);

            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            switch (orientation){
                case ExifInterface.ORIENTATION_ROTATE_90:
                    bitmap = rotateBitmap(img, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    bitmap = rotateBitmap(img, 180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    bitmap = rotateBitmap(img, 270);
                    break;
                case ExifInterface.ORIENTATION_NORMAL:
                    bitmap = img;
                    break;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return bitmap;
    }

    protected Bitmap rotateBitmap(Bitmap imgSource, float angle){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(imgSource, 0, 0, imgSource.getWidth(), imgSource.getHeight(), matrix, true);
    }

}
