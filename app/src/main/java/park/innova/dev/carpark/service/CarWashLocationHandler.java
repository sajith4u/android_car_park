package park.innova.dev.carpark.service;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import park.innova.dev.carpark.R;

/**
 * Created by sajith on 11/27/16.
 */

public class CarWashLocationHandler {

    private GoogleMap googleMap;

    public CarWashLocationHandler(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    public void showLocations() {
        googleMap.clear();
        List<LatLng> locations = new ArrayList<>();
        LatLng locationTwo = new LatLng(6.93690, 79.84782);
        LatLng l2 = new LatLng(6.925577, 79.844511);
        LatLng l3 = new LatLng(6.927388, 79.850964);
        LatLng l4 = new LatLng(6.934082, 79.847147);
        locations.add(locationTwo);
        locations.add(l2);
        locations.add(l3);
        locations.add(l4);
        for (LatLng latLng : locations) {
            MarkerOptions location = new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.carwash_theme_icon))
                    .title("");
            googleMap.addMarker(location);
        }

    }
}
