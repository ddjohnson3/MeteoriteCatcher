public class Meteorite implements MeteoriteObjectInterface {

    private String name;
    private double latitude;
    private double longitude;
    private String fall;
    private double mass;

    // Constructor
    public Meteorite(String name, double latitude, double longitude, String fall, double mass) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.fall = fall;
        this.mass = mass;
    }

    // Accessor Methods
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public String getFall() {
        return fall;
    }

    @Override
    public double getMass() {
        return mass;
    }

    // Mutator Methods
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public void setFall(String fall) {
        this.fall = fall;
    }

    @Override
    public void setMass(double mass) {
        this.mass = mass;
    }

    // Equals Method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meteorite meteorite = (Meteorite) o;

        return Double.compare(meteorite.mass, mass) == 0 &&
               Double.compare(meteorite.latitude, latitude) == 0 &&
               Double.compare(meteorite.longitude, longitude) == 0 &&
               name.equals(meteorite.name) &&
               fall.equals(meteorite.fall);
    }

    // Comparable Implementation
    @Override
    public int compareTo(MeteoriteObjectInterface other) {
        return Double.compare(this.mass, other.getMass());
    }
}
