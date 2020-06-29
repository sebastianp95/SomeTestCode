public class City {
    private double latitude;
    private double longitude;
    private double temperature;

    public City(double latitude, double longitude, double temperature) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
    }
    private City(){

    }

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

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
