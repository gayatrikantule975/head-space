package com.example.headspace;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class QrCodeActivity extends AppCompatActivity {
SharedPreferences preferences;
ImageView ivQrImage;
    String strUserName;
Bitmap bitmap;
private static final int QRWidth=500;
private static final int QRHeight=500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
       preferences= PreferenceManager.getDefaultSharedPreferences(QrCodeActivity.this);
        strUserName =preferences.getString("UserName","");
       ivQrImage=findViewById(R.id.ivQRimage);
       try {
           {
               createQrCode();//Use to  create the QR code
           }
       }
       catch (WriterException e) {
           Toast.makeText(QrCodeActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
       }
    }
    private void createQrCode() throws  WriterException{
        bitmap=textToImageEncode(strUserName);//Set the text to be converted in the image
        ivQrImage.setImageBitmap(bitmap);//Set the bitmap image to the imageView
    }

    private Bitmap textToImageEncode(String strUserName)throws WriterException {
        BitMatrix bitMatrix;//Two dimensional qr code or bar code
        bitMatrix=  new MultiFormatWriter().encode(strUserName, BarcodeFormat.QR_CODE,QRWidth,QRHeight);
        int[] pixels=new int[bitMatrix.getWidth()*bitMatrix.getHeight()];
        for( int x=0;x<bitMatrix.getWidth();x++)
        {
            int offset=x*bitMatrix.getHeight();
            for(int y=0;y<bitMatrix.getHeight();y++)
            {
              pixels[offset+y]=bitMatrix.get(x,y)?getResources().getColor(R.color.darkpink):
                      getResources().getColor(R.color.gray);
            }
        }
        bitmap=Bitmap.createBitmap(QRWidth,QRHeight,Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels,0,500,0,0,500,500);
        return bitmap;
    }

}
//MultiFormatWriter:Encode or decode bar code or qr code
//pixels=declares the color of pixels
//offset=position