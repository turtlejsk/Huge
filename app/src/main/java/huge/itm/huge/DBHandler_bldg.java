package huge.itm.huge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by ??? on 2015-07-16.
 */
public class DBHandler_bldg {
    private DBHelper helper;
    private SQLiteDatabase db;

    private DBHandler_bldg(Context ctx) {
        this.helper = new DBHelper(ctx);
        this.db = helper.getWritableDatabase();
    }

    public static DBHandler_bldg open(Context ctx) throws SQLException {
        DBHandler_bldg handler = new DBHandler_bldg(ctx);
        return handler;
    }

    public void close() {
        helper.close();
    }

    public long insert(String name, int sex, int floor, int toilet){
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("sex",sex);
        values.put("floor",floor);
        values.put("toilet",toilet);
        return db.insert("bldg",null,values);
    }
    public long init(String name){
        ContentValues values = new ContentValues();
        values.put("name",name);

        return db.insert("bldg",null,values);
    }
    public long init2(String name,String huge,String toilet){
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("toilet",toilet);
        values.put("huge", huge);
        String where = "name = '" + name + "'";
        //String where = "name = '" + name + "'"+" AND "+"time = '"+time+"'"+" AND "+"groupID = '"+group+"'"+" AND "+"email = '"+email+"'";
        Log.i("DBHandler_bldg", "name: " + name + ", huge: " + huge);

        return db.update("bldg", values, where,null);
    }
    public Cursor getData(String name){
        Cursor cursor=db.query(true, "bldg", new String[]{"toilet", "huge"},
                "name = '" + name+"'", null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

/*
    public long insert(String day, int time, String option, String group, String email) {
        ContentValues values = new ContentValues();
        values.put("day", day);
        values.put("time", time);
        values.put("option", option);
        values.put("group", group);
        values.put("email", email);

        return db.insert("schedule", null, values);
    }

    public long update(String day, int time, String option, String group, String email) {
        Log.i("DBHandler_bldg", "day: " + day + ", time: " + time + ", option: " + option + ", group: " + group + ", user: " + email);
        ContentValues values = new ContentValues();
        values.put("option", option);

        String where = "day = '" + day + "'"+" AND "+"time = '"+time+"'"+" AND "+"groupID = '"+group+"'"+" AND "+"email = '"+email+"'";
        return db.update("schedule", values, where, null);
    }

    public long delete(String email) {
        String where = "email = '" + email + "'";
        return db.delete("schedule", where, null);
    }

    public Cursor select(int id) throws SQLException {
        Cursor cursor = db.query(true, "schedule", new String[]{"_id", "date", "time", "option"},
                "_id" + "=" + id, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor select(String group, String email) throws SQLException {
        Cursor cursor = db.query(true, "schedule", null,
                "email='" + email + "'" + " AND " + "group='" + group + "'", null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }*/
}