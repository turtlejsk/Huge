package huge.itm.huge;

import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;

import android.database.Cursor;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;
/**
 * Created by 김준성 on 2015-07-15.
 */

public class BuildingListActivity extends Activity implements View.OnClickListener  {
    DBHandler_user_bldg dbhandler_ub;
    DBHandler_bldg dbhandler_b;
    ExpandableListView mList;
    String[] arProv=new String[8];
    String[] warn = new String[8];
    Cursor c1;
    Cursor c2;
    int count=0;

    String[][] arMan= new String[8][8];
    String[][] arWman = new String[8][8];

    String userID;
    SharedPreferences pref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_monitor);
        bldgs();
        pref = getSharedPreferences("useremail", MODE_PRIVATE);
        userID = pref.getString("useremail", "fail");
        mList = (ExpandableListView) findViewById(R.id.ELV);
        List<Map<String, String>> provData = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> cityData = new ArrayList<List<Map<String, String>>>();
        for (int i = 0; i < arProv.length; i++) {
            Map<String, String> Prov = new HashMap<String, String>();
            Prov.put("prov", arProv[i]);
            provData.add(Prov);
            List<Map<String, String>> children = new ArrayList<Map<String, String>>();
            for (int j = 0; j < arMan[i].length; j++) {
                Map<String, String> man = new HashMap<String, String>();
                Map<String, String> woman = new HashMap<String, String>();
                man.put("man", arMan[i][j]);
                woman.put("woman",arWman[i][j]);
                children.add(man);
                children.add(woman);
            }
            cityData.add(children);
        }
        ExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this, provData,android.R.layout.simple_expandable_list_item_1,new String[]{"prov"}, new int[]{android.R.id.text1},cityData,
                R.layout.layout_floor, new String[]{"man"}, new int[]{R.id.childtext}
        );
        mList.setAdapter(adapter);

    }
    //EListView에 건물목록 생성
    public void bldgs(){
        pref = getSharedPreferences("useremail", MODE_PRIVATE);
        userID = pref.getString("useremail", "fail");
        try {
            dbhandler_ub=DBHandler_user_bldg.open(this);
            dbhandler_b=DBHandler_bldg.open(this);
            c1=dbhandler_ub.blist(userID);
            c1.moveToFirst();
            if(c1.getCount()==0){
            }
            String count= String.valueOf(c1.getCount());
            for(int i =0;i < c1.getCount();i++) {
                arProv[i] = c1.getString(0);
                dbhandler_b.init2(c1.getString(0),"70","1");
                receiveData(c1.getString(0),i);
                c1.moveToNext();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //건물별 데이터 가져오기
    public void receiveData(String bldg, int index){
        Cursor c3;
        c3=dbhandler_b.getData(bldg);
        c3.moveToFirst();
        for(int i=0;i<c3.getCount();i++){
            arMan[index][i]= c3.getString(1);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuItem item =menu.add(0,1,0,"Logout");
        menu.add(0,2,0,"담당 구역 수정");
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return true;
            case 2:
                Intent intent2 = new Intent(this, SelectActivity.class);
                startActivity(intent2);
                return true;
        }
        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {//뒤로가기 버튼을 눌렀을 때

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (count > 0) {
                System.exit(0);
            } else {
                Toast toast = Toast.makeText(BuildingListActivity.this, "Dasan 2층 남자화장실 3번 칸, 도난이 의심됩니다,", Toast.LENGTH_LONG);
                toast.show();
                count++;
            }
        }
        return false;
    }

    @Override
    public void onClick(View view) {
    }
}