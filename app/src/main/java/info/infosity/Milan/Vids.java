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

import info.infosity.Milan.Global.OftenUsed;
import info.infosity.Milan.generalDBHelper.GeneralDbAdapter;

public class Vids extends OftenUsed {

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
	private Button ritorno;
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
		setRitorno();
		serieDaMostrare = new Vector<Attrazioni>();
		serieDaMostrare = RouteTracker.getOttenuteserie();
		//Log.wtf("serie dimension" , "the series has a length of : " + serieDaMostrare.size());
		if(serieDaMostrare.size()>0) {
			int quanti = serieDaMostrare.size();
			if (quanti > 9) {
				quanti = 9;
			}
			creaAttrazioni();

			for (int i = 0; i < quanti; i++) {
				final int valore = i;
				Attrazioni questa = serieDaMostrare.get(i);
				vettoreBottoni.get(i).setText(questa.getName());
				vettoreBottoni.get(i).setVisibility(View.VISIBLE);
				vettoreBottoni.get(i).setOnClickListener(
						new OnClickListener() {
							@Override
							public void onClick(View v) {
								scrivi(attrazioniDaMostrare.get(valore));
								//Log.wtf("alive", "alive");
								nomeattrazione = attrazioniDaMostrare.get(valore).getName();
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
			alert.show();		}
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
		ritorno = (Button) findViewById(R.id.ritorno);
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


	/*
	(String name , String gen, String tec
            , String desc, double latitude, double longitude, double distance)
	 */
	private void scrivi(Attrazioni a)
	{
		dbHelper = new GeneralDbAdapter(this);
		dbHelper.open();
		Time now = new Time();
		now.setToNow();
		String time = now.format2445();
		a.setVisited("yes");
		dbHelper.updateContact( a.getLatitude(), a.getLongitude(), a.getDistanceNs(), a.getDistanceEw(),a.getLat1(),a.getLong1(),
			a.getLat2(), a.getLong2(), a.getLat3(), a.getLong3(), a.getLat4(), a.getLong4(), a.getLat5(),
			a.getLong5(), a.getLat6(), a.getLong6(), a.getLat7(), a.getLong7(), a.getLat8(), a.getLong8(),
			a.getLat9(), a.getLong9(), a.getLat10(), a.getLong10(), a.getLat11(),a.getLong11(), a.getLat12(),
			a.getLong12(), a.getName(), a.getType(),a.getCollections(), a.getAddress(), a.getPhone(),
			a.getOpening(), a.getClosed(), a.getPrice(), a.getGetHere(), a.getDescription(),a.getHistory(),a.getVisited() );
		dbHelper.close();
	}

	private void setRitorno()
	{
		ritorno.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getApplicationContext(), Preambolo.class);
					startActivity(intent);
				}
			}
	);
	}

}
