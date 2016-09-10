	package info.infosity.Milan;

	import android.app.Activity;

	import android.app.AlertDialog;
	import android.content.DialogInterface;
	import android.database.Cursor;
	import android.hardware.Sensor;
	import android.hardware.SensorEvent;
	import android.hardware.SensorEventListener;
	import android.hardware.SensorManager;
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
		private float Rot[] = null; //for gravity rotational data
		//don't use R because android uses that for other stuff
		private float I[] = null; //for magnetic rotational data
		private float accels[] = new float[3];
		private float mags[] = new float[3];
		private float[] values = new float[3];

		// 1 NNO; 2 NOO; 3 OOS; 4 SSO; 5 SSE, 6 SEE, 7NEE, 8 NNE
		private int caseGoalDirection = 0; //

		private float azimuth;
		private float pitch;
		private float roll;

		private SensorEventListener sListener;
		private SensorManager sManager;

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
			sManager = (SensorManager) getSystemService(SENSOR_SERVICE);

			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.loading);
			ottenuteserie = new Vector<Attrazioni>(); // vettore di attrazioni puntate
			scatto = (ImageButton) findViewById(R.id.imageView2);
		}



		// @Override
		public void onStart()
		{
			super.onStart();
			ottenuteserie = new Vector<Attrazioni>();
			ottenuteserie.clear();

			serie = Globals.getSerie();
			//Log.wtf("from globals i take a serie having length: :", "from globals i take a serie having length: :" + serie.size() );

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
			double cl =a.getDistanceEw();
			double clo =a.getLat1();
			double distanza = a.getDistanceEw();
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
			//Log.wtf("alive", "alive");
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
				//Log.wtf("all the attraction: " , "cardinality of all the attraction:" + serie.size() );
		    	for(int i=0;i<serie.size();i++)
		    	{
			        Attrazioni questa = (Attrazioni) serie.get(i);
			        double distante = distante(questa);

                    boolean vicina = vicina(distante, questa.getDistanceEw());
                    boolean orientata = orientata(); //TODO
			        if(true){//just for tests
					//if(vicina && orientata){
						//Log.i("AVVISO","DENTRO: " + serie.size());
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

		private void checkBasso(int azint, int piint, int roint) {
			if (near(piint, -90)) {
				Log.i("basso", "schermo in basso");
			}
		}

		// 1 NNO; 2 NOO; 3 OOS; 4 SSO; 5 SSE, 6 SEE, 7NEE, 8 NNE
		//int caseGoalDirection = 0; //
		private void checkVertical(int azint, int piint, int roint) {
			if (near(piint, 0) && near(roint, 0)) {
				//setDialog("posizione da foto con cell in verticale");
				// il valore interessato è azimuth
				if (near(azint, 0)) {
					Log.i("avviso","cell in verticale NORD");
					if(caseGoalDirection==1 || caseGoalDirection==8)
					{
						Log.i("bingo", "STO PUNTANTO");
					}
				}
				if (near(azint, 45)) {
					Log.i("avviso","cell in verticale NORD-EST");
					if(caseGoalDirection==7 || caseGoalDirection==8)
					{
						Log.i("bingo", "STO PUNTANTO");
					}
				}
				// 1 NNO; 2 NOO; 3 OOS; 4 SSO; 5 SSE, 6 SEE, 7NEE, 8 NNE
				//int caseGoalDirection = 0; //
				if (near(azint, 90)) {
					Log.i("avviso","cell in verticale EST");
					if(caseGoalDirection==7 || caseGoalDirection==6)
					{
						Log.i("bingo", "STO PUNTANTO");
					}
				}
				if (near(azint, 135)) {
					Log.i("avviso","cell in verticale SUD-EST");
					if(caseGoalDirection==5 || caseGoalDirection==6)
					{
						Log.i("bingo", "STO PUNTANTO");
					}
				}
				// 1 NNO; 2 NOO; 3 OOS; 4 SSO; 5 SSE, 6 SEE, 7NEE, 8 NNE
				//int caseGoalDirection = 0; //
				if (near(azint, -135)) {
					Log.i("avviso","cell in verticale SUD-OVEST");
					if(caseGoalDirection==4 || caseGoalDirection==3)
					{
						Log.i("bingo", "STO PUNTANTO");
					}
				}
				if (near(azint, -45)) {
					Log.i("avviso","cell in verticale NORD-OVEST");
					if(caseGoalDirection==1 || caseGoalDirection==2)
					{
						Log.i("bingo", "STO PUNTANTO");
					}
				}
				// 1 NNO; 2 NOO; 3 OOS; 4 SSO; 5 SSE, 6 SEE, 7NEE, 8 NNE
				//int caseGoalDirection = 0; //
				if (near(azint, -90)) {
					Log.i("avviso","cell in verticale ovest");
					if(caseGoalDirection==2 || caseGoalDirection==3)
					{
						Log.i("bingo", "STO PUNTANTO");
					}
				}
				if (near(azint, -180) || near(azint, +180)) {
					Log.i("avviso","cell in verticale SUD");
					if(caseGoalDirection==4 || caseGoalDirection==5)
					{
						Log.i("bingo", "STO PUNTANTO");
					}
				}
			}
		}

		public boolean near(int a, int rif) {
			if (Math.abs(a - rif) < 22) {
				return true;
			}
			if (Math.abs(rif - a) < 22) {
				return true;
			}
			return false;
		}

		private void checkHorizontal(int azint, int piint, int roint) {
			Log.i("orizzontale", "cell in orizzontale");
			if (near(piint, 90)) {
				if (near(roint, 0) && (near(azint, 180) || near(azint, -180))) {
					Log.i("avviso","schermo in alto SUD");
				}
				if (near(roint, 0) && near(azint, 0)) {
					Log.i("avviso","schermo in alto NORD");
				}
				if (near(roint, 0) && near(azint, -90)) {
					Log.i("avviso","schermo in alto OVEST");
				}
				if (near(roint, 0) && near(azint, 90)) {
					Log.i("avviso","schermo in alto EST");
				}
			}
		}

		private int[] getCompassValue()
		{
			int ris[] = new int[3];
			Rot = new float[9];
			I = new float[9];
			SensorManager.getRotationMatrix(Rot, I, accels, mags);
			// Correct if screen is in Landscape

			float[] outR = new float[9];
			SensorManager.remapCoordinateSystem(Rot, SensorManager.AXIS_X, SensorManager.AXIS_Z, outR);
			SensorManager.getOrientation(outR, values);

			azimuth = values[0] * 57.2957795f; //looks like we don't need this one
			pitch = values[1] * 57.2957795f;
			roll = values[2] * 57.2957795f;

			int azint = Math.round(azimuth);
			int piint = Math.round(pitch);
			int roint = Math.round(roll);

			ris[0]=azint;
			ris[1]=piint;
			ris[2]=roint;
			return ris;
		}

		@Override
        public void onSensorChanged(SensorEvent event) {
			//sceglie quale sensore usare
			switch (event.sensor.getType()) {
				case Sensor.TYPE_MAGNETIC_FIELD:
					mags = event.values.clone();
					break;
				case Sensor.TYPE_ACCELEROMETER:
					accels = event.values.clone();
					break;
			}
			//se qualcosa vive
			if (mags != null && accels != null) {
				int compassValueInt[] = getCompassValue();

				int azint = compassValueInt[0];
				int piint = compassValueInt[1];
				int roint = compassValueInt[2];

				Log.i("valori letti", "valori letti= " + azint + "; " + piint + "; " + roint);

				checkVertical(azint, piint, roint);
				checkHorizontal(azint, piint, roint);
				checkBasso(azint, piint, roint);
				mags = null; //retrigger the loop when things are repopulated
				accels = null; ////retrigger the loop when things are repopulated
			}
		}
	}


