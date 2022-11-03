package bg.tu_sofia.fpmi.jatos.diplomna.Class;

import android.os.Parcel;
import android.os.Parcelable;

public class TemperatureAndRadiation implements Parcelable {
    int id;
    double temp;
    double rad;
    int rID;

    public TemperatureAndRadiation(int id, double temp, double rad, int rID) {
        this.id=id;
        this.temp=temp;
        this.rad=rad;
        this.rID=rID;
    }

    protected TemperatureAndRadiation(Parcel in) {
        id = in.readInt();
        temp = in.readDouble();
        rad = in.readDouble();
        rID = in.readInt();
    }

    public static final Creator<TemperatureAndRadiation> CREATOR = new Creator<TemperatureAndRadiation>() {
        @Override
        public TemperatureAndRadiation createFromParcel(Parcel in) {
            return new TemperatureAndRadiation(in);
        }

        @Override
        public TemperatureAndRadiation[] newArray(int size) {
            return new TemperatureAndRadiation[size];
        }
    };

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public double getTemp() { return temp; }

    public void setTemp(double temp) { this.temp = temp; }

    public double getRad() { return rad; }

    public void setRad(double rad) { this.rad = rad; }

    public int getrID() { return rID; }

    public void setrID(int rID) { this.rID = rID; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeDouble(temp);
        parcel.writeDouble(rad);
        parcel.writeInt(rID);
    }
}
