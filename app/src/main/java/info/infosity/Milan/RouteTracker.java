package info.infosity.Milan;


//importa le classi necessarie: location si occupa del gps,
//maps del far apparire la mappa a schermo (non sono certo della sua
// effettiva utilit�
import android.app.Activity;

import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.os.Handler;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

//import com.google.android.maps.MapController;
//import com.google.android.maps.MapView;

import android.content.Intent;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
//import android.os.PowerManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.*;

import info.infosity.Milan.generalDBHelper.GeneralDatabaseHelper;
import info.infosity.Milan.generalDBHelper.GeneralDbAdapter;
import info.infosity.Milan.generalDBHelper.GodOfDb;


public class RouteTracker extends Activity implements SensorEventListener
{
	//lon e lat sono longitudine e latitudine, edittext �
	//la classe predefinita per i bottoni
	public static Vector<Attrazioni> ottenuteserie;
	public static int cont;
	public static int cambio = 0;

	private Handler handler;

	private GeneralDbAdapter dbHelper;
	private Cursor cursor;


	private static final int TAKE_PICTURE_ID = 4;




	public static final int google_maps_api_key1=0x7f040001;

    public double l;
    public double lo;
    private ProgressBar progbar;

    private ImageButton scatto;
    private static Vector<Attrazioni> serie;

    private BearingFrameLayout bearingFrameLayout;

    //quattro variabili che richiamano le classi predefinite per il gps:
    private LocationManager locationManager;

   @Override
   public void onCreate(Bundle savedInstanceState)
   //oncreate c'� in tutti i programmi
   {

      super.onCreate(savedInstanceState);
	   requestWindowFeature(Window.FEATURE_NO_TITLE);
      setContentView(R.layout.loading);

      ottenuteserie = new Vector<Attrazioni>();
	   cambio = 0;

//      progbar = (ProgressBar) findViewById(R.id.progressBar1);

		GodOfDb god = new GodOfDb();
	    serie = god.getSerie();
        scatto = (ImageButton) findViewById(R.id.imageView2);
	   dbHelper = new GeneralDbAdapter(this);
	   dbHelper.open();

	   Cursor cursor = dbHelper.fetchAllContacts();

	   Integer num = cursor.getCount();
	   Log.i("contatore", "PRIMA nel db ci sono: " + num.toString());
	   if(cursor.getCount()<20)
	   {
		   riempiIlDb(serie, dbHelper);
	   }
	   cursor = dbHelper.fetchAllContacts();
	   num = cursor.getCount();
	   Log.i("contatore", "DOPO nel db ci sono: " + num.toString());

   }


	private void riempiIlDb(Vector<Attrazioni> s, GeneralDbAdapter dbh)
	{
		for(int i=0; i<s.size(); i++)
		{
			Attrazioni a = serie.get(i);
			String name = a.getNome();
			String gen = a.getGen();
			String desc = a.getDesc();
			String tec = a.getTec();
			double latitude = a.getLat();
			double longitude = a.getLon();
			double distance = a.getDistanza();
			dbh.createContact(name, gen, desc, tec, latitude, longitude, distance);
		}
	}


  // @Override
   public void onStart()
   {
      super.onStart();

     // altre parte presa dall'esempio: per ora non ci serve
      Criteria criteria = new Criteria();
      criteria.setAccuracy(Criteria.ACCURACY_FINE);
      criteria.setBearingRequired(true);
      criteria.setCostAllowed(true);
      criteria.setPowerRequirement(Criteria.POWER_LOW);
      criteria.setAltitudeRequired(false);

//le righe seguenti sono sempre uguali per ogni programma con gps
      locationManager =
         (LocationManager) getSystemService(LOCATION_SERVICE);

      locationManager.addGpsStatusListener(gpsStatusListener);

            String provider = locationManager.getBestProvider(criteria, true);


      locationManager.requestLocationUpdates(provider, 0, 0,
          locationListener);


     // bearingFrameLayout.invalidate();
   }



