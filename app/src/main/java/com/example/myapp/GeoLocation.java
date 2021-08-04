package com.example.myapp;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeoLocation {
    public static void getAddress(String[] locationaddress, Context context, Handler handler ) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                double[] result = new double[4];
                String add1=locationaddress[0];
                String add2=locationaddress[1];
                try {
                    List addresslist1 = geocoder.getFromLocationName(add1, 2);
                    if (addresslist1 != null && addresslist1.size() > 0) {
                        Address address1 = (Address) addresslist1.get(0);
                        result[0]=address1.getLatitude();
                        result[1]=address1.getLongitude();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    List addresslist2 = geocoder.getFromLocationName(add2, 2);
                    if (addresslist2 != null && addresslist2.size() > 0) {
                        Address address2 = (Address) addresslist2.get(0);
                        result[2]=address2.getLatitude();
                        result[3]=address2.getLongitude();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Message message = Message.obtain();
                message.setTarget(handler);
                if (result != null) {
                    message.what = 1;
                    Bundle bundle = new Bundle();
                    bundle.putDoubleArray("address", result);
                    message.setData(bundle);
                }
                message.sendToTarget();
            }
        };
        thread.start();
    }
}
