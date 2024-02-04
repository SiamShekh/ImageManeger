package com.siam.imagemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    EditText Url;
    ImageView holder;
    TextView downlaod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Url = findViewById(R.id.UrlEdit);
        holder = findViewById(R.id.image);
        downlaod = findViewById(R.id.Download);

        // Show Image via url
        Url.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String url = s.toString();
                if (url != null){
                    Picasoo_Load(url);
                }
                // Image load void

            }
        });

        // Download images
        downlaod.setOnClickListener(v -> {
            BitmapDrawable draw = (BitmapDrawable) holder.getDrawable(); // Get image from Imageview
            Bitmap bitmap = draw.getBitmap(); // convart to bitmap

            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DISPLAY_NAME, "Image_" + System.currentTimeMillis());
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);

            Uri externalContentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

            ContentResolver resolver = getContentResolver();
            Uri insertUri = resolver.insert(externalContentUri, values);

            try {
                if (insertUri != null) {
                    try (OutputStream outputStream = resolver.openOutputStream(insertUri)) {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        Toast.makeText(this, "Image Save", Toast.LENGTH_SHORT).show();
                    }
                    // You can use insertUri to reference the saved image
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        });
    }
    protected void Picasoo_Load (String url){
        Picasso.get().load(url).into(holder); // laod image
    }
}