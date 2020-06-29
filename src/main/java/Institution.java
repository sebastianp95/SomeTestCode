public class Institution  {
    int id;
    String name, city, state, type;
    int totalGrant;

    int numStudents;
    double averageAmount;

    public Institution(int id, String name, String city, String state, String type, int totalGrant, int numStudents, double averageAmount) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
        this.type = type;
        this.totalGrant = totalGrant;
        this.numStudents = numStudents;
        this.averageAmount = averageAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalGrant() {
        return totalGrant;
    }

    public void setTotalGrant(int totalGrant) {
        this.totalGrant = totalGrant;
    }

    public int getNumStudents() {
        return numStudents;
    }

    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }

    public double getAverageAmount() {
        return averageAmount;
    }

    public void setAverageAmount(double averageAmount) {
        this.averageAmount = averageAmount;
    }

    @Override
    public String toString(){

        return ("\nname: " + getName() + "\ncity :" + getCity() + "\nstate :" + getState() + "\ntype :" + getType() +
                "\ntotalGrant :" + getTotalGrant() + "\nnumStudents :" + getNumStudents() + "\naverageAmount :" + getAverageAmount() + "\n\n");
    }
}
