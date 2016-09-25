package info.infosity.Milan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.location.LocationManager;
import android.view.Window;
import android.content.Context;
import android.content.*;
import android.app.AlertDialog;

import info.infosity.Milan.Global.OftenUsed;
import info.infosity.Milan.SBF.*;

import info.infosity.Milan.Global.Globals;


public class Preambolo extends OftenUsed {

    ImageButton search;
    ImageButton seen;
    ImageButton searchByField;
    ImageButton info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.preambolo);
        Globals.create(this);

        search = (ImageButton) findViewById(R.id.search);
        search.setOnClickListener(clickSearch);

        seen = (ImageButton) findViewById(R.id.attractions_seen);
        seen.setOnClickListener(clickSeen);

        searchByField = (ImageButton) findViewById(R.id.search_by_field);
        searchByField.setOnClickListener(clickSBF);

        info = (ImageButton) findViewById(R.id.bottoneinfo);
        info.setOnClickListener(clickInfo);

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

    private View.OnClickListener clickSearch = new View.OnClickListener()
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

    private View.OnClickListener clickInfo = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Info.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener clickSeen = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Seen.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener clickSBF = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), SearchByField.class);
            startActivity(intent);
        }
    };

}
