package info.infosity.Milan;


import android.app.Activity;
import android.content.Intent;

import java.util.Vector;


// classe che simula l'esistenza di un database non ancora creato
// supponendo 1 sola attrazione posta in (0,0)

public class Attrazioni extends Activity implements Comparable<Attrazioni> {

	private double scarto=0;
	private double latitude, longitude, distanceNs, distanceEw, lat1, long1, lat2, long2, lat3, long3, lat4, long4, lat5, long5, lat6, long6, lat7, long7, lat8, long8, lat9, long9, lat10, long10, lat11, long11, lat12, long12;
	private String name, type, collections, address, phone, opening, closed, price, getHere, description, history, visited;

	public Attrazioni(double latitude, double longitude, double distanceNs, double distanceEw, double lat1, double long1,
					  double lat2, double long2, double lat3, double long3, double lat4, double long4, double lat5,
					  double long5, double lat6, double long6, double lat7, double long7, double lat8, double long8,
					  double lat9, double long9, double lat10, double long10, double lat11, double long11, double lat12,
					  double long12, String name, String type, String collections, String address, String phone,
					  String opening, String closed, String price, String getHere, String description, String history, String visited){
		this.latitude = latitude;
		this.longitude = longitude;
		this.distanceNs = distanceNs;
		this.distanceEw = distanceEw;
		this.lat1 = lat1;
		this.long1 = long1;
		this.lat2 = lat2;
		this.long2 = long2;
		this.lat3 = lat3;
		this.long3 = long3;
		this.lat4 = lat4;
		this.long4= long4;
		this.lat5=lat5;
		this.long5 = long5;
		this.lat6 = lat6;
		this.long6 = long6;
		this.lat7 = lat7;
		this.long7 = long7;
		this.lat8 = lat8;
		this.long8 = long8;
		this.lat9 = lat9;
		this.long9=long9;
		this.lat10=lat10;
		this.long10=long10;
		this.lat11=lat11;
		this.long11=long11;
		this.lat12 = lat12;
		this.long12 = long12;
		this.name = name;
		this.type = type;
		this.collections = collections;
		this.address = address;
		this.phone = phone;
		this.opening = opening;
		this.closed = closed;
		this.price = price;
		this.getHere = getHere;
		this.description = description;
		this.history = history;
		this.visited = visited;
	}



	public void video(){

	Intent intent = new Intent(getApplicationContext(), Vids.class);

	//String pkg=getPackageName(); //per rendere univoci i nomi delle chiavi passate
	//� consigliato (la doc dice 'must') aggiungere il nome del nostro package davanti al nome

	//intento.putExtra(pkg+".myString", nome);
	startActivity(intent);


	}

	public Double getScarto()
	{
		return scarto;
	};

	public String getType()
	{
		return type;
	}
	public String getCollections()
	{
		return collections;
	}
	public String getAddress()
	{
		return address;
	}
	public String getPhone()
	{
		return phone;
	}
	public String getOpening()
	{
		return opening;
	}
	public String getClosed()
	{
		return closed;
	}
	public String getPrice()
	{
		return price;
	}
	public String getGetHere()
	{
		return getHere;
	}
	public String getDescription()
	{
		return description;
	}
	public String getHistory()
	{
		return history;
	}

	public double getLatitude()
	{
		return latitude;
	};
	public double getLongitude()
	{
		return longitude;
	}

	public double getDistanceNs()
	{
		return distanceNs;
	}

	public double getDistanceEw()
	{
		return distanceEw;
	}

	public double getLat1()
	{
		return lat1;
	}
	public double getLat2()
	{
		return lat2;
	}
	public double getLat3()
	{
		return lat3;
	}
	public double getLat4()
	{
		return lat4;
	}
	public double getLat5()
	{
		return lat5;
	}
	public double getLat6()
	{
		return lat6;
	}
	public double getLat7()
	{
		return lat7;
	}
	public double getLat8()
	{
		return lat8;
	}
	public double getLat9()
	{
		return lat9;
	}
	public double getLat10()
	{
		return lat10;
	}
	public double getLat11()
	{
		return lat11;
	}
	public double getLat12()
	{
		return lat12;
	}
	public double getLong1()
	{
		return long1;
	}

	public double getLong2()
	{
		return long2;
	}

	public double getLong3()
	{
		return long3;
	}

	public double getLong4()
	{
		return long4;
	}

	public double getLong5()
	{
		return long5;
	}

	public double getLong6()
	{
		return long6;
	}

	public double getLong7()
	{
		return long7;
	}
	public double getLong8()
	{
		return long8;
	}
	public double getLong9()
	{
		return long9;
	}
	public double getLong10()
	{
		return long10;
	}
	public double getLong11()
	{
		return long11;
	}
	public double getLong12()
	{
		return long12;
	}
	public Vector<Coordinate> getCoordinates()
	{
		Vector<Coordinate> risp = new Vector<Coordinate>();

		Coordinate c1 = new Coordinate( getLat1(), getLong1() );
		Coordinate c2 = new Coordinate( getLat2(), getLong2() );
		Coordinate c3 = new Coordinate( getLat3(), getLong3() );
		Coordinate c4 = new Coordinate( getLat4(), getLong4() );
		Coordinate c5 = new Coordinate( getLat5(), getLong5() );
		Coordinate c6 = new Coordinate( getLat6(), getLong6() );
		Coordinate c7 = new Coordinate( getLat7(), getLong7() );
		Coordinate c8 = new Coordinate( getLat8(), getLong8() );
		Coordinate c9 = new Coordinate( getLat9(), getLong9() );
		Coordinate c10 = new Coordinate( getLat10(), getLong10() );
		Coordinate c11 = new Coordinate( getLat11(), getLong11() );
		Coordinate c12 = new Coordinate( getLat12(), getLong12() );

		risp.add(c1);
		risp.add(c2);
		risp.add(c3);
		risp.add(c4);
		risp.add(c5);
		risp.add(c6);
		risp.add(c7);
		risp.add(c8);
		risp.add(c9);
		risp.add(c10);
		risp.add(c11);
		risp.add(c12);

		return risp;
	}
	public String getVisited() { return visited; }
	public void setVisited(String s) { visited = s; }



	public String getName()
	{
		return name;
	}

	public void setScarto(double scarto) {
		this.scarto = scarto;
	}

	@Override
	public int compareTo(Attrazioni a) {
		// TODO Auto-generated method stuba
		if(a.getScarto().floatValue()>this.getScarto().floatValue())
		{
			return -1;
		}
		return 1;
	}


}
