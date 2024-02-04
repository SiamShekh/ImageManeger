
# Image Maneger

Image Maneger download image via url.


## Authors

- [@SiamShekh](https://www.github.com/SiamShekh)


## Important code

Download Image

```bash
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
```
"# Image-Maneger" 
"# Image-Maneger" 
"# ImageManeger" 
"# ImageManeger" 
