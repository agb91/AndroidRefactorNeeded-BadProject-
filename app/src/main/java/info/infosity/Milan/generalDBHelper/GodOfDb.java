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
public class GodOfDb extends OftenUsed {

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
        Vector<String> names = dbHelper.fetchAllContactsNames();
        //Log.wtf("read names n. : " , "read names n: " + names.size());
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
            String area = "null";
            String inside = "null";
            String address = "Museumstraat 1 <br> 1071 XX Amsterdam";
            String phone = "+31(0)206747000";
            String opening = "Mon-Sun, 9:00 am - 5:00pm <br> Ticket desk close at 4:30 pm";
            String closed = "Open all days";
            String price = "Adults: 17.50 <br> Youth aged 18 and under: free";
            String getHere = "<i>Tram</i><br>2, 5: Rijksmuseum tram stop<br>7, 10: Spiegelgracht tram stop<br>12: Museumplein tram stop<br><i>Bus</i><br>145, 170, 172, 174, 197: Rijksmuseum bus stop";
            String description = "With 2.35 million visitors in 2015, the Rijksmuseum (National Museum) is the most visited museum in the Netherlands. The museum, with its collection of 1 million objects is also the largest art museum in the country.<br>The Rijksmuseum has on display 8,000 objects from the years 1200-2000. Part of the collection consist of 5000 paintings, most importantly those by Dutch and Flemish masters from the 15th to 19th centuries, including masterpieces by Rembrandt, Frans Hals, and Johannes Vermeer.";
            String history = "The Rijksmuseum opened in 1800. Originally it was named 'Nationale Kunstgalerij' and was housed in Huis ten Bosch in The Hague. The museum exhibited around 200 paintings and historic objects from the remains of the viceregal collections and other state institutions.<br>The collections were moved to Amsterdam in 1808. The objects were housed in the Royal Palace in Dam Square. Here, some of the citys foremost paintings, including the Night Watch by Rembrandt were added to the collection.. In 1809, the Koninklijk Museum opened its doors on the top floor of the palace.<br>In 1813, the collection relocated to the Trippenhuis. Unfortunately, this new location was unsuitable as a museum. In 1876, the construction for a new building begun. The official opening took place in 1885.<br>Almost all the paintings belonging to the city of Amsterdam were hung in the Rijksmuseum along with the original collection. The collection of 19th-century art from Haarlem was also added to the museums collection.<br>"
                        +"Over the years, the museum underwent many changes. Between 1904 and 1916, more rooms were added to the building in order to house the collection of 19th-century paintings donated to the museum by Mr and Mrs Drucker-Fraser.<br>During the 1950s,  the Association of Friends of Asian Art donated a collection of asian objects.";

            String visited = "yes";
            if( !inVector(name, names) )
            {
                dbHelper.createContact(latitude, longitude, distanceNs, distanceEw, lat1, long1, lat2, long2, lat3, long3, lat4, long4, lat5, long5, lat6, long6, lat7, long7, lat8, long8, lat9, long9, lat10, long10, lat11, long11, lat12, long12, name, type, collections, address, phone, opening, closed, price, getHere, description, history, visited);
            }

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
            area = "null";
            inside = "null";
            address = "Prinsengracht 263-267 <br>1016 GV Amsterdam";
            phone = "+31(0)205567100";
            opening = "<i>April - October </i><br>Mon-Sun, 9:00 am - 10:00pm <br><i>November - March</i><br>Sun-Fri, 9:00am - 7:00pm<br>Sat, 9:00am - 9:00pm<br><br>From 9:00 am to 3:00pm, access only with online ticket";
            closed = "Yom Kippur";
            price = "Adults: 9 <br> Age 10-17: 4.50<br> Age 0-9: Free<br> European Youth Card: 4.50";
            getHere = "<i>Tram</i><br>13, 14, 17: Westermarkt tram stop<br><i>Bus</i><br>170, 172, 174: Westermarkt bus stop";
            description = "With 1.2 million visitors, the Anne Frank House is the third most visited museum in the Netherlands, after the Rijksmuseum and Van Gogh Museum.";
            history = "In 1957, Otto Frank and Johannes Kleiman created the Anne Frank foundation in order to purchase and restore the building. The building was donated to the foundation by the owner. With the money raised, the Anne Frank foundation bought the house next door (number 256).<br>The Anne Frank House opened in 1960. In its first year, the building attracted over 9,000 visitors. Over the years, the number of visitors grew so much that the building has to be closed for renovation.<br>The house closed temporarly in 1970 and in 1999. In 2001,  Queen Beatrix of the Netherlands reopened the museum. Today the Anne Fran House incorporates the entire building between exhibition spaces, a bookshop and a cafe.";

