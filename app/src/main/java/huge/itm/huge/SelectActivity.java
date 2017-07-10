package huge.itm.huge;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.sql.SQLException;

/**
 * Created by 김준성 on 2016-09-20.
 */
public class SelectActivity extends Activity implements View.OnClickListener {

    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;
    CheckBox cb4;
    CheckBox cb5;
    CheckBox cb6;
    CheckBox cb7;
    CheckBox cb8;
    Button btn_finish;
    String name;
    int count=0;
    Cursor c1;
    Cursor c2;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select);

        cb1 = (CheckBox) findViewById(R.id.btn_fron);
        cb2 = (CheckBox) findViewById(R.id.btn_high);
        cb3 = (CheckBox) findViewById(R.id.btn_mugung);
        cb4 = (CheckBox) findViewById(R.id.btn_dasan);
        cb5 = (CheckBox) findViewById(R.id.btn_mirae);
        cb6 = (CheckBox) findViewById(R.id.btn_davinci);
        cb7 = (CheckBox) findViewById(R.id.btn_language);
        cb8 = (CheckBox) findViewById(R.id.btn_eoui);
        btn_finish=(Button) findViewById(R.id.btn_finish);
        SharedPreferences pref = getSharedPreferences("useremail", MODE_PRIVATE);
        name = pref.getString("useremail", "fail");

        btn_finish.setOnClickListener(this);


    }
    public void checklist(){
        try {
            DBHandler_bldg dbhandler_b= DBHandler_bldg.open(this);
            DBHandler_user_bldg dbhandler_ub = DBHandler_user_bldg.open(this);
            dbhandler_b.init(cb1.getText().toString());

            dbhandler_b.init(cb2.getText().toString());
            dbhandler_b.init(cb3.getText().toString());
            dbhandler_b.init(cb4.getText().toString());
            dbhandler_b.init(cb5.getText().toString());
            dbhandler_b.init(cb6.getText().toString());
            dbhandler_b.init(cb7.getText().toString());
            dbhandler_b.init(cb8.getText().toString());


            if(cb1.isChecked()==true){
                String bldgID=cb1.getText().toString();
                c2=dbhandler_ub.select(name,bldgID);
                if(c2.getCount()==0){
                dbhandler_ub.insert(name,bldgID);
                }
                else{
                }
            }else{
                String bldgID=cb1.getText().toString();
                c1=dbhandler_ub.select(name,bldgID);
                if(c1.getCount()!=0)
                {
                dbhandler_ub.delete(name,bldgID);
                }else{
                }
            }
            if(cb2.isChecked()==true){

                String bldgID=cb2.getText().toString();
                dbhandler_ub.insert(name,bldgID);
            }else{
                String bldgID=cb2.getText().toString();
                c1=dbhandler_ub.select(name,bldgID);
                if(c1.getCount()!=0)
                {
                    Log.i("CheckBox2", "UnChecked");
                    dbhandler_ub.delete(name,bldgID);
                    Log.i("CheckBox2", "deleted");
                }else{
                }
            }
            if(cb3.isChecked()==true){

                String bldgID=cb3.getText().toString();
                dbhandler_ub.insert(name,bldgID);
            }else{
                String bldgID=cb3.getText().toString();
                c1=dbhandler_ub.select(name,bldgID);
                if(c1.getCount()!=0)
                {
                    Log.i("CheckBox3", "UnChecked");
                    dbhandler_ub.delete(name,bldgID);
                    Log.i("CheckBox3", "deleted");
                }else{
                }
            }
            if(cb4.isChecked()==true){

                String bldgID=cb4.getText().toString();
                dbhandler_ub.insert(name,bldgID);
            }else{
                String bldgID=cb4.getText().toString();
                c1=dbhandler_ub.select(name,bldgID);
                if(c1.getCount()!=0)
                {
                    Log.i("CheckBox4", "UnChecked");
                    dbhandler_ub.delete(name,bldgID);
                    Log.i("CheckBox4", "deleted");
                }else{
                }
            }
            if(cb5.isChecked()==true){

                String bldgID=cb5.getText().toString();
                dbhandler_ub.insert(name,bldgID);
            }else{
                String bldgID=cb5.getText().toString();
                c1=dbhandler_ub.select(name,bldgID);
                if(c1.getCount()!=0)
                {
                    Log.i("CheckBox5", "UnChecked");
                    dbhandler_ub.delete(name,bldgID);
                    Log.i("CheckBox5", "deleted");
                }else{
                }
            }

            if(cb6.isChecked()==true){

                String bldgID=cb6.getText().toString();
                dbhandler_ub.insert(name,bldgID);
            }else{
                String bldgID=cb6.getText().toString();
                c1=dbhandler_ub.select(name,bldgID);
                if(c1.getCount()!=0)
                {
                    Log.i("CheckBox6", "UnChecked");
                    dbhandler_ub.delete(name,bldgID);
                    Log.i("CheckBox6", "deleted");
                }else{
                }
            }
            if(cb7.isChecked()==true){

                String bldgID=cb7.getText().toString();
                dbhandler_ub.insert(name,bldgID);
            }else{
                String bldgID=cb7.getText().toString();
                c1=dbhandler_ub.select(name,bldgID);
                if(c1.getCount()!=0)
                {
                    Log.i("CheckBox7", "UnChecked");
                    dbhandler_ub.delete(name,bldgID);
                    Log.i("CheckBox7", "deleted");
                }else{
                }
            }
            if(cb8.isChecked()==true){

                String bldgID=cb8.getText().toString();
                dbhandler_ub.insert(name,bldgID);
            }else{
                String bldgID=cb8.getText().toString();
                c1=dbhandler_ub.select(name,bldgID);
                if(c1.getCount()!=0)
                {
                    Log.i("CheckBox8", "UnChecked");
                    dbhandler_ub.delete(name,bldgID);
                    Log.i("CheckBox8", "deleted");
                }else{
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        if (v == btn_finish) {//뒤로가기
            Log.i("SelectActivity", "UnChecked");
            checklist();
            Log.i("SelectActivity", "Checked");
            Intent intent = new Intent(this, BuildingListActivity.class);
            startActivity(intent);
            finish();
    }
}
    public boolean onKeyDown(int keyCode, KeyEvent event) {//뒤로가기 버튼을 눌렀을 때

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, BuildingListActivity.class);
            startActivity(intent);
            finish();
        }
        return false;
    }
}
