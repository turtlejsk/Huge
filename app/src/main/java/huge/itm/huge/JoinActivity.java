package huge.itm.huge;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;


public class JoinActivity extends Activity implements View.OnClickListener {
    Button btn_back, btn_save, btn_e_confirm;
    static EditText et_email;
    EditText et_passwd, et_pwconfirm, et_familyn, et_firstn;
    TextView tv_possiblemail;
    boolean mInitSpinner;
    String domain;
    ArrayAdapter<CharSequence> adspin;
    int a;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_join);
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
        et_pwconfirm = (EditText) findViewById(R.id.et_pwconfirm);
        et_familyn = (EditText) findViewById(R.id.et_familyname);
        et_firstn = (EditText) findViewById(R.id.et_firstname);

        tv_possiblemail = (TextView) findViewById(R.id.tv_possiblemail);

        btn_back = (Button) findViewById(R.id.button_back);
        btn_back.setOnClickListener(this);

        btn_save = (Button) findViewById(R.id.button_save);
        btn_save.setOnClickListener(this);

        btn_e_confirm = (Button) findViewById(R.id.button_e_confirm);
        btn_e_confirm.setOnClickListener(this);

    }

    public void onClick(View v) {
        String email = et_email.getText().toString();
        String password = et_passwd.getText().toString();
        String pwconfirm = et_pwconfirm.getText().toString();
        String familyn = et_familyn.getText().toString();
        String firstn = et_firstn.getText().toString();
        String possible = tv_possiblemail.getText().toString();

        try {
            DBHandler_user dbhandler = DBHandler_user.open(this);//handler생성
            if (v == btn_back) {//뒤로가기
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else if (v == btn_save) {//저장
                a = 0;//모든 조건을 충족시킬 때에 a는 0으로 유지, 저장버튼 누를 때마다 갱신
                if (password.length() < 8 || password.length() > 12) {//패스워드 길이 설정
                    Toast toast = Toast.makeText(JoinActivity.this, "패스워드는 8자 이상, 12자 이하로 입력해주세요", Toast.LENGTH_SHORT);
                    toast.show();
                    et_passwd.setText("");
                    et_pwconfirm.setText("");
                    a++;
                }
                if (!password.contentEquals(pwconfirm)) {//pw확인이 되지 않았을 때
                    Toast toast = Toast.makeText(JoinActivity.this, "패스워드를 확인해주세요", Toast.LENGTH_SHORT);
                    toast.show();
                    et_passwd.setText("");
                    et_pwconfirm.setText("");
                    a++;
                }
                if (email.isEmpty() || password.isEmpty() || pwconfirm.isEmpty() || familyn.isEmpty() || firstn.isEmpty()) {
                    Toast toast = Toast.makeText(JoinActivity.this, "빈칸 없이 입력해주세요", Toast.LENGTH_SHORT);
                    toast.show();
                    a++;
                }
                if (!possible.equals("가능")) {//이메일 중복 확인이 완료되지 않았을 시
                    Toast toast = Toast.makeText(JoinActivity.this, "이메일 중복확인을 해주세요.", Toast.LENGTH_SHORT);
                    toast.show();
                    a++;
                }
                if (a == 0) {
                    dbhandler.insert(email+domain, password, familyn, firstn);//DB에 저장
                    Toast toast = Toast.makeText(JoinActivity.this, familyn + firstn + " 씨, 환영합니다 ! ", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            } else if (v == btn_e_confirm) {//이메일 확인버튼
                Cursor cursor = dbhandler.eselect(email+domain);
                startManagingCursor(cursor);//커서 탐색
                if (email.isEmpty()) {
                    Toast toast = Toast.makeText(JoinActivity.this, "먼저 이메일을 입력하세요.", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (!domain.contains("@")) {//도메인을 선택하지 않았을 시
                    Toast toast = Toast.makeText(JoinActivity.this, "도메인을 선택해주세요.", Toast.LENGTH_SHORT);
                    toast.show();
                    tv_possiblemail.setText("불가능");
                    tv_possiblemail.setTextColor(Color.RED);
                } else if (cursor.getCount() == 0) {//중복된 것이 없을 때
                    Toast toast = Toast.makeText(JoinActivity.this, "사용 가능한 이메일입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    tv_possiblemail.setText("가능");
                    tv_possiblemail.setTextColor(Color.BLUE);
                } else {//중복할 때
                    Toast toast = Toast.makeText(JoinActivity.this, "이미 존재하는 E-mail입니다\n다른 이메일을 사용해주세요.", Toast.LENGTH_SHORT);
                    toast.show();
                    JoinActivity.et_email.setText("");
                    tv_possiblemail.setText("불가능");
                    tv_possiblemail.setTextColor(Color.RED);
                }
            }
            dbhandler.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

