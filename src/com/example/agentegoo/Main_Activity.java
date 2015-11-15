package com.example.agentegoo;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.agentegoo._Goo.ConnectTask;
import com.example.agentegoo.busqueda.MyTreeFecha;
import com.example.agentegoo.busqueda.MyTreeNombre;
import com.example.agentegoo.cliente.TcpClient;

public class Main_Activity extends Activity {
	final public static MyTreeNombre treeNombre = new MyTreeNombre();
	final public static MyTreeFecha treeFecha = new MyTreeFecha();
	public static boolean tipoBusqueda = false;
	final public static List<String> list = new ArrayList<String>();
	final static Date horaActual = new Date();
	final static GregorianCalendar date = new GregorianCalendar();

	public static TcpClient mTcpClient;

	Timer timer;
	boolean enProgreso;
	Handler handler;
	ProgressBar pb;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash_screen);

		// Arranco server
		if (mTcpClient != null) {
			mTcpClient.sendMessage("");
		}

		// Button btnsig = (Button) findViewById(R.id.btnSigBienvenido);

		pb = (ProgressBar) findViewById(R.id.progressBarSplash);
		pb.setMax(100);
		pb.setProgress(0);

		handler = new Handler();
		Contador();

	}

	public void progreso() {
		int n = pb.getProgress() + 1;
		pb.setProgress(n);

		if (n == 40) {
			enProgreso = false;
			Intent intent = new Intent(Main_Activity.this, _Goo.class);
			Bundle b = new Bundle();
			intent.putExtras(b);
			startActivity(intent);
		}
	}

	public void Contador() {

		pb.setProgress(0);
		enProgreso = true;

		Runnable tarea = new Runnable() {
			@Override
			public void run() {

				progreso();
				if (enProgreso) {
					handler.postDelayed(this, 100);
				}
			}
		};

		handler.postDelayed(tarea, 100);
	}

	public void Borrar_Eventos_Pasados() {

		treeNombre.delete_eventos_pasados(new GregorianCalendar());
		treeFecha.delete_eventos_pasados(new GregorianCalendar());
	}

}
