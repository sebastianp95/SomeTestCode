public class Truck extends Vehicle{

    private boolean sideStep;
    private int towCapacity;

    public Truck(String make, String model, int year, boolean fourWheel, double price, int mpg, boolean sideStep, int towCapacity) {
        super(make, model, year, fourWheel, price, mpg);
        this.sideStep = sideStep;
        this.towCapacity = towCapacity;
    }

    public boolean isSideStep() {
        return sideStep;
    }

    public void setSideStep(boolean sideStep) {
        this.sideStep = sideStep;
    }

    public int getTowCapacity() {
        return towCapacity;
    }

    public void setTowCapacity(int towCapacity) {
        this.towCapacity = towCapacity;
    }

    public void printVehicle() {
        super.printVehicle();
        String str = sideStep ? "Has a side step" : "No side Step";
        System.out.println(str);
        System.out.println("Tow capacity: "+this.towCapacity);

    }
}
