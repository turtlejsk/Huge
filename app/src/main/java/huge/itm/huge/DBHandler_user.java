package huge.itm.huge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by ä�� on 2015-07-16.
 */
public class DBHandler_user {
    private DBHelper helper;
    private SQLiteDatabase db;

    private DBHandler_user(Context ctx) {
        this.helper = new DBHelper(ctx);
        this.db = helper.getWritableDatabase();
    }

    public static DBHandler_user open(Context ctx) throws SQLException {
        DBHandler_user handler = new DBHandler_user(ctx);
        return handler;
    }

    public void close() {
        helper.close();
    }

    public long insert(String email, String password, String family_name, String first_name) {//DB�� ���ο� ������ ����
        Log.i("DBHandler_User", "insert_ email: " + email + ", password: " + password + ", familY_name: " + family_name + ", first_name: " + first_name);
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        values.put("family_name", family_name);
        values.put("first_name", first_name);

        return db.insert("user", null, values);
    }
    public long update(String email, String password, String family_name, String first_name) {//DB ���� ����
        Log.i("DBHandler_User", "update_ email: " + email + ", password: " + password + ", family_name: " + family_name + ", first_name: " + first_name);
        ContentValues values = new ContentValues();
        values.put("password", password);
        values.put("family_name", family_name);
        values.put("first_name", first_name);

        String where = "email = '" + email + "'";
        return db.update("user", values, where, null);
    }
    public long delete(String email) {//DB����
        String where = "email = '" + email + "'";
        return db.delete("user", where, null);
    }

    public Cursor select(int id) throws SQLException {
        Cursor cursor = db.query(true, "user", new String[]{"_id", "email", "password", "family_name", "first_name"},
                "_id" + "=" + id, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor eselect(String email) throws SQLException {//�̸��� �ߺ�Ȯ�� �� ��
        Log.i("DBHandler_user", "email is " + email);
        Cursor cursor = db.query(true, "user", null,
                "email='" + email + "'", null, null, null, null, null);

        return cursor;
    }

    public Cursor loginselect(String email, String password) throws SQLException {//�α��� �� �� �̸��ϰ� �н����� Ȯ��
        Log.i("DBHandler_User", "insert_ email: " + email + ", password: " + password);
        Cursor cursor = db.query(true, "user", new String[]{"email", "password","family_name","first_name"},
                "email='" + email + "'"+" AND " + "password='" + password + "'", null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        return cursor;
    }
    public Cursor infoselect(String email) throws SQLException {//�������� �� �̸��� �̸��� Ȯ��
        Cursor cursor = db.query(true, "user", new String[]{"email", "family_name", "first_name"},
                "email='" + email + "'", null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
