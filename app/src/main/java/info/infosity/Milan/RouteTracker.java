	package info.infosity.Milan;

	import android.app.Activity;

	import android.app.AlertDialog;
	import android.content.DialogInterface;
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

	import info.infosity.Milan.Global.Globals;
	import info.infosity.Milan.generalDBHelper.GeneralDatabaseHelper;
	import info.infosity.Milan.generalDBHelper.GeneralDbAdapter;
	import info.infosity.Milan.generalDBHelper.GodOfDb;


	public class RouteTracker extends Activity implements SensorEventListener
	{
		private static Vector<Attrazioni> ottenuteserie;
		private static int cont;
		private static int cambio = 0;

		private Handler handler;

		private static final int TAKE_PICTURE_ID = 4;

		public static final int google_maps_api_key1=0x7f040001;

		private double l;
		private double lo;
		private ProgressBar progbar;

		private ImageButton scatto;
		private static Vector<Attrazioni> serie;

		private BearingFrameLayout bearingFrameLayout;

		//quattro variabili che richiamano le classi predefinite per il gps:
		private LocationManager locationManager;

		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.loading);
			ottenuteserie = new Vector<Attrazioni>(); // vettore di attrazioni puntate
			scatto = (ImageButton) findViewById(R.id.imageView2);
		}



		// @Override
		public void onStart()
		{
			super.onStart();
			serie = Globals.getSerie();

			Criteria criteria = new Criteria();
			criteria.setAccuracy(Criteria.ACCURACY_FINE);
			criteria.setBearingRequired(true);
			criteria.setCostAllowed(true);
			criteria.setPowerRequirement(Criteria.POWER_LOW);
			criteria.setAltitudeRequired(false);
			locationManager =
				(LocationManager) getSystemService(LOCATION_SERVICE);
			locationManager.addGpsStatusListener(gpsStatusListener);
			String provider = locationManager.getBestProvider(criteria, true);
			locationManager.requestLocationUpdates(provider, 0, 0,
				locationListener);


			// per test alla cazzo prossime 2 righe
			scatto.setOnClickListener(gestioneScatto);
			scatto.performClick();
		}

		private double distante (Attrazioni a)
		{
			double risp = 0;
			double cl =a.getLat();
			double clo =a.getLon();
			double distanza = a.getDistanza();
			double distLa= Math.abs(l - cl);
			double distLo= Math.abs(lo - clo);
			Double tot = distLa + distLo;
  			return tot;
		}

        private boolean vicina(double vera, double massima)
        {
			Log.i("dists","VERA : " +vera + ";   MASSIMA: " +  massima);
            if(vera<massima)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

		public static Vector<Attrazioni> getOttenuteserie()
		{
			return ottenuteserie;
		}

        private void gestisciVicinanza(Attrazioni questa, double distante)
        {
			//scrivi(questa);
            cont++;
            questa.setScarto(distante);

            ottenuteserie.add(questa);
			//Log.i("VICIANNZA", "VICINANZA!!, dim: " + ottenuteserie.size());

			Collections.sort(ottenuteserie, new Comparator<Attrazioni>() {
                public int compare(Attrazioni a1, Attrazioni a2) {
                    Double d1 = Double.valueOf(a1.getScarto());
                    Double d2 = Double.valueOf(a2.getScarto());
                    return d1.compareTo(d2);
                }
            });
        }

        private boolean orientata()
        {
            //TODO
            return true;
        }

	    private OnClickListener gestioneScatto = new OnClickListener()
	    {
		    @Override
		    public void onClick(View v) {
			    // TODO Auto-generated method stub
			    int niente =1;
			    int ripetizione=0;
			    cont =0;

		    	for(int i=0;i<serie.size();i++)
		    	{
			        Attrazioni questa = (Attrazioni) serie.get(i);
			        double distante = distante(questa);

                    boolean vicina = vicina(distante, questa.getDistanza());
                    boolean orientata = orientata(); //TODO
			        if(vicina && orientata){
						Log.i("AVVISO","DENTRO: " + serie.size());
                        niente=0;
                        gestisciVicinanza(questa, distante);
			        }
		        }

				Intent intent = new Intent(getApplicationContext(), Vids.class); //passa a video
				startActivity(intent);

                if ((niente==1)&(ripetizione==0)){
                    ripetizione=1;
                }
		    }
	    };

        public void updateLocation(Location location)
	    {
			 l = location.getLatitude();
			 lo = location.getLongitude() ;
			 if(l!=0 & lo!=0 & cambio==0)
			 {
				 cambio=1;
				 scatto = (ImageButton) findViewById(R.id.imageView2);
				 scatto.setOnClickListener(gestioneScatto);
				 scatto.performClick();
				 scatto.setBackgroundResource(R.drawable.founded);
                 ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);
				 pb.setVisibility(View.INVISIBLE);
			  }
		}

        //ora 5 metodi standard  che non ci servono quindi lascio vuoti
	    private final LocationListener locationListener =
		    new LocationListener()
	    {
		    public void onLocationChanged(Location location)
		    {
                updateLocation(location); //richiama metodo a ogni cambiamento
		    }

            public void onProviderDisabled(String provider){}
            public void onProviderEnabled(String provider){}
            public void onStatusChanged(String provider, int status, Bundle extras){}
	   };

        GpsStatus.Listener gpsStatusListener = new GpsStatus.Listener()
        {
           public void onGpsStatusChanged(int event)
           {
               if (event == GpsStatus.GPS_EVENT_FIRST_FIX){}
           } // end method on GpsStatusChanged
        }; // end anonymous inner class

        protected boolean isRouteDisplayed()
        {
          return false;
        }

        protected void onDestroy() {
            super.onDestroy();
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {}

        protected void onResume() {
            super.onResume();
        }

        protected void onPause() {
            super.onPause();
        }

        @Override
        public void onSensorChanged(SensorEvent event) {}

	}


