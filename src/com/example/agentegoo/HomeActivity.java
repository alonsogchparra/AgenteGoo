package com.example.agentegoo;

import com.example.agentegoo.login.LoginDataBaseAdapter;
import com.example.agentegoo.login.SignUPActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends Activity {
	Button btnSignIn, btnSignUp;
	LoginDataBaseAdapter loginDataBaseAdapter;

	// Probar agarrar el Username
	// EditText etUser;
	// public final String hello = " ";
	// String user = etUser.getText().toString();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_main);

//		Intent intent = new Intent(HomeActivity.this, Main_Activity.class);
//		Bundle b = new Bundle();
//		intent.putExtras(b);
//		startActivity(intent);
//		//
//		// //Inicializando y probando el username
		// etUser = (EditText) findViewById(R.id.editTextUserNameToLogin);
		//
		// Intent intent = new Intent(this, _Goo.class);
		// intent.putExtra(user, "user");

		// create a instance of SQLite Database
		loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		loginDataBaseAdapter = loginDataBaseAdapter.open();

		// Get The Refference Of Buttons
		btnSignIn = (Button) findViewById(R.id.buttonSignIN);
		btnSignUp = (Button) findViewById(R.id.buttonSignUP);

		// Set OnClick Listener on SignUp button
		btnSignUp.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// / Create Intent for SignUpActivity and Start The Activity
				Intent intentSignUP = new Intent(getApplicationContext(),
						SignUPActivity.class);
				startActivity(intentSignUP);
			}
		});
	}

	// Methos to handleClick Event of Sign In Button
	public void signIn(View V) {
		final Dialog dialog = new Dialog(HomeActivity.this);
		dialog.setContentView(R.layout.login);
		dialog.setTitle("Login");

		// get the Refferences of views
		final EditText editTextUserName = (EditText) dialog
				.findViewById(R.id.editTextUserNameToLogin);
		final EditText editTextPassword = (EditText) dialog
				.findViewById(R.id.editTextPasswordToLogin);

		Button btnSignIn = (Button) dialog.findViewById(R.id.buttonSignIn);

		// Set On ClickListener
		btnSignIn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// get The User name and Password
				String userName = editTextUserName.getText().toString();
				String password = editTextPassword.getText().toString();

				// fetch the Password form database for respective user name
				String storedPassword = loginDataBaseAdapter
						.getSinlgeEntry(userName);

				// intentando d otra manera
				// Intent intent = new Intent(HomeActivity.this, Goo.class);
				// startActivity(intent);

				// check if the Stored password matches with Password entered by
				// user
				if (password.equals(storedPassword)) {
					Toast.makeText(HomeActivity.this, "Ingresando",
							Toast.LENGTH_LONG).show();
					dialog.dismiss();

					// Acepta la contrasena y cambia de layout
					Intent intent = new Intent(HomeActivity.this,
							Main_Activity.class);
					Bundle b = new Bundle();
					intent.putExtras(b);
					startActivity(intent);

					// intentando d otra manera
					// Intent intent = new Intent(HomeActivity.this,
					// Prueba.class);
					// startActivity(intent);

				} else {
					Toast.makeText(HomeActivity.this,
							"El Usuario o Contraseña no son correctos",
							Toast.LENGTH_LONG).show();
				}
			}
		});

		// //intentando d otra manera
		// Intent intent = new Intent(HomeActivity.this, Prueba.class);
		// startActivity(intent);

		dialog.show();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Close The Database
		loginDataBaseAdapter.close();
	}
}
