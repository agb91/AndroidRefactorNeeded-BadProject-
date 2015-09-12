package info.infosity.Milan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

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

	private DbAdapter dbHelper;
	private Cursor cursor;
	
//	private Button indietro;
	
	public static String nomeattrazione;
	public static int numVettoreAttrazione; 
	
    public void onCreate(Bundle savedInstanceState){

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);


	//	indietro = (Button) findViewById(R.id.ritorno);
		
	//	indietro.setOnClickListener(toccato);

		
		String parola = String.valueOf(RouteTracker.cont);

		if(parola.equals("0"))
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

		if(parola.equals("1"))
		{
			bottone1=(Button) findViewById(R.id.nome1);
			bottone1.setOnClickListener(primo);
			bottone1.setText(RouteTracker.ottenuteserie.get(0).getNome());
			bottone1.setVisibility(View.VISIBLE);
	    }
		
		if(parola.equals("2"))
		{
			bottone1=(Button) findViewById(R.id.nome1);
			bottone1.setOnClickListener(primo);
			bottone1.setText(RouteTracker.ottenuteserie.get(0).getNome());
			bottone1.setVisibility(View.VISIBLE);
			bottone2=(Button) findViewById(R.id.nome2);
			bottone2.setOnClickListener(secondo);
			bottone2.setText(RouteTracker.ottenuteserie.get(1).getNome());
			bottone2.setVisibility(View.VISIBLE);
			
	    }
		
		if(parola.equals("3"))
		{
			bottone1=(Button) findViewById(R.id.nome1);
			bottone1.setOnClickListener(primo);
			bottone1.setText(RouteTracker.ottenuteserie.get(0).getNome());
			bottone1.setVisibility(View.VISIBLE);
			bottone2=(Button) findViewById(R.id.nome2);
			bottone2.setOnClickListener(secondo);
			bottone2.setText(RouteTracker.ottenuteserie.get(1).getNome());
			bottone2.setVisibility(View.VISIBLE);
			bottone3=(Button) findViewById(R.id.nome3);
			bottone3.setOnClickListener(terzo);
			bottone3.setText(RouteTracker.ottenuteserie.get(2).getNome());
			bottone3.setVisibility(View.VISIBLE);
			
	    }
		
		if(parola.equals("4"))
		{
			bottone1=(Button) findViewById(R.id.nome1);
			bottone1.setOnClickListener(primo);
			bottone1.setText(RouteTracker.ottenuteserie.get(0).getNome());
			bottone1.setVisibility(View.VISIBLE);
			bottone2=(Button) findViewById(R.id.nome2);
			bottone2.setOnClickListener(secondo);
			bottone2.setText(RouteTracker.ottenuteserie.get(1).getNome());
			bottone2.setVisibility(View.VISIBLE);
			bottone3=(Button) findViewById(R.id.nome3);
			bottone3.setOnClickListener(terzo);
			bottone3.setText(RouteTracker.ottenuteserie.get(2).getNome());
			bottone3.setVisibility(View.VISIBLE);
			bottone4=(Button) findViewById(R.id.nome4);
			bottone4.setOnClickListener(quarto);
			bottone4.setText(RouteTracker.ottenuteserie.get(3).getNome());
			bottone4.setVisibility(View.VISIBLE);
			
	    }
		
		if(parola.equals("5"))
		{
			bottone1=(Button) findViewById(R.id.nome1);
			bottone1.setOnClickListener(primo);
			bottone1.setText(RouteTracker.ottenuteserie.get(0).getNome());
			bottone1.setVisibility(View.VISIBLE);
			bottone2=(Button) findViewById(R.id.nome2);
			bottone2.setOnClickListener(secondo);
			bottone2.setText(RouteTracker.ottenuteserie.get(1).getNome());
			bottone2.setVisibility(View.VISIBLE);
			bottone3=(Button) findViewById(R.id.nome3);
			bottone3.setOnClickListener(terzo);
			bottone3.setText(RouteTracker.ottenuteserie.get(2).getNome());
			bottone3.setVisibility(View.VISIBLE);
			bottone4=(Button) findViewById(R.id.nome4);
			bottone4.setOnClickListener(quarto);
			bottone4.setText(RouteTracker.ottenuteserie.get(3).getNome());
			bottone4.setVisibility(View.VISIBLE);
			bottone5=(Button) findViewById(R.id.nome5);
			bottone5.setOnClickListener(quinto);
			bottone5.setText(RouteTracker.ottenuteserie.get(4).getNome());
			bottone5.setVisibility(View.VISIBLE);
			
		}
		
		if(parola.equals("6"))
		{
			bottone1=(Button) findViewById(R.id.nome1);
			bottone1.setOnClickListener(primo);
			bottone1.setText(RouteTracker.ottenuteserie.get(0).getNome());
			bottone1.setVisibility(View.VISIBLE);
			bottone2=(Button) findViewById(R.id.nome2);
			bottone2.setOnClickListener(secondo);
			bottone2.setText(RouteTracker.ottenuteserie.get(1).getNome());
			bottone2.setVisibility(View.VISIBLE);
			bottone3=(Button) findViewById(R.id.nome3);
			bottone3.setOnClickListener(terzo);
			bottone3.setText(RouteTracker.ottenuteserie.get(2).getNome());
			bottone3.setVisibility(View.VISIBLE);
			bottone4=(Button) findViewById(R.id.nome4);
			bottone4.setOnClickListener(quarto);
			bottone4.setText(RouteTracker.ottenuteserie.get(3).getNome());
			bottone4.setVisibility(View.VISIBLE);
			bottone5=(Button) findViewById(R.id.nome5);
			bottone5.setOnClickListener(quinto);
			bottone5.setText(RouteTracker.ottenuteserie.get(4).getNome());
			bottone5.setVisibility(View.VISIBLE);
			bottone6=(Button) findViewById(R.id.nome6);
			bottone6.setOnClickListener(sesto);
			bottone6.setText(RouteTracker.ottenuteserie.get(5).getNome());
			bottone6.setVisibility(View.VISIBLE);
			
	    }
		
		if(parola.equals("7"))
		{
			bottone1=(Button) findViewById(R.id.nome1);
			bottone1.setOnClickListener(primo);
			bottone1.setText(RouteTracker.ottenuteserie.get(0).getNome());
			bottone1.setVisibility(View.VISIBLE);
			bottone2=(Button) findViewById(R.id.nome2);
			bottone2.setOnClickListener(secondo);
			bottone2.setText(RouteTracker.ottenuteserie.get(1).getNome());
			bottone2.setVisibility(View.VISIBLE);
			bottone3=(Button) findViewById(R.id.nome3);
			bottone3.setOnClickListener(terzo);
			bottone3.setText(RouteTracker.ottenuteserie.get(2).getNome());
			bottone3.setVisibility(View.VISIBLE);
			bottone4=(Button) findViewById(R.id.nome4);
			bottone4.setOnClickListener(quarto);
			bottone4.setText(RouteTracker.ottenuteserie.get(3).getNome());
			bottone4.setVisibility(View.VISIBLE);
			bottone5=(Button) findViewById(R.id.nome5);
			bottone5.setOnClickListener(quinto);
			bottone5.setText(RouteTracker.ottenuteserie.get(4).getNome());
			bottone5.setVisibility(View.VISIBLE);
			bottone6=(Button) findViewById(R.id.nome6);
			bottone6.setOnClickListener(sesto);
			bottone6.setText(RouteTracker.ottenuteserie.get(5).getNome());
			bottone6.setVisibility(View.VISIBLE);
			bottone7=(Button) findViewById(R.id.nome7);
			bottone7.setOnClickListener(settimo);
			bottone7.setText(RouteTracker.ottenuteserie.get(6).getNome());
			bottone7.setVisibility(View.VISIBLE);
			
	    }
		
		if(parola.equals("8"))
		{
			bottone1=(Button) findViewById(R.id.nome1);
			bottone1.setOnClickListener(primo);
			bottone1.setText(RouteTracker.ottenuteserie.get(0).getNome());
			bottone1.setVisibility(View.VISIBLE);
			bottone2=(Button) findViewById(R.id.nome2);
			bottone2.setOnClickListener(secondo);
			bottone2.setText(RouteTracker.ottenuteserie.get(1).getNome());
			bottone2.setVisibility(View.VISIBLE);
			bottone3=(Button) findViewById(R.id.nome3);
			bottone3.setOnClickListener(terzo);
			bottone3.setText(RouteTracker.ottenuteserie.get(2).getNome());
			bottone3.setVisibility(View.VISIBLE);
			bottone4=(Button) findViewById(R.id.nome4);
			bottone4.setOnClickListener(quarto);
			bottone4.setText(RouteTracker.ottenuteserie.get(3).getNome());
			bottone4.setVisibility(View.VISIBLE);
			bottone5=(Button) findViewById(R.id.nome5);
			bottone5.setOnClickListener(quinto);
			bottone5.setText(RouteTracker.ottenuteserie.get(4).getNome());
			bottone5.setVisibility(View.VISIBLE);
			bottone6=(Button) findViewById(R.id.nome6);
			bottone6.setOnClickListener(sesto);
			bottone6.setText(RouteTracker.ottenuteserie.get(5).getNome());
			bottone6.setVisibility(View.VISIBLE);
			bottone7=(Button) findViewById(R.id.nome7);
			bottone7.setOnClickListener(settimo);
			bottone7.setText(RouteTracker.ottenuteserie.get(6).getNome());
			bottone7.setVisibility(View.VISIBLE);
			bottone8=(Button) findViewById(R.id.nome8);
			bottone8.setOnClickListener(ottavo);
			bottone8.setText(RouteTracker.ottenuteserie.get(7).getNome());
			bottone8.setVisibility(View.VISIBLE);
			
	    }
		


		if(parola.equals("9"))
		{
			bottone1=(Button) findViewById(R.id.nome1);
			bottone1.setOnClickListener(primo);
			bottone1.setText(RouteTracker.ottenuteserie.get(0).getNome());
			bottone1.setVisibility(View.VISIBLE);
			bottone2=(Button) findViewById(R.id.nome2);
			bottone2.setOnClickListener(secondo);
			bottone2.setText(RouteTracker.ottenuteserie.get(1).getNome());
			bottone2.setVisibility(View.VISIBLE);
			bottone3=(Button) findViewById(R.id.nome3);
			bottone3.setOnClickListener(terzo);
			bottone3.setText(RouteTracker.ottenuteserie.get(2).getNome());
			bottone3.setVisibility(View.VISIBLE);
			bottone4=(Button) findViewById(R.id.nome4);
			bottone4.setOnClickListener(quarto);
			bottone4.setText(RouteTracker.ottenuteserie.get(3).getNome());
			bottone4.setVisibility(View.VISIBLE);
			bottone5=(Button) findViewById(R.id.nome5);
			bottone5.setOnClickListener(quinto);
			bottone5.setText(RouteTracker.ottenuteserie.get(4).getNome());
			bottone5.setVisibility(View.VISIBLE);
			bottone6=(Button) findViewById(R.id.nome6);
			bottone6.setOnClickListener(sesto);
			bottone6.setText(RouteTracker.ottenuteserie.get(5).getNome());
			bottone6.setVisibility(View.VISIBLE);
			bottone7=(Button) findViewById(R.id.nome7);
			bottone7.setOnClickListener(settimo);
			bottone7.setText(RouteTracker.ottenuteserie.get(6).getNome());
			bottone7.setVisibility(View.VISIBLE);
			bottone8=(Button) findViewById(R.id.nome8);
			bottone8.setOnClickListener(ottavo);
			bottone8.setText(RouteTracker.ottenuteserie.get(7).getNome());
			bottone8.setVisibility(View.VISIBLE);
			bottone9=(Button) findViewById(R.id.nome9);
			bottone9.setOnClickListener(nono);
			bottone9.setText(RouteTracker.ottenuteserie.get(8).getNome());
			bottone9.setVisibility(View.VISIBLE);
	
			
	    }
		
		if(parola.equals("10")|| Double.valueOf(parola)>=11)
		{
			bottone1=(Button) findViewById(R.id.nome1);
			bottone1.setOnClickListener(primo);
			bottone1.setText(RouteTracker.ottenuteserie.get(0).getNome());
			bottone1.setVisibility(View.VISIBLE);
			bottone2=(Button) findViewById(R.id.nome2);
			bottone2.setOnClickListener(secondo);
			bottone2.setText(RouteTracker.ottenuteserie.get(1).getNome());
			bottone2.setVisibility(View.VISIBLE);
			bottone3=(Button) findViewById(R.id.nome3);
			bottone3.setOnClickListener(terzo);
			bottone3.setText(RouteTracker.ottenuteserie.get(2).getNome());
			bottone3.setVisibility(View.VISIBLE);
			bottone4=(Button) findViewById(R.id.nome4);
			bottone4.setOnClickListener(quarto);
			bottone4.setText(RouteTracker.ottenuteserie.get(3).getNome());
			bottone4.setVisibility(View.VISIBLE);
			bottone5=(Button) findViewById(R.id.nome5);
			bottone5.setOnClickListener(quinto);
			bottone5.setText(RouteTracker.ottenuteserie.get(4).getNome());
			bottone5.setVisibility(View.VISIBLE);
			bottone6=(Button) findViewById(R.id.nome6);
			bottone6.setOnClickListener(sesto);
			bottone6.setText(RouteTracker.ottenuteserie.get(5).getNome());
			bottone6.setVisibility(View.VISIBLE);
			bottone7=(Button) findViewById(R.id.nome7);
			bottone7.setOnClickListener(settimo);
			bottone7.setText(RouteTracker.ottenuteserie.get(6).getNome());
			bottone7.setVisibility(View.VISIBLE);
			bottone8=(Button) findViewById(R.id.nome8);
			bottone8.setOnClickListener(ottavo);
			bottone8.setText(RouteTracker.ottenuteserie.get(7).getNome());
			bottone8.setVisibility(View.VISIBLE);
			bottone9=(Button) findViewById(R.id.nome9);
			bottone9.setOnClickListener(nono);
			bottone9.setText(RouteTracker.ottenuteserie.get(8).getNome());
			bottone9.setVisibility(View.VISIBLE);
			bottone10=(Button) findViewById(R.id.nome10);
			bottone10.setOnClickListener(decimo);
			bottone10.setText(RouteTracker.ottenuteserie.get(9).getNome());
			bottone10.setVisibility(View.VISIBLE);
		
			
	    }
	

	}

	private void scrivi(Attrazioni a)
	{
		dbHelper = new DbAdapter(this);
		dbHelper.open();
		Time now = new Time();
		now.setToNow();
		//  Calendar c = Calendar.getInstance();
		// int seconds = c.get(Calendar.SECOND);

		dbHelper.createContact(a.getNome(), now.toString());

		dbHelper.close();
	}
	
	private OnClickListener toccato =
		      new OnClickListener()
		      {
		         // launch image choosing activity
		         @Override
		         public void onClick(View v)
		         {
		            Intent intent = new Intent(getApplicationContext(), RouteTracker.class);
		            startActivity(intent);
		         } // end method onClick
		      };
		      
	private OnClickListener primo =
			new OnClickListener()
				{
				 @Override
				 public void onClick(View v)
				     {
						 scrivi(RouteTracker.ottenuteserie.get(0));
				        nomeattrazione=RouteTracker.ottenuteserie.get(0).getNome();
				        numVettoreAttrazione=0;
				        Intent intent = new Intent(getApplicationContext(), Atr.class);
				       startActivity(intent);
				     } // end method onClick
				 };
				 
	private OnClickListener secondo =
			new OnClickListener()
				{
				@Override
					public void onClick(View v)
						{
							scrivi(RouteTracker.ottenuteserie.get(1));
							nomeattrazione=RouteTracker.ottenuteserie.get(1).getNome();
							numVettoreAttrazione=1;
							Intent intent = new Intent(getApplicationContext(), Atr.class);
							startActivity(intent);
						} // end method onClick
					};
					
		private OnClickListener terzo =
				new OnClickListener()
					{
					@Override
					public void onClick(View v)
							{
								scrivi(RouteTracker.ottenuteserie.get(2));
								nomeattrazione=RouteTracker.ottenuteserie.get(2).getNome();
								numVettoreAttrazione=2;
								Intent intent = new Intent(getApplicationContext(), Atr.class);
									
										startActivity(intent);
							} // end method onClick
					};				
		      

		private OnClickListener quarto =
				new OnClickListener()
					{
					@Override
					public void onClick(View v)
							{
								scrivi(RouteTracker.ottenuteserie.get(3));
								nomeattrazione=RouteTracker.ottenuteserie.get(3).getNome();
									numVettoreAttrazione=3;
									Intent intent = new Intent(getApplicationContext(), Atr.class);
									startActivity(intent);
							} // end method onClick
					};	
	
		private OnClickListener quinto =
				new OnClickListener()
					{
					@Override
					public void onClick(View v)
							{
								scrivi(RouteTracker.ottenuteserie.get(4));

								nomeattrazione=RouteTracker.ottenuteserie.get(4).getNome();
								numVettoreAttrazione=4;
								Intent intent = new Intent(getApplicationContext(), Atr.class);
								startActivity(intent);
							} // end method onClick
					};	
	
					
		private OnClickListener sesto =
				new OnClickListener()
					{
					@Override
					public void onClick(View v)
							{
								scrivi(RouteTracker.ottenuteserie.get(5));

								nomeattrazione=RouteTracker.ottenuteserie.get(5).getNome();
									numVettoreAttrazione=5;
									Intent intent = new Intent(getApplicationContext(), Atr.class);
									startActivity(intent);
							} // end method onClick
					};	
					
		private OnClickListener settimo =
				new OnClickListener()
					{
					@Override
					public void onClick(View v)
							{
								scrivi(RouteTracker.ottenuteserie.get(6));

								nomeattrazione=RouteTracker.ottenuteserie.get(6).getNome();
									numVettoreAttrazione=6;
									Intent intent = new Intent(getApplicationContext(), Atr.class);
									startActivity(intent);
							} // end method onClick
					};	
					
		private OnClickListener ottavo =
				new OnClickListener()
					{
					@Override
					public void onClick(View v)
							{
								scrivi(RouteTracker.ottenuteserie.get(7));

								nomeattrazione=RouteTracker.ottenuteserie.get(7).getNome();
									numVettoreAttrazione=7;
									Intent intent = new Intent(getApplicationContext(), Atr.class);
									startActivity(intent);
							} // end method onClick
					};	
					
		private OnClickListener nono =
				new OnClickListener()
					{
					@Override
					public void onClick(View v)
							{
								scrivi(RouteTracker.ottenuteserie.get(8));

								nomeattrazione=RouteTracker.ottenuteserie.get(8).getNome();
									numVettoreAttrazione=8;
									Intent intent = new Intent(getApplicationContext(), Atr.class);
									startActivity(intent);
						} // end method onClick
					};
					
		private OnClickListener decimo =
				new OnClickListener()
					{
					@Override
						public void onClick(View v)
						{
							scrivi(RouteTracker.ottenuteserie.get(9));

							nomeattrazione=RouteTracker.ottenuteserie.get(9).getNome();
								numVettoreAttrazione=9;
								Intent intent = new Intent(getApplicationContext(), Atr.class);
								startActivity(intent);
						} // end method onClick
					};	

}
