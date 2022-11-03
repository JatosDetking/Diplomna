package bg.tu_sofia.fpmi.jatos.diplomna.Class;

import android.os.Parcel;
import android.os.Parcelable;

public class Water implements Parcelable {
    int id;
    double water;
    int rID;
    public Water(int id, double water, int rID) {
        this.id=id;
        this.water=water;
        this.rID=rID;
    }

    protected Water(Parcel in) {
        id = in.readInt();
        water = in.readDouble();
        rID = in.readInt();
    }

    public static final Creator<Water> CREATOR = new Creator<Water>() {
        @Override
        public Water createFromParcel(Parcel in) {
            return new Water(in);
        }

        @Override
        public Water[] newArray(int size) {
            return new Water[size];
        }
    };

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public double getWater() { return water; }

    public void setWater(double water) { this.water = water; }

    public int getrID() { return rID; }

    public void setrID(int rID) { this.rID = rID; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeDouble(water);
        parcel.writeInt(rID);
    }
}
