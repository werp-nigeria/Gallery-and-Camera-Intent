package com.github.arekolek.phone;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

public class WerpIntent extends AppCompatActivity {

    private Button add;
    private ImageView image1 , image2 , image3;
    private static final int CAMERA_REQUEST = 11113;
    private static final int gallery_request=11151;
    public static int imageCount =0;
    private Uri mImageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_werp_intent);
        add = (Button)findViewById(R.id.add_images);
        image1 = (ImageView) findViewById(R.id.imagee1);
        image2 = (ImageView) findViewById(R.id.imagee2);
        image3 = (ImageView) findViewById(R.id.imagee3);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WerpIntent.this);
                builder.setTitle("Pick From")
                        .setItems(R.array.camgall_array, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                                } else {
                                    Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
                                    gallery.setType("image/*");
                                    startActivityForResult(gallery, gallery_request);
                                }
                            }
                        });
                final AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == gallery_request && resultCode == RESULT_OK){
            mImageUri = data.getData();
            if(imageCount == 0){
                image1.setImageURI(mImageUri);
            }
            if(imageCount == 1){
                image2.setImageURI(mImageUri);
            }
            if(imageCount == 2){
                image3.setImageURI(mImageUri);

            }
            imageCount+=1;
        }if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mImageUri = data.getData();
            mImageUri = data.getData();
            if(imageCount == 0){
                image1.setImageBitmap(photo);
            }
            if(imageCount == 1){
                image2.setImageBitmap(photo);
            }
            if(imageCount == 2){
                image3.setImageBitmap(photo);
            }
            imageCount+=1;
        }
    }
}
