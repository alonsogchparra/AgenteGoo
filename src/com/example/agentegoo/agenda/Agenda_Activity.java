package com.example.agentegoo.agenda;

import com.example.agentegoo.R;
import com.example.agentegoo._Goo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Agenda_Activity extends Activity {

	public static boolean mostrarListado = false;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.agenda_activity);

		Button btnAgregarEvento = (Button) findViewById(R.id.btnNuevoEvento);
		Button btnListarEvento = (Button) findViewById(R.id.btnListarEvento);
		Button btnBuscarEvento = (Button) findViewById(R.id.btnBuscarEvento);
		Button btnVolver = (Button) findViewById(R.id.btnVolverAgenda);

		if (mostrarListado)
			btnListarEvento.setEnabled(true);
		else if (mostrarListado == false)
			btnListarEvento.setEnabled(false);

		btnVolver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Agenda_Activity.this, _Goo.class);
				Bundle b = new Bundle();
				intent.putExtras(b);
				startActivity(intent);
			}
		});

		btnAgregarEvento.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Intent intent = new Intent(Agenda_Activity.this,
						NuevoEvento_Activity.class);
				Bundle b = new Bundle();
				intent.putExtras(b);
				startActivity(intent);
			}
		});

		btnListarEvento.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Intent intent = new Intent(Agenda_Activity.this,
						ListarEventos_Activity.class);
				Bundle b = new Bundle();
				intent.putExtras(b);
				startActivity(intent);
			}
		});

		btnBuscarEvento.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(Agenda_Activity.this,
						Buscar_Evento_Activity.class);
				Bundle b = new Bundle();
				intent.putExtras(b);
				startActivity(intent);
			}
		});

	}

}
