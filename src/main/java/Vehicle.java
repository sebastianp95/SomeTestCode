public class Vehicle implements Comparable<Vehicle>{
    private String make;
    private String model;
    private int year;
    private boolean fourWheel;
    private double price;
    private int mpg;

    public Vehicle(String make, String model, int year, boolean fourWheel, double price, int mpg) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fourWheel = fourWheel;
        this.price = price;
        this.mpg = mpg;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isFourWheel() {
        return fourWheel;
    }

    public void setFourWheel(boolean fourWheel) {
        this.fourWheel = fourWheel;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMpg() {
        return mpg;
    }

    public void setMpg(int mpg) {
        this.mpg = mpg;
    }
    public void printVehicle(){
        System.out.println(year +" "+ make+" "+model);
        String str= fourWheel ? "4WD" : "Not a 4WD";
        System.out.println(str);
        System.out.println("$" + price);
        System.out.println(mpg + "MPG");
    }

    public int compareTo(Vehicle o) {
        return (int)(this.getPrice() - o.getPrice());
    }
}
