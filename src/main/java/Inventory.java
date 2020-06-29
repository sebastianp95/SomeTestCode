import java.time.LocalDate;
import java.util.ArrayList;

public class Inventory {

    private ArrayList<Vehicle> vehicles;

    public Inventory() {
        this.vehicles = new ArrayList<Vehicle>();
    }



    //add 1
//    public void add(Vehicle v) {
//        vehicles.add(v);
//    }
    public void add(Vehicle v) {
        if(v.getPrice()<5000){
            throw new VehicleTooCheapException("Vehicle too cheap allow");
        }
        LocalDate now = LocalDate.now();
        int year=now.getYear();
        if(year-v.getYear()>8){
            throw new VehicleTooOldException("No old cars allowed");
        }


        vehicles.add(v);
    }

    public void remove(Vehicle v) {
        vehicles.remove(v);
    }

    public int size() {

        return vehicles.size();
    }

    public Vehicle findCheapestVehicle() {
        if (vehicles.size() == 0)
            throw new InventoryException("0 cars in inv");
//        Vehicle cheapest = vehicles.get(0);
//        for (Vehicle x : vehicles) {
//            if (x.getPrice() < cheapest.getPrice())
//                cheapest = x;
//        }
//        return cheapest;
        return vehicles.stream().min(Vehicle::compareTo).get();
    }

    public Vehicle findMostExpensiveVehicle() {
        Vehicle mostExpensive = vehicles.get(0);
        for (Vehicle x : vehicles) {
            if (x.getPrice() > mostExpensive.getPrice())
                mostExpensive = x;
        }
        return mostExpensive;
    }

    public void printAveragePriceOfAllVehicles() {
        System.out.println(getAveragePriceOfAllVehicles());

    }

    public double getAveragePriceOfAllVehicles() {
        double avg = 0;
        for (Vehicle x : vehicles) {
            avg = +x.getPrice();
        }


        return avg / vehicles.size();
    }
    public Vehicle get (int index){
        return vehicles.get(index);
    }

}
