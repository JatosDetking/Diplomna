package bg.tu_sofia.fpmi.jatos.diplomna.Class;

import android.os.Parcel;
import android.os.Parcelable;

public class Wind implements Parcelable {
    int id;
    double wind;
    int rID;
    public Wind(int id, double wind, int rID) {
        this.id=id;
        this.wind=wind;
        this.rID=rID;
    }

    protected Wind(Parcel in) {
        id = in.readInt();
        wind = in.readDouble();
        rID = in.readInt();
    }

    public static final Creator<Wind> CREATOR = new Creator<Wind>() {
        @Override
        public Wind createFromParcel(Parcel in) {
            return new Wind(in);
        }

        @Override
        public Wind[] newArray(int size) {
            return new Wind[size];
        }
    };

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public double getWind() { return wind; }

    public void setWind(double water) { this.wind = water; }

    public int getrID() { return rID; }

    public void setrID(int rID) { this.rID = rID; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeDouble(wind);
        parcel.writeInt(rID);
    }
}
