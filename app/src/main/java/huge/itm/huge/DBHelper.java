package huge.itm.huge;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ä�� on 2015-07-16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "huge.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String user_table = "CREATE TABLE user ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "email TEXT NOT NULL," + "password TEXT NOT NULL, "
                + "family_name TEXT NOT NULL," + "first_name TEXT NOT NULL);";
        db.execSQL(user_table);
        String bldg="CREATE TABLE bldg ( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, sex INTEGER NOT NULL DEFAULT '0', floor INTEGER NOT NULL DEFAULT '0', toilet INTEGER NOT NULL DEFAULT '0', huge INTEGER NOT NULL DEFAULT '0');";
        db.execSQL(bldg);
        // �׷�����
        StringBuffer sbG2 = new StringBuffer();
        /*
        StringBuffer sbG = new StringBuffer();

        sbG.append("CREATE TABLE ");

        // ���̺��

        sbG.append("bldg (");

        // �÷�
        sbG.append("_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sbG.append("name TEXT NOT NULL, ");
        sbG.append("sex INTEGER NOT NULL DEFAULT '0', ");
        sbG.append("floor INTEGER NOT NULL DEFAULT '0', ");
        sbG.append("toilet INTEGER NOT NULL DEFAULT '0', ");
        sbG.append("huge INTEGER NOT NULL DEFAULT '0'");
        sbG.append(");");
        db.execSQL(sbG.toString());
        // ����

*/
        sbG2.append("CREATE TABLE ");

        sbG2.append("u_b (");

        // �÷�
        sbG2.append("_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sbG2.append("name TEXT NOT NULL,");
        sbG2.append("bldgID TEXT NOT NULL,");
        sbG2.append("manage INTEGER NOT NULL DEFAULT '0'");
        sbG2.append(");");
        db.execSQL(sbG2.toString());

   }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
