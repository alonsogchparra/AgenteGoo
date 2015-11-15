package com.example.agentegoo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agentegoo.agenda.Agenda_Activity;
import com.example.agentegoo.agenda.NuevoEvento_Activity;
import com.example.agentegoo.busqueda.Evento;
import com.example.agentegoo.busqueda.Node;
import com.example.agentegoo.cliente.TcpClient;
import com.example.agentegoo.planificacion.PlanificadorOpcionesActivity;
import com.example.agentegoo.planificacion.PlanificadorSelectCategoria;

@SuppressLint({ "DefaultLocale", "NewApi" })
public class _Goo extends Activity {

	String msg = "";

	ProgressDialog progressBar;
	private ImageButton imgbtnKeyboard;
	private ImageButton imgbtnMicro;
	private ImageButton imgbtnList;
	public static EditText editComando;
	private LinearLayout linearlayoutUser;
	private LinearLayout linearlayoutGoo;
	private TextView txtUser;
	private TextView txtGoo;
	private WebView browser;
	private boolean web = false;
	private boolean hacer_fiesta = false;
	public static boolean planifica = false;
	public static boolean estudiar = false;
	// Split
	public String[] separar;
	public String[] s_guion;
	public String caso1, caso2, caso3, caso4;
	protected static final int RESULT_SPEECH = 1;
	Node nodePorNombre;
	NotificationManager notificationManager;
	Notification myNotification;
	private static final int HELLO_ID = 1;

	// HomeActivity activity;
	// TextView tvUser;

