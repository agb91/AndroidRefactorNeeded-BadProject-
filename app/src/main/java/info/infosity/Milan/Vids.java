package info.infosity.Milan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

import java.util.Vector;

import info.infosity.Milan.generalDBHelper.GeneralDbAdapter;

public class Vids extends Activity {

	private Button bottone1;
	private Button bottone2;
	private Button bottone3;
	private Button bottone4;
	private Button bottone5;
	private Button bottone6;
	private Button bottone7;
	private Button bottone8;
	private Button bottone9;
	private Button bottone10;
	private Vector<Button> vettoreBottoni;
	private Vector<Attrazioni> serieDaMostrare;
	private GeneralDbAdapter dbHelper;
	private Cursor cursor;
	private Attrazioni atr1;
	private Attrazioni atr2;
	private Attrazioni atr3;
	private Attrazioni atr4;
	private Attrazioni atr5;
	private Attrazioni atr6;
	private Attrazioni atr7;
	private Attrazioni atr8;
	private Attrazioni atr9;
	private Attrazioni atr10;
	private Vector<Attrazioni> attrazioniDaMostrare = new Vector<Attrazioni>();

	public static String nomeattrazione;
	public static int numVettoreAttrazione;

    public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		vettoreBottoni = new Vector<Button>();
		creaBottoni();
		riempiBottoni();
		serieDaMostrare = new Vector<Attrazioni>();
		serieDaMostrare = RouteTracker.getOttenuteserie();
		//Log.i("seriedim" , "la serie è grandde : " + serieDaMostrare.size());
		if(serieDaMostrare.size()>0) {
			int quanti = serieDaMostrare.size();
			if (quanti > 9) {
				quanti = 9;
			}
			creaAttrazioni();

			for (int i = 0; i < quanti; i++) {
				final int valore = i;
				Attrazioni questa = serieDaMostrare.get(i);
				vettoreBottoni.get(i).setText(questa.getNome());
				vettoreBottoni.get(i).setVisibility(View.VISIBLE);
				vettoreBottoni.get(i).setOnClickListener(
						new OnClickListener() {
							@Override
							public void onClick(View v) {
								scrivi(attrazioniDaMostrare.get(valore));
								nomeattrazione = attrazioniDaMostrare.get(valore).getNome();
								numVettoreAttrazione = valore;
								Intent intent = new Intent(getApplicationContext(), Atr.class);
								startActivity(intent);
							}
						}
				);
			}
		}
		else
		{
			final AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Nothing found. Go back home?")
					.setCancelable(false)
					.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
							Intent intent = new Intent(getApplicationContext(), Preambolo.class);
							startActivity(intent);
						}
					})
					.setNegativeButton("No", new DialogInterface.OnClickListener() {
						public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
							dialog.cancel();
						}
					});
			final AlertDialog alert = builder.create();
			alert.show();
		}
	}

	public Vector<Attrazioni> getAttrazioniDaMostrare()
	{
		return attrazioniDaMostrare;
	}

	private void creaBottoni()
	{
		bottone1=(Button) findViewById(R.id.nome1);
		bottone2=(Button) findViewById(R.id.nome2);
		bottone3=(Button) findViewById(R.id.nome3);
		bottone4=(Button) findViewById(R.id.nome4);
		bottone5=(Button) findViewById(R.id.nome5);
		bottone6=(Button) findViewById(R.id.nome6);
		bottone7=(Button) findViewById(R.id.nome7);
		bottone8=(Button) findViewById(R.id.nome8);
		bottone9=(Button) findViewById(R.id.nome9);
		bottone10=(Button) findViewById(R.id.nome10);
	}

	private void creaAttrazioni()
	{
		attrazioniDaMostrare.add(atr1);
		attrazioniDaMostrare.add(atr2);
		attrazioniDaMostrare.add(atr3);
		attrazioniDaMostrare.add(atr4);
		attrazioniDaMostrare.add(atr5);
		attrazioniDaMostrare.add(atr6);
		attrazioniDaMostrare.add(atr7);
		attrazioniDaMostrare.add(atr8);
		attrazioniDaMostrare.add(atr9);
		int quanti = serieDaMostrare.size();
		if(quanti>9)
		{
			quanti=9;
		}
		for(int i = 0; i<quanti; i++)
		{
			//Log.i("DIM VETTORI: " , " DIM SERIE: " + serieDaMostrare.size() + ";  atr da mostrare: " + attrazioniDaMostrare.size());
			attrazioniDaMostrare.set(i,serieDaMostrare.get(i));
		}
		/*atr1 = serieDaMostrare.get(0);
		atr2 = serieDaMostrare.get(1);
		atr3 = serieDaMostrare.get(2);
		atr4 = serieDaMostrare.get(3);
		atr5 = serieDaMostrare.get(4);
		atr6 = serieDaMostrare.get(5);
		atr7 = serieDaMostrare.get(6);
		atr8 = serieDaMostrare.get(7);
		atr9 = serieDaMostrare.get(8);
		atr10 = serieDaMostrare.get(9);*/
	}

	private void riempiAttrazioni()
	{
		attrazioniDaMostrare.add(atr1);
		attrazioniDaMostrare.add(atr2);
		attrazioniDaMostrare.add(atr3);
		attrazioniDaMostrare.add(atr4);
		attrazioniDaMostrare.add(atr5);
		attrazioniDaMostrare.add(atr6);
		attrazioniDaMostrare.add(atr7);
		attrazioniDaMostrare.add(atr8);
		attrazioniDaMostrare.add(atr9);
		attrazioniDaMostrare.add(atr10);
	}

	private void riempiBottoni()
	{
		vettoreBottoni.add(bottone1);
		vettoreBottoni.add(bottone2);
		vettoreBottoni.add(bottone3);
		vettoreBottoni.add(bottone4);
		vettoreBottoni.add(bottone5);
		vettoreBottoni.add(bottone6);
		vettoreBottoni.add(bottone7);
		vettoreBottoni.add(bottone8);
		vettoreBottoni.add(bottone9);
		vettoreBottoni.add(bottone10);
	}


	private void scrivi(Attrazioni a)
	{
		dbHelper = new GeneralDbAdapter(this);
		dbHelper.open();
		Time now = new Time();
		now.setToNow();
		dbHelper.close();
	}


/*	private OnClickListener toccato =
		  new OnClickListener()
	{
	 @Override
	 public void onClick(View v)
	 {
		Intent intent = new Intent(getApplicationContext(), RouteTracker.class);
		startActivity(intent);
	 } // end method onClick
	};*/

}