            visited = "no";
            if( !inVector(name, names) )
            {
                dbHelper.createContact(latitude, longitude, distanceNs, distanceEw, lat1, long1, lat2, long2, lat3, long3, lat4, long4, lat5, long5, lat6, long6, lat7, long7, lat8, long8, lat9, long9, lat10, long10, lat11, long11, lat12, long12, name, type, collections, address, phone, opening, closed, price, getHere, description, history, visited);
            }


            latitude = 52.358386 ;
            longitude = 4.881082;
            distanceNs = 0.003299;
            distanceEw = 0.006598;
            lat1 = 52.35868;
            long1 = 4.880041;
            lat2 = 52.35693;
            long2 = 4.883822;
            lat3 = 52.35919;
            long3 = 4.880282;
            lat4 = 52.35836;
            long4 = 4.883147;
            lat5 = 52.35891;
            long5 = 4.879252;
            lat6 = 52.35823;
            long6 = 4.880714;
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
            name = "Van Gogh Museum";
            type = "Museum";
            subType = "Art";
            collections = "Paintings <br> Drawings <br> Letters";
            area = "null";
            inside = "null";
            address = "Museumpleing 6 <br> 1071 Dj Amsterdam";
            phone = "+31(0)205705200";
            opening = "<i>25 March - 14 July</i><br>Sat-Thur, 9:00 am - 6:00pm<br> Fri, 9:00am - 10:00pm<br><i>15 July - 4 September</i><br>Sun-Thur, 9:00 am - 7:00pm<br> Fri, 9:00am - 10:00pm<br>Sat, 9:00 am - 9:00pm <br><i>5 September - 6 November</i><br>Sat-Thur, 9:00 am - 6:00pm<br> Fri, 9:00am - 10:00pm<br><i>All other days</i><br>Sat-Thur, 9:00 am - 5:00pm<br> Fri, 9:00am - 10:00pm<br>";
            closed = "null";
            price = "Adults: 17<br>Under 18: free<br>Museumkaart holders: free<br>CJP card holders: 14";
            getHere = "<i>Tram</i><br>2, 5: Van Baerlestraat stop<br>12: Museumplein stop<br><i>Bus</i><br>170, 172: Museumplein stop";
            description = "With 1.9 million visitors, the Van Gogh Museum is the second most visited museum in the Netherlands. The museum is located at the Museum Square in the borough Amsterdam South.<br>The museum is composed by 200 paintings, 400 drawings and 700 letters. This collection is the largest Van Gogh collection in the world.The museum consists of two buildings, the Rietveld building, designed by Gerrit Rietveld, and the Kurokawa wing, designed by Kisho Kurokawa.";
            history = "The Van Gogh Museum was commisioned by the Dutch government in 1963. The main building (Rietveld) was opened in 1973.<br>In 1991, twenty paintings were stolen from the museum. The pices were found 35 minutes later in an abandoned car.<br>In 1999, a second building was added to the museum. The new building was designed by Kisho Kurokawa.<br>In 2002, two paintings were stolen:  Congregation Leaving the Reformed Church in Nuenen and View of the Sea at Scheveningen. Unfortunately, these paintings were never recovered and the museum offered a reward of 100,000 to anyone that gives information that leads to the recovery of the objects.<br>The new entrance hall opens to the public on 5 September 2015.";


            visited = "no";
            if( !inVector(name, names) )
            {
                dbHelper.createContact(latitude, longitude, distanceNs, distanceEw, lat1, long1, lat2, long2, lat3, long3, lat4, long4, lat5, long5, lat6, long6, lat7, long7, lat8, long8, lat9, long9, lat10, long10, lat11, long11, lat12, long12, name, type, collections, address, phone, opening, closed, price, getHere, description, history, visited);
            }




