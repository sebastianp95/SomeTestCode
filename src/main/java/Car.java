public class Car  extends Vehicle{
    private boolean convertible;

    public Car(String make, String model, int year, boolean fourWheel, double price, int mpg, boolean convertible) {
        super(make, model, year, fourWheel, price, mpg);
        this.convertible = convertible;
    }

    public boolean isConvertible() {
        return convertible;
    }

    public void setConvertible(boolean convertible) {
        this.convertible = convertible;
    }

    @Override
    public void printVehicle(){
        super.printVehicle();
        String str= convertible ? "Convertible" : "Not convertible";
    }
}
