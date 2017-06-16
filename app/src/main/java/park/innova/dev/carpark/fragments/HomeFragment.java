package park.innova.dev.carpark.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import park.innova.dev.carpark.R;
import park.innova.dev.carpark.domain.Categories;
import park.innova.dev.carpark.domain.ParkingLocation;
import park.innova.dev.carpark.view.CategoryViewAdapter;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private Fragment currentFragment;

    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home_layout, container, false);
        currentFragment = this;
        ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.categoryTypes);
        LinearLayoutManager layoutSelectDateManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        CategoryViewAdapter categoryViewAdapter = new CategoryViewAdapter(getMainCategories(), currentFragment.getActivity(), this);
        recyclerView.setLayoutManager(layoutSelectDateManager);
        recyclerView.setAdapter(categoryViewAdapter);
        return rootView;
    }

    private List<Categories> getMainCategories() {
        List<Categories> categories = new ArrayList<>();
        Categories carWashCenter = new Categories();
        carWashCenter.setName("Car Wash".toUpperCase());
        carWashCenter.setCategoryImage(R.drawable.car_wash);
        carWashCenter.setColor(R.color.text_color);

        Categories carPark = new Categories();
        carPark.setName("Car Park".toUpperCase());
        carPark.setCategoryImage(R.drawable.parkings_category);
        carPark.setColor(R.color.category_one_color);

        Categories carService = new Categories();
        carService.setName("Car Service".toUpperCase());
        carService.setCategoryImage(R.drawable.car_service);
        carService.setColor(R.color.category_two_color);

        Categories chargeCenters = new Categories();
        chargeCenters.setName("Charge Centers".toUpperCase());
        chargeCenters.setCategoryImage(R.drawable.charge_centers);
        chargeCenters.setColor(R.color.category_three_color);

        Categories more = new Categories();
        more.setName("more".toUpperCase());
        more.setCategoryImage(R.drawable.more_categories);
        more.setColor(R.color.blue_light);

        categories.add(carWashCenter);
        categories.add(carPark);
        categories.add(carService);
        categories.add(chargeCenters);
        categories.add(more);
        return categories;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng businessLocation = new LatLng(6.936236, 79.849932);
        MapsInitializer.initialize(currentFragment.getActivity());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(businessLocation, 15);
        googleMap.animateCamera(cameraUpdate);
        MarkerOptions title = new MarkerOptions()
                .position(businessLocation)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.parking_icon))
                .title("");
        googleMap.addMarker(title);
        addMarkers(googleMap);
    }

    private void addMarkers(GoogleMap googleMap) {
        LatLng businessLocation = new LatLng(6.984716, 79.930449);
        MarkerOptions title = new MarkerOptions()
                .position(businessLocation)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.carwash_theme_icon))
                .title("");
        googleMap.addMarker(title);
        addParkingLocations(googleMap);
    }

    private void addParkingLocations(GoogleMap googleMap) {
        LatLng businessLocation = new LatLng(6.93685, 79.8472);
        LatLng locationTwo = new LatLng(6.93690, 79.84782);
        MarkerOptions title = new MarkerOptions()
                .position(businessLocation)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.parking_icon))
                .title("");

        MarkerOptions location2 = new MarkerOptions()
                .position(locationTwo)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.carwash_theme_icon))
                .title("");

        googleMap.addMarker(title);
        googleMap.addMarker(location2);
    }

    public GoogleMap getGoogleMap() {
        return googleMap;
    }
}
