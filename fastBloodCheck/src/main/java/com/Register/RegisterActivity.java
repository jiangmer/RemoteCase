package com.Register;

import com.geniuseoe.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * �û�ע����,�������û�ע��ʱ��д����Ϣ�����ڱ���SQLite���ݿ�user.db��
 * 
 * @author ������
 * @Time 2013-10-01
 */
public class RegisterActivity extends Activity {

	EditText reg_username, reg_password, reg_name, reg_age, reg_phone;
	RadioGroup reg_sex;
	Button reg_register;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);

		// ע��������Ϣ
		reg_username = (EditText) findViewById(R.id.reg_account);
		reg_password = (EditText) findViewById(R.id.reg_password);
		reg_name = (EditText) findViewById(R.id.reg_name);
		reg_sex = (RadioGroup) findViewById(R.id.reg_sex);
		reg_age = (EditText) findViewById(R.id.reg_age);
		reg_phone = (EditText) findViewById(R.id.reg_phone);
		reg_register = (Button) findViewById(R.id.reg_register);

		reg_register.setOnClickListener(new OnClickListener() {
			public void onClick(View source) {
				// Ҫ����д��������Ϣ
				if (reg_username.getText().toString().equals("")
						|| reg_password.getText().toString().equals("")
						|| reg_name.getText().toString().equals("")
						|| reg_age.getText().toString().equals("")
						|| reg_phone.getText().toString().equals("")) {
					Toast.makeText(RegisterActivity.this, "��Ϣ��д������",
							Toast.LENGTH_SHORT).show();
				} else {
					// ����ĸ�����ϢתΪ�ַ�
					String username = reg_username.getText().toString().trim();
					String password = reg_password.getText().toString().trim();
					String name = reg_name.getText().toString().trim();
					String sex = "";
					for (int i = 0; i < reg_sex.getChildCount(); i++) {
						RadioButton radioButton = (RadioButton) reg_sex
								.getChildAt(i);
						if (radioButton.isChecked()) {
							sex = radioButton.getText().toString();
							break;
						}
					}
					String age = reg_age.getText().toString().trim();
					String phone = reg_phone.getText().toString().trim();

					// ��д����Ϣ���û����ռ���ͳһ�������ݿ�
					User user = new User();
					user.setUsername(username);
					user.setPassword(password);
					user.setName(name);
					user.setSex(sex);
					user.setAge(Integer.parseInt(age));
					user.setPhone(phone);
					
					// �������ݿ�
					UserDAO uService = new UserDAO(
							RegisterActivity.this);
					uService.register(user);
					
					Toast.makeText(RegisterActivity.this, "ע��ɹ�",
							Toast.LENGTH_LONG).show();
					finish();
				}
			}
		});
	}
}