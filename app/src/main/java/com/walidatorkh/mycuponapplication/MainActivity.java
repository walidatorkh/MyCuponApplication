package com.walidatorkh.mycuponapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    private ArrayList<Coupon> coupons = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 100; i++) {
            Coupon c = new Coupon("coupon.jpg", "Coupon Title", "6/2/2017", "4/10/2017");
            coupons.add(c);
        }
        CouponsAdapter adapter = new CouponsAdapter(this, coupons);
        setListAdapter(adapter);
    }
}
