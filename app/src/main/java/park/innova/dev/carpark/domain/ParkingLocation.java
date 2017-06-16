package park.innova.dev.carpark.domain;

/**
 * Created by sajith on 11/24/16.
 */

public class ParkingLocation {

    private double latitude;

    private double longitude;

    private String name;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
