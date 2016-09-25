package info.infosity.Milan.SBF;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Button;
import android.view.View;
import android.app.Activity;

import java.util.Vector;

import info.infosity.Milan.Attrazioni;
import info.infosity.Milan.Global.Globals;
import info.infosity.Milan.Global.OftenUsed;
import info.infosity.Milan.R;
import info.infosity.Milan.generalDBHelper.GodOfDb;

/**
 * Created by andrea on 27/06/15.
 */

public class SearchByField extends OftenUsed {

    private EditText query;
    private Button queryButton;

    private String readQuery = "";
    private static Context thisContext = null;
    private static GodOfDb god;

    private LinearLayout linearLayout;

    @TargetApi( Build.VERSION_CODES.JELLY_BEAN )
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.menusbf );
        linearLayout = (LinearLayout) findViewById(R.id.sbf_lin);
        thisContext = this;

        query = ( EditText ) findViewById( R.id.what_to_search );

        queryButton = ( Button ) findViewById( R.id.search_button );
        queryButton.setOnClickListener( clickSearch );
    }

    private View.OnClickListener clickSearch = new View.OnClickListener()
    {
        @Override
        public void onClick( View v ) {
            String readQuery = String.valueOf( query.getText() );
            //showDialog(thisContext, readQuery);
            Vector<Attrazioni> matchingAttractions = getAttractionsByField(readQuery);
            createAllButtons( matchingAttractions );
        }
    };

    private void createAllButtons( Vector<Attrazioni> mA )
    {
        for( int i = 0 ; i < mA.size() ; i++ )
        {
            Button btn = new Button( this );
            btn.setId( i );
            Attrazioni thisAtr = mA.get( i );
            btn.setText( thisAtr.getName() );
            linearLayout.addView( btn );
        }
    }

    private Vector<Attrazioni> getAttractionsByField( String needle )
    {
        god = new GodOfDb( thisContext );
        Vector<Attrazioni> resultingSerie = god.getSerieByType( thisContext , needle );
        //showDialog( thisContext , String.valueOf( resultingSerie.size() ) );
        return resultingSerie;
    }


}