            latitude = 52.357975;
            longitude = 4.868520;
            distanceNs = 0.005182;
            distanceEw = 0.016318;
            lat1 = 52.35753;
            long1 = 4.855001;
            lat2 = 52.354936;
            long2 = 4.867049;
            lat3 = 52.358727;
            long3 = 4.859674;
            lat4 = 52.356295;
            long4 = 4.869947;
            lat5 = 52.359673;
            long5 = 4.864606;
            lat6 = 52.357379;
            long6 = 4.872486;
            lat7 = 52.360783;
            long7 = 4.869699;
            lat8 = 52.358385;
            long8 = 4.875326;
            lat9 = 52.36119;
            long9 = 4.872159;
            lat10 = 52.359682;
            long10 = 4.87752;
            lat11 = 999;
            long11 = 999;
            lat12 = 999;
            long12 = 999;
            name = "Vondelpark";
            type = "Park";
            subType = "Urban";
            collections = "null";
            area = "120 acres (0.48km<sup>2</sup>)";
            inside = "";
            address = "null";
            phone = "null";
            opening = "24h";
            closed = "";
            price = "Free";
            getHere = "<i>Tram</i><br>1: Jan Pieter Heijestraat stop<br>1: Rhiknvis Feithstraat stop<br>1: Overtoomsesluis stop<br>2, 3, 5, 7, 10, 12, 16: Van Baerlestraat stop<br>2, 5: Cornelis Schuytstraat stop";
            description = "The Vondelpark is a public urban park of 47 hectares. Originally, the park was names Nieuwe Park, but later renamet to Vondelpark after the poet Joost van den Vondel. The park has around 10 million visitors a year.";
            history = "The park opened in 1865 with the name Het Nieuwe Park (The New Park). Originally it was open only for members of the association that bought the land and for all the citizienz that paied a fee. In 1867, a statue of the poet Joost van den Vondel was placed inside tha park. As a result, people started to call the park Vondelspark. Between 1875 and 1877,  Louis Paul Zocher designed the last part of the park, reaching the current size of 47 hectares.<br>Over the years several structures were added to the park. For example, in 1936, a rose garden was built in the center of the park. In 1937, a tearoom (Blauwe Theehuis) designed by the architectural office Baanders opened. During the 1980s the open-air theater was opened.<br>In 1953, due to an intensified use of the park, the association donated the Vondelsparkt to the city of Amsterdam. After, the park was renovated.<br>The Vondelpark received the status of state monument in 1996.";

            visited = "no";
            if( !inVector(name, names) )
            {
                dbHelper.createContact(latitude, longitude, distanceNs, distanceEw, lat1, long1, lat2, long2, lat3, long3, lat4, long4, lat5, long5, lat6, long6, lat7, long7, lat8, long8, lat9, long9, lat10, long10, lat11, long11, lat12, long12, name, type, collections, address, phone, opening, closed, price, getHere, description, history, visited);
            }



