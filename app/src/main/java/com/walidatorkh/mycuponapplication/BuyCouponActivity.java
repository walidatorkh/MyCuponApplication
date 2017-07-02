package com.walidatorkh.mycuponapplication;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BuyCouponActivity extends ListActivity implements DialogInterface.OnClickListener {
    private ArrayList<Coupon> coupons = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_coupon);
        for (int i = 0; i < 100; i++) {
            Coupon c = new Coupon("coupon.jpg", "Coupon Title", "6/2/2017", "4/10/2017", 10.58);
            coupons.add(c);
        }
        CouponsAdapter adapter = new CouponsAdapter(this, coupons);
        setListAdapter(adapter);
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        AlertDialog dialog = new AlertDialog.Builder(this).create();

        dialog.setTitle("Really buy coupon? ");

        dialog.setButton(Dialog.BUTTON_POSITIVE, "Buy", this);
        dialog.setButton(Dialog.BUTTON_NEGATIVE, "Cancel", this);

        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        if (which == Dialog.BUTTON_POSITIVE) {
            Toast.makeText(this, "You clicked to buy!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "You clicked to cancel! " , Toast.LENGTH_LONG).show();
        }

    }
}
