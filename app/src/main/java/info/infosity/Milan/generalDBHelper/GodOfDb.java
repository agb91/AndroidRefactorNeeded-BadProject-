package info.infosity.Milan.generalDBHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.ImageButton;

import java.util.Vector;

import info.infosity.Milan.Attrazioni;

/**
 * Created by andrea on 23/04/16.
 */
public class GodOfDb {

    private ImageButton scatto;
    private static Vector<Attrazioni> serie;
    private GeneralDbAdapter dbHelper;
    private Cursor cursor;

    public GodOfDb(Context context)
    {

        dbHelper = new GeneralDbAdapter(context);
        dbHelper.open();

        serie = getSerie(context);
    }


    public Vector<Attrazioni> getSerie(Context context)
    {

        Vector<Attrazioni> atrs= dbHelper.fetchAllContactsByObjects();
        Integer num = atrs.size();
        if(num<20) {
            double latitude = 52.359998;
            double longitude = 4.885315;
            double distanceNs = 0.004018;
            double distanceEw = 0.008036;
            double lat1 = 52.362182;
            double long1 = 4.884046;
            double lat2 = 52.358454;
            double long2 = 4.887059;
            double lat3 = 52.360656;
            double long3 = 4.886011;
            double lat4 = 52.358931;
            double long4 = 4.888836;
            double lat5 = 52.360571;
            double long5 = 4.882926;
            double lat6 = 52.359336;
            double long6 = 4.884375;
            double lat7 = 52.359382;
            double long7 = 4.881892;
            double lat8 = 52.357697;
            double long8 = 4.884619;
            double lat9 = 999;
            double long9 = 999;
            double lat10 = 999;
            double long10 = 999;
            double lat11 = 999;
            double long11 = 999;
            double lat12 = 999;
            double long12 = 999;
            String name = "Rijksmuseum";
            String type = "Museum";
            String subType = "Art <br> History";
            String collections = "Art <br> Craft <br> History <br> from the years 1200 to 2000";
            String address = "Museumstraat 1 <br> 1071 XX Amsterdam";
            String phone = "+31(0)206747000";
            String opening = "Mon-Sun, 9:00 am - 5:00pm <br> Ticket desk close at 4:30 pm";
            String closed = "Open 365 days";
            String price = "Adults: 17.50 <br> Youth aged 18 and under: free";
            String getHere = "<i>Tram</i><br>2, 5: Rijksmuseum tram stop<br>7, 10: Spiegelgracht tram stop<br>12: Museumplein tram stop<br><i>Bus</i><br>145, 170, 172, 174, 197: Rijksmuseum bus stop";
            String description = "With 2.35 million visitors in 2015, the Rijksmuseum (National Museum) is the most visited museum in the Netherlands. The museum, with its collection of 1 million objects is also the largest art museum in the country.<br>The Rijksmuseum has on display 8,000 objects from the years 1200-2000. Part of the collection consist of 5000 paintings, most importantly those by Dutch and Flemish masters from the 15th to 19th centuries, including masterpieces by Rembrandt, Frans Hals, and Johannes Vermeer.";
            String history = "The Rijksmuseum opened in 1800. Originally it was named 'Nationale Kunstgalerij' and was housed in Huis ten Bosch in The Hague. The museum exhibited around 200 paintings and historic objects from the remains of the viceregal collections and other state institutions.<br>The collections were moved to Amsterdam in 1808. The objects were housed in the Royal Palace in Dam Square. Here, some of the citys foremost paintings, including the Night Watch by Rembrandt were added to the collection.. In 1809, the Koninklijk Museum opened its doors on the top floor of the palace.<br>In 1813, the collection relocated to the Trippenhuis. Unfortunately, this new location was unsuitable as a museum. In 1876, the construction for a new building begun. The official opening took place in 1885.<br>Almost all the paintings belonging to the city of Amsterdam were hung in the Rijksmuseum along with the original collection. The collection of 19th-century art from Haarlem was also added to the museums collection.<br>"
                +" Over the years, the museum underwent many changes. Between 1904 and 1916, more rooms were added to the building in order to house the collection of 19th-century paintings donated to the museum by Mr and Mrs Drucker-Fraser.<br>During the 1950s,  the Association of Friends of Asian Art donated a collection of asian objects.";

            String visited = "yes";
            dbHelper.createContact(latitude, longitude, distanceNs, distanceEw, lat1, long1, lat2, long2, lat3, long3, lat4, long4, lat5, long5, lat6, long6, lat7, long7, lat8, long8, lat9, long9, lat10, long10, lat11, long11, lat12, long12, name, type, collections, address, phone, opening, closed, price, getHere, description, history, visited);

            latitude = 52.375218;
            longitude = 4.883955;
            distanceNs = 0.003134;
            distanceEw = 0.006268;
            lat1 = 52.375964;
            long1 = 4.88383;
            lat2 = 52.374797;
            long2 = 4.884508;
            lat3 = 52.375288;
            long3 = 4.882876;
            lat4 = 52.374678;
            long4 = 4.884012;
            lat5 = 999;
            long5 = 999;
            lat6 = 999;
            long6 = 999;
            lat7 = 999;
            long7 = 999;
            lat8 = 999;
            long8 = 999;
            lat9 = 999;
            long9 = 999;
            lat10 = 999;
            long10 = 999;
            lat11 = 999;
            long11 = 999;
            lat12 = 999;
            long12 = 999;
            name = "Anne Frank House";
            type = "Museum";
            subType = "Biographical <br> History";
            collections = "Personal documents and objects <br> The secret room";
            address = "Prinsengracht 263-267 <br>1016 GV Amsterdam ";
            phone = "+31(0)205567100";
            opening = "<i>April - October </i><br>Mon-Sun, 9:00 am - 10:00pm <br><i>November - March</i><br>Sun-Fri, 9:00am - 7:00pm<br>Sat, 9:00am - 9:00pm<br><br>From 9:00 am to 3:00pm, access only with online ticket";
            closed = "Yom Kippur";
            price = "Adults: 9 <br> Age 10-17: 4.50<br> Age 0-9: Free<br> European Youth Card: 4.50";
            getHere = "<i>Tram</i><br>13, 14, 17: Westermarkt tram stop<br><i>Bus</i><br>170, 172, 174: Westermarkt bus stop";
            description = "With 1.2 million visitors, the Anne Frank House is the third most visited museum in the Netherlands, after the Rijksmuseum and Van Gogh Museum.";
            history = "In 1957, Otto Frank and Johannes Kleiman created the Anne Frank foundation in order to purchase and restore the building. The building was donated to the foundation by the owner. With the money raised, the Anne Frank foundation bought the house next door (number 256).<br>The Anne Frank House opened in 1960. In its first year, the building attracted over 9,000 visitors. Over the years, the number of visitors grew so much that the building has to be closed for renovation.<br>The house closed temporarly in 1970 and in 1999. In 2001,  Queen Beatrix of the Netherlands reopened the museum. Today the Anne Fran House incorporates the entire building between exhibition spaces, a bookshop and a cafe.";
            visited = "yes";
            dbHelper.createContact(latitude, longitude, distanceNs, distanceEw, lat1, long1, lat2, long2, lat3, long3, lat4, long4, lat5, long5, lat6, long6, lat7, long7, lat8, long8, lat9, long9, lat10, long10, lat11, long11, lat12, long12, name, type, collections, address, phone, opening, closed, price, getHere, description, history, visited);

        }
        atrs = dbHelper.fetchAllContactsByObjects();

        Vector<Attrazioni> risp = new Vector<Attrazioni>();
        risp.addAll(atrs);
        return risp;
    }



}
