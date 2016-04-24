package info.infosity.Milan;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
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
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.menuseen);

            LinearLayout linear = (LinearLayout) findViewById(R.id.lin);



            dbHelper = new GeneralDbAdapter(this);
            dbHelper.open();
          //  dbHelper.createContact("primo","1-1-1");
          //  dbHelper.createContact("secondo","2 ginevro");

            // QUESTO DB È MOLTO LUNGO PERCHÈ AD OGNI PROVA MI AH AGGIUNTO PEZZI!!!!
            cursor = dbHelper.fetchAllContacts();
            Integer num = cursor.getCount();

            if(cursor.getCount()==0){//se è vuoto caccia dentro qualcosa
               // dbHelper.createContact("primo","1-1-1");
                //dbHelper.createContact("secondo","2 ginevro");
            }
            cursor.moveToLast();

            Vector<String> pezziGiaMessi = new Vector<String>();
            String pezzo= "";
            for (int i=1; i<num-1; i++)
            {
                pezzo = "";
  //              pezzo += + i+" : ";
                pezzo += (String) cursor.getString(1);
 //               String data = (String) cursor.getString(2);
  //              try {
 //                   String anno = data.substring(0, 4);
  //                  String mese = data.substring(4, 6);
   //                 String giorno = data.substring(6, 8);
    //                pezzo += "; " + giorno + "-" + mese + "-" + anno + ";";
    //            }catch(Exception e){
    //            }
                cursor.moveToPosition(num - 1 - i);
                //pezzo += "<br><br>";

                if(!inVettore(pezzo,pezziGiaMessi)) {

                    //ora genero dinamicamente un bottone
                    Button btn = new Button(this);
                    btn.setId(i);
                    btn.setBackground(this.getResources().getDrawable(R.drawable.bottonigiaviste));
                    btn.setText(pezzo);
                    //btn.setBackgroundColor(Color.rgb(70, 80, 90));
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
                pezziGiaMessi.add(pezzo);
            }

            cursor.close();

            dbHelper.close();

           // linear.setVerticalScrollBarEnabled(true);
            //linear.setScrollContainer(true);


        }



    private boolean inVettore(String ago, Vector<String> pagliaio)
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