   private OnClickListener takePictureButtonListener =
		      new OnClickListener()
		      {
		         // launch image choosing activity
		         @Override
		         public void onClick(View v)
		         {
		            // create new Intent to launch the Slideshowplayer Activity
		            Intent takePicture =
		            		new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		            String value="pathname da definire";
					takePicture.putExtra(MediaStore.EXTRA_OUTPUT, value);


		            startActivityForResult(takePicture, TAKE_PICTURE_ID);
		         } // end method onClick
		      };

   private OnClickListener scattoSoloPerTest = new OnClickListener()
   {

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int niente =1;
		int ripetizione=0;
		cont =0;

		ottenuteserie = new Vector<Attrazioni>();

		/*AlertDialog.Builder dialogBuilder3 =
				new AlertDialog.Builder(RouteTracker.this);
		dialogBuilder3.setTitle("sono dentro sst");
		dialogBuilder3.setPositiveButton(
				R.string.button_ok, null);
		dialogBuilder3.show();*/

        for(int i=0;i<serie.size();i++)
        {
       	 Attrazioni questa = (Attrazioni) serie.get(i);
       	 double cl =questa.getLat();
       	 double clo =questa.getLon();
       	 double distanza = questa.getDistanza();
    	 double distLa= Math.abs(l - cl);
       	 double distLo= Math.abs(lo - clo);

       	    if((distLa<distanza)&(distLo<distanza)){
				//scrivi(questa);
       	    	niente=0;
       	    	cont++;
       	    	Double tot = distLa + distLo;
       	    	questa.setScarto(tot);

              	ottenuteserie.add(questa);
                Collections.sort(ottenuteserie, new Comparator<Attrazioni>() {
					public int compare(Attrazioni a1, Attrazioni a2) {
						Double d1 = Double.valueOf(a1.getScarto());
						Double d2 = Double.valueOf(a2.getScarto());
						return d1.compareTo(d2);
					}
				});
       	    }
        }



	    	Intent intent = new Intent(getApplicationContext(), Vids.class);
	    	startActivity(intent);


        if ((niente==1)&(ripetizione==0)){

   	    	ripetizione=1;

   	    }


	}
   };


   public void updateLocation(Location location)
   {
		   // prende nuova posizione a ogni spostamento(updateLocation
	   // � richiamato a ogni spostamento
         l = location.getLatitude();
         lo = location.getLongitude() ;
         if(l!=0&lo!=0&cambio==0)
         {
			 cambio=1;

			 scatto = (ImageButton) findViewById(R.id.imageView2);
		//	 scatto.setBackgroundResource(R.drawable.findex);
             scatto.setOnClickListener(scattoSoloPerTest);
			 scatto.performClick();
			 scatto.setBackgroundResource(R.drawable.founded);

			  ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);
			 pb.setVisibility(View.INVISIBLE);
		  }
       // bearingFrameLayout.invalidate();
	   //scatto.performClick();
    }



  //ora 5 metodi standard  che non ci servono quindi lascio vuoti
   private final LocationListener locationListener =
      new LocationListener()
   {

	   public void onLocationChanged(Location location)
      {
            updateLocation(location); //richiama metodo a ogni cambiamento
      }

      public void onProviderDisabled(String provider)
      {
      }

      public void onProviderEnabled(String provider)
      {
      }

      public void onStatusChanged(String provider,
         int status, Bundle extras)
      {
      }
   };

   GpsStatus.Listener gpsStatusListener = new GpsStatus.Listener()
   {
      public void onGpsStatusChanged(int event)
      {
         if (event == GpsStatus.GPS_EVENT_FIRST_FIX)
         {
           // gpsFix = true;
           // Toast results = Toast.makeText(RouteTracker.this,
             //  "signa",
              // Toast.LENGTH_SHORT);

            // center the Toast in the screen
          //  results.setGravity(Gravity.CENTER,
            //   results.getXOffset() / 2, results.getYOffset() / 2);
            //results.show(); // display the results
         } // end if
      } // end method on GpsStatusChanged
   }; // end anonymous inner class



   protected boolean isRouteDisplayed()
   {
      return false;
   }

   protected void onDestroy() {
	    super.onDestroy();

	  }

   public void onAccuracyChanged(Sensor sensor, int accuracy) {  }

   protected void onResume() {
	    super.onResume();
	  }

	  protected void onPause() {
	    super.onPause();
	   }


	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub

	}



}


