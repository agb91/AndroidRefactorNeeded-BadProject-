package info.infosity.Milan.Global;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

import java.util.Vector;

import info.infosity.Milan.Attrazioni;

/**
 * Created by andrea on 13/09/16.
 */
public class OftenUsed extends Activity {

    public void showDialog(Context context, String text)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Dialog");
        alertDialog.setMessage(text);
        alertDialog.show();
    }

    public boolean inVector(String needle, Vector<String> haystack)
    {
        boolean risp = false;
        for(int i=0; i<haystack.size(); i++)
        {
            if( needle.equalsIgnoreCase ( haystack.get(i) ) )
            {
                risp = true;
            }
        }
        return risp;
    }

    public boolean inVector(Attrazioni needle, Vector<Attrazioni> haystack)
    {
        boolean risp = false;
        for(int i=0; i<haystack.size(); i++)
        {
            Attrazioni actual = haystack.get(i);
            if( needle.getName().equalsIgnoreCase ( actual.getName() ) )
            {
                risp = true;
            }
        }
        return risp;
    }

}
