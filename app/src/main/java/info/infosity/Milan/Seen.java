package info.infosity.Milan;

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
import info.infosity.Milan.generalDBHelper.GeneralDbAdapter;

/**
 * Created by andrea on 27/06/15.
 */

public class Seen extends Activity {

        private GeneralDbAdapter dbHelper;
        private Cursor cursor;
        private Attrazioni questa;
        public static Attrazioni accedi;

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.menuseen);

            LinearLayout linear = (LinearLayout) findViewById(R.id.lin);



            dbHelper = new GeneralDbAdapter(this);
            dbHelper.open();
          //  dbHelper.createContact("primo","1-1-1");
          //  dbHelper.createContact("secondo","2 ginevro");

            Vector<Attrazioni> readed = dbHelper.fetchAllContactsByObjects();
            Integer num = readed.size();
            //Log.wtf("num", "wtf before: " + String.valueOf(num));

            Vector<String> alreadyInsertedPieces = new Vector<String>();
            for (int i=0; i<readed.size(); i++)
            {
                Attrazioni piece = readed.get(i);
  //              pezzo += + i+" : ";
                String pieceName = piece.getName();

                if(!inVector(pieceName,alreadyInsertedPieces))
                {
                    Button btn = new Button(this);
                    btn.setId(i);
                    btn.setBackground(this.getResources().getDrawable(R.drawable.bottonigiaviste));
                    btn.setText(pieceName);
                    linear.addView(btn);
                    Vector<Attrazioni> atr = Globals.getSerie();
                    btn.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), AtrSeen.class);
                            Button b = (Button)view;
                            String buttonText = b.getText().toString();
                            AtrSeen.setAtr(buttonText);
                            startActivity(intent);

                        }
                    });
                }
                alreadyInsertedPieces.add(pieceName);
            }
            dbHelper.close();
        }



        private boolean inVector(String ago, Vector<String> pagliaio)
        {
            boolean ris = false;
            for(int i = 0; i<pagliaio.size(); i++)
            {
                if(ago.equalsIgnoreCase(pagliaio.get(i)))
                {
                    ris = true;
                }
            }
            return ris;
        }


}

