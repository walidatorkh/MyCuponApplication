package com.walidatorkh.mycuponapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

// Downloads an image from a given url:
public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

    private Callbacks callbacks; // Notify to activity what happened.
    private int httpStatusCode; // Http status code.
    private String errorMessage; // Error message.

    // Constructor:
    public ImageDownloader(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    // Executes before doInBackground in the UI thread:
    protected void onPreExecute() {
        callbacks.onAboutToBegin();
    }

    // Executes in the background in a different thread than the UI thread:
    protected Bitmap doInBackground(String... params) {

        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            // Take given link:
            String link = params[0];

            // Create a url:
            URL url = new URL(link);

            // Open connection:
            connection = (HttpURLConnection)url.openConnection();

            // Check for failure:
            httpStatusCode = connection.getResponseCode();
            if(httpStatusCode != HttpURLConnection.HTTP_OK) {
                errorMessage = connection.getResponseMessage(); // Can be null.
                return null;
            }

            // Create readers:
            inputStream = connection.getInputStream();

            // Download and return the bitmap:
            return BitmapFactory.decodeStream(inputStream);
        }
        catch(Exception ex) {
            errorMessage = ex.getMessage(); // Can be null.
            return null;
        }
        finally { // Close readers:
            if(connection != null) connection.disconnect();
            if(inputStream != null) try { inputStream.close(); } catch (Exception ex) { ex.printStackTrace(); }
        }
    }

    // Executes after doInBackground in the UI thread:
    protected void onPostExecute(Bitmap downloadedBitmap) {
        if(downloadedBitmap != null) // Don't check errorMessage cause it can be null even if there is an error.
            callbacks.onSuccess(downloadedBitmap);
        else
            callbacks.onError(httpStatusCode, errorMessage);
    }

    // Callback functions:
    public interface Callbacks {
        void onAboutToBegin();
        void onSuccess(Bitmap downloadedBitmap);
        void onError(int httpStatusCode, String errorMessage);
    }
}