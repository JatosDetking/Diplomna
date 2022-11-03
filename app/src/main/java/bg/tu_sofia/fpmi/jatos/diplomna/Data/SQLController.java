package bg.tu_sofia.fpmi.jatos.diplomna.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SQLController extends SQLiteOpenHelper {

    public Context context;
    private static String databaseName = "save.db";
    private static int databaseVersion = 1;
    private static String tableName = "Terrains";
    private static String tableName2 = "TR";
    private static String tableName3 = "Water";
    private static String tableName4 = "Wind";

    private static final String column_id  = "_id" ;
    private static final String column_name = "_name" ;
    private static final String column_c1 = "_c1" ;
    private static final String column_c2 = "_c2" ;
    private static final String column_c3 = "_c3" ;
    private static final String column_p1 = "_p1" ;
    private static final String column_p2 = "_p2" ;
    private static final String column_p3 = "_p3" ;
    private static final String column_temperature = "_temperature" ;
    private static final String column_radiation = "_radiation" ;
    private static final String column_water = "_water" ;
    private static final String column_wind = "_wind" ;
    private static final String column_WaID = "_WaID" ;
    private static final String column_WiID = "_WiID" ;
    private static final String column_TRID = "_TRID" ;
    private static final String column_Rid = "_Rid" ;

    public SQLController(@Nullable Context context) {
        super(context, databaseName, null, databaseVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + tableName + " (" + column_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                column_name + " TEXT, " +
                column_c1 + " INTEGER, " +
                column_c2 + " INTEGER, " +
                column_c3 + " INTEGER, " +
                column_p1 + " REAL, " +
                column_p2 + " REAL, " +
                column_p3 + " REAL);";
        db.execSQL(query);
        String query2 = "CREATE TABLE " + tableName2 + " (" + column_TRID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                column_Rid + " INTEGER REFERENCES " + tableName + " (" + column_id + "), " +
                column_temperature + " REAL, " +
                column_radiation + " REAL);";

        db.execSQL(query2);
        String query3 = "CREATE TABLE " + tableName3 + " (" + column_WaID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                column_Rid + " INTEGER REFERENCES " + tableName + " (" + column_id + "), " +
                column_water + " REAL);";
        db.execSQL(query3);
        String query4 = "CREATE TABLE " + tableName4 + " (" + column_WiID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                column_Rid + " INTEGER REFERENCES " + tableName + " (" + column_id + "), " +
                column_wind + " REAL);";
        db.execSQL(query4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        this.onCreate(db);
    }
    public Cursor readAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + tableName;
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    public Cursor readAllData2(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + tableName2;
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    public Cursor readAllData3(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + tableName3;
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    public Cursor readAllData4(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + tableName4;
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    public int addTerrain(Context context1, String name, int[] costs, double[] probabilities){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(column_name,name);
        cv.put(column_c1,costs[0]);
        cv.put(column_c2,costs[1]);
        cv.put(column_c3,costs[2]);
        cv.put(column_p1,probabilities[0]);
        cv.put(column_p2,probabilities[1]);
        cv.put(column_p3,probabilities[2]);
        long result = db.insert(tableName,null,cv);

        if(result == -1){
            Toast.makeText(context1,"Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context1,"ADDED To TABLE",Toast.LENGTH_SHORT).show();
        }
        return (int)result;
    }
    public void addTR(Context context1,int id, double t, double r){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_Rid,id);
        cv.put(column_temperature,t);
        cv.put(column_radiation,r);

        long result = db.insert(tableName2,null,cv);

        if(result == -1){
            Toast.makeText(context1,"Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context1,"ADDED To TABLE",Toast.LENGTH_SHORT).show();
        }
    }
    public void addWa(Context context1,int id, double w){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_Rid,id);
        cv.put(column_water,w);

        long result = db.insert(tableName3,null,cv);

        if(result == -1){
            Toast.makeText(context1,"Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context1,"ADDED To TABLE",Toast.LENGTH_SHORT).show();
        }
    }
    public void addWi(Context context1,int id, double w){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_Rid,id);
        cv.put(column_wind,w);

        long result = db.insert(tableName4,null,cv);

        if(result == -1){
            Toast.makeText(context1,"Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context1,"ADDED To TABLE",Toast.LENGTH_SHORT).show();
        }
    }
    public void deleteTerrain(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs = {String.valueOf(id)};
        db.delete(tableName, "_id=?", whereArgs);
        db.delete(tableName2, "_Rid=?", whereArgs);
        db.delete(tableName3, "_Rid=?", whereArgs);
        db.delete(tableName4, "_Rid=?", whereArgs);
    }
    public void updateTerrain(Context context1,int id, String name ,int[] costs,double[]probabilities){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_name,name);
        cv.put(column_c1,costs[0]);
        cv.put(column_c2,costs[1]);
        cv.put(column_c2,costs[2]);
        cv.put(column_p1,probabilities[0]);
        cv.put(column_p2,probabilities[1]);
        cv.put(column_p3,probabilities[2]);


        String[] whereArgs = {String.valueOf(id)};
        long result = db.update(tableName,cv,column_id+"=?",whereArgs);
        if(result == -1){
            Toast.makeText(context1,"Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context1,"Update",Toast.LENGTH_SHORT).show();
        }
    }
    public void updateTR(Context context1,int id, double t, double r){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_temperature,t);
        cv.put(column_radiation,r);

        String[] whereArgs = {String.valueOf(id)};
        long result = db.update(tableName2,cv,column_TRID+"=?",whereArgs);
        if(result == -1){
            Toast.makeText(context1,"Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context1,"Update",Toast.LENGTH_SHORT).show();
        }
    }
    public void updateWa(Context context1,int id, double w){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_water,w);

        String[] whereArgs = {String.valueOf(id)};
        long result = db.update(tableName3,cv,column_WaID+"=?",whereArgs);
        if(result == -1){
            Toast.makeText(context1,"Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context1,"Update",Toast.LENGTH_SHORT).show();
        }
    }
    public void updateWi(Context context1,int id, double w){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_wind,w);

        String[] whereArgs = {String.valueOf(id)};
        long result = db.update(tableName4,cv,column_WiID+"=?",whereArgs);
        if(result == -1){
            Toast.makeText(context1,"Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context1,"Update",Toast.LENGTH_SHORT).show();
        }
    }
}
