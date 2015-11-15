package com.example.agentegoo.planificacion;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agentegoo.Main_Activity;
import com.example.agentegoo.R;
import com.example.agentegoo._Goo;

public class PlanificadorSelectCategoria extends ListActivity {

	public static String category = "";
	public static Boolean bandera;
	public static int i = 0;
	public static String categoria_seleccionada = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		i = category.indexOf(",");
		PlanificadorSelectCategoria.category = category.substring(i + 1,
				category.length());

		String[] str = category.split(",");

		if (str.length == 1) {
			i = -1;
		}

		System.out.println("Planificador! SelectCate: "
				+ PlanificadorSelectCategoria.category);
		// for (int i = 0; i < str.length; i++) {
		// System.out
		// .println("Planificador! SelectCate: " + str[i].toString());
		// }

		setListAdapter(new ArrayAdap(this, str));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		String selectedValue = (String) getListAdapter().getItem(position);

		// Toast.makeText(this, "Espere un momento...",
		// Toast.LENGTH_SHORT).show();

		toast();
		categoria_seleccionada = selectedValue.toString();
		_Goo.planifica = true;
		Main_Activity.mTcpClient.sendMessage("planificador," + selectedValue.toString());

		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(2000);
					Intent intent = new Intent(
							PlanificadorSelectCategoria.this,
							PlanificadorOpcionesActivity.class);
					Bundle b = new Bundle();
					intent.putExtras(b);
					startActivity(intent);

				} catch (Exception e) {
				}
			}
		}).start();

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
		text.setText("Espere un momento...");

		// Toast...
		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(layout);
		toast.show();

	}

}