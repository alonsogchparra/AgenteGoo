package com.example.agentegoo.agenda;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.agentegoo.Main_Activity;
import com.example.agentegoo.R;
import com.example.agentegoo.busqueda.Evento;


public class ListarEventos_Activity extends Activity {

	private SimpleAdapter sa;
	ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	List<Evento> listaPorNombre = Main_Activity.treeNombre
			.depthFirstTraversal();
	List<Evento> listaPorFecha = Main_Activity.treeFecha.depthFirstTraversal();
	final Context context = this;
	String msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.agenda_lista_eventos_activity);
		final ListView lv1 = (ListView) findViewById(R.id.listView1);
		final Button btnAtrasAgenda = (Button) findViewById(R.id.btnSigLista);
		HashMap<String, String> item;

		// Borrar Eventos pasados

		Borrar_Eventos_Pasados();

		if (listaPorFecha != null) {
			for (Evento t : listaPorFecha) {
				item = new HashMap<String, String>();
				item.put("line1", t.getNombre().toString());
				item.put("line2",
						t.getFechaString() + "    " + t.getHoraString());
				list.add(item);
			}
		}

		lv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				AlertDialog diaBox = makeAndShowDialogBox();

				diaBox.show();

			}
		});

		sa = new SimpleAdapter(this, list, android.R.layout.two_line_list_item,
				new String[] { "line1", "line2" }, new int[] {
						android.R.id.text1, android.R.id.text2 });
		lv1.setAdapter(sa);

		btnAtrasAgenda.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Intent intent = new Intent(ListarEventos_Activity.this,
						Agenda_Activity.class);
				Bundle b = new Bundle();
				intent.putExtras(b);
				startActivity(intent);
			}
		});

	}

	public void Borrar_Eventos_Pasados() {

		// Main_Activity.treeNombre
		// .delete_eventos_pasados(new GregorianCalendar());
		Main_Activity.treeFecha.delete_eventos_pasados(new GregorianCalendar());
	}

	private AlertDialog makeAndShowDialogBox() {

		AlertDialog myQuittingDialogBox =

		new AlertDialog.Builder(this)
				// set message, title, and icon
				.setTitle("Opciones")
				.setMessage("Que desea hacer con este Evento?")
				.setIcon(R.drawable.ic_launcher)

				// set three option buttons
				.setPositiveButton("Volver",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// whatever should be done when answering "YES"
								// goes here
								msg = "Modificar"
										+ Integer.toString(whichButton);
								Intent intent = new Intent(
										ListarEventos_Activity.this,
										ListarEventos_Activity.class);
								Bundle b = new Bundle();
								intent.putExtras(b);
								startActivity(intent);
							}
						})
				// setPositiveButton

				.setNeutralButton("Eliminar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								msg = "Cancel " + Integer.toString(whichButton);
							}
						})

				.setNegativeButton("Modificar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// whatever should be done when answering "NO"
								// goes here
								msg = "NO " + Integer.toString(whichButton);
							}
						})// setNegativeButton

				.create();

		return myQuittingDialogBox;
	}
}
