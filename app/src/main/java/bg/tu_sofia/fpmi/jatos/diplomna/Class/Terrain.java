package bg.tu_sofia.fpmi.jatos.diplomna.Class;

import android.os.Parcel;
import android.os.Parcelable;

public class Terrain implements Parcelable {
    int id;
    String name;
    int[] costs= new int[3];
    double[]probabilities =new double[3];

    double[]temps;
    double[]radiations;
    double[]wind;
    double[]water;
    double optimalEff;
    int optimalCost;
    int index;
    double[][] efficiency = new double[3][3];

    public Terrain(int id, String name ,int[] costs,double[]probabilities) {
        this.id=id;
        this.name=name;
        this.costs=costs;
        this.probabilities=probabilities;
    }

    protected Terrain(Parcel in) {
        id = in.readInt();
        name = in.readString();
        costs = in.createIntArray();
        probabilities = in.createDoubleArray();
        optimalEff = in.readDouble();
        optimalCost = in.readInt();
        index = in.readInt();
    }

    public static final Creator<Terrain> CREATOR = new Creator<Terrain>() {
        @Override
        public Terrain createFromParcel(Parcel in) {
            return new Terrain(in);
        }

        @Override
        public Terrain[] newArray(int size) {
            return new Terrain[size];
        }
    };

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getCosts() {
        return costs;
    }

    public int getCost(int index) { return costs[index]; }

    public void setCosts(int[] costs) {
        this.costs = costs;
    }

    public double getProbabilitie(int index) { return probabilities[index]; }

    public double[] getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(double[] probabilities) {
        this.probabilities = probabilities;
    }

    public double[] getTemps() {
        return temps;
    }

    public void setTemps(double[] temps) {
        this.temps = temps;
    }

    public double[] getRadiations() {
        return radiations;
    }

    public void setRadiations(double[] radiations) {
        this.radiations = radiations;
    }

    public double[] getWind() {
        return wind;
    }

    public void setWind(double[] wind) {
        this.wind = wind;
    }

    public double[] getWater() {
        return water;
    }

    public void setWater(double[] water) {
        this.water = water;
    }

    public double[][] getEfficiency() { return efficiency; }

    public void setEfficiency(double[][] efficiency) { this.efficiency = efficiency; }

    public void setEff(double e, int a, int b) { this.efficiency[a][b] = e; }

    public double getOptimalEff() { return optimalEff; }

    public void setOptimalEff(double optimalEff) { this.optimalEff = optimalEff; }

    public int getOptimalCost() { return optimalCost; }

    public void setOptimalCost(int optimalCost) { this.optimalCost = optimalCost; }

    public int getIndex() { return index; }

    public void setIndex(int index) { this.index = index; }

    public void update(String name ,int[] costs,double[]probabilities){
        setName(name);
        setCosts(costs);
        setProbabilities(probabilities);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeIntArray(costs);
        parcel.writeDoubleArray(probabilities);
        parcel.writeDouble(optimalEff);
        parcel.writeInt(optimalCost);
        parcel.writeInt(index);
    }
}
