package com.example.alejandrogs.trabajapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import Objetos.FirebaseReference;
import Objetos.IMageUrl;
import Objetos.Post;

/**
 * Implementation of App Widget functionality.
 */
public class widgetTrabajApp extends AppWidgetProvider {
    static AppWidgetManager appWidgetManager;
    Bitmap bitmapt;
    int id_widget;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int i = 0; i<appWidgetIds.length; i++){
            id_widget = appWidgetIds[i];
        }

        actualizarAppWidget(context, appWidgetManager,id_widget);
    }


    static void actualizarAppWidget(final Context context, AppWidgetManager appWidgetManager, int widgetId) {



        RemoteViews controles = new RemoteViews(context.getPackageName(), R.layout.widget_trabaj_app);


        SharedPreferences preferencias = context.getSharedPreferences("mospreferencias", Context.MODE_PRIVATE);
        String dato = preferencias.getString("dato","No resivio valor");
        String dato2 = preferencias.getString("dato2","No resivio valor");
        String dato3 = preferencias.getString("dato3","No resivio valor");

        Bitmap bitmap = getBitmapFromURL(dato3);

        controles.setTextViewText(R.id.Name,dato);
        controles.setTextViewText(R.id.Necesita,dato2);

        //Glide.with(context).load(dato3).into(imageView);
        controles.setImageViewBitmap(R.id.image, bitmap);



        Intent intent = new Intent(context,widgetTrabajApp.class);
        intent.setAction("ACTUALIZAR");
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,widgetId);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,widgetId,intent,0);
        controles.setOnClickPendingIntent(R.id.ac,pendingIntent);




        appWidgetManager.updateAppWidget(widgetId, controles);




    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals("ACTUALIZAR")) {
            int widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

            AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);

            if (widgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {

                actualizarAppWidget(context, widgetManager, widgetId);


            }
        }
    }

    public static Bitmap getBitmapFromURL(String url_image) {
        Log.i("IMAGE","ENRRRRR");
        Bitmap bm = null;
        try {
            URL url = new URL(url_image);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if(conn.getResponseCode() != 200)
            {
                return bm;
            }
            conn.connect();
            InputStream is = conn.getInputStream();

            BufferedInputStream bis = new BufferedInputStream(is);
            try
            {
                bm = BitmapFactory.decodeStream(bis);
            }
            catch(OutOfMemoryError ex)
            {
                bm = null;
            }
            bis.close();
            is.close();
        } catch (Exception e) {}

        return bm;
    }



}