	public String[] auxiliar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_goo);
		new ConnectTask().execute("");

		toast();

		// txtGoo.setText("");
		// txtUser.setText("");

		Agenda_Activity.mostrarListado = true;
		Main_Activity.treeFecha.add(new Evento("Odontologo",
				new GregorianCalendar(2013, 11, 10, 20, 10), 1, true));
		// nodePorNombre = Main_Activity.treeNombre.findNode("Homa");

		Thread thread = new Thread() {
			public void run() {
				while (true) {
					try {
						// do something here
						Calendar cal = Calendar.getInstance();

						// System.out.println("Calendario Actual: "+cal.);

						// System.out.println("Fecha: "+
						// "Ano "+cal.YEAR+" mes "+ cal.MONTH+
						// " dia "+cal.DAY_OF_WEEK +" hora "+ cal.HOUR
						// +" minutos "+ cal.MINUTE);

						Calendar c = Calendar.getInstance();
						// System.out.println("Current time => " + c.getTime());
						// SimpleDateFormat df1 = new SimpleDateFormat(
						// "dd-MMM-yyyy");
						// String formattedDate1 = df1.format(c.getTime());
						// SimpleDateFormat df2 = new SimpleDateFormat(
						// "dd-MM-yyyy");
						// String formattedDate2 = df2.format(c.getTime());
						// SimpleDateFormat df3 = new SimpleDateFormat(
						// "dd-MM-yyyy HH:mm:ss a");
						// String formattedDate3 = df3.format(c.getTime());
						// // System.out.println("=========> Date 1 => "
						// // + formattedDate1);
						// // System.out.println("=========> Date 2 => "
						// // + formattedDate2);
						// System.out.println("=========> Date 3 => "
						// + formattedDate3);

						Calendar ahoraCal = Calendar.getInstance();
						System.out.println("ANYO: "
								+ ahoraCal.get(Calendar.YEAR));
						System.out.println("MES: "
								+ ahoraCal.get(Calendar.MONTH));
						System.out.println("DIA: "
								+ ahoraCal.get(Calendar.DATE));
						System.out.println("HORA: "
								+ ahoraCal.get(Calendar.HOUR));
						System.out.println("MINUTOS: "
								+ ahoraCal.get(Calendar.MINUTE));
						int ano = ahoraCal.get(Calendar.YEAR);
						int mes = ahoraCal.get(Calendar.MONTH);
						int dia = ahoraCal.get(Calendar.DATE);
						int hora = ahoraCal.get(Calendar.HOUR_OF_DAY);
						int minutos = ahoraCal.get(Calendar.MINUTE);

						// Main_Activity.treeFecha.!=null{
						// System.out.println("tiene");
						//
						//
						// }

						if (ahoraCal.get(Calendar.SECOND) > 0
								&& ahoraCal.get(Calendar.SECOND) <= 59) {

							if (Main_Activity.treeFecha
									.findNode(new GregorianCalendar(ano, mes,
											dia, hora, minutos + 1)) != null) {
								// System.out.println(Main_Activity.treeFecha
								// .findNode(
								// new GregorianCalendar(2013, 10, 10,
								// 10, 10)).getElem()
								// .getNombre());
								System.out.println("si hay");
								mostrarNotBarra(
										Main_Activity.treeFecha
												.findNode(
														new GregorianCalendar(
																ano, mes, dia,
																hora,
																minutos + 1))
												.getElem().getNombre(),
										Main_Activity.treeFecha
												.findNode(
														new GregorianCalendar(
																ano, mes, dia,
																hora,
																minutos + 1))
												.getElem().getHoraString());
							} else {
								System.out
										.println("No hay eventos para esta hora");
							}
						}

						// String[] a = null;
						//
						// a = formattedDate3.split("-");

						// nodePorNombre = Main_Activity.treeFecha
						// .findNode(new GregorianCalendar());

						// if (Main_Activity.treeFecha.findNode(
						// new GregorianCalendar(ahoraCal
						// .get(Calendar.YEAR), ahoraCal
						// .get(Calendar.MONTH), ahoraCal
						// .get(Calendar.DATE), ahoraCal
						// .get(Calendar.HOUR), ahoraCal
						// .get(Calendar.MINUTE))).getElem().getNombre() !=
						// null)

						Thread.sleep(60000);
					} catch (InterruptedException e) {
						System.err.println("local Thread error");
					}
				}
			}
		};
		thread.start();

		final ImageButton ibtnPerfil = (ImageButton) findViewById(R.id.imageButtonEditarPerfil);
		final ImageButton ibtnAgenda = (ImageButton) findViewById(R.id.imageButtonAgenda);
		final ImageButton ibtnAyuda = (ImageButton) findViewById(R.id.imageButtonAyuda);
		imgbtnList = (ImageButton) findViewById(R.id.imageButtonAyuda);
		imgbtnKeyboard = (ImageButton) findViewById(R.id.imageButtonKeyboard);
		imgbtnMicro = (ImageButton) findViewById(R.id.ImageButtonMicro);
		editComando = (EditText) findViewById(R.id.editTextComando);
		editComando.requestFocus();
		txtGoo = (TextView) findViewById(R.id.TextViewGoo);
		txtUser = (TextView) findViewById(R.id.TextViewUser);
		linearlayoutUser = (LinearLayout) findViewById(R.id.linearLayout2);
		linearlayoutGoo = (LinearLayout) findViewById(R.id.LinearLayout01);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		editComando.setVisibility(View.INVISIBLE);
		linearlayoutGoo.setVisibility(View.INVISIBLE);
		linearlayoutUser.setVisibility(View.INVISIBLE);

		browser = (WebView) findViewById(R.id.webView);

		browser.setVisibility(View.INVISIBLE);

		// habilitamos javascript y el zoom
		browser.getSettings().setJavaScriptEnabled(true);
		browser.getSettings().setBuiltInZoomControls(true);

		// habilitamos los plugins (flash)
		browser.getSettings().setPluginsEnabled(true);

		ibtnAgenda.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(_Goo.this, Agenda_Activity.class);
				Bundle b = new Bundle();
				intent.putExtras(b);
				startActivity(intent);
				// Main_Activity.treeNombre.add(new Evento("Hola",
				// new GregorianCalendar(2013, 10, 10, 8, 5), 1, true));
				// Main_Activity.treeNombre.add(new Evento("Doctor",
				// new GregorianCalendar(2013, 10, 10, 9, 5), 1, true));
				// nodePorNombre = Main_Activity.treeNombre.findNode("Hola");
				//
				// List<Evento> listaPorFecha = Main_Activity.treeNombre
				// .depthFirstTraversal();
				// for (Evento t : listaPorFecha) {
				// System.out.println(t.getNombre().toString());
				// }

			}
		});

		ibtnAyuda.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(_Goo.this, Tutorial_Activity.class);
				Bundle b = new Bundle();
				intent.putExtras(b);
				startActivity(intent);

			}
		});

		ibtnPerfil.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Intent intent = new Intent(_Goo.this,
				// Planificacion_Activity.class);
				// Bundle b = new Bundle();
				// intent.putExtras(b);
				// startActivity(intent);
				// Intent intent = new Intent(_Goo.this,
				// PlanificadorSelectCategoria.class);
				// Bundle b = new Bundle();
				// intent.putExtras(b);
				// startActivity(intent);
				Main_Activity.treeFecha.add(new Evento("Odontologo",
						new GregorianCalendar(2013, 10, 10, 10, 10), 1, true));
				// nodePorNombre = Main_Activity.treeFecha
				// .findNode(new GregorianCalendar(2013, 10, 10, 10, 10));

			}
		});

		imgbtnList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// Intent intent = new Intent(_Goo.this, Hookup.class);
				// Bundle b = new Bundle();
				// intent.putExtras(b);
				// startActivity(intent);

			}
		});

		browser.setWebViewClient(new WebViewClient() {
			// evita que los enlaces se abran fuera nuestra app en el navegador
			// de android
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				return false;
			}

		});

		// Esto esto estaba comentado excepto loadActivity();

		// if (Main_Activity.mTcpClient != null) {
		// Main_Activity.mTcpClient.sendMessage("");
		// }
		// arrayList = new ArrayList<String>();

		loadActivity();

		// ---Probando la captura del nombre del logueo-------------

		// tvUser = (TextView) findViewById(R.id.TextViewUserName);
		// String un = getIntent().getExtras().getString("user");
		//
		// tvUser.setText(un);
		// ---------------------------------------------------------

	}

	private void loadActivity() {

		final Context context = this;

		imgbtnMicro.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(
						RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
				try {
					startActivityForResult(intent, RESULT_SPEECH);
				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"Opps! Your device doesn't support Speech to Text",
							Toast.LENGTH_SHORT);
					t.show();
				}

			}
		});

		imgbtnKeyboard.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				imgbtnMicro.setVisibility(View.INVISIBLE);
				imgbtnKeyboard.setVisibility(View.INVISIBLE);
				editComando.setVisibility(View.VISIBLE);

				// ----------------------
				// Probando EditComando para q no tenga nada cada vez q accedo
				editComando.setText("");

				// ----------------------

				// editComando.setText(editComando.getText().toString()
				// .toLowerCase());

				// browser.setVisibility(View.INVISIBLE);

			}
		});

		editComando.setOnKeyListener(new View.OnKeyListener() {

			public boolean onKey(View arg0, int arg1, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (arg1 == KeyEvent.KEYCODE_ENTER)) {

					editComando.setVisibility(View.INVISIBLE);
					imgbtnMicro.setVisibility(View.VISIBLE);
					imgbtnKeyboard.setVisibility(View.VISIBLE);
					getWindow()
							.setSoftInputMode(
									WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

					linearlayoutUser.setVisibility(View.VISIBLE);
					linearlayoutGoo.setVisibility(View.VISIBLE);

					txtUser.setText(editComando.getText().toString()
							.toLowerCase());
					String entrada = editComando.getText().toString()
							.toLowerCase();

					separar = entrada.toString().split(" ");

					for (int i = 0; i < separar.length; i++) {

						System.out.println(separar[i] + "\n");
					}

					// System.out.println(separar[0]);

					if (RecognizerIntent.ACTION_RECOGNIZE_SPEECH
							.equals("llamar")) {
						try {
							Intent i = new Intent(Intent.ACTION_DIAL);
							startActivity(i);
						} catch (Exception e) {
							e.printStackTrace();
						}

					}

					// -----------------------------------------------------------------------
					// Invocar Llamada

					if (separar[0].equals("llamar")) {
						try {
							Intent i = new Intent(Intent.ACTION_DIAL);
							startActivity(i);
						} catch (Exception e) {
							e.printStackTrace();
						}

					}

					// -----------------------------------------------------------------------

					// ------------------------------------------------------------------------
					// Invocar Mensajes

					if (separar[0].equals("enviar")
							&& separar[1].equals("mensaje")) {

						try {
							Intent sms = new Intent(Intent.ACTION_VIEW);
							sms.putExtra("sms_body", " ");
							sms.setType("vnd.android-dir/mms-sms");
							startActivity(sms);

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
					// ------------------------------------------------------------------------

					// -------------------------------------------------------------------------
					// Invocar lista de contactos

					if (separar[0].equals("lista")
							&& separar[2].equals("contactos")) {
						try {
							Intent contact = new Intent();
							contact.setAction(Intent.ACTION_VIEW);
							contact.setData(Uri
									.parse("content://contacts/people/"));
							startActivity(contact);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}

					if (separar[0].equals("agregar")) {
						browser.setVisibility(View.INVISIBLE);
						Intent intent = new Intent(_Goo.this,
								NuevoEvento_Activity.class);
						startActivity(intent);

					}
					if (separar[0].equals("apunta")) {

						Main_Activity.treeNombre.add(new Evento(separar[9],
								new GregorianCalendar(2013, Integer
										.parseInt(separar[4]) - 1, Integer
										.parseInt(separar[3]), 10, 00), 1, true));

						Main_Activity.treeFecha.add(new Evento(separar[9],
								new GregorianCalendar(2013, Integer
										.parseInt(separar[4]) - 1, Integer
										.parseInt(separar[3]), 10, 00), 1, true));
						txtGoo.setText("El Evento: " + separar[9]
								+ " se agrego satisfactoriamente.");
						Agenda_Activity.mostrarListado = true;
						browser.setVisibility(View.INVISIBLE);

					}

					if (editComando.getText().toString()
							.equals("quiero hacer una fiesta")) {

						hacer_fiesta = true;
						Intent intent = new Intent(_Goo.this,
								PlanificadorSelectCategoria.class);
						startActivity(intent);
						System.out.println("e");

					}

					if (editComando.getText().toString()
							.equals("quiero estudiar")) {

						estudiar = true;
						// Intent intent = new Intent(_Goo.this,
						// PlanificadorSelectCategoria.class);
						// startActivity(intent);
						// System.out.println("e");

					}

					// ---------------------------------------------------------------------

					tipoEntrada(editComando.getText().toString());

					if (txtGoo.getText().toString() != "Oops!" && web == true) {

						getWindow()
								.setSoftInputMode(
										WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

						final ProgressDialog myPd_ring = ProgressDialog.show(
								_Goo.this, "Un Momento, Por favor",
								"Consultando Base de Conocimientos..", true);
						myPd_ring.setCancelable(true);
						new Thread(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								try {
									Thread.sleep(2000);
									System.err.println("termino!!!!");
									// System.err.println(dialogo);
									if (txtGoo.getText().toString() == "Oops!") {
										System.err.println(txtGoo.getText()
												.toString());

									}

								} catch (Exception e) {
								}
								myPd_ring.dismiss();
							}
						}).start();
						//
					}

					return true;
				}
				return false;
			}

		});

	}

	@Override
	public void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	public class ConnectTask extends AsyncTask<String, String, TcpClient> {

		@Override
		protected TcpClient doInBackground(String... message) {

			// we create a TCPClient object and
			Main_Activity.mTcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
				@Override
				// here the messageReceived method is implemented
				public void messageReceived(String message) {
					// this method calls the onProgressUpdate

					publishProgress(message);
					msg = message.toString();
				}
			});
			Main_Activity.mTcpClient.run();

			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
			String str = values[0];

			msg = values[0] + "";

			if (hacer_fiesta) {
				// Planificacion_Activity.string_entrada = values[0] + "";
				hacer_fiesta = false;
				PlanificadorSelectCategoria.category = values[0].toString();
				System.out.println(values[0].toString());

				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(2000);
							Intent intent = new Intent(_Goo.this,
									PlanificadorSelectCategoria.class);
							startActivity(intent);

						} catch (Exception e) {
						}
					}
				}).start();

				System.out.println("Planificador! _Goo: "
						+ values[0].toString());

			}

			if (estudiar) {
				// txtGoo.setText(values[0].replace("planificador,", ""));
			}

			if (planifica) {
				// System.out.println("respuesta: " + values[0]);
				PlanificadorOpcionesActivity.Categoria = values[0];
				System.out
						.println("Planificador! _Goo planifica: " + values[0]);
				String str2 = PlanificadorOpcionesActivity.Categoria.toString();
				str2 = str2.replace("planificador,", "");
				PlanificadorOpcionesActivity.Categoria = str2.toString();
				// System.out.println("planificador sin planificador: "+values[0].toString().replace("planificador",
				// ""));
			}

			// if

			txtGoo.setText(msg);
			//
			imagenesWeb(msg);
			String[] array = msg.split(",");
			// En este momento tenemos un array en el que cada elemento es un
			// color.
			for (int i = 0; i < array.length; i++) {
				System.out.println(array[i]);

				if (array[i].equals("Deseas agregar uno?")) {

					txtGoo.setText("Oops!");
					crearDialogo(array[0]);

				}
			}

			// notify the adapter that the data set has changed. This means that
			// new message received
			// from server was added to the list

			// Estaba comentado OJO
			// mAdapter.notifyDataSetChanged();
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SPEECH: {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> text = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				linearlayoutUser.setVisibility(View.VISIBLE);
				linearlayoutGoo.setVisibility(View.VISIBLE);
				if (Main_Activity.mTcpClient != null) {
					Main_Activity.mTcpClient
							.sendMessage(text.get(0).toString().toLowerCase());
				}
				txtUser.setText(text.get(0));
				tipoEntrada(text.get(0).toString().toLowerCase());

				// -----------Captar Opciones de Voz-------------

				// --------------------Llamar--------------------
				if (text.get(0).toString().equals("llamar")) {
					try {
						data = new Intent(Intent.ACTION_DIAL);
						startActivity(data);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				// ----------Enviar Mensajes---------------------
				if (text.get(0).toString().equals("enviar mensaje")) {
					try {
						data = new Intent(Intent.ACTION_VIEW);
						data.putExtra("sms_body", " ");
						data.setType("vnd.android-dir/mms-sms");
						startActivity(data);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}

				// ---------Lista de Contactos--------------------
				if (text.get(0).toString().equals("lista de contactos")) {
					try {
						data = new Intent();
						data.setAction(Intent.ACTION_VIEW);
						data.setData(Uri.parse("content://contacts/people/"));
						startActivity(data);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				// -----------------------------------------------

			}
			break;
		}

		}

	}

	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void imagenesWeb(String msg) {
		String[] array = msg.split(" ");

		for (int i = 0; i < array.length; i++) {
			if (array[i].equals("pizza"))
				browser.loadUrl("http://imagenfinal.net/wp-content/uploads/2012/04/pizza.jpg");
			if (array[i].equals("patacones"))
				browser.loadUrl("http://cdn.noticiaaldia.com/wp-content/uploads/2013/05/PATAC%C3%93N.jpg");
			if (array[i].equals("pasta"))
				browser.loadUrl("http://www.comidayvinos.com/wp-content/uploads/2011/01/PASTA-CON-ATUN.jpg");
			if (array[i].equals("hamburguesas"))
				browser.loadUrl("http://1.bp.blogspot.com/_a7932umckrI/TRo5EuAODoI/AAAAAAAAAYE/uX0p1qXyDtI/s1600/hamburguesa.jpg");
			if (array[i].equals("torta"))
				browser.loadUrl("http://3.bp.blogspot.com/-foz3va8k134/Te0MJY3dxDI/AAAAAAAAC4Q/Wuxwt5V0KCg/s1600/0+IMG_0466.JPG");
			if (array[i].equals("mexicana"))
				browser.loadUrl("http://ladysoft.com.uy/wp-content/uploads/2012/08/link-71-mexico.jpg");
			if (array[i].equals("empanada"))
				browser.loadUrl("http://www.cervezadeargentina.com.ar/recetascomidas/imagenescomidas/empanadacerveza.jpg");
			if (array[i].equals("enfermo"))
				browser.loadUrl("http://www.diagnosticosmedicos.com/diagnosticoh.html");
			if (array[i].equals("perro caliente"))
				browser.loadUrl("http://www.top10de.com/wp-content/uploads/2012/08/hot-dog.jpg");
			if (array[i].equals("parrilla"))
				browser.loadUrl("http://us.123rf.com/400wm/400/400/alexsmith/alexsmith1109/alexsmith110900054/10602711-carne-de-cerdo-a-la-parrilla-en-un-plato-con-ensalada-verde.jpg");
			if (array[i].equals("china"))
				browser.loadUrl("http://l02paprm.files.wordpress.com/2011/01/combo4.jpg");
			if (array[i].equals("pera"))
				browser.loadUrl("http://karitostars18.files.wordpress.com/2012/03/peras-en-el-plato-blanco-thumb4927374.jpg");
			if (array[i].equals("galleta"))
				browser.loadUrl("http://static.recetasgratis.net/images/recetas/20091104213248.jpg");
		}
	}

	public void tipoEntrada(String comando) {
		String[] arrayEntrada = comando.toString().split(" ");
		String aux1 = "";

		String x = "quien, qué, quien, quién";
		String[] y = x.split(",");

		for (int i = 0; i < y.length; i++) {
			if (y[i].equals(arrayEntrada[0])) {
				System.err.println("es una busqueda web");
				web = true;
				break;
			}

		}

		if (web) {

			for (int i = 0; i < arrayEntrada.length; i++) {
				System.out.println(arrayEntrada[i]);
				if (i > 1) {
					aux1 = aux1 + " " + arrayEntrada[i];
				}

				txtGoo.setText("La respuesta a lo que me preguntas es: ");
				web = false;

			}

			browser.loadUrl("http://es.m.wikipedia.org/wiki/" + aux1.toString());

			browser.setVisibility(View.VISIBLE);
			getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		} else {
			browser.setVisibility(View.VISIBLE);
			String msg = "";
			if (Main_Activity.mTcpClient != null) {

				if (editComando.getText().toString().equals("me siento mal")) {
					imagenesWeb("enfermo");
					Main_Activity.mTcpClient.sendMessage(editComando.getText().toString()
							.toLowerCase());

				}

				if (editComando.getText().toString().equals("que hay pa hoy")) {
					msg = "que_hay_pa_hoy";
					Main_Activity.mTcpClient.sendMessage(msg.toString().toLowerCase());
					browser.setVisibility(View.INVISIBLE);
				} else {
					msg = editComando.getText().toString();
					Main_Activity.mTcpClient.sendMessage(msg.toString().toLowerCase());
				}

				if (editComando.getText().toString()
						.equals("quiero hacer una fiesta")) {

					// hacer_fiesta = true;
					// msg = "quiero hacer una fiesta";
					// System.out.println("entro a edit co");
					// Main_Activity.mTcpClient.sendMessage(msg.toString());
					// browser.setVisibility(View.INVISIBLE);
					// Intent intent = new Intent(_Goo.this,
					// PlanificadorSelectCategoria.class);
					// startActivity(intent);

				} else {
					msg = editComando.getText().toString().toLowerCase();
					Main_Activity.mTcpClient.sendMessage(msg.toString());
				}

				// WebView Invisible
				// ------------------------------------------------------

				if (separar[0].equals("quiero")) {
					try {
						browser.setVisibility(View.INVISIBLE);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (separar[0].equals("cual")
						&& separar[3].equals("probabilidad")) {
					try {
						browser.setVisibility(View.INVISIBLE);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// ----------------------------------------------------
				// Intento de llamar con voz
				if (editComando.getText().toString().equals("llamar")) {

					System.out.println("Llamame");
					try {
						Intent i = new Intent(Intent.ACTION_DIAL);
						startActivity(i);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (RecognizerIntent.ACTION_RECOGNIZE_SPEECH.equals("llamar")) {
					try {
						Intent i = new Intent(Intent.ACTION_DIAL);
						startActivity(i);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				if (RecognizerIntent.ACTION_RECOGNIZE_SPEECH
						.equals("enviar mensaje")) {
					try {
						Intent sms = new Intent(Intent.ACTION_VIEW);
						sms.putExtra("sms_body", " ");
						sms.setType("vnd.android-dir/mms-sms");
						startActivity(sms);
					} catch (Exception e) {
						// TODO: handle exception

					}
				}

				// ------------------------------------------------------

			}
			//
			new ConnectTask().execute("");

			imagenesWeb(editComando.getText().toString());
		}
	}

	public void crearDialogo(final String aux) {

		final String TAG = "DialogActivity";
		final int DLG_EXAMPLE1 = 0;
		final int TEXT_ID = 0;

		final String[] tipoComida = aux.split(" ");

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Advertencia");
		builder.setMessage("No conozco ningun lugar donde puedas comer "
				+ tipoComida[2]
				+ ". Si Deseas agregar uno, Introduce el Nombre");

		// Use an EditText view to get user input.
		final EditText input = new EditText(_Goo.this);
		input.setId(TEXT_ID);
		builder.setView(input);

		builder.setPositiveButton("Agregar",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						String value = input.getText().toString().toLowerCase();
						// Log.d(TAG, "User name: " + value);
						if (Main_Activity.mTcpClient != null) {

							String[] msg = editComando.getText().toString()
									.split(" ");

							Main_Activity.mTcpClient.sendMessage("comida," + tipoComida[2]
									+ "," + value);

						}

						return;
					}
				});

		builder.setNegativeButton("Cancelar",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						return;
					}
				});

		builder.show();
	}

	private void mostrarNotBarra(String nombre_Evento, String Fecha_evento) {
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// Agregando el icono, texto y momento para lanzar la notificaciÃ³n
		int icon = R.drawable._goo;
		CharSequence tickerText = "GOO! Notificación!";
		long when = System.currentTimeMillis();

		Notification notification = new Notification(icon, tickerText, when);

		Context context = getApplicationContext();
		CharSequence contentTitle = "Nuevo Evento: " + nombre_Evento;
		CharSequence contentText = "a las: " + Fecha_evento;

		// // Agregando sonido
		// notification.defaults |= Notification.DEFAULT_SOUND;

		// // Agregando vibraciÃ³n
		notification.defaults |= Notification.DEFAULT_VIBRATE;

		Intent notificationIntent = new Intent(this, _Goo.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);
		notification.setLatestEventInfo(context, contentTitle, contentText,
				contentIntent);

		mNotificationManager.notify(HELLO_ID, notification);

		MediaPlayer mp = MediaPlayer.create(context, R.raw.sound1);
		mp.start();
	}

	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("sali!");
		// Main_Activity.mTcpClient.sendMessage("quit");

		// insert here your instructions
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// Log.d("Test", "Back button pressed!");
			System.out.println("back");
		} else if (keyCode == KeyEvent.KEYCODE_HOME) {
			// Log.d("Test", "Home button pressed!");
			System.out.println("home");
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		System.out.println("press back");
		return;
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
		text.setText("Goo Tip: Diríjase al Tutorial para ver los Comandos...");

		// Toast...
		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();

	}

}
