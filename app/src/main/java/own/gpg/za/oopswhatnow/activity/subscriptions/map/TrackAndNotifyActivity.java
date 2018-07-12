package own.gpg.za.oopswhatnow.activity.subscriptions.map;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import own.gpg.za.oopswhatnow.R;

public class TrackAndNotifyActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_and_notify);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.track_and_notify_map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        final Long sample_timeout = intent.getLongExtra("SAMPLE_TIMEOUT", 0L);
        final View view = mapFragment.getView();

        if (sample_timeout > 1) {
            Snackbar.make(view, "This Sample will timeout in 1 minute! ", Snackbar.LENGTH_LONG).show();
            final Thread timeoutThread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(sample_timeout);
                    } catch (Exception te) {
                        te.printStackTrace();
                    }

                    Snackbar.make(view, "Please subscribe to get full access! ", Snackbar.LENGTH_LONG).show();

                    finish();

                }
            });
            timeoutThread.run();

        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
