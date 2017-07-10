package huge.itm.huge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by ???? on 2015-07-16.
 */
public class DBHandler_user_bldg {
    private DBHelper helper;
    private SQLiteDatabase db;

    private DBHandler_user_bldg(Context ctx) {
        this.helper = new DBHelper(ctx);
        this.db = helper.getWritableDatabase();
    }

    public static DBHandler_user_bldg open(Context ctx) throws SQLException {
        DBHandler_user_bldg handler = new DBHandler_user_bldg(ctx);
        return handler;
    }

    public void close() {

        helper.close();
    }

    public long insert (String user_name, String bldgID) {
        Log.i("DBHandler_user_bldg", "DB insert: " + user_name + ", " + bldgID);
        ContentValues values = new ContentValues();
        values.put("name", user_name);
        values.put("bldgID", bldgID);
        values.put("manage", "1");
        Log.i("DB_user_bldg", "name: "+user_name+", bldgID: "+bldgID);
        return db.insert("u_b",null, values);
    }

    public void delete(String user_name, String bldgID) {
        String sql = "delete from u_b where name = '" + user_name +"' and bldgID ='" + bldgID + "';";

        db.execSQL(sql);
    }

    public void deleteUser(String user_name){
        String sql = "delete from u_b where name = '" + user_name +"';";
        db.execSQL(sql);
    }

    public Cursor select(String user_name, String bldgID) throws SQLException {
        Cursor cursor = db.query(true, "u_b", new String[]{"_id", "name", "bldgID"},
                "name='" + user_name + "'" + " AND " + "bldgID='" + bldgID + "'", null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor blist(String user_name) throws SQLException {
        Cursor cursor = db.query(true, "u_b", new String[]{"bldgID"}, "name='"+user_name+"'", null, null, null, null, null);
        return cursor;
    }



}