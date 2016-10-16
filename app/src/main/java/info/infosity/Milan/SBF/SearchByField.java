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

    private EditText queryText;
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

        queryText = ( EditText ) findViewById( R.id.what_to_search );
        queryText.setOnClickListener( clickInsertTextToSearch );
        queryButton = ( Button ) findViewById( R.id.search_button );
        queryButton.setOnClickListener( clickSearch );
    }

    private View.OnClickListener clickSearch = new View.OnClickListener()
    {
        @Override
        public void onClick( View v ) {
            queryButton.setVisibility( View.GONE );
            String readQuery = String.valueOf( queryText.getText() );
            //showDialog(thisContext, readQuery);
            deleteAllButtons();
            Vector<Attrazioni> matchingAttractions = getAttractionsByField(readQuery);
            createAllButtons( matchingAttractions );
        }
    };

    private View.OnClickListener clickInsertTextToSearch = new View.OnClickListener()
    {
        @Override
        public void onClick( View v ) {
            deleteAllButtons();
            queryButton.setVisibility( View.VISIBLE );
           }
    };

    private void deleteAllButtons()
    {
        for( int i = 0 ; i < 12 ; i++ )
        {
            Button btn = ( Button ) findViewById( i );
            try{
                ( ( LinearLayout ) btn.getParent() ).removeView( btn );
            }catch (Exception e)
            {
                Log.wtf( "curse" , "curse" );
            }
        }
    }

    private void createAllButtons( Vector<Attrazioni> mA )
    {   //I don't wanna more then 12 results! it is a little screen!!!
        int maxShown = mA.size();
        if ( maxShown > 12 )
        {
            maxShown = 12;
        }
        for( int i = 0 ; i < maxShown ; i++ )
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