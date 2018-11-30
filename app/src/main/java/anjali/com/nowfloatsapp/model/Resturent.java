package anjali.com.nowfloatsapp.model;

public class Resturent {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    private String name;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLan() {
        return lan;
    }

    public void setLan(double lan) {
        this.lan = lan;
    }

    private double lat;
    private double lan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
}
