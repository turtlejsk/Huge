package huge.itm.huge;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.sql.SQLException;

/**
 * Created by 김준성 on 2016-09-20.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    Button btn_login;
    EditText et_email, et_passwd;
    boolean mInitSpinner;
    String domain;
    ArrayAdapter<CharSequence> adspin;

    public void onClick(View v) {
        try {
            DBHandler_user dbhandler = DBHandler_user.open(this);//핸들러 오픈
            String email = et_email.getText().toString();
            String password = et_passwd.getText().toString();

           if (v == btn_login) {//로그인
                try {
                    Cursor cursor = dbhandler.loginselect(email + domain, password);
                    startManagingCursor(cursor);//커서탐색
                    Log.i("LoginActivity", "onClick::btn_login");
                    if (email.isEmpty() || password.isEmpty()) {//입력하지 않았을 때
                        Toast toast = Toast.makeText(LoginActivity.this, "이메일과 비밀번호를 모두 입력해주세요.", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (cursor.getCount() == 0) {//일치하는 것이 없을 때
                        Toast toast = Toast.makeText(LoginActivity.this, "이메일이나 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT);
                        toast.show();
                        et_passwd.setText("");
                    } else {//로그인 성공
                        String familyn = cursor.getString(2);
                        String firstn = cursor.getString(3);
                        Toast toast = Toast.makeText(LoginActivity.this, familyn + firstn + " 씨, 안녕하세요 ! ", Toast.LENGTH_SHORT);
                        toast.show();
                        String userID = email + domain;//현재 로그인 한 이메일로 유저 아이디 생성 후 다른 기능에서 이용
                        SharedPreferences pref = getSharedPreferences("useremail", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("useremail", userID);
                        editor.commit();
                        Intent intent = new Intent(this, SelectActivity.class);//메인메뉴로 이동
                        startActivity(intent);
                        finish();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            dbhandler.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("MainActivity", "onCreate::");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        Spinner spin = (Spinner) findViewById(R.id.spinner);//spinner생성(이메일 도메인 선택에 사용)
        adspin = ArrayAdapter.createFromResource(this, R.array.email, android.R.layout.simple_spinner_item);
        adspin.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spin.setAdapter(adspin);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mInitSpinner == false) {
                    mInitSpinner = true;
                    return;
                }
                domain = adspin.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        et_email = (EditText) findViewById(R.id.et_email);
        et_passwd = (EditText) findViewById(R.id.et_passwd);


        btn_login = (Button) findViewById(R.id.button_login);
        btn_login.setOnClickListener(this);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {//뒤로가기 버튼을 눌렀을 때

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, MainActivity.class);//메인메뉴로 이동
            startActivity(intent);
        }
        return false;
    }
}
