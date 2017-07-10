package huge.itm.huge;

import android.app.Activity;
import android.content.Intent;


import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {
    Button btn_join;
    Button btn_login;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        btn_join = (Button) findViewById(R.id.btn_main_join);
        btn_join.setOnClickListener(this);

        btn_login = (Button) findViewById(R.id.btn_main_login);
        btn_login.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v == btn_login){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }else if(v== btn_join){
            Intent intent = new Intent(this, JoinActivity.class);
            startActivity(intent);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {//뒤로가기 버튼을 눌렀을 때

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (count > 0) {
                System.exit(0);
            } else {
                Toast toast = Toast.makeText(MainActivity.this, "'뒤로' 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT);
                Log.i("MainActivity", "Off");
                toast.show();
                count++;
            }
        }
        return false;
    }
}
