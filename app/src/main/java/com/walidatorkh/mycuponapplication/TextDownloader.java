package com.walidatorkh.mycuponapplication;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class TextDownloader extends AsyncTask<String, Void, String> {
    private Callbacks callbacks;
    private int httpStatusCode;
    private String errorMessage;

    public TextDownloader (Callbacks callbacks){
        this.callbacks = callbacks;
    }
    protected void onPreExecute() {
        callbacks.onAboutToBegin();
    }

    protected String doInBackground(String... urls) {

        HttpURLConnection connection = null;
        InputStream inpuStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try {
            String link = urls[0];
            URL url = new URL(link);
            connection = (HttpURLConnection)url.openConnection();

            httpStatusCode = connection.getResponseCode();

            if (httpStatusCode != HttpURLConnection.HTTP_OK) {
                errorMessage = connection.getResponseMessage();
                return null;
            }

            inpuStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inpuStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder downloadedText = new StringBuilder();
            String oneLine = bufferedReader.readLine();
            while (oneLine != null) {
                downloadedText.append(oneLine);
                downloadedText.append("\n");
                oneLine = bufferedReader.readLine();
            }
            return downloadedText.toString();

        } catch (Exception ex) {
            errorMessage = ex.getMessage();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inpuStream != null) {
                try {
                    inpuStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    protected void onPostExecute(String downloadedText){
        if (downloadedText != null) {
            callbacks.onSuccess(downloadedText);
        }
        else {callbacks.onError(httpStatusCode, errorMessage);}
    }
    public interface Callbacks {
        void onAboutToBegin();
        void onSuccess(String downloadedText);
        void onError(int httpStatusCode, String errorMessage);
    }
}
