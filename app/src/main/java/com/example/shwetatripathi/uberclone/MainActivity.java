package com.example.shwetatripathi.uberclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.rides.RideParameters;
import com.uber.sdk.android.rides.RideRequestButton;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.ServerTokenSession;
import com.uber.sdk.rides.client.SessionConfiguration;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SessionConfiguration config = new SessionConfiguration.Builder()
                // mandatory
                .setClientId("bLwET2iqo-_e3StBnBA18ipEPx1kVH5P")
                // required for enhanced button features
                .setServerToken("LWDgVstT_fcFvy0qD8b9QuaMHfUrxQPfsc4fCjvV")
                // required for implicit grant authentication
                //.setRedirectUri("<REDIRECT_URI>")
                .setScopes(Arrays.asList(Scope.RIDE_WIDGETS))
                .setEnvironment(SessionConfiguration.Environment.PRODUCTION)
                .build();

        UberSdk.initialize(config);
        RideRequestButton requestButton=new RideRequestButton(MainActivity.this);
        RelativeLayout layout=new RelativeLayout(this);
        layout.addView(requestButton);


        RideParameters rideParams = new RideParameters.Builder()
                // Optional product_id from /v1/products endpoint (e.g. UberX). If not provided, most cost-efficient product will be used
                .setProductId("a1111c8c-c720-46c3-8534-2fcdd730040d")
                // Required for price estimates; lat (Double), lng (Double), nickname (String), formatted address (String) of dropoff location
                .setDropoffLocation(
                        37.775304, -122.417522, "Uber HQ", "1455 Market Street, San Francisco")
                // Required for pickup estimates; lat (Double), lng (Double), nickname (String), formatted address (String) of pickup location
                .setPickupLocation(37.775304, -122.417522, "Uber HQ", "1455 Market Street, San Francisco")
                .build();
// set parameters for the RideRequestButton instance
        requestButton.setRideParameters(rideParams);


        ServerTokenSession session = new ServerTokenSession(config);
        requestButton.setSession(session);

        requestButton.loadRideInformation();

    }
}
