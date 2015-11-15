package com.example.agentegoo.login;

import com.example.agentegoo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.agentegoo.*;


public class SignUPActivity extends Activity {
	EditText editTextUserName, editTextPassword, editTextConfirmPassword;
	Button btnCreateAccount;

	LoginDataBaseAdapter loginDataBaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_signup_activity);

		// get Instance of Database Adapter
		loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		loginDataBaseAdapter = loginDataBaseAdapter.open();

		// Get Refferences of Views
		// btnGoBack = (Button) findViewById(R.id.btnGoBack);
		editTextUserName = (EditText) findViewById(R.id.editTextUserName);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);

		btnCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);
		btnCreateAccount.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				String userName = editTextUserName.getText().toString();
				String password = editTextPassword.getText().toString();
				String confirmPassword = editTextConfirmPassword.getText()
						.toString();

				// check if any of the fields are vaccant
				if (userName.equals("") || password.equals("")
						|| confirmPassword.equals("")) {
					Toast.makeText(getApplicationContext(),
							"Faltan Campos Por Llenar", Toast.LENGTH_LONG)
							.show();
					return;
				}
				// check if both password matches
				if (!password.equals(confirmPassword)) {
					Toast.makeText(getApplicationContext(),
							"La contraseña no concuerda", Toast.LENGTH_LONG)
							.show();
					return;
				} else {
					// Save the Data in Database
					loginDataBaseAdapter.insertEntry(userName, password);
					Toast.makeText(getApplicationContext(),
							"Cuenta  creada Exitosamente ", Toast.LENGTH_LONG)
							.show();
				}

				Intent intent = new Intent(SignUPActivity.this,
						HomeActivity.class);
				Bundle b = new Bundle();
				intent.putExtras(b);
				startActivity(intent);
			}

		});

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		loginDataBaseAdapter.close();
	}
}
