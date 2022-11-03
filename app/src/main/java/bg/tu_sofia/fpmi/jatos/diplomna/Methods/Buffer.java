package bg.tu_sofia.fpmi.jatos.diplomna.Methods;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;

import bg.tu_sofia.fpmi.jatos.diplomna.Class.TemperatureAndRadiation;
import bg.tu_sofia.fpmi.jatos.diplomna.Class.Water;
import bg.tu_sofia.fpmi.jatos.diplomna.Class.Wind;
import bg.tu_sofia.fpmi.jatos.diplomna.Data.SQLController;
import bg.tu_sofia.fpmi.jatos.diplomna.Class.Terrain;

public class Buffer {

    static public int id;
    static public Terrain terrain;
    static public ArrayList<Terrain> terrains;
    static public ArrayList<TemperatureAndRadiation> tr;
    static public ArrayList<Water> wa;
    static public ArrayList<Wind> wi;
    static public String editType;
    static public SQLController mydb;

    static public void fillListFromDB(Context context1){
        mydb= new SQLController(context1);
        Cursor cursor = mydb.readAllData();
        terrains = new ArrayList<Terrain>();
        if(cursor.getCount() == 0){
            Toast.makeText(context1,"No Data",Toast.LENGTH_SHORT).show();
        }else{
            for(int i = terrains.size()-1 ; i >= 0;i--){
                terrains.remove(i);
            }
            while(cursor.moveToNext()){
                terrains.add(new Terrain(cursor.getInt(0),cursor.getString(1),new int[]{cursor.getInt(2),cursor.getInt(3),cursor.getInt(4)},
                        new double[]{cursor.getDouble(5),cursor.getDouble(6),cursor.getDouble(7)}));
            }
        }
        fillListFromDB1(context1);
    }
    static public void fillListFromDB1(Context context1){
        double[]temps;
        double[]radiations;
        double[]water;
        double[]wind;

        ArrayList<Double> tempsL = new ArrayList<Double>();
        ArrayList<Double> radiationsL = new ArrayList<Double>();
        ArrayList<Double> windL = new ArrayList<Double>();
        ArrayList<Double> waterL = new ArrayList<Double>();
        for (Terrain item : terrains) {
            fillListFromDB2(context1,(item.getId()));
            fillListFromDB3(context1,(item.getId()));
            fillListFromDB4(context1,(item.getId()));
            for (TemperatureAndRadiation item2 : tr) {
                tempsL.add(item2.getTemp());
                radiationsL.add(item2.getRad());
            }
            for (Wind item3 : wi) {
                waterL.add(item3.getWind());
            }
            for (Water item4 : wa) {
                windL.add(item4.getWater());
            }
            temps = listToArr(tempsL);
            radiations = listToArr(radiationsL);
            water = listToArr(waterL);
            wind = listToArr(windL);

            item.setTemps(temps);
            item.setRadiations(radiations);
            item.setWater(water);
            item.setWind(wind);
        }
    }
    static public void fillListFromDB2(Context context1, int Rid){
        Cursor cursor = mydb.readAllData2();
        tr = new ArrayList<TemperatureAndRadiation>();
        if(cursor.getCount() == 0){
            Toast.makeText(context1,"No Data",Toast.LENGTH_SHORT).show();
        }else{
            for(int i = Buffer.tr.size()-1 ; i >= 0;i--){
                Buffer.tr.remove(i);
            }
            while(cursor.moveToNext()){
                if (Rid == cursor.getInt(1)){
                    tr.add(new TemperatureAndRadiation(cursor.getInt(0),cursor.getDouble(2),cursor.getDouble(3),cursor.getInt(1)));
                }
            }
        }
    }
    static public void fillListFromDB3(Context context1, int Rid){
        Cursor cursor = mydb.readAllData3();
        wa = new ArrayList<Water>();
        if(cursor.getCount() == 0){
            Toast.makeText(context1,"No Data",Toast.LENGTH_SHORT).show();
        }else{
            for(int i = Buffer.wa.size()-1 ; i >= 0;i--){
                Buffer.wa.remove(i);
            }
            while(cursor.moveToNext()){
                if (Rid == cursor.getInt(1)){
                    wa.add(new Water(cursor.getInt(0),cursor.getDouble(2),cursor.getInt(1)));
                }
            }
        }
    }
    static public void fillListFromDB4(Context context1, int Rid){
        Cursor cursor = mydb.readAllData4();
        wi = new ArrayList<Wind>();
        if(cursor.getCount() == 0){
            Toast.makeText(context1,"No Data",Toast.LENGTH_SHORT).show();
        }else{
            for(int i = Buffer.wi.size()-1 ; i >= 0;i--){
                Buffer.wi.remove(i);
            }
            while(cursor.moveToNext()){
                if (Rid == cursor.getInt(1)){
                    wi.add(new Wind(cursor.getInt(0),cursor.getDouble(2),cursor.getInt(1)));
                }
            }
        }
    }

    static public void arrToArrT(ArrayList<String> list){
            for (Terrain item : terrains) {
                //list.add("Name: "+item.name);
                list.add(item.getName());
            }
    }
    static public void arrToArrTR(ArrayList<String> list){
            for (TemperatureAndRadiation item : tr) {
                String text = "temp: "+ item.getTemp() +"\n"+ "rаd: " +item.getRad();
                list.add(text);
            }
    }
    static public void arrToArrWa(ArrayList<String> list){
        for (Water item : wa) {
            String text = "High tide: "+ item.getWater();
            list.add(text);
        }
    }
    static public void arrToArrWi(ArrayList<String> list){
        for (Wind item : wi) {
            String text = "Wind Speed: "+ item.getWind();
            list.add(text);
        }
    }
    static public double[] listToArr(ArrayList<Double> list){
        double[] arr = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i]=list.get(i);
        }
        return arr;
    }
    static public String[] setResult(int[] indexs){
        int j=0;
        int g=0;
        for (int i = 0; i < indexs.length; i++) {
            if (indexs[i] ==1){
                j++;
            }
        }
        String[] result = new String[j];
        for (int i = 0; i < indexs.length; i++) {
            if (indexs[i] ==1){
                String res = "Name: "+terrains.get(i).getName()+ " Cost: "+ terrains.get(i).getOptimalCost();
                if(terrains.get(i).getIndex()==0){
                    res+= " Solar";
                }else if(terrains.get(i).getIndex()==1){
                    res+= " HPP";
                }else if(terrains.get(i).getIndex()==2){
                    res+= " turbine";
                }
                result[g] = res;
                g++;
            }
        }
        return result;
    }

}
