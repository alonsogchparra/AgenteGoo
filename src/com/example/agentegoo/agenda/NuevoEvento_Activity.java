package com.example.agentegoo.agenda;

import java.util.GregorianCalendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.agentegoo.Main_Activity;
import com.example.agentegoo.R;
import com.example.agentegoo.busqueda.Evento;

@SuppressLint("NewApi")
public class NuevoEvento_Activity extends Activity {

	int prioridad_number;
	public static String txtNombreEvento = "";

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.agenda_nuevo_evento_activity);

		final DatePicker fecha = (DatePicker) findViewById(R.id.datePicker1);
		Button btnAgregar = (Button) findViewById(R.id.btnCrearEvento);
		fecha.setCalendarViewShown(false);
		final Spinner prioridad = (Spinner) findViewById(R.id.spinner1);
		final EditText txtNombre = (EditText) findViewById(R.id.editText1);

		final TimePicker hora = (TimePicker) findViewById(R.id.timePicker1);
		
		txtNombre.setText(txtNombreEvento);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.prioridad_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		prioridad.setAdapter(adapter);
		// Borrar eventos
		Borrar_Eventos_Pasados();

		Object objSelectedItem1 = prioridad.getSelectedItem();
		final String[] arrayDia = this.getResources().getStringArray(
				R.array.prioridad_array);
		String strSelectedItem1 = String.valueOf(objSelectedItem1);

		prioridad
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							android.view.View v, int position, long id) {
						prioridad_number = Integer.parseInt(arrayDia[position]);
					}

					public void onNothingSelected(AdapterView<?> parent) {
						// lblMensaje.setText("");
					}
				});

		btnAgregar.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			public void onClick(View arg0) {

				Intent intent = new Intent(NuevoEvento_Activity.this,
						Agenda_Activity.class);
				Bundle b = new Bundle();

				b.putString("NOMBREEVENTO", txtNombre.getText().toString());
				b.putString("PRIORIDAD", Integer.toString(prioridad_number));

				if (Main_Activity.treeNombre.add(new Evento(txtNombre.getText()
						.toString(), new GregorianCalendar(fecha.getYear(),
						fecha.getMonth(), fecha.getDayOfMonth(), hora
								.getCurrentHour(), hora.getCurrentMinute()),
						prioridad_number, true)) != null
						&& Main_Activity.treeFecha.add(new Evento(txtNombre
								.getText().toString(),
								new GregorianCalendar(fecha.getYear(), fecha
										.getMonth(), fecha.getDayOfMonth(),
										hora.getCurrentHour(), hora
												.getCurrentMinute()),
								prioridad_number, true)) != null) {
					intent.putExtras(b);
					startActivity(intent);
					Agenda_Activity.mostrarListado = true;
					txtNombreEvento = "";
				} else {
					Toast.makeText(getApplicationContext(),
							R.string.err_insertar, Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	public void Borrar_Eventos_Pasados() {

		Main_Activity.treeFecha.delete_eventos_pasados(new GregorianCalendar());
	}

}
