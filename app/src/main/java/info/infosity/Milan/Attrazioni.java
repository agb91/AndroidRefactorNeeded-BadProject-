package info.infosity.Milan;


import android.app.Activity;
import android.content.Intent;




// classe che simula l'esistenza di un database non ancora creato 
// supponendo 1 sola attrazione posta in (0,0)

public class Attrazioni extends Activity implements Comparable<Attrazioni> {

	
private double lon;
private double lat;
private double distanza;
private String nome;
private String generale;
private String desc;
private String tecnica;
private Double scarto;

public Attrazioni(double _lat, double _lon, 
		String _nome,String _generale, String _desc, String _tec, double _distanza){
	lon=_lon;
	lat=_lat;
	nome=_nome;
	generale=_generale;
	desc=_desc;
	tecnica = _tec;
	distanza=_distanza;
}

public String getDesc(){
	return desc;
}

public String getGen(){
	return generale;
}

public String getNome(){
	return nome;
}

public String getTec()
{
	return tecnica;
}

public double getLon(){
	return lon;
}

public double getLat(){
	return lat;
}

public double getDistanza(){
	return distanza;
}

public void video(){

Intent intent = new Intent(getApplicationContext(), Vids.class);

//String pkg=getPackageName(); //per rendere univoci i nomi delle chiavi passate
//� consigliato (la doc dice 'must') aggiungere il nome del nostro package davanti al nome

//intento.putExtra(pkg+".myString", nome);   
startActivity(intent);


}

public Double getScarto() {
	return scarto;
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
