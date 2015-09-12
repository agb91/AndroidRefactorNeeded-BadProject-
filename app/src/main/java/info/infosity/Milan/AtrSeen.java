package info.infosity.Milan;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;

import android.app.Activity;

import java.util.Vector;

public class AtrSeen extends Activity {

    public static Attrazioni questa;

    public static void setAtr(String q)
    {
        questa = getAtByName(q);
    }

    private static Attrazioni getAtByName(String ago)
    {
        Vector<Attrazioni> pagliaio = Preambolo.serie;
        Attrazioni ris = pagliaio.get(0);
        for (int i=0; i<pagliaio.size(); i++)
        {
            if(pagliaio.get(i).getNome().equalsIgnoreCase(ago))
            {

                ris = pagliaio.get(i);
            }
        }
        return ris;
    }


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.attraz);

        ActionBar.Tab Tab1, Tab2, Tab3;
        Fragment fragmentTab1 = new FragmentTab1seen();
        Fragment fragmentTab2 = new FragmentTab2seen();
        Fragment fragmentTab3 = new FragmentTab3seen();

        ActionBar actionBar = getActionBar();

        // Hide Actionbar Icon
        actionBar.setDisplayShowHomeEnabled(false);

        // Hide Actionbar Title
        actionBar.setDisplayShowTitleEnabled(false);

        // Create Actionbar Tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set Tab Icon and Titles
        Tab1 = actionBar.newTab().setText("General");
        Tab2 = actionBar.newTab().setText("History");
        Tab3 = actionBar.newTab().setText("Technical");

        // Set Tab Listeners
        Tab1.setTabListener(new TabListener(fragmentTab1));
        Tab2.setTabListener(new TabListener(fragmentTab2));
        Tab3.setTabListener(new TabListener(fragmentTab3));

        // Add tabs to actionbar
        actionBar.addTab(Tab1);
        actionBar.addTab(Tab2);
        actionBar.addTab(Tab3);

    }

}