            latitude = 52.375159;
            longitude = 4.899378;
            distanceNs = 0.001641;
            distanceEw = 0.002576;
            lat1 = 52.37531;
            long1 = 4.899216;
            lat2 = 52.375016;
            long2 = 4.899993;
            lat3 = 52.375146;
            long3 = 4.899215;
            lat4 = 52.374885;
            long4 = 4.899984;
            lat5 = 52.375454;
            long5 = 4.899738;
            lat6 = 52.375027;
            long6 = 4.900114;
            lat7 = 52.374967;
            long7 = 4.899281;
            lat8 = 52.374701;
            long8 = 4.899854;
            lat9 = 999;
            long9 = 999;
            lat10 = 999;
            long10 = 999;
            lat11 = 999;
            long11 = 999;
            lat12 = 999;
            long12 = 999;
            name = "Ons'Lieve Heer Op Solder";
            type = "Museum";
            subType = "Church";
            collections = "Religious artifacts<br>Paintings";
            area = "null";
            inside = "null";
            address = "Oudezijds Voorburgwal 38<br>1012 GE Amsterdam";
            phone = "+31(0)206246604";
            opening = "Mon-Sat, 10:00 am - 5:00 pm<br> Sun and holidays, 1:00pm - 5:00pm";
            closed = "Apr, 27th";
            price = "Adults: 10<br>Age 5-18: 5<br>Age 0-4: free<br>I amsterdam City Card:free<br>Museumkaart: free";
            getHere = "<i>Tram</i><br>4,9,16: Dam Square Stop<br><i>Bus</i><br>755,757: Dam Square Stop";
            description = "The Ons'Lieve Heer Op Solder (Our Lord in the Attic) is a 17-th century house church museum.  It is an example of clandestine church in which religious dissenters from the seventeenth century Dutch Reformed Church held services.<br>Today, the church is a museum visited by almost 85,000 visitors a year.";
            history = "The canal house was built in 1630. In 1663, the top 3 floors were transformed into a church. While it was prohibited to celebrate mass, the authorities turned a blind eye. Indeed, the church symbolises the characteristic (religious) tolerance of the Netherlands, established by the Dutch in the sixteenth century under Willem of Orange.<br>In 1888, the building opened as a museum, making it the second oldest museum in Amsterdam (after the Rijksmuseum).  The museum was previously named Museum Amstelkring.";
            visited = "no";
            if( !inVector(name, names) )
            {
                dbHelper.createContact(latitude, longitude, distanceNs, distanceEw, lat1, long1, lat2, long2, lat3, long3, lat4, long4, lat5, long5, lat6, long6, lat7, long7, lat8, long8, lat9, long9, lat10, long10, lat11, long11, lat12, long12, name, type, collections, address, phone, opening, closed, price, getHere, description, history, visited);
            }



            latitude = 52.375665;
            longitude = 4.908318;
            distanceNs = 0.002455;
            distanceEw = 0.005519;
            lat1 = 52.376113;
            long1 = 4.905311;
            lat2 = 52.373808;
            long2 = 4.910372;
            lat3 = 52.374293;
            long3 = 4.906992;
            lat4 = 52.37272;
            long4 = 4.910418;
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
            name = "Central Library";
            type = "Library";
            subType = "Library";
            collections = "null";
            area = "null";
            inside = "null";
            address = "Oosterdokskade 143<br>1011 DL Amsterdam";
            phone = "null";
            opening = "Mon-Sun, 10:00 am - 10:00pm";
            closed = "null";
            price = "free";
            getHere = "<i>Tram</i><br>4,9,16,26: Centraal Station Stop<br><i>Bus</i><br>301, 304, 306, 307, 308, 312, 314, 315, 316, 319: Prins Hendrikkade/CS stop";
            description = "The Central Library, a complex of 28,500 m2 spread out over 10 floors, is the largest library in Amsterdam. The collection includes some 1.7 million books. The building also include and auditorium, an exhibition room, the Library Museum, the Gerard Reve Museum and a self-service restaurant.";
            history = "null";
            visited = "no";
            if( !inVector(name, names) )
            {
                dbHelper.createContact(latitude, longitude, distanceNs, distanceEw, lat1, long1, lat2, long2, lat3, long3, lat4, long4, lat5, long5, lat6, long6, lat7, long7, lat8, long8, lat9, long9, lat10, long10, lat11, long11, lat12, long12, name, type, collections, address, phone, opening, closed, price, getHere, description, history, visited);
            }






