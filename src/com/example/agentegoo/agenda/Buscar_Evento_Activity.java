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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.agentegoo.Main_Activity;
import com.example.agentegoo.R;
import com.example.agentegoo.busqueda.Node;

@SuppressLint("NewApi")
public class Buscar_Evento_Activity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.agenda_busqueda_activity);

		final EditText editText = (EditText) findViewById(R.id.editEvet);
		final Button btn = (Button) findViewById(R.id.btnBusqueda);
		final Button btnvolver = (Button) findViewById(R.id.btnVolverBusqueda);
		final Button btnvolverActivity = (Button) findViewById(R.id.btnVolverActivityBusqueda);
		final RadioGroup rdg = (RadioGroup) findViewById(R.id.radioGroupBusqueda);
		RadioButton rdbPorNombre = (RadioButton) findViewById(R.id.radiobtnNombre);
		RadioButton rdbPorFecha = (RadioButton) findViewById(R.id.radiobtnFecha);
		final DatePicker dpicker = (DatePicker) findViewById(R.id.datePickerBusqueda);
		final TimePicker tpicker = (TimePicker) findViewById(R.id.timePickerHora);
		final TableLayout tableResultadoBuscar = (TableLayout) findViewById(R.id.tableResultadoBuscar);
		dpicker.setVisibility(View.INVISIBLE);
		tpicker.setVisibility(View.INVISIBLE);
		tableResultadoBuscar.setVisibility(View.INVISIBLE);
		btnvolver.setVisibility(View.INVISIBLE);

		// Borrar Eventos
		Borrar_Eventos_Pasados();

		rdbPorNombre.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editText.setEnabled(true);
				editText.setVisibility(View.VISIBLE);
				dpicker.setVisibility(View.INVISIBLE);
				tpicker.setVisibility(View.INVISIBLE);
				Main_Activity.tipoBusqueda = false;

			}
		});

		btnvolver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
				Intent intent = new Intent(Buscar_Evento_Activity.this,
						Buscar_Evento_Activity.class);
				Bundle b = new Bundle();
				intent.putExtras(b);
				startActivity(intent);

			}
		});

		btnvolverActivity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
				Intent intent = new Intent(Buscar_Evento_Activity.this,
						Agenda_Activity.class);
				Bundle b = new Bundle();
				intent.putExtras(b);
				startActivity(intent);

			}
		});

		rdbPorFecha.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				editText.setVisibility(View.INVISIBLE);
				dpicker.setCalendarViewShown(false);
				dpicker.setVisibility(View.VISIBLE);
				tpicker.setVisibility(View.VISIBLE);
				Main_Activity.tipoBusqueda = true;

			}
		});

		btn.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Node nodePorNombre;
				Node nodePorFecha;

				if (Main_Activity.tipoBusqueda) {

					nodePorFecha = Main_Activity.treeFecha
							.findNode(new GregorianCalendar(dpicker.getYear(),
									dpicker.getMonth(),
									dpicker.getDayOfMonth(), tpicker
											.getCurrentHour(), tpicker
											.getCurrentMinute()));
					if (nodePorFecha != null) {

						btnvolver.setVisibility(View.VISIBLE);
						btn.setVisibility(View.INVISIBLE);
						rdg.setVisibility(View.INVISIBLE);
						dpicker.setVisibility(View.INVISIBLE);
						tpicker.setVisibility(View.INVISIBLE);
						editText.setVisibility(View.INVISIBLE);
						btnvolverActivity.setVisibility(View.INVISIBLE);

						TextView txtbusquedaNombre = (TextView) findViewById(R.id.txtEvtEncontradoNombre);
						TextView txtbusquedaFecha = (TextView) findViewById(R.id.txtEvtEncontradoFecha);
						TextView txtEvtEncontradoHora = (TextView) findViewById(R.id.txtEvtEncontradoHora);
						TextView txtbusquedaPrioridad = (TextView) findViewById(R.id.txtEvtEncontradoPrioridad);
						TextView txtbusquedaEstado = (TextView) findViewById(R.id.txtEvtEncontradoEstado);

						tableResultadoBuscar.setVisibility(View.VISIBLE);
						txtbusquedaNombre.setText(nodePorFecha.getElem()
								.getNombre());
						txtbusquedaFecha.setText(nodePorFecha.getElem()
								.getFechaString());
						txtEvtEncontradoHora.setText(nodePorFecha.getElem()
								.getHoraString());
						txtbusquedaPrioridad.setText(nodePorFecha.getElem()
								.getPrioridadString());
						if (nodePorFecha.getElem().isEstado() == true)
							txtbusquedaEstado.setText("Evento activo");
						else {
							txtbusquedaEstado.setText("Evento ya pasado");
						}
						System.out.println(nodePorFecha.getElem()
								.getHoraString());
					} else {
						tableResultadoBuscar.setVisibility(View.INVISIBLE);
						Toast.makeText(getApplicationContext(),
								"Evento no Encontrado!", Toast.LENGTH_LONG)
								.show();

					}

				} else {
					nodePorNombre = Main_Activity.treeNombre.findNode(editText
							.getText().toString());

					if (nodePorNombre != null) {
						TextView txtbusquedaNombre = (TextView) findViewById(R.id.txtEvtEncontradoNombre);
						TextView txtbusquedaFecha = (TextView) findViewById(R.id.txtEvtEncontradoFecha);
						TextView txtEvtEncontradoHora = (TextView) findViewById(R.id.txtEvtEncontradoHora);
						TextView txtbusquedaPrioridad = (TextView) findViewById(R.id.txtEvtEncontradoPrioridad);
						TextView txtbusquedaEstado = (TextView) findViewById(R.id.txtEvtEncontradoEstado);
						editText.setVisibility(View.INVISIBLE);
						tableResultadoBuscar.setVisibility(View.VISIBLE);
						txtbusquedaNombre.setText(nodePorNombre.getElem()
								.getNombre());
						txtbusquedaFecha.setText(nodePorNombre.getElem()
								.getFechaString());
						txtEvtEncontradoHora.setText(nodePorNombre.getElem()
								.getHoraString());
						txtbusquedaPrioridad.setText(nodePorNombre.getElem()
								.getPrioridadString());
						if (nodePorNombre.getElem().isEstado() == true)
							txtbusquedaEstado.setText("Evento activo");
						else {
							txtbusquedaEstado.setText("Evento ya pasado");
						}
						System.out.println(nodePorNombre.getElem()
								.getHoraString());
					} else {
						tableResultadoBuscar.setVisibility(View.INVISIBLE);
						Toast.makeText(getApplicationContext(),
								R.string.err_busqueda, Toast.LENGTH_LONG)
								.show();
					}
				}
			}
		});

	}

	public void Borrar_Eventos_Pasados() {

		Main_Activity.treeFecha.delete_eventos_pasados(new GregorianCalendar());
	}

}
