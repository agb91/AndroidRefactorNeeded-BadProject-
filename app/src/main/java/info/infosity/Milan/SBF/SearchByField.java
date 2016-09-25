package info.infosity.Milan.SBF;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Button;
import android.view.View;
import android.app.Activity;

import java.util.Vector;

import info.infosity.Milan.Global.Globals;
import info.infosity.Milan.R;

/**
 * Created by andrea on 27/06/15.
 */

public class SearchByField extends Activity {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menusbf);
    }




}