            latitude = 52.357831;
            longitude = 4.891825;
            distanceNs = 0.002637;
            distanceEw = 0.007486;
            lat1 = 52.358514;
            long1 = 4.890502;
            lat2 = 52.357203;
            long2 = 4.892735;
            lat3 = 999;
            long3 = 999;
            lat4 = 999;
            long4 = 999;
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
            name = "The Heineken Experience";
            type = "Museum";
            subType = "Industrial";
            collections = "Historical artifacts<br>Products<br>Samplings";
            area = "null";
            inside = "null";
            address = "Stadhouderskade 78<br>1072 AE Amsterdam";
            phone = "null";
            opening = "Mon-Thu, 10:30 am - 7:30pm<br>Fri-Sun, 10:30 am - 10:00 pm<br>Last ticket sale 2 hours before closing hour";
            closed = "Dec, 24, 31: closing at 4:00 pm";
            price = "Adults: 16<br>Age 12-17: 12.50<br>Age 0-11: free<br>Vip tour: 49<br>Vip tour include premium tasting. Minors under the age of 18 are not allowed in this tour";
            getHere = "<i>Tram</i>16: Stadhouderskade Stop<br><i>Bus</i>754: Stadhouderskade Stop";
            description = "The Heineken Experience is a historic brewery and visitor center for the internationally distributed Dutch pilsner, Heineken beer.";
            history = "In 1988 Heineken decided to close an Amsterdam brewery due to the spread of its smell over the De Pijp district.  In 1991, the brewery opened to the public as a brewery tour and visitor center.<br>The Heineken Experience reopened to visitors in 2008 after a 1 year remodeling and expansion.";
            visited = "no";
            if( !inVector(name, names) )
            {
                dbHelper.createContact(latitude, longitude, distanceNs, distanceEw, lat1, long1, lat2, long2, lat3, long3, lat4, long4, lat5, long5, lat6, long6, lat7, long7, lat8, long8, lat9, long9, lat10, long10, lat11, long11, lat12, long12, name, type, collections, address, phone, opening, closed, price, getHere, description, history, visited);
            }



            latitude = 52.367846;
            longitude = 4.912635;
            distanceNs = 0.004885;
            distanceEw = 0.002816;
            lat1 = 52.368344;
            long1 = 4.912339;
            lat2 = 52.367211;
            long2 = 4.914109;
            lat3 = 999;
            long3 = 999;
            lat4 = 999;
            long4 = 999;
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
            name = "The Dutch Resistance Museum";
            type = "Museum";
            subType = "History";
            collections = "Photographs<br>Objects<br>Films";
            area = "null";
            inside = "null";
            address = "Plantage Kerklaan 61A<br>1018 CX Amsterdam";
            phone = "+31206202535";
            opening = "Sat-Mon, 11:00 am - 5:00pm<br>Tue-Fri, 10:00 am - 5:00 pm";
            closed = "Jan, 1st<br>Apr, 27th<br>Dec, 25th";
            price = "Adults: 10<br>Age 7-15: 5<br>Students: 5<br>Family: 22.5<br>I amsterdam City Card: free";
            getHere = "<i>Tram</i><br>4,9,14: Artis Stop<br><i>Bus</i><br>757: Artis Stop";
            description = "The Verzetsmuseum (the Dutch Resistance Museum) is a museum located in the Plantage neighbourhood. Permanent exhibit of the museum recreates the atmosphere of the streets of Amsterdam during the German occupation of the World War II.";
            history = "null";
            visited = "no";
            if( !inVector(name, names) )
            {
                dbHelper.createContact(latitude, longitude, distanceNs, distanceEw, lat1, long1, lat2, long2, lat3, long3, lat4, long4, lat5, long5, lat6, long6, lat7, long7, lat8, long8, lat9, long9, lat10, long10, lat11, long11, lat12, long12, name, type, collections, address, phone, opening, closed, price, getHere, description, history, visited);
            }




            latitude = 52.367525;
            longitude = 4.901288;
            distanceNs = 0.002907;
            distanceEw = 0.005127;
            lat1 = 52.36839;
            long1 = 4.900412;
            lat2 = 52.367021;
            long2 = 4.90319;
            lat3 = 52.367431;
            long3 = 4.899809;
            lat4 = 52.366386;
            long4 = 4.902596;
            lat5 = 52.367608;
            long5 = 4.898144;
            lat6 = 52.366684;
            long6 = 4.901049;
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
            name = "The Dutch National Opera & Ballet";
            type = "Theatre";
            subType = "Opera<br>Ballet";
            collections = "null";
            area = "null";
            inside = "null";
            address = "Amstel 3, 1011 PN Amsterdam";
            phone = "+31205518117";
            opening = "<i>Box Office</i><br>Mon-Fri, 12:00 pm - 6:00 pm<br>Sat-Sun, 12:00 pm - 3:00 pm<br>Holidays, 12:00 pm - 3:00 pm";
            closed = "From Jul 9th to Aug 14th";
            price = price;
            getHere = "<i>Tram</i><br>9,14: Waterlooplein Stop<br><i>Bus</i><br>757,759,761,763: Waterlooplein Stop";
            description = "The Dutch National Opera & Ballet is located in a building complex named Stopera. The Stopera also includes the city hall. The music theater is situated at the borders of the river Amstel at the Waterlooplein, in one of the oldest parts of the city. Today, it is the most important venue of these arts in the Netherlands.";
            history = "The idea of building a new opera house and a new city hall started in 1915. Various locations were considered until Waterlooplein square was chosen. In 1955, the city established a commission to create the design of the building. The final proposal was rejected in 1964.<br>In 1979, the city decided to combine the new opera house and the new city hall in a unique complex. The city of Amsterdam approved the new, and final, design for the building in 1980. One year later, the national government approved the project as well.<br>The construction begun in 1982, followed by riots. Finally, the opera opened in 1986 followed, two years later, by the new city hall.";

