package info.infosity.Milan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.location.LocationManager;
import java.util.Vector;
import android.view.Window;
import android.content.Context;
import android.content.*;
import android.app.AlertDialog;

import info.infosity.Milan.generalDBHelper.GodOfDb;


public class Preambolo extends Activity {

    ImageButton search;
    ImageButton seen;
    ImageButton info;


    private static RouteTracker appoggio = new RouteTracker();
    public static Vector<Attrazioni> serie = new Vector<Attrazioni>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.menu20);


        GodOfDb god = new GodOfDb();
        serie = god.getSerie();

        search = (ImageButton) findViewById(R.id.imageButton2);
        search.setOnClickListener(cliccasearch);

        seen = (ImageButton) findViewById(R.id.imageButton);
        seen.setOnClickListener(cliccaseen);

        info = (ImageButton) findViewById(R.id.bottoneinfo);
        info.setOnClickListener(cliccainfo);

        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }


     };


    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
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

    private View.OnClickListener cliccasearch = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), RouteTracker.class);
           try {
               startActivity(intent);
           }
           catch(Exception e)
           {
                AlertDialog.Builder dialogBuilder3 =
                        new AlertDialog.Builder(Preambolo.this);
                dialogBuilder3.setTitle(e.getMessage());
                dialogBuilder3.setPositiveButton(
                        R.string.button_ok, null);
                dialogBuilder3.show();
           }
        }
    };

    private View.OnClickListener cliccainfo = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Info.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener cliccaseen = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Seen.class);
            startActivity(intent);
        }
    };

}
