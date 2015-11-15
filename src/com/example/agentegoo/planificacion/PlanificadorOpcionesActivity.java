package com.example.agentegoo.planificacion;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.example.agentegoo.R;
import com.example.agentegoo.R.id;
import com.example.agentegoo.planificacion.lista_planificada.Planificador_ExpandableListMainActivity;

public class PlanificadorOpcionesActivity extends Activity {

	List<String> groupList;
	List<String> childList;
	Map<String, List<String>> laptopCollection;
	ExpandableListView expListView;
	int cont = 0;

	public static String Categoria = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_planificador_opciones);

		Button btn_next = (Button) findViewById(id.buttonNextPlanf);

		createGroupList();

		createCollection();

		expListView = (ExpandableListView) findViewById(R.id.laptop_list);
		final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
				this, groupList, laptopCollection);
		expListView.setAdapter(expListAdapter);

		setGroupIndicatorToRight();
		expListView.expandGroup(0);

		expListView.setOnChildClickListener(new OnChildClickListener() {

			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				final String selected = (String) expListAdapter.getChild(
						groupPosition, childPosition);

				return true;
			}
		});

		btn_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (PlanificadorSelectCategoria.i != -1) {
					Intent intent = new Intent(
							PlanificadorOpcionesActivity.this,
							PlanificadorSelectCategoria.class);
					Bundle b = new Bundle();
					intent.putExtras(b);
					startActivity(intent);
				} else {
					Intent intent = new Intent(
							PlanificadorOpcionesActivity.this,
							Planificador_ExpandableListMainActivity.class);
					Bundle b = new Bundle();
					intent.putExtras(b);
					startActivity(intent);
				}

			}
		});

		// Categoria="";

		// expListView.expandGroup(1);
		// expListView.ex

	}

	private void createGroupList() {
		groupList = new ArrayList<String>();

		//
		groupList.add(PlanificadorSelectCategoria.categoria_seleccionada);
	}

	private void createCollection() {
		// preparing laptops collection(child)
		// String string_resp_serv=
		// "planificador,alquilar sonido, pedir prestado, contratar DJ";

		String string_resp_serv = Categoria;
		// System.out.println("Categoria con planificador: " +
		// string_resp_serv.toString());
		// String string_resp_serv = Categoria;
		// String st = string_resp_serv.substring(1,
		// 10);
		// System.out.println("Categoria sin planificador: "+ st.toString());

		String[] str = string_resp_serv.split(",");

		String auxiliar = "";

		for (int i = 0; i < str.length; i++) {
			if (i < str.length - 1)
				auxiliar = auxiliar + str[i] + ",";
			else
				auxiliar = auxiliar + str[i];
		}
		auxiliar = auxiliar + ";";

		String text = Planificador_ExpandableListMainActivity.entrada_planificador_lista
				+ PlanificadorSelectCategoria.categoria_seleccionada
				+ ","
				+ auxiliar;

		Planificador_ExpandableListMainActivity.entrada_planificador_lista = text;
		System.out
				.println("lista planificada: "
						+ Planificador_ExpandableListMainActivity.entrada_planificador_lista
								.toString());

		laptopCollection = new LinkedHashMap<String, List<String>>();

		for (String laptop : groupList) {
			loadChild(str);
			laptopCollection.put(laptop, childList);
		}

	}

	private void loadChild(String[] laptopModels) {
		childList = new ArrayList<String>();
		for (String model : laptopModels)
			childList.add(model);
	}

	private void setGroupIndicatorToRight() {
		/* Get the screen width */
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;

		expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
				- getDipsFromPixel(5));
	}

	// Convert pixel to dip
	public int getDipsFromPixel(float pixels) {
		// Get the screen's density scale
		final float scale = getResources().getDisplayMetrics().density;
		// Convert the dps to pixels, based on density scale
		return (int) (pixels * scale + 0.5f);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}