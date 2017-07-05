package com.walidatorkh.mycuponapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends ListActivity implements TextDownloader.Callbacks{

    private ArrayList<Coupon> coupons;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//        for (int i = 0; i < 100; i++) {
//            Coupon c = new Coupon("coupon.jpg", "Coupon Title", "6/2/2017", "4/10/2017", 10.58);
//            coupons.add(c);
//        }
//        CouponsAdapter adapter = new CouponsAdapter(this, coupons);
//        setListAdapter(adapter);
//        TextDownloader textDownloader = new TextDownloader(this);
//        textDownloader.execute("https://jsonplaceholder.typicode.com/photos");



    protected void onResume() {
        super.onResume();
        TextDownloader textDownloader = new TextDownloader(this);
        textDownloader.execute("https://jsonplaceholder.typicode.com/photos");
    }

    public void buttonBuy_onClick(View view) {
        Intent intent = new Intent(this, BuyCouponActivity.class);
        startActivity(intent);
    }

    public void onAboutToBegin() {

    }

    public void onSuccess(String downloadedText) {
        try {
            coupons = new ArrayList<>();

            JSONArray jsonArray = new JSONArray(downloadedText);
            for ( int i =0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String thumbnailUrl = jsonObject.getString("thumbnailUrl");
                String title = jsonObject.getString("title");
                Coupon coupon = new Coupon(thumbnailUrl, title,"","", 0);
                coupons.add(coupon);
            }
            CouponsAdapter adapter = new CouponsAdapter(this, coupons);
            setListAdapter(adapter);
        }
        catch (Exception ex) {
            Toast.makeText(this, "Error" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    public void onError(int httpStatusCode, String errorMessage) {
        Toast.makeText(this, "Error" + errorMessage, Toast.LENGTH_LONG).show();

    }
}