            visited = "no";
            if( !inVector(name, names) )
            {
                dbHelper.createContact(latitude, longitude, distanceNs, distanceEw, lat1, long1, lat2, long2, lat3, long3, lat4, long4, lat5, long5, lat6, long6, lat7, long7, lat8, long8, lat9, long9, lat10, long10, lat11, long11, lat12, long12, name, type, collections, address, phone, opening, closed, price, getHere, description, history, visited);
            }



            latitude = 52.366013;
            longitude = 4.916532;
            distanceNs = 0.004304;
            distanceEw = 0.009645;
            lat1 = 52.368008;
            long1 = 4.913014;
            lat2 = 52.366059;
            long2 = 4.919723;
            lat3 = 52.367834;
            long3 = 4.912336;
            lat4 = 52.365836;
            long4 = 4.912973;
            lat5 = 52.367297;
            long5 = 4.91131;
            lat6 = 52.36604;
            long6 = 4.912263;
            lat7 = 52.367041;
            long7 = 4.919294;
            lat8 = 52.364601;
            long8 = 4.921018;
            lat9 = 52.36648;
            long9 = 4.91202;
            lat10 = 52.364564;
            long10 = 4.921024;
            lat11 = 52.364603;
            long11 = 4.91729;
            lat12 = 52.363535;
            long12 = 4.920983;
            name = "Artis Zoo";
            type = "Park";
            subType = "Zoological";
            collections = "null";
            area = "null";
            inside = "null";
            address = "Plantage Kerklaan 38-40<br>1018 CZ Amsterdam";
            phone = "+31205233694";
            opening = "<i>Nov 1st to Feb 28th</i><br>Mon-Sun, 9:00 am - 5:00 pm <br><i>Mar 1st to Oct 31st</i><br>Mon-Sun, 9:00 am - 6:00 pm<br><i>Jun, Jul, Aug</i><br>Sat, 9:00 am - sunset";
            closed = "Open all days";
            price = "<i>Only Artis</i><br>Adults: 20.5<br>Age 3-9: 17<br>Age 0-2: free<br><i>Artis + Micropia</i><br>Adults: 27.5<br>Age 3-9: 23.5<br>Age 0-2: free";
            getHere = "<i>Tram</i><br>4,9,14: Artis Stop<br><i>Bus</i><br>757: Artis Stop";
            description = "Artis is the oldest zoo in the Netherlands and one of the oldes in Europe. The zoo contains some 700 animal species and 200 tree varieties. The park also contains a planetarium, an aquarium, an arboretum and a library.";
            history = "Gerard Westerman, J.W.H. Werlemann and J.J. Wijsmuller  founded the zoo in 1838. Until 1851, it was open only for members. After that date, the zoo opened also for the public but only in September. Starting 1920, the park opened year-round to the public.";

            visited = "no";
            if( !inVector(name, names) )
            {
                dbHelper.createContact(latitude, longitude, distanceNs, distanceEw, lat1, long1, lat2, long2, lat3, long3, lat4, long4, lat5, long5, lat6, long6, lat7, long7, lat8, long8, lat9, long9, lat10, long10, lat11, long11, lat12, long12, name, type, collections, address, phone, opening, closed, price, getHere, description, history, visited);
            }


        }
        atrs = dbHelper.fetchAllContactsByObjects();

        Vector<Attrazioni> risp = new Vector<Attrazioni>();
        risp.addAll(atrs);
        return risp;
    }



}
