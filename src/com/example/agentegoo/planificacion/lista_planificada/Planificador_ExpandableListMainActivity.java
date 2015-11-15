package com.example.agentegoo.planificacion.lista_planificada;

import java.util.ArrayList;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agentegoo.R;
import com.example.agentegoo._Goo;
import com.example.agentegoo.agenda.NuevoEvento_Activity;

public class Planificador_ExpandableListMainActivity extends
		ExpandableListActivity {
	// Create ArrayList to hold parent Items and Child Items
	private ArrayList<String> parentItems = new ArrayList<String>();
	private ArrayList<Object> childItems = new ArrayList<Object>();
	int cant_group = 0;

	public static String entrada_planificador_lista = "";

	// "Sonido,mp3,discos,cornetas,cds;Invitar gente,llamar,sms,facebook,twitter,correos;Comida,pasapalos,torta,refrescos";

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Create Expandable List and set it's properties
		ExpandableListView expandableList = getExpandableListView();
		expandableList.setDividerHeight(2);
		expandableList.setGroupIndicator(null);
		expandableList.setClickable(true);

		// expandableList.setAnimation(animation)

		// Set the Items of Parent
		setGroupParents();
		// Set The Child Data
		setChildData();

		// Create the Adapter
		Planificador_MyExpandableAdapter adapter = new Planificador_MyExpandableAdapter(
				parentItems, childItems);

		adapter.setInflater(
				(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),
				this);

		// Set the Adapter to expandableList
		expandableList.setAdapter(adapter);
		expandableList.setOnChildClickListener(this);

		for (int j = 0; j < cant_group; j++) {

			expandableList.expandGroup(j);

		}
		
		toast();
	}

	// method to add parent Items
	public void setGroupParents() {

		String[] a = null;

		a = entrada_planificador_lista.split(";");

		for (int i = 0; i < a.length; i++) {

			String[] b = null;
			b = a[i].split(",");

			for (int j = 0; j < b.length; j++) {

				if (j == 0) {
					parentItems.add(">> " + b[j]);
					cant_group++;
				}
			}

		}
	}

	public void setChildData() {

		String[] a = null;

		a = entrada_planificador_lista.split(";");

		ArrayList<String> child = new ArrayList<String>();
		for (int i = 0; i < a.length; i++) {

			String[] b = null;
			b = a[i].split(",");

			for (int j = 0; j < b.length; j++) {

				if (j != 0) {
					child.add(b[j].substring(0, 1).toUpperCase()
							+ b[j].substring(1, b[j].length()));
				}
			}
			childItems.add(child);
			child = new ArrayList<String>();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Alternativa 1
		getMenuInflater().inflate(R.menu.menu_planificacion, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.MnuOpc1:
			NuevoEvento_Activity.txtNombreEvento="Rumbita";
			Intent intent = new Intent(
					Planificador_ExpandableListMainActivity.this,
					NuevoEvento_Activity.class);
			Bundle b = new Bundle();
			intent.putExtras(b);
			startActivity(intent);
			return true;
		case R.id.MnuOpc2:
			;
			return true;
		case R.id.MnuOpc3:
			Intent intent2 = new Intent(
					Planificador_ExpandableListMainActivity.this,
					_Goo.class);
			Bundle b2 = new Bundle();
			intent2.putExtras(b2);
			startActivity(intent2);
			;
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void toast() {
		// TODO Auto-generated method stub
		// get your custom_toast.xml ayout
		LayoutInflater inflater = getLayoutInflater();

		View layout = inflater.inflate(R.layout.toast,
				(ViewGroup) findViewById(R.id.custom_toast_layout_id));

		// set a dummy image
		ImageView image = (ImageView) layout.findViewById(R.id.image);
		image.setImageResource(R.drawable._goo);

		// set a message
		TextView text = (TextView) layout.findViewById(R.id.text);
		text.setText("Pulsa Menú para Planificar ...");

		// Toast...
		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();

	}

}
