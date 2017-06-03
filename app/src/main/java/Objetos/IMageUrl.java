package Objetos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by alejandrogs on 20/05/17.
 */

public class IMageUrl
{
    ImageView imageView;
    String url;
    public IMageUrl(ImageView imageView, String url) {
        this.imageView = imageView;
        this.url = url;
    }

    public void cambiar(Context context){
        /*Glide glide
                .with(context)
                .load(internetUrl)
                .into(targetImageView);*/
        Glide.with(context).load(url).into(imageView);
    }


    public class GetImageToURL extends AsyncTask< String, Void, Bitmap > {

        @Override
        protected Bitmap doInBackground(String...params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);

                return myBitmap;
            } catch (IOException e) {
                // Log exception
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap myBitMap) {
            imageView.setImageBitmap(myBitMap);
        }
    }


}
