package park.innova.dev.carpark.service;

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

public class CarParkLocationHandler {

    private GoogleMap googleMap;

    public CarParkLocationHandler(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }
    public void showLocations() {
        googleMap.clear();
        List<LatLng> locations = new ArrayList<>();
        LatLng locationTwo = new LatLng(6.930870, 79.847580);
        LatLng l2 = new LatLng(6.933703, 79.846282);
        LatLng l3 = new LatLng(6.934981, 79.843825);
        LatLng l4 = new LatLng(6.933522, 79.842382);
        locations.add(locationTwo);
        locations.add(l2);
        locations.add(l3);
        locations.add(l4);
        for (LatLng latLng : locations) {
            MarkerOptions location = new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.parking_icon))
                    .title("");
            googleMap.addMarker(location);
        }
    }
}
