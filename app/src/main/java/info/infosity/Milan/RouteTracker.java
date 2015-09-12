package info.infosity.Milan;


//importa le classi necessarie: location si occupa del gps, 
//maps del far apparire la mappa a schermo (non sono certo della sua
// effettiva utilit�
import android.app.Activity;

import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.os.Handler;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

//import com.google.android.maps.MapController;
//import com.google.android.maps.MapView;

import android.content.Intent;
import android.location.Criteria;       
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
//import android.os.PowerManager;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.*;


public class RouteTracker extends Activity implements SensorEventListener
{
	//lon e lat sono longitudine e latitudine, edittext �
	//la classe predefinita per i bottoni
public static Vector<Attrazioni> ottenuteserie;
public static int cont;
public static int cambio = 0;

private Handler handler;

	private DbAdapter dbHelper;
	private Cursor cursor;


private static final int TAKE_PICTURE_ID = 4;
private String desc99="<center><strong>History</strong></center><br>"
		+ "A fire destroyed the previous theatre, the Teatro Regio Ducale, on 25 February 1776, after a carnival gala. A group of ninety wealthy Milanese, who owned palchi (private boxes) in the theatre, wrote to Archduke Ferdinand of Austria-Este asking for a new theatre and a provisional one to be used while completing the new one. The neoclassical architect Giuseppe Piermarini produced an initial design but it was rejected by Count Firmian (the governor of the then Austrian Lombardy).<br>"
		+ "A second plan was accepted in 1776 by Empress Maria Theresa. The new theatre was built on the former location of the church of Santa Maria alla Scala, from which the theatre gets its name. The church was deconsecrated and demolished, and over a period of two years the theatre was completed by Pietro Marliani, Pietro Nosetti and Antonio and Giuseppe Fe. The theatre had a total of 3,000 or so seats.<br>"
		+ "The original structure was renovated in 1907, when it was given its current layout with 1,987 seats. In 1943, during WWII, La Scala was severely damaged by bombing. It was rebuilt and reopened on 11 May 1946, with a memorable concert conducted by Arturo Toscaninitwice La Scala's principal conductor and an associate of the composers Giuseppe Verdi and Giacomo Pucciniwith a soprano solo by Renata Tebaldi, which created a sensation.";
private String desc1="<center><strong>History</strong></center><br>"
		+ "A fire destroyed the previous theatre, the Teatro Regio Ducale, on 25 February 1776, after a carnival gala. A group of ninety wealthy Milanese, who owned palchi (private boxes) in the theatre, wrote to Archduke Ferdinand of Austria-Este asking for a new theatre and a provisional one to be used while completing the new one. The neoclassical architect Giuseppe Piermarini produced an initial design but it was rejected by Count Firmian (the governor of the then Austrian Lombardy).<br>"
		+ "A second plan was accepted in 1776 by Empress Maria Theresa. The new theatre was built on the former location of the church of Santa Maria alla Scala, from which the theatre gets its name. The church was deconsecrated and demolished, and over a period of two years the theatre was completed by Pietro Marliani, Pietro Nosetti and Antonio and Giuseppe Fe. The theatre had a total of 3,000 or so seats.<br>"
		+ "The original structure was renovated in 1907, when it was given its current layout with 1,987 seats. In 1943, during WWII, La Scala was severely damaged by bombing. It was rebuilt and reopened on 11 May 1946, with a memorable concert conducted by Arturo Toscaninitwice La Scala's principal conductor and an associate of the composers Giuseppe Verdi and Giacomo Pucciniwith a soprano solo by Renata Tebaldi, which created a sensation.";
	//triennale
private String desc2="<center><strong>History</strong></center><br>"
		+ "Not available";
	//San siro
private String desc3="<center><strong>History</strong></center><br>"
		+ "Construction of the stadium commenced in 1925 in the district of Milan named San Siro, with the new stadium originally named Nuovo Stadio Calcistico San Siro (San Siro New Football Stadium).<br>"
		+ "The inauguration was on 19 September 1926, when 35,000 spectators saw Internazionale defeat Milan 6-3. Originally, the ground was home and property of AC Milan. In 1947 FC Internazionale became tenants and the two have shared the ground ever since.<br>"
		+ "As well as being used by Milan and Inter, the Italian national team also plays occasional games there and it has also been used for the 1965, 1970, and 2001 UEFA Champions League finals. The stadium was also used for Internazionale's UEFA Cup finals when played over home and away legs but has never featured since the competition changed to a single final structure in 1997-98. The stadium has been chosen by UEFA to host the 2016 Champions League Final.<br>";
	//Simplon Gate
private String desc4="<center><strong>History</strong></center><br>"
		+ "A gate that roughly corresponds to modern Porta Sempione was already part of Roman walls of Milan. It was called Porta Giovia (Jupiter's Gate) and was located at the end of modern Via San Giovanni sul Muro. At the time, the gate was meant to control an important road leading to what is nowCastelseprio. Very little remains of the original Roman structure; some Roman tombstones that used to be placed by the outer side of the walls have been employed in the construction of later buildings such as the Basilica of Saint Simplician (located in Corso Garibaldi).<br>"
		+ "In 1807, under the Napoleonic rule, the Arch of Peace was built by architect Luigi Cagnola. This new gate marked the place where the new Strada del Sempione entered Milan. This road, which is still in use today, connects Milan to Paris through the Simplon Pass crossing the Alps. At the time, the gate was still called Porta Giovia. When the Napoleonic Kingdom of Italy fell and Milan was conquered by the Austrian Empire, the gate was not yet completed, and the construction was abandoned for a while.<br>"
		+ "The construction of the Arch was resumed, again by Cagnola, in 1826, for Emperor Francis II, who dedicated the monument to the 1815 Congress of Vienna. When Cagnola died in 1833, his project was taken over by Francesco Londonio and Francesco Peverelli, who brought it to completion in 1838.<br>"
	    + "The gate was the scene of several prominent events in the Milanese history of the 19th century. In 22 March 1848, the Austrian army led by marshal Josef Radetzky escaped from Milan through Porta Giovia after being defeated in the Five Days of Milan rebellion. On 8 June 1859, four days after the Battle of Magenta, Napoleon III and Victor Emmanuel II of Italy triumphally entered Milan through the gate";
	//Sforza Castle
private String desc5="<center><strong>History</strong></center><br>"
		+ "The original construction was ordered by local lord Galeazzo II Visconti in 1358-c. 1370. The castle was the main residence in the city of its Visconti lords, and was destroyed by the short-lived Golden Ambrosian Republic which ousted them in 1447.<br>"
		+ "In 1450, Francesco Sforza, once shattered the republicans, began reconstruction of the castle to turn it into his princely residence. In 1452 he hired sculptor and architect Filarete to design and decorate the center tower, which is still known as Torre del Filarete. After Francesco's death, the construction was continued by his son Galeazzo Maria, under architect Benedetto Ferrini. The decoration was executed by local painters. In 1476, during the regency of Bona of Savoy, the tower with her name was built.<br>"
		+ "After the French victory in the 1515 Battle of Marignano, the defeated Maximilian Sforza, his Swiss mercenaries, and the cardinal-bishop of Sion retreated into the castle. However, King Francis I of France followed them into Milan, and his sappers placed mines under the castle's foundations, whereupon the defenders capitulated. In 1521, in a period in which it was used as a weapons depot, the Torre del Filarete exploded. When Francesco II Sforzareturned briefly to power in Milan, he had the fortress restored and enlarged, and a part of it adapted as residence for his wife, Christina of Denmark.<br>"
		+ "Under the Spanish domination which followed, the castle became a citadel, as the governor's seat was moved to the Ducal Palace (1535). Its garrison varied from 1,000 to 3,000 men, led by a Spanish castellan. In 1550 works began to adapt the castle to modern fortification style, as an hexagon (originally pentagon)-shaped star fort, following the addition of 12 bastions. The external fortifications reached 3 km in length and covered an area of 25.9 hectares. The castle remained in use as a fort also after the Spaniards were replaced by the Austrians in Lombardy.<br>"
		+ "After the unification of Italy in the 19th century, the castle was transferred from military use to the city of Milan. Parco Sempione, one of the largest parks in the city, was created on the former parade grounds.<br>"
		+ "Allied bombardment of Milan in 1943 during World War II severely damaged the castle. The post-war reconstruction of the building for museum purposes was undertaken by the BBPR architectural partnership.";
	//Science museum
private String desc6="<center><strong>History</strong></center><br>"
		+ "The Museo della Scienza e della Tecnologia Leonardo da Vinci is the largest science and technology museum in Italy, and is dedicated to Italianpainter and scientist Leonardo da Vinci. It was opened on 5 February 1953, inaugurated by prime minister of Italy, Alcide De Gasperi.";
	//San Nazaro
private String desc7="<center><strong>History</strong></center><br>"
		+ "The church was built by St. Ambrose starting from 382 on the road that connected Milan (then Mediolanum) to Rome. It was originally dedicated to the Apostles, and thus known as Basilica Apostolorum.<br>"
		+ "Over the course of the years, it has undergone many modifications. Today, it comprises artistic treasures from different ages. The interior is now based on contrasts between the white of the new plaster, the brick-red vaulting ribs, and the grey of some original Palaeochristian masonry that has been left exposed. The building has the cruciform floor plan typical of Palaeochristian architecture. Inside, Romanesque and Renaissance features can easily be distinguished.";
	//San vittore
private String desc8="<center><strong>History</strong></center><br>"
		+ "The church and monastery of San Vittore al Corpo were an ancient monastery of the Olivetan order built in the early 16th century. The site once had a 4th-century basilica and mausoleum that once held the burials of the emperors Gratian and Valentinian III. The basilica was enlarged in the 8th century to house the relics of the saints Vittore and Satiro. A Benedictine monastery soon was attached to the church. In 1507, the monastery was transferred to the Olivetans, who began a major reconstruction.<br>"
		+ "During the Napoleonic wars, the site became a military hospital, and afterwards became barracks. It suffered damage during the bombardments of 1943. The monastery now houses a museum of science. Reconstruction of the church was begun in 1533 by Vincenzo Seregni, and completed in 1568 by Pellegrino Tibaldi. La façade remains incomplete. The dome was frescoed in 1617 by Guglielmo Caccia (called il Moncalvo). In the chapel of St Anthony is a 1619 canvas by Daniele Crespi (Death of St Paul the hermit). In the transept on the left, is an early 1600s cycle of canvases of the Stories of San Benedetto, byAmbrogio Figino while the right transept has an altarpiece by Camillo Procaccini. Other chapels have paintings by Pompeo Batoni and Giovanni Battista Discepoli.";
	//San cristoforo
private String desc9="<center><strong>History</strong></center><br>"
		+ "The complex is composed by two churches. The left one is the most ancient, which is known to be a Romanesque reconstruction of a far more ancient edifice (probably in turn located on the site of a Roman temple). The Romanesque edifice was again rebuilt in the 13th century, when the Naviglio Grande was excavated. In the mid-14th century it received the Gothic portal and rose window.<br>"
		+ "The Gothic church was flanked by an hospital, built around 1364.<br>"
		+ "The more recent church, which currently is united to the other and gives the appearance of a single edifice, was constructed along the Naviglio bank in the 15th century, called Ducal Chapel. It was commissioned by Duke Gian Galeazzo Visconti in order to provide a holy edifice entitled to the protector of ill people, after a plague, that had killed some 20,000 Milanese in 1399, ceased suddenly for the alleged intercession of St. Christopher. The Chapel was also dedicated to the Saints John the Baptist and James, Blessed Christina, all protectors of the House of Visconti. The façade has in fact the Visconti coat of arms; the older church has instead that of Cardinal Pietro Filargo, then bishop of Milan and later pope as Alexander V.<br>"
		+ "In 1405 the counterfaçade of the Ducal Chapel was decorated with a Madonna Enthroned and Saints and a Crucifixion inspired by that in San Marco of Milan.";
	//San carlo
private String desc10="<center><strong>History</strong></center><br>"
		+ "The church facade was designed in 1844 by Carlo Amati and was finished in 1847. It then served as a model for the Chiesa Rotonda in San Bernardino, Switzerland, 1867.<br>"
		+ "The complex was built to replace Convent of the Servite founded as early as 1290 and later was suppressed in 1799. The new church was built in thanks for the ending a cholera epidemic, and dedicated to Saint Charles Borromeo who was the Bishop of Milan during the time of the bubonic plague in Milan during the 16th century.";
	//San bernardino
private String desc11="<center><strong>History</strong></center><br>"
		+ "The church's origins date to 1145, when a hospital and a cemetery were built in front of the basilica of Santo Stefano Maggiore. In 1210 a chamber was built to house bones from the cemetery, next to which a church was built in 1269. It was restored for the first time in 1679 by Giovanni Andrea Biffi, who modified the façade and decorated the walls of the ossuary with human skulls and tibiae.<br>"
		+ "The church was destroyed in 1712; it was replaced by a new edifice designed by Carlo Giuseppe Merlo, featuring a center plan and larger size reflecting the increasing popularity of the ossuary. The new church, connected to the former one by an ambulatory, was entitled to St. Bernardino of Siena.";
	//San babila
private String desc12="<center><strong>History</strong></center><br>"
		+ "At the beginning of the 5th century, Marolus, the bishop of Milan, brought from Antioch to Milan relics of saints Babylas of Antioch and Romanus of Caesarea. Marolus founded the Basilica Concilia Sanctorum or church of San Romano, which stood until the 19th century a few meters south of the church of San Babila, on the site of a Roman temple dedicated to the Sun.<br>"
		+ "The church of San Babila was built on the same site in about 1095. In the 16th century, the Church was extended with an additional construction at the front and a new baroque façade.<br>"
		+ "The whole complex was renovated in the 19th century with the intent of restoring the appearance of the Medieval basilica, and in the early 20th century the Neo-Romanesque façade by Paolo Cesa-Bianchi was built. The bell tower is from 1920, and replaced the original tower which fell down in the 16th century.<br>"
		+ "The interior has a nave and two aisles. Nothing of the original edifice has remained after the restoration and reconstruction carried on in the following centuries. The two side chapels are from the late Renaissance. The right aisle has an image of the Madonna which is highly venerated by the Milanese population.";
	//Basilica san marco
private String desc13="<center><strong>History</strong></center><br>"
		+ "According to tradition, the church was dedicated to St. Mark, patron of Venice, after the help given by that city in the war against Frederick Barbarossa in the 12th century. However, the first mention of the church dates from 1254 when the Augustinians built a Gothic style edifice with a nave and two aisles re-using pre-existing constructions.<br>"
		+ "The structure was heavily modified in the Baroque style during the 17th century, when it became the largest church in the city after the Duomo di Milano.<br>"
		+ "In early 1770, the young Mozart resided in the monastery of San Marco for three months and, on May 22, 1874, the first anniversary of the death of the Milanese poet and novelist Alessandro Manzoni was commemorated in the church by the first performance of Verdi's Requiem, written in his honour.";
	//Royal villa
private String desc14="<center><strong>History</strong></center><br>"
		+ "The Royal Villa of Milan (Italian: Villa Reale), also called Villa Belgiojoso, was built between 1790 and 1796 as residence of count Ludovico Barbiano di Belgiojoso. The villa was designed by Leopoldo Pollack in a neoclassical style with the main entrance on via Palestro, in front of the Indro Montanelli Gardens(known as the Gardens of Porta Venezia), for the strategical position near the Porta Venezia gate of the city.<br>"
		+ "In 1920 the Royal Villa came under municipal ownership, while in 1921 became the seat of the Modern Art Gallery of Milan. In 1951 the royal villa was flanked by the Padiglione d'Arte Contemporanea dedicated to contemporary art exhibitions, located in via Palestro next to the Modern Art Gallery.<br>"
		+ "Behind the villa there is an English garden with a pond in the middle also designed by Leopoldo Pollack.";
	//Royal palace
private String desc15="<center><strong>History</strong></center><br>"
		+ "The royal palace has ancient origins. It was first called the Palazzo del Broletto Vecchio and was the seat of city's government during the period of medieval communes in the Middle Ages.<br>"
		+ "The palace became a key political centre during the rules of the Torriani, Visconti and Sforza households. After the construction of the Cathedral, there was an important renovation under the government of Francesco Sforza.<br>"
		+ "Between the late 15th and early 16th centuries, with the fall of the Sforza governments and the French invasion, the Castello Sforzesco, which until then was the official residence of the Dukes of Milan, had increasingly become more of a fortress suited for weapons. Under the French rule of Louis XII and of François I, the seat of the court was moved to the current Royal Palace.<br>"
		+ "Thanks to the arrival of the Governor Ferrante Gonzaga in Milan, who took permanent residence in the city from 1546, the building flourished, elevating the ducal court to a true palace and governor's residence in Milan. The Gonzaga were the first to begin to complete the rooms of the complex.<br>"
		+ "To pursue these projects, we know that the Gonzaga governor demolished the old church of Sant'Andrea al Muro Rotto, annexing the land area of the building, while an interior road and enclosed courtyard leading from the church of San Gottardo.<br>"
		+ "New renovations of the building were chosen at the end of the 16th century with the arrival of Governor Antonio de Guzman y Zuniga, Marquis of Ayamonte, who was able to recruit Pellegrino Tibaldi, the architect for the archbishop Charles Borromeo, already engaged in the work of the Duomo in the archbishop's palace and the courtyard of the royalties. Tibaldi worked on the construction of the building from 1573 in 1598 and it was in these years which the pictorial decoration of the apartments noble porticos, of the private chapel and the church of San Gottardo was rebuilt. Several major artists of the time undertook this task, including Aurelio Luini, Ambrose Figino, Antonio Fields and naturally Pellegrino Tibaldi himself, while other stucco and grotesque works were built by Valerio Profondavalle, a Flemish artist-impresario who had also produced some windows for the Duomo of Milan.<br>"
		+ "On 24-25 January 1695, a fire destroyed the Court theater. Reconstruction and expansion of a new ducal theater did not begun until 26 April 1717, when Milan, now part of the Austrian Monarchy as a result of the War of Spanish Succession, received its first Austrian governor, the Count of Loewenstein. The new theater was larger, with four tiers of boxes and a gallery, in the shape of a horseshoe. The design was commissioned from Francesco Galli Bibbiena, who employed his pupilsGiandomenico Barbieri and Domenico Valmagini. The completed theater was inaugurated on 26 December 1717 with the opera Constantino by Gasparini. At that time, the theater sported an adjacent Riddotino for gambling and drinking.<br>"
		+ "A second fire damaged the ceremonial halls of the palace in 1723. Then Austrian magistrate, Wirich Philipp von Daun, commissioned restorations, updating the wings of the Cortile d'Onore (Honor Courtyard) in a livelier style, whitewashing walls and framing the windows with baroque frames designed by Carlo Rinaldi. The church of San Gottardo was rebuilt in a baroque style and is now renamed the Royal Ducal Chapel.<br>"
		+ "In 1745, Gian Luca Pallavicini became governor and minister plenipotentiary of Milan. At his expense, he refurbished the interior with new furniture and decorations, employing architect Francesco Croce, active with the Cathedral Workshop. Croce commissioned tapestries reproducing Raphael works from the Gobelins factories. The halls of Festini and Audienzia were fused to create the present larger ballroom called Hall of the Caryatids with boxes built to hold an orchestra. Pallavacini also commissioned a salon for gala dinners. When Pallavacini left in 1752, he sold his furniture and decor to the state.<br>"
		+ "Rebuilding in 1773 was directed by Giuseppe Piermarini, in collaboration with the Viennese Leopold Pollack. Piermarini lifted the side of the courtyard to the Cathedral, with the other three, creating the Royal Piazetta, and it geometric design on the pavement, then the largest square of the Cathedral. The renovation had to balance the demands of the archduke and the financial limitations imposed by Vienna. Piermarini constructed the present neoclassical facade.<br>"
		+ "Fire struck again, destroying the Court Theater on February 26, 1776. This time the fire-prone Court Theater was built elsewhere, now Teatro Alla Scala, which became one of, if not the first public opera houses. A smaller court theater, now Lyric theater, was built at the site of a demolished school.<br>"
		+ "In 1796, French rulers, specifically Napoleon Bonaparte occupied and ruled Lombardy for nearly two decades. The Palace was renamed the National Palace and became the seat of the main governing bodies of the new republic, namely the military command, first and then the Directory. When the Austro-Russians regain control of Milan in 1799, the French government hastily sold most of the furnishings of the building at auction as well as allowing the looting of other halls of the population.<br>"
		+ "It will only be in 1805 that the building will again rise, inter alia, reaching its peak of splendor. It would indeed be in the same year that Milan will become the capital of the newborn Kingdom of Italy consisting of Napoleon's adopted son Eugène de Beauharnais who was appointed Viceroy and take residence right in Milan's Palazzo Reale. Milan is the capital of a vast kingdom that includes all of northern Italy and as such also the home of the new government needs to be worthy of this privilege.<br>"
		+ "With the annexation of Lombardy to Piedmont domains in 1859, the palace became the seat of the new governorship of the city of Milan, led by Massimo d'Azeglio who start up the 13 February 1860 to leave his post later that year. With the proclamation of the kingdom of Italy in the 1861, the palace became the property directly from the Savoy family, but they tended to stay there very little since Milan was no longer the capital of a kingdom. Umberto I, resided mainly in the Villa Reale di Monza and how this little trodden ground of Milan and after his assassination in 1900 the son Vittorio Emanuele III will tend to stay away from hot environments Milan. The last official receipt dates back to 1906 for the Universal Exhibition.<br>"
		+ "The whole building was heavily damaged during the night of 15 August 1943 when the city was hit by a bombardment English against the Nazi occupiers. In fact the bombs were not directly hit the building, but it was destroyed by fire unleashed in neighbouring buildings that erode the attic of the Hall of Cariatidi, burning the wood warping and causing the collapse of large trusses that in their fall overwhelm the time, the balcony and split in several places and the floor. The high temperature in the room overheated unleashed stucco and turned the color and the constituent material, permanently ruining the famous hall, including Appiani paintings that were kept.<br>"
		+ "Will be after the war in 1947 that the Superintendence of Cultural Heritage will give the start to the work of recovery of the building and specifically the Hall of Caryatids. It is thus achieved a new flooring and a new roof of the hall floor, without the reconstruction of earlier decorations (of which, however, has ample documentation) in the choice of leaving a testimony of the war in Milan.";
	//Porta venezia
private String desc16="<center><strong>History</strong></center><br>"
		+ "A gate whose location and direction roughly correspond to those of modern Porta Venezia was already part of the Roman walls of Milan (then Mediolanum), which were expanded in the middle ages. It connected Milan to eastern Brianza and Bergamo. In the 16th century, the medieval walls were replaced by the Spanish walls, which enclosed a larger area; correspondingly, the gate leading to Bergamo was moved farther away from the city centre to the place it is located today.<br>"
		+ "Over time, the walls lost their defensive purpose, and so did the gates, which were adapted as customs tax stations. During the Austrian Empire rule, between 1783 and 1786, the walls in the Porta Venezia area were redesigned, and avenues and city parks were created in the area; yet the gate itself remained.<br>"
		+ "Under Napoleonic rule, and especially during Francesco Melzi d'Eril's mandate as the Vice-president of the Italian Republic, the Spanish gates of Milan were demolished and replaced with new ones, which would both serve as tax stations and reflect, in their architecture, the prestige of Milan, elected capital of the Republic. The renewal of Porta Venezia was commissioned to architect Giuseppe Piermarini, who is responsible for a large part of the renewal of Milan and the Milanese in the late 18th Century (for example, he designed La Scala, Palazzo Belgioioso, part of the Pinacoteca di Brera, as well as the sumptuous Villa Reale in Monza). Piermarini made plans for the renewal of the gate in neoclassic style, but died (in 1805) before the plan was realized. Piermarini's work was continued by his student Luigi Cagnola, who built a first temporary triumphal arch to celebrate the visit of Eugène de Beauharnais. He then proceeded to the final design, which was still incomplete in 1825 when Emperor Francis II visited Milan.<br>"
		+ "The gate was completed between 1827 and 1828 with the addition of customs offices designed by Rodolfo Vantini. The neoclassical bas-reliefs and statues were added in 1833.";
	//Poldi pezzoli museum
private String desc17="<center><strong>History</strong></center><br>"
		+ "The museum was originated in the 19th century as private collection of Gian Giacomo Poldi Pezzoli (1822-1879) and his mother, Rosa Trivulzio, of the family of the condottiero Gian Giacomo Trivulzio. Many of the rooms in the palace were redecorated starting in 1846, a commissions entrusted to Luigi Scrosati and Giuseppe Bertini. Individual rooms were often decorated and furnished to match the paintings hung on the walls. The architect Simone Cantoni(1736-1818) rebuilt the palace in its present Neoclassical style with an English-style interior garden. In 1850-1853, Poldi Pezzoli commissioned the architect Giuseppe Balzaretto to refurbish his apartment<br>"
		+ "Poldi Pezzoli in his testament left the house and contents to the Brera Academy. Giuseppe Bertini, director of the Academy, opened the museum on April 25, 1881. During World War II, the palace suffered grave damage, but the artworks had been placed in safe storage. The museum was reopened in 1951 after reconstruction.";
	//Parco basiliche
private String desc18="<center><strong>History</strong></center><br>"
		+ "In 1925, the authorities of Milan started a requalification project for the area surrounding the Basilica of San Lorenzo and the nearby Colonne di San Lorenzo(a double colonnade that is one of the major Roman ruins in Milan). At the time, the area had marshes of polluted water produced by the tanneries located in the adjacent Piazza della Vetra, as well as old buildings in a state of decay. The area was cleared in 1934, but it was only after World War II that it was actually redesigned as a green public space. Architects Pier Fausto Bagatti Valsecchi and Antonio Grandi, enrolled in 1956, conceived the new park as a sort of archaeological promenade connecting the two basilicas, the Colonne di San Lorenzo, and the ruins of a Roman amphitheatre.<br>"
		+ "The area surrounding the park and the Colonne di San Lorenzo is one of the most popular night-life districts of Milan, with a number of bars, pubs, disco clubs, and other venues of the so-called 'Milanese movida'. In the last decades of the 20th century, security issues were repeatedly reported, including vandalisms, drug trafficking, excessive noise and abusive parking. These problems were addressed in the park renovation that took place in 2000, on the occasion of the Great Jubilee; specifically, the whole area of the park was fenced and security cameras were installed. On the same occasion, the green area was also partially redesigned, and the name of the park was formally changed to Parco Papa Paolo Giovanni II.";
	//Palazzo mezzanotte
private String desc19="<center><strong>History</strong></center><br>"
		+ "The original stock exchange of Milan was established in 1808 in the seat of the Mount of piety of the city. The following year it was moved to the Palazzo dei Giureconsulti, in Piazza Mercanti, and at the beginning of the 20th century it was relocated again in a newly constructed building in Piazza Cordusio; this building now houses the headquarters of the Poste Italiane postal service, and has thus been renamed Palazzo della Posta (Post Palace).<br>"
		+ "In 1925, the city authorities of Milan launched a project for a new, larger building for the stocks exchange. Architect Paolo Mezzanotte began his design in 1927, and construction works began in 1929. The ruins of a Roman theatre were discovered during the excavations, and this delayed the construction. A plaque showing the map of those ruins is now affixed on one of the outer walls of the palace.<br>"
		+ "The building was completed in 1932.<br>"
		+ "The main room of the Palace, called sala delle grida (cries room) as businessmen would shout their offers to buy and sell, is lighted by a large net, affixed to the ceiling, which reproduces the constellations in the celestial sphere. Since the 1990s, all business is done remotely via the network, and the room has lost its original function; it is now mostly used to house conferences.";
	//Palazzo marino
private String desc20="<center><strong>History</strong></center><br>"
		+ "The palace was built from 1557 to 1563 for Tommaso Marino. It was designed by architect Galeazzo Alessi from Perugia. Its main facade was originally that facing Piazza San Fedele, as Piazza della Scala didn't yet exist; the corresponding area was occupied by buildings. The construction was occasionally slowed down by the opposition of the population, that had a very conservative attitude towards the architecture of the centre of Milan.<br>"
		+ "When Marino died leaving his family bankrupt, the palace became a property of the State, but in 1632 it was sold to another banker, Carlo Omodei. The House of Omodei never inhabited the palace, which maintained its original name Marino and was rented to several notable Milanese.<br>"
		+ "In 1781, the palace was once again bought by the State (the notable Milanese scholar Pietro Verri had an important role in convincing the authorities to buy the palace) and became the seat of administrative and tax offices. The palace was then restored, with the supervision of architect Giuseppe Piermarini, who was responsible for the renovation of the entire area.<br>"
		+ "In 1848, after the Five Days of Milan, the palace was temporarily used as the seat of the new government of Lombardy. It was finally elected as Milan's city hall on 19 September 1861. The acquisition of the palace by the city administration marked a new thorough restoration of the building and the surrounding area. The block that occupied what is now Piazza della Scala was demolished to create the plaza; the facade of Palazzo Marino facing the plaza was renewed to become the palace's main facade (on a design by Luca Beltrami, completed in 1892).<br>"
		+ "A second major restoration occurred after the end of World War II.";
	//Museum 20th century
private String desc21="<center><strong>History</strong></center><br>"
		+ "Opened in December 2010, the museum features one of the largest collections of Italian and international Twentieth Century's art in Italy, with particularly relevant sections dedicated to Futurism, Spatialism and Arte povera. The collection includes masterpieces by several Italian artists such asAmedeo Modigliani, Giorgio de Chirico, Giuseppe Pellizza da Volpedo, Umberto Boccioni, Giacomo Balla, Carlo Carrà, Lucio Fontana, Salvatore Garau,Giorgio Morandi, Mario Sironi as well as international artists as Pablo Picasso, Wassily Kandinsky, Georges Braque, Piet Mondrian, Paul Klee and Henri Matisse.";
	//Museum of Risorgimetno
private String desc22="<center><strong>History</strong></center><br>"
		+ "The Museum was founded on a collection of documents on the Risorgimento, gathered for the Exhibition of Turin in 1884 and then moved to the showroom at Milan's Public Gardens. The exhibition was later transferred to the Rocchetta rooms at the Sforza Castle, where it was officially inaugurated on 24 June 1896. In 1943, due to the war-time bombardment of the castle, the museum was temporarily moved to the estate of Casa Manzoni (home of the famed Italian poet and novelist Alessandro Manzoni). Finally in 1951 it was housed inside the Moriggia Palace, where it remains today.";
	//Museum musical instrument
private String desc23="<center><strong>History</strong></center><br>"
		+ "Not available";
	//Museum ancient art
private String desc24="<center><strong>History</strong></center><br>"
		+ "Not available";
	//Monumental cemetry
private String desc25="<center><strong>History</strong></center><br>"
		+ "Officially opened in 1866, it has since then been filled with a wide range of contemporary and classical Italian sculptures as well as Greek temples, elaborateobelisks, and other original works such as a scaled-down version of the Trajan's Column. Many of the tombs belong to noted industrialist dynasties, and have been designed by renowned artists such as Giò Ponti, Arturo Martini, Lucio Fontana, Medardo Rosso, Giacomo Manzù, Floriano Bodini, and Giò Pomodoro.<br>"
		+ "The Civico Mausoleo Palanti designed by the architect Mario Palanti is a tomb built for meritorious Milanesi, or citizens of Milan. The memorial of about 800 Milanese killed in Nazi concentration camps is located in the center and is the work of the group BBPR, formed by leading exponents of Italian rationalist architecture that included Gianluigi Banfi.";
	//Modern art gallery
private String desc26="<center><strong>History</strong></center><br>"
		+ "Built between 1790 and 1796, the museum was designed by Leopoldo Pollack in neoclassical style. Its collection is largely composed of Italian and European artworks from the 18th to the 20th centuries.<br>"
		+ "In 1951 the Modern Art Gallery was flanked by the Padiglione d'Arte Contemporanea, a space dedicated to contemporary art exhibitions. In 2011 some artworks were relocated to the Museum of Twentieth Century, including Bambina che corre sul balcone by Giacomo Balla (1912), Uomo che dorme by Renato Guttuso (1938) and The Fourth Estate by Pellizza da Volpedo (1901).";
	//Planetarium
private String desc27="<center><strong>History</strong></center><br>"
		+ "The Planetarium building was inaugurated on May 20, 1930. It was designed by architect Piero Portaluppi for Ulrico Hoepli, who donated it to Milan. It has an octagonal base and it is 19.6 meters in diameter, with an overall capacity of 300 seats. The dome-shaped screen is decorated with the silhouette of the Milan skyline as it was in 1930";
	//Duomo
private String desc28="<center><strong>History</strong></center><br>"
		+ "The first cathedral, the new basilica (basilica nova) dedicated to St Thecla, was completed by 355. It seems to share, on a slightly smaller scale, the plan of the contemporaneous church recently rediscovered beneath Tower Hill in London. An adjoining basilica was erected in 836. The old baptistery, the Battistero Paleocristiano, dates to 335 and still can be visited under the Milan Cathedral. When a fire damaged the cathedral and basilica in 1075, they were later rebuilt as the Duomo.<br>"
		+ "In 1386, Archbishop Antonio da Saluzzo began construction of the cathedral. Start of the construction coincided with the ascension to power in Milan of the archbishop's cousin Gian Galeazzo Visconti, and was meant as a reward to the noble and working classes, who had suffered under his tyrannical Visconti predecessor Barnabò. Before actual work began, three main buildings were demolished: the palace of the Archbishop, the Ordinari Palace and the Baptistry of St. Stephen at the Spring, while the old church of Sta. Maria Maggiore was exploited as a stone quarry.<br>"
		+ "In 1389, a French chief engineer, Nicolas de Bonaventure, was appointed, adding to the church its Rayonnant Gothic, a French style not typical for Italy. He decided that the brick structure should be panelled with marble. Galeazzo gave the Fabbrica del Duomo exclusive use of the marble from the Candoglia quarry and exempted it from taxes. Ten years later another French architect, Jean Mignot, was called from Paris to judge and improve upon the work done, as the masons needed new technical aid to lift stones to an unprecedented height. Mignot declared all the work done up till then as in pericolo di ruina (peril of ruin), as it had been done sine scienzia (without science). In the following years Mignot's forecasts proved untrue, but anyway they spurred Galeazzo's engineers to improve their instruments and techniques. Work proceeded quickly, and at the death of Gian Galeazzo in 1402, almost half the cathedral was complete. Construction, however, stalled almost totally until 1480, for lack of money and ideas: the most notable works of this period were the tombs of Marco Carelli and Pope Martin V (1424) and the windows of the apse (1470s), of which those extant portray St. John the Evangelist, by Cristoforo de' Mottis, and Saint Eligius and San John of Damascus, both by Niccolò da Varallo. In 1452, under Francesco Sforza, the nave and the aisles were completed up to the sixth bay.<br>"
		+ "In 1500 to 1510, under Ludovico Sforza, the octagonal cupola was completed, and decorated in the interior with four series of 15 statues each, portraying saints, prophets, sibyls and other characters of the Bible. The exterior long remained without any decoration, except for the Guglietto dell'Amadeo (Amadeo's Little Spire), constructed 1507-1510. This is a Renaissance masterwork which nevertheless harmonized well with the general Gothic appearance of the church.<br>"
		+ "After the accession of Carlo Borromeo to the archbishop's throne, all lay monuments were removed from the Duomo. In 1575-1585 the presbytery was rebuilt, while new altars and the baptistry were added in the Wooden choir stalls were constructed by 1614 for the main altar by Francesco Brambilla. In 1577 Borromeo finally consecrated the whole edifice as a new church, distinct from the old Santa Maria Maggiore and Santa Tecla (which had been unified in 1549 after heavy disputes).<br>"
		+ "At the beginning of the 17th century Federico Borromeo had the foundations of the new façade laid by Francesco Maria Richini and Fabio Mangone. Work continued until 1638 with the construction of five portals and two middle windows. In 1649, however, the new chief architect Carlo Buzzi introduced a striking revolution: the façade was to revert to original Gothic style, including the already finished details within big Gothic pilasters and two giant belfries. Other designs were provided by, among others, Filippo Juvarra (1733) and Luigi Vanvitelli (1745), but all remained unapplied. In 1682 the façade of Santa Maria Maggiore was demolished and the cathedral's roof covering completed.<br>"
		+ "On May 20, 1805, Napoleon Bonaparte, about to be crowned King of Italy, ordered the façade to be finished by Pellicani. In his enthusiasm, he assured that all expenses would fall to the French treasurer, who would reimburse the Fabbrica for the real estate it had to sell. Even though this reimbursement was never paid, it still meant that finally, within only seven years, the Cathedral had its façade completed. Pellicani, largely followed Buzzi's project, adding some neo-Gothic details to the upper windows. As a form of thanksgiving, a statue of Napoleon was placed at the top of one of the spires. Napoleon was crowned King of Italy at the Duomo.<br>"
		+ "In the following years, most of the missing arches and spires were constructed. The statues on the southern wall were also finished, while in 1829-1858, new stained glass windows replaced the old ones, though with less aesthetically significant results. The last details of the cathedral were finished only in the 20th century: the last gate was inaugurated on January 6, 1965. This date is considered the very end of a process which had proceeded for generations, although even now, some uncarved blocks remain to be completed as statues. The Duomo's main façade went under renovation from 2003 to early 2009: as of February 2009, it has been completely uncovered, showing again the colours of the Candoglia marble.";
	//Indro gardens
private String desc29="<center><strong>History</strong></center><br>"
		+ "In the second half of the 18th Century, the area of the Public Gardens was owned by the Dugnani family and was mostly cultivated land; a number of canals irrigated the area that were later closed. The area also included the buildings of two former monasteries (the San Dionigi and the Carcanine monasteries) which had ceased activity under Austrian rule. In 1780, Ferdinand, Duke of Breisgau, who was viceroy of Milan in 1771-1796, assigned architect Giuseppe Piermarini at the renewal of the area and the establishment of a city park. The works were completed between 1782 and 1786, and largely employed prisoners serving a life sentence as manpower.<br>"
		+ "In 1856-1862 architect Giuseppe Balzaretto designed the enlargement of the west side of the Gardens based on the English landscape park model with artificial hills, rooks, and lakes. These works were concluded after the Unification of Italy. In the 19th Century the Natural History Museum was esbalished, along with other animal attractions such as aviaries and exhibits with deers, monkeys and agiraffe; this would later evolve in Milan's Zoo (which was dismantled in 1992). Some of the zoo's most popular animals have been stuffed and are exposed in the Natural History Museum; a few structures from the zoo (such as the pavilion that housed the big cats cages) have remained.";
	//Galleria
private String desc30="<center><strong>History</strong></center><br>"
		+ "The Galleria Vittorio Emanuele II is one of the world's oldest shopping malls. Housed within a four-story double arcade in center Milan, the Galleria is named after Vittorio Emanuele II, the first king of the Kingdom of Italy. It was designed in 1861 and built by Giuseppe Mengoni between 1865 and 1877.";
	//Diocesan museum
private String desc31="<center><strong>History</strong></center><br>"
		+ " The first idea for the Museum dates back to 1931, when the Blessed Ildefonso Schuster, archbishop of Milan, wrote a letter to the clergy titled For sacred art and a Diocesan museum. The suggestion was taken up only in 1960, when cardinal GiovanBattista Montini, later to become Pope Paul VI, signed an agreement between the archbishopric and the Municipality of Milan, in which the cloisters of Sant'Eustorgio were to be restored for the new museum. But the project began only in the 1980s, when cardinal Carlo Maria Martini launched the project for the reconstruction and adaptation of the cloisters for the museum. Design was commissioned from Lodovico Barbiano di Belgiojoso. The Museo Diocesano was inaugurated on 5 November 2001.";
	//Columns
private String desc32="<center><strong>History</strong></center><br>"
		+ "The colonnade, consisting mainly of 16 tall corinthian columns in a row, now fronts an open square. In the 4th century, the columns were moved here, after removal from a likely 2nd century pagan temple or public bath house structure. South of the columns, one of the medieval gates still has some Roman marble decoration still in place. In the 17th century, in preparations for a celebratory entrance into Milan of the monarch King Phillip II of Spain, it was proposed to raze the colonnade to widen the route;Ferrante Gonzaga declined the suggestion.<br>"
		+ "Up until 1935, the space between the church and columns was entirely occupied by old houses abutting onto the façade of the church itself. Indeed, the church complex was fully surrounded by old houses. Despite the plans to conserve this ancient urban fabric, the renovations led to the demolition of the old houses and the isolation of the monument on the front side. Following bombing during World War II, the church complex became isolated also on the rear side, where the fenced Basilicas Park now stands, allowing popular views of the Basilica.";
	//Natural history
private String desc33="<center><strong>History</strong></center><br>"
		+ "The Museo Civico di Storia Naturale di Milano (Milan Natural History Museum) was founded in 1838 when naturalist Giuseppe de Cristoforis (1803-1837) donated his collections to the city of Milan, Italy. Its first director was Giorgio Jan (1791-1866).<br>"
		+ "The Milan Natural History Museum is located within a 19th-century building in the Indro Montanelli Garden, near the historical city gate of Porta Venezia. The structure was built between 1888 end 1893 in Neo-Romanesque style with Gothic elements.";
	//Aquarium
private String desc34="<center><strong>History</strong></center><br>"
		+ "The Civic Aquarium of Milan (Acquario Civico di Milano in Italian) is an aquarium in Milan, Italy, and the third oldest aquarium in Europe. Built in 1905 on the occasion of the Milan World's fair, It is the only surviving building from the event. Sited on the edge of Sempione Park, the aquarium has over 100 different types of underwater life located in several tanks with a particular attention for the fishes and aquatic vegetation of the Italian seacoasts, lakes, and rivers.";
	//St mary
private String desc35="<center><strong>History</strong></center><br>"
		+ "In 1268, the carmelites obtained a seta near the Castello Sforzesco: here, starting from the 14th century, they built a convent with an annexed church. The latter was however destroyed in a fire in 1330. The rebuilt church fell also in abandon before the end of the century, after the friars moved to another convent.<br>"
		+ "The new church was built from 1400, under the design of friar Bernardo da Venezia. Works were completed in 1446. The vault crumbled down three years after the completion, and a restoration was necessary. In the mid-15th century, the church became a favourite destination for aristocratic burials, as testified by the numerous noble tombs in the chapels and niches. In the 17th century, the presbytery was remade in Baroque style. The current façade was designed by Carlo Maciachini and completed in 1880.";
	//San maurizio
private String desc36="<center><strong>History</strong></center><br>"
		+ "The construction began in 1503 under design of Gian Giacomo Dolcebuono in collaboration with Giovanni Antonio Amadeo. The edifice was finished fifteen years later by Cristoforo Solari, divided into two parts: one for the faithful, one for the nuns. Until 1794 the latter were strongly forbidden to cross the dividing wall.";
	//Santa maria
private String desc37="<center><strong>History</strong></center><br>"
		+ "The Duke of Milan Francesco I Sforza ordered the building of a Dominican convent and a church in the place where a small chapel dedicated to St. Mary of the Graces was.<br>"
		+ "The main architect was Guiniforte Solari, the convent was completed by 1469 while the church took more time. The new duke Ludovico Sforza decided to have the church as the Sforza family burial place and rebuild the cloister and the apse which were completed after 1490.<br>"
		+ "In 1543, the Holy Crown chapel on the right of the nave, received a painting by Titian, depicting the Christ receives the Crown of Thorns. Looted by French troops in 1797, it is now in the Louvre.<br>"
		+ "During World War II, on the night of 15 August 1943, bombs dropped by British and American planes hit the church and the convent. Much of the refectory was destroyed, but some walls survived, including the one that holds the The Last Supper, which had been sand-bagged in order to protect it. Some preservation works are done to maintain it for the future.<br>"
		+ "Nowadays the Sacrestia vecchia, or the Old Sacristy, designed and constructed by Donato Bramante, is the seat of a Dominican Cultural Centre (Centro Culturale alle Grazie), in which the brethren organize and host conferences on various themes pertaining to spirituality, philosophy, art, literature and sociology, in addition to musical concerts and artistic exhibitions.";
	//Manzoni
private String desc38="<center><strong>History</strong></center><br>"
		+ "Built in the 18th century, the palace was restored in 1864 by Andrea Boni with a renaissance revival architecture which especially characterizes the façade overlooking Belgiojoso square, designed in 1864 at the instance of Manzoni by architect Andrea Boni and coated with red terracotta.<br>"
		+ "The palace hosted the gatherings of the club Il Conciliatore but even famous men such as Giuseppe Verdi, Cavour and Garibaldi visited it.";
	//Boschi
private String desc39="<center><strong>History</strong></center><br>"
		+ "The Casa Museo Boschi Di Stefano is a historic house museum situated in Via Jan where 300 works from the Boschi Di Stefano collection may be viewed. Antonio Boschi (1896-1988) and Marieda Di Stefano (1901-1968) as well being husband and wife also shared a passion for modern Italian art and became avid collectors. Their collection containing a total of 2,000 pieces was donated to the City of Milan in 1974.";
	//Brera picture
private String desc40="<center><strong>History</strong></center><br>"
		+ "The Palazzo Brera owes its name to the Germanic braida, indicating a grassy opening in the city structure: compare the Bra ofVerona. The convent on the site passed to the Jesuits (1572), then underwent a radical rebuilding by Francesco Maria Richini (1627-28). When the Jesuits were disbanded in 1773, the palazzo remained the seat of the astronomical Observatory and the Braidense National Library founded by the Jesuits. In 1774 were added the herbarium of the new botanical garden. The buildings were extended to designs by Giuseppe Piermarini, who was appointed professor in the Academy when it was formally founded in 1776, with Giuseppe Parini as dean. Piermarini taught at the Academy for 20 years, while he was controller of the city's urbanistic projects, like the public gardens (1787-1788) and piazza Fontana, (17801782).<br>"
		+ "For the better teaching of architecture, sculpture and the other arts, the Academy initiated by Parini was provided with a collection of casts after the Antique, an essential for inculcating a refined Neoclassicism in the students. Under Parini's successors, the abate Carlo Bianconi (1778-1802) and artist Giuseppe Bossi (1802-1807), the Academy acquired the first paintings of its pinacoteca during the reassignment of works of Italian art that characterized the Napoleonic era. Raphael's Sposalizio (the Marriage of the Virgin) was the key painting of the early collection, and the Academy increased its cultural scope by taking on associates across the First French Empire: David, Pietro Benvenuti,Vincenzo Camuccini, Canova, Thorvaldsen and the archaeologist Ennio Quirino Visconti. In 1805, under Bossi's direction, the series of annual exhibitions was initiated with a system of prizes, a counterpart of the Paris Salons, which served to identify Milan as the cultural capital for contemporary painting in Italy through the 19th century. The Academy's artistic committee, the Commissione di Ornato exercised a controlling influence on public monuments, a precursor of today's Sopraintendenze delle Belle Arti.<br>"
		+ "In 1882 the Paintings Gallery was separated from the Academy. From 1891 the exhibitions were reduced to triennial events, and architectural projects developed their autonomous course. During the period of the avant-garde when Modernism was becoming established, the director of the Academy Camillo Boito had as pupil Luca Beltrami, and Cesare Tallone taught Carlo Carrà and Achille Funi.";
	//Brera garden
private String desc41="<center><strong>History</strong></center><br>"
		+ "The garden was established in 1774 by Abbot Fulgenzio Vitman under the direction of Maria Theresa of Austria, transforming an existing Jesuit garden to serve students of medicine and pharmacology. <br>"
		+ "When the Jesuites were suppressed by Pope Clemente XIV, the whole Brera complex became a property of the Austrian State. The Austrian government maintained the destination of the palace as a place of higher learning. The prestigious Platine Schools were transfered there and new cultural institutions were created, among them the School of Botany, Fulgezio Vitman, a Vallombrosan monk was charged to organize the new botanic garden and to teach officinal botany there. Vitman worked out the plans for transforming the ancien Jesuitic garden into a botanic garden with the advice of the famous architect Giuseppe Piermarini, well known for the Scala Theatre. The structure created by Vitman is conserved until present.<br>"
		+ "At the time of Napoleone the fashion of exotic species also reached our botanic garden that became a site of plesure open to the population. When the Austrians came back after the end of the Napoleonic Empire the Botanic Garden of Brera became a part of the S. Alessandro High School and the teaching of medicinale plants was conserved, even at a time when most botanic gardens of Lombardy were suppressed.<br>"
		+ "After the union of Italy into a single kingdom the Botanic Garden was aggregated to the School of Tecnology and in 1870 to the Agricultural School that used it for its experimental cultivations.<br>"
		+ "After many other events the Botanic Garden became a part of the University of Milano that is still managing it.";
	//Branca tower
private String desc42="<center><strong>History</strong></center><br>"
		+ "The tower was designed by architect Gio Ponti and inaugurated in 1933, in the Fascist era; it was originally named Torre Littoria after fascio littorio, i.e., thefasces. After World War II it was renamed Torre del Parco (park tower). In 1972, access to the top of the tower was closed as the structure needed restoring. It was restructured in 2002 by the Branca liquor company, and thus renamed Torre Branca. Since 2002 it is again open to the public.";
	//Bramante
private String desc43="<center><strong>History</strong></center><br>"
		+ "In the early nineties of the 15th century, Ludovico il Moro, Duke of Milan, decided to launch an ambitious expansion and renovation project of the convent of  Santa Maria delle Grazie, which was intended to become the mausoleum of the Sforza family. For this purpose, Ludovico relied on two of the greatest artists of those times, who were already serving at his court: Bramante, to whom he entrusted the task of building the lantern of the basilica and a new sacristy, and Leonardo, who was commissioned to paint the Last Supper on the north wall of the refectory.<br>"
		+ "In 1947 the duchess of Milan, Beatrice d'Este, died prematurely and was buried in the choir of the 'Grazie' basilica. This sad event made the completion of the renovation project even more urgent: by the end of the same year the little cloister and the sacristy are reported to be nearly complete, even though in 1499 the remaining expansion plans stopped suddenly after the fall of Ludovico's Signoria.";
	//Sant ambrogio
private String desc44="<center><strong>History</strong></center><br>"
		+ "One of the most ancient churches in Milan, it was built by St. Ambrose in 379-386, in an area where numerous martyrs of the Roman persecutions had been buried. The first name of the church was in fact Basilica Martyrum.<br>"
		+ "In the centuries after its construction, the edifice underwent several restorations and partial reconstructions, assuming the current appearance in the 12th Century, when it was rebuilt in the Romanesque style.<br>"
		+ "Initially, the basilica was outside the city of Milan, but over the following centuries, the city grew up around it. It became a center of religious life and a community of canons developed in the church. In 789, a monastery was established within the basilica grounds. The canons, however, retained their own community and identity instead of fading away. Two, separate, distinct religious communities shared the basilica. In the 11th century, the canons adopted orders and became Canons Regular. There were now two separate monastic orders following different rules living in the basilica. The canons were in the northern building, the cloister of the canons, while the monks were in the two southern buildings. The two towers symbolize the division in the basilica.<br>"
		+ "On 4 August 1528 it was the so-called 'Peace of St. Ambrose', between the noble and popular factions of the city, was signed here. In 1492 the Benedictines commissioned Donato Bramante, structural architect of St. Peter's Basilica, to renovate the new rectory.<br>"
		+ "In August 1943 the Anglo-American bombings heavily damaged the basilica, in particular the apse and surrounding area. As a result of this a new building, painted in pink, was constructed to house the Abbott's offices and the museum.";
	//Sant eustorgio
private String desc45="<center><strong>History</strong></center><br>"
		+ "Probably founded in the 4th century, its name refers to Eustorgius I, the bishop of Milan to whom is attributed the translation of the supposed relics of the Magito the city from Constantinople in 344. In 1764, when an ancient pillar was removed, a Christian burial was discovered, housing coins of emperor Constans, the son of Constantine the Great.<br>"
		+ "The church was later rebuilt in Romanesque style. In the 12th century, when Milan was sacked by Frederick Barbarossa, the relics of the Magi were appropriated and subsequently taken to Cologne. It was only in 1903/4 that fragments of the bones and garments were sent back to Sant'Eustorgio's. <br>"
		+ "From the 13th century the church was the main Milanese seat of the Dominican Order, who promoted its rebuilding. The current façade is a 19th-century reconstruction. The interior has a nave and two aisles, covered with groin vaults. Of the Romanesque church only parts of the apse remain, while of the original Early Christian building, remains have been excavated also under the apse.";
	//San simpliciano
private String desc46="<center><strong>History</strong></center><br>"
		+ "The site of the present church was occupied in the 3rd century AD by a pagan cemetery. Here St. Ambrose began the construction of the Basilica Virginum('Basilica of the Virgins'), which was finished by his successor Simplicianus, who was buried here. A brick with the mark of the Lombard King Agilulf shows that repairs were made between the years 590-615 AD.<br>"
		+ "In the ninth century the Cluniac Benedictines took possession of the church.<br>"
		+ "On the night of 6-7 April 1252 the body of Peter of Verona (later St. Peter Martyr) was laid in state after his assassination. A great multitude came to watch vigil, and the origins of Peter's cult began, as people started to report miraculous occurrences. In 1517 it was acquired by theBenedictines of Montecassino, who remained here until 1798, when the convent was secularized and for a time turned into barracks. In the 16th century the Spanish governor Ferrante Gonzaga had the bell tower lowered by 25 meters. The dome and the side wings were also modified in 1582. Other interventions were carried out in the 19th century, with poor results, while the façade was reworked in 1870. In 1927 stained-glass windows portraying episodes of the battle of Legnano were added.";
	//San lorenzo
private String desc47="<center><strong>History</strong></center><br>"
		+ "The basilica was built between the late fourth and early fifth centuries. The exact date is uncertain.<br>"
		+ "While Medieval Milan underwent a period of decline, San Lorenzo maintained a leading role in the city's liturgy: as the highest place in Milan it came to represent the Mount of Olives and on Palm Sunday the bishop blessed the palms and led the procession that from there to the now-demolished Basilica of Santa Tecla.<br>"
		+ "The eleventh and twelfth centuries were marked by numerous disasters: fires, in particular the terrible 'fire of the Stork', that in 1071 devoured the basilica, devastating the internal decorations, and earthquakes, that undermined the stability of the complex, making new restorations necessary between the twelfth and thirteenth centuries. Towards the middle of the eleventh century, the open space behind the basilica, called Vetra, was used as the place of executions: this practice continued until 1840.<br>"
		+ "The basilica of San Lorenzo remained throughout the Middle Ages a symbol of the legacy of the Roman Empire in Milan. On 5 June 1573 the dome of the basilica suddenly collapsed, fortunately without causing casualties. Construction of a new dome in a more modern style began immediately and were completed in 1619. During the reconstruction, a miracle occurred, one predicted by Archbishop Carlo Borromeo: one year after his death in 1585, a sick woman was cured in front of the icon of the Madonna del Latte, displayed on the Piazza della Vetra. Following this event, donations increased enabling more rapid progress in the reconstruction. In 1626, the Madonna del Latte was transferred to the high altar where it remains to this day.";
	//San calimero
private String desc48="<center><strong>History</strong></center><br>"
		+ "Its name refers to Saint Calimerius (died 190 AD), an early bishop of the city. It dates from the 5th century but was almost completely rebuilt in 1882 by the architect Angelo Colla in an attempt to restore it to the original medieval structure.";
	//Bagatti
private String desc49="Beginning in the 1880s,  Barons Fausto (Milan, 1843-1914) and Giuseppe (Milano, 1845-1934) Bagatti Valsecchi  undertook the refurbishment of their family home in the heart of Milan between via Gesù and via Santo Spirito.<br>"
		+ "Fausto and Giuseppe were involved firsthand in the restyling of the mansion, and together were the tireless creators of its splendor. Even though they had graduated with law degrees, they never exercised law, professionally. At the center of their interests were the restructuring of the family home, its decoration and the collection of art works destined to embellish it.<br>"
		+ "After the death of Fausto and Giuseppe, the Bagatti Valsecchi family home continued to be inhabited by their descendents until 1974. In that year, Pasino, one of Giuseppe's children who was by then in his seventies, decided to create the Bagatti Valsecchi Foundation to which he donated the patrimony of art his ancestors had collected. At the same time, the mansion was sold to the region of Lombardy with the clause that the historic displays on the first floor were to be preserved 'as is' in order to preserve the unbreakable tie between the 'container' (the spaces) and the 'contained' (the furnishings and art collections), one of the distinctive traits of the Bagatti Valsecchi brothers' collecting efforts. Twenty years later, in 1994, the Bagatti Valsecchi Museum was opened to the public.";
	//Archeological
private String desc50="Not available";
	//Ambrosiana
private String desc51="Cardinal Borromeo envisioned developing this library in Milan as one open to scholars and that would serve as a bulwark of Catholic scholarship in the service of the Counter-Reformation against the treatises issuing from Protestant presses.<br>"
		+ "Construction began in 1603 under designs and direction of Lelio Buzzi and Francesco Maria Richini. Its first reading room opened to the public on 8 December 1609.<br>"
		+ "Constant acquisitions, soon augmented by bequests, required enlargement of the space. Borromeo intended an academy (which opened in 1625) and a collection of pictures, for which a new building was initiated in 1611-18 to house the Cardinal's paintings and drawings, the nucleus of the Pinacoteca (picture gallery).<br>"
		+ "Shortly after the cardinal's death, his library acquired twelve manuscripts of Leonardo da Vinci, including the Codex Atlanticus.<br>"
		+ "Among the 30,000 manuscripts, which range from Greek and Latin to Hebrew, Syriac, Arabic, Ethiopian, Turkish and Persian, is the Muratorian fragment, of ca 170 A.D., the earliest example of a Biblical canon and an original copy of De divina proportione by Luca Pacioli. Among Christian and Islamic Arabic manuscripts are treatises on medicine, a unique 11th-century diwan of poets, and the oldest copy of the Kitab Sibawahaihi.";


private String tec99 ="<center><strong>Address</strong></center><br>"
+ "Via Filodrammatici 2";

//teatro scala
private String tec1="<center><strong>Address</strong></center><br>"
	+ "Via Filodrammatici, 2";
//triennale
private String tec2="<center><strong>Address</strong></center><br>"
	+ "Viale Alemagna, 6";
//San siro
private String tec3="<center><strong>Address</strong></center><br>"
	+ "Via dei Piccolomini, 5";
//Simplon Gate
private String tec4="<center><strong>Address</strong></center><br>"
	+ "Piazza Sempione";
//Sforza Castle
private String tec5="<center><strong>Address</strong></center><br>"
	+ "Piazza castello";
//Science museum
private String tec6="<center><strong>Address</strong></center><br>"
	+ "Via San Vittore, 21<br>"
	+ "<br>"
	+ "<center><strong>Departments</strong></center><br>"
	+ "Materials, Transport, Energy, Communication, Leonardo da Vinci: Art and Science, New Frontiers, Science for young people<br>"
	+ "<br>"
	+ "<center><strong>Director</strong></center><br>"
	+ "Fiorenzo Galli";
//San Nazaro
private String tec7="<center><strong>Address</strong></center><br>"
	+ "Piazza San Nazaro in Brolo, 5<br>"
	+ "<br>"
	+ "<center><strong>Style</strong></center><br>"
	+ "Romanesque";
//San vittore
private String tec8="<center><strong>Address</strong></center><br>"
	+ "Via degli Olivetani, 3";
//San cristoforo
private String tec9="<center><strong>Address</strong></center><br>"
	+ "Via San Cristoforo, 3<br>"
	+ "<br>"
	+ "<center><strong>Style</strong></center><br>"
	+ "Gothic";
//San carlo
private String tec10="<center><strong>Address</strong></center><br>"
	+ "Corso Giacomo Matteotti, 14<br>"
	+ "<br>"
	+ "<center><strong>Architect</strong></center><br>"
	+ "Carlo Amati; Filippo Pizzigalli<br>"
	+ "<br>"
	+ "<center><strong>Style</strong></center><br>"
	+ "Neoclassical";
//San bernardino
private String tec11="<center><strong>Address</strong></center><br>"
	+ "Via Verziere, 2<br>"
	+ "<br>"
	+ "<center><strong>Style</strong></center><br>"
	+ "Mannerist";
//San babila
private String tec12="<center><strong>Address</strong></center><br>"
	+ "Corso Monforte<br>"
	+ "<br>"
	+ "<center><strong>Style</strong></center><br>"
	+ "Romanesque Revival";
//Basilica san marco
private String tec13="<center><strong>Address</strong></center><br>"
	+ "Piazza San Marco, 2<br>"
	+ "<br>"
	+ "<center><strong>Style</strong></center><br>"
	+ "Gothic; Baroque; Gothic Revival";
//Royal villa
private String tec14="<center><strong>Address</strong></center><br>"
	+ "Via Palestro, 16";
//Royal palace
private String tec15="<center><strong>Address</strong></center><br>"
	+ "Piazza del Duomo, 12";
//Porta venezia
private String tec16="<center><strong>Address</strong></center><br>"
	+ "Corso di Porta Venezia";
//Poldi pezzoli museum
private String tec17="<center><strong>Address</strong></center><br>"
	+ "Via Manzoni, 12<br>"
	+ "<br>"
	+ "<center><strong>Director</strong></center><br>"
	+ "Annalisa Zanni";
//Parco Basiliche
private String tec18="<center><strong>Address</strong></center><br>"
	+ "Via Molino delle Armi <br>"
	+ "<br>"
	+ "<center><strong>Area</strong></center><br>"
	+ "40,700 square metres (438,000 sq ft)";
//Palazzo mezzanotte
private String tec19="<center><strong>Address</strong></center><br>"
	+ "Piazza degli Affari, 6<br>"
	+ "<br>"
	+ "<center><strong>Architect</strong></center><br>"
	+ "Paolo Mezzanotte<br>"
	+ "<br>"
	+ "<center><strong>Style</strong></center><br>"
	+ "Neoclassic";
//Palazzo marino
private String tec20="<center><strong>Address</strong></center><br>"
	+ "Piazza della Scala, 2";
//Museum 20th century
private String tec21="<center><strong>Address</strong></center><br>"
	+ "Via Marconi, 1<br>"
	+ "<br>"
	+ "<center><strong>Director</strong></center><br>"
	+ "Marina Pugliese";
//Museum of risorgimento
private String tec22="<center><strong>Address</strong></center><br>"
	+ "Via Borgonuovo, 23<br>"
	+ "<br>"
	+ "<center><strong>Director</strong></center><br>"
	+ "Marina Messina";
//Museum musical instrument+
private String tec23="<center><strong>Address</strong></center><br>"
	+ "Piazza del castello 3";
//Museum ancient art
private String tec24="<center><strong>Address</strong></center><br>"
	+ "Piazza del castello 3<br>"
	+ "<br>"
	+ "<center><strong>Director</strong></center><br>"
	+ "Giulia Amato";
//Monumental cemetry
private String tec25="<center><strong>Address</strong></center><br>"
	+ "Piazzale Cimitero Monumentale<br>"
	+ "<br>"
	+ "<center><strong>Area</strong></center><br>"
	+ "250,000 square meters (2,700,000 sq ft)";
//Modern art gallery
private String tec26="<center><strong>Address</strong></center><br>"
	+ "Via Palestro, 16<br>"
	+ "<br>"
	+ "<center><strong>Director</strong></center><br>"
	+ "Marina Pugliese <br>"
	+ "<br>"
	+ "<center><strong>Curator</strong></center><br>"
	+ "Paola Zatti";
//Planetarium
private String tec27="<center><strong>Address</strong></center><br>"
	+ "Corso Venezia 57";
//Duomo
private String tec28="<center><strong>Address</strong></center><br>"
	+ "Piazza del Duomo, 16<br>"
	+ "<br>"
	+ "<center><strong>Style</strong></center><br>"
	+ "Italian Gothic<br>"
	+ "<br>"
	+ "<center><strong>Capacity</strong></center><br>"
	+ "40,000"
	+ "<br>"
	+ "<center><strong>Length</strong></center><br>"
	+ "158.5 metres (520 ft)<br>"
	+ "<br>"
	+ "<center><strong>Width</strong></center><br>"
	+ "92 metres (302 ft)<br>"
	+ "<br>"
	+ "<center><strong>Height</strong></center><br>"
	+ "108 metres (354 ft)";
//Indro gardens
private String tec29="<center><strong>Entrances/strong></center><br>"
	+ "Bastioni di Porta Venezia, Via Manin, Via Palestro, Corso Venezia<br>"
	+ "<br>"
	+ "<center><strong>Area</strong></center><br>"
	+ "172.000 square meters<br>"
	+ "<br>"
	+ "<center><strong>Monuments</strong></center><br>"
	+ "Several monuments and tourist attractions are enclosed within the park: the Ulrico Hoepli Planetarium, the Natural History Museum, Palazzo Dugnani, and the eclectic Padiglione del Caffè.";
//Galleria
private String tec30="<center><strong>Address</strong></center><br>"
	+ "Piazza duomo<br>"
	+ "<br>"
	+ "<center><strong>Architect</strong></center><br>"
	+ "Giuseppe Mengoni";
//Diocesean museum
private String tec31="<center><strong>Address</strong></center><br>"
	+ "Corso di Porta Ticinese, 95<br>"
	+ "<br>"
	+ "<center><strong>Director</strong></center><br>"
	+ "Paolo Biscottini";
//Columns
private String tec32="<center><strong>Address</strong></center><br>"
	+ "Corso di Porta Ticinese<br>"
	+ "<br>"
	+ "<center><strong>Style</strong></center><br>"
	+ "Roman";
//Natural history
private String tec33="<center><strong>Address</strong></center><br>"
	+ "Corso Venezia, 55<br>"
	+ "<br>"
	+ "<center><strong>Sections</strong></center><br>"
	+ "Mineralogy, Paleontology, Natural history of man, Invertebrate Zoology, Vertebrate Zoology <br>"
	+ "<br>"
	+ "<center><strong>Director</strong></center><br>"
	+ "Domenico Piraina";
//Aquarium
private String tec34="<center><strong>Address</strong></center><br>"
	+ "Viale G. Gadio, 2";
//St mary
private String tec35="<center><strong>Address</strong></center><br>"
	+ "Piazza del Carmine, 2";
//San maurizio
private String tec36="<center><strong>Address</strong></center><br>"
	+ "Corso Magenta, 13 <br>"
	+ "<br>"
	+ "<center><strong>Style</strong></center><br>"
	+ "Renaissance; Baroque<br>"
	+ "<br>"
	+ "<center><strong>Architect</strong></center><br>"
	+ "Gian Giacomo Dolcebuono, Giovanni Antonio Amadeo";
//Santa maria
private String tec37="<center><strong>Address</strong></center><br>"
	+ "Piazza Santa Maria delle Grazie, 2 <br>"
	+ "<br>"
	+ "<center><strong>Style</strong></center><br>"
	+ "Gothic (Nave), Renaissance (Apse and Dome)<br>"
	+ "<br>"
	+ "<center><strong>Architect</strong></center><br>"
	+ "Guiniforte Solari, Donato Bramante";
//Manzoni
private String tec38="<center><strong>Address</strong></center><br>"
	+ "Via Gerolamo Morone, 1 <br>"
	+ "<br>"
	+ "<center><strong>Style</strong></center><br>"
	+ "Renaissance Revival architecture";
//Boschi
private String tec39="<center><strong>Address</strong></center><br>"
	+ "Via Giorgio Jan, 15<br>"
	+ "<br>"
	+ "<center><strong>Type</strong></center><br>"
	+ "Contemporary Art, Decorative Arts, Historic House";
//Brera picture
private String tec40="<center><strong>Address</strong></center><br>"
	+ "Via Brera, 28<br>"
	+ "<br>"
	+ "<center><strong>Other</strong></center><br>"
	+ "It was the first museum of the city and one of the most important picture galleries in the world. It houses some of the greatest selections of antique and modern masterpieces and sculptures by italian artists of the 20th century.";
//Brera Garden
private String tec41="<center><strong>Address</strong></center><br>"
	+ "Via Brera, 28<br>"
	+ "<br>"
	+ "<center><strong>Area</strong></center><br>"
	+ "5,000 square meters<br>"
	+ "<br>"
	+ "<center><strong>Other</strong></center><br>"
	+ "The Astronomy Museum in the same building conserves valuable instruments from Milan University and from the Observatory; the latter is the oldest scientific research institution in the city. Desired by Maria and Teresa of Austria, it was founded in the late 18th century by two Jesuit priests. Father Giuseppe Ruggero Boscovich, a professor of mathematics at the University of Pavia, played an active part in the Observatory's foundation, and he helped spread the institution's reputation by means of his work. ";
//Branca tower
private String tec42="<center><strong>Address</strong></center><br>"
	+ "Viale Alemagna";
//Bramante
private String tec43="<center><strong>Address</strong></center><br>"
	+ "Via Caradosso, 1 (in the convent of Santa Maria delle Grazie)";
//Sant ambrogio
private String tec44="<center><strong>Address</strong></center><br>"
	+ "Piazza Sant'Ambrogio, 15<br>"
	+ "<br>"
	+ "<center><strong>Style</strong></center><br>"
	+ "Romanesque";
//Sant eustorgio
private String tec45="<center><strong>Address</strong></center><br>"
	+ "Piazza Sant'Eustorgio, 1<br>"
	+ "<br>"
	+ "<center><strong>Style</strong></center><br>"
	+ "First Romanesque";
//San simpliciano
private String tec46="<center><strong>Address</strong></center><br>"
	+ "Piazza San Simpliciano, 7<br>"
	+ "<br>"
	+ "<center><strong>Style</strong></center><br>"
	+ "Romanesque";
//San lorenzo
private String tec47="<center><strong>Address</strong></center><br>"
	+ "Corso di Porta Ticinese, 35<br>"
	+ "<br>"
	+ "<center><strong>Style</strong></center><br>"
	+ "Early Christian,Renaissance, Baroque";
//San calimero
private String tec48="<center><strong>Address</strong></center><br>"
	+ "Via San Calimero<br>"
	+ "<br>"
	+ "<center><strong>Style</strong></center><br>"
	+ "Romanesque Revival ( Neo-Romanesque)";
//Bagatti
private String tec49="<center><strong>Address</strong></center><br>"
	+ "Via Gesù, 5<br>"
	+ "<br>"
	+ "<center><strong>Collections</strong></center><br>"
	+ "Musical instruments, Sculptures, Wooden furnishing, Gilded and stuccoed coffers, Clocks, Wooden objects, Metal objects, Scientific instruments, Textiles, Stained glass windows, Glass, Tapestries, Arms, Ivory objects, Ceramics, Paintings";
//Archeological
private String tec50="<center><strong>Address</strong></center><br>"
	+ "Corso Magenta, 15<br>"
	+ "<br>"
	+ "<center><strong>Collections</strong></center><br>"
	+ "The collections are displayed in different rooms depending on the culture of origin. On the ground floor, after the first cloister (a Milanese architectural decoration) is the Ancient Milan section, while downstairs the route continues to Living in Mediolanum. Also downstairs are the Caesarea Maritima (Israel) and Gandhara sections.<br>"
	+ "The viewing route continues in the back cloister ('Milanese society through inscriptions'). At the end of the second cloister a path leads to the polygonal tower (late third century) with early medieval frescoes (thirteenth century) and comes out in the new museum in Via Nirone where the early medieval section is on the first floor.<br>"
	+ "On the second floor is the Etruscan section; the items are an important heritage, valuable in the reconstruction of many aspects of Etruscan civilisation.<br>"
	+ "The third and last floor is the Greek section. The exhibition is dedicated to Greek society and everyday life marked by economic activities, from theatre and religion to reflections on life and death.<br>"
	+ "The Egyptian collections of Prehistory and Early History are on display in the Sale Visconti of the Castello Sforzesco. ";
//Amrbosiana
private String tec51="<center><strong>Address</strong></center><br>"
	+ "Piazza Pio XI";

private String gen99="<center><strong>Ticket</strong></center><br>"
		+ "The cost of the ticket varies depending on the seat and the show";

//teatro scala
private String gen1="<center><strong>Ticket</strong></center><br>"
	+ "The cost of the ticket varies depending on the seat and the show";
//triennale
private String gen2="<center><strong>Opening hours</strong></center><br>"
	+ "Tuesday - Sunday, from 10:30 a.m. to 8:30 p.m.<br>"
	+ "Thursday,  from 10:30 a.m. to 11:00 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br><br>"
	+ "Adult: 12€ <br>"
	+ "Reduced: 10€";
//San siro
private String gen3="<center><strong>Opening hours</strong></center><br>"
	+ "<strong>Museum</strong><br>"
	+ "Monday - Sunday, from 9:30 a.m. to 6:00 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br><br>"
	+ "Adult: 17€<br>"
	+ "Reduced: 12€ (until 12 years old, over 65)";
//Simplon gate
private String gen4="<center><strong>Opening hours</strong></center><br>"
	+ "Everyday, 24h<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free";
//Sforza Castle
private String gen5="<center><strong>Opening hours</strong></center><br>"
	+ "<strong>Castle</strong><br>"
	+ "<strong>Summer time</strong><br>"
	+ "Monday - Sunday, from 7:00 a.m. to 7:00 p.m.<br>"
	+ "<strong>Winter time</strong><br>"
	+ "Monday - Sunday, from 7:00 a.m. to 6:00 p.m.<br>"
	+ "<strong>Museums</strong><br>"
	+ "Tuesday - Sunday, from 9:00 a.m. to 5:30 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "Monday, January 1, Easter, May 1, December 25<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free (except museums)";
//Science museum
private String gen6="<center><strong>Opening hours</strong></center><br>"
	+ "Tuesday - Friday, from 9:30 a.m. to 5:00 p.m. (last admission at 4:30 p.m.)<br>"
	+ "Saturday - Sunday, from 9:30 a.m. to 6:30 p.m. (last admission at 6:00 p.m.)<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "Monday, January 1, December 24, December 25<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Adult: 10€<br>"
	+ "Reduced: 7.5€";
//San Nazaro
private String gen7="<center><strong>Opening hours</strong></center><br>"
	+ "Monday - Friday, from 7:30 a.m. to 12:00 a.m. and from 3:30 p.m. to 6:30 p.m.<br>"
	+ "Saturday - Sunday, from 8:00 a.m. to 12:30 p.m. and from 3:30 p.m. to 7:00 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free";
//San vittore
private String gen8="<center><strong>Opening hours</strong></center><br>"
	+ "Monday, Tuesday, Thursday, from 7:30 a.m. to 12:00 a.m. and from 3:30 p.m. to 6:30 p.m.<br>"
	+ "Wednesday, from 7:30 a.m. to 12:00 a.m.<br>"
	+ "Friday, Saturday, from 7:30 a.m. to 6:30 p.m.<br>"
	+ "Sunday, from 8:00 a.m. to 1:00 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "July, August<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free";
//San cristoforo
private String gen9="<center><strong>Opening hours</strong></center><br>"
	+ "Monday - Sunday, from 8:00 a.m. to 5:30 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free";
//San carlo
private String gen10="Not available";
//San bernardino
private String gen11="<center><strong>Opening hours</strong></center><br>"
	+ "Monday - Friday, from 7:30 a.m. to 12:00 a.m. and from 1:00 p.m. to 6:00 p.m.<br>"
	+ "Saturday, from 7:30 a.m. to 12:00 a.m.<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "Sunday";
//San babila
private String gen12="<center><strong>Opening hours</strong></center><br>"
	+ "Monday - Sunday, from 7:30 a.m. to 12:00 a.m. and from 3:30 p.m. to 7:00 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free";
//Basilica san marco
private String gen13="Not available";
//Royal villa
private String gen14="<center><strong>Opening hours</strong></center><br>"
	+ "Tuesday - Sunday, from 9:00 a.m. to 5:30 p.m. (last admission at 5:00 p.m.)<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "Monday, January 1, May 1, December 25<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Adult: 5€<br>"
	+ "Reduced: 3€<br>"
	+ "Free: everyday from 4:30 p.m. (Tuesday from 2:00 p.m.)";
//Royal palace
private String gen15="<center><strong>Opening hours</strong></center><br>"
	+ "Monday, from 2:30 p.m. to 7:30 p.m.<br>"
	+ "Tuesday - Sunday, from 9:30 a.m. to 7:30 p.m.<br>"
	+ "Thursday and Saturday, from 9:30 a.m. to 10:30 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Adult: 9€";
//Porta venezia
private String gen16="<center><strong>Opening hours</strong></center><br>"
	+ "Everyday, 24h<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free";
//Poldi pezzoli museum
private String gen17="<center><strong>Opening hours</strong></center><br>"
	+ "Wednesday - Monday, from 10:00 a.m. to 6:00 p.m. (last admission at 5:30 p.m.)<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "Tuesday, January 1, Easter, April 25, May 1, August 15, November 1, December 8, December 25, December 26<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Adult: 10€<br>"
	+ "Reduced: 7€ (11-18 years old, students under 26 years old, over 65)";
//Parco basiliche
private String gen18="<center><strong>Opening hours</strong></center><br>"
	+ "Everyday, 24h<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free";
//Palazzo mezzanotte
private String gen19="Not available";
//Palazzo Marino
private String gen20="Palazzo Marino is open to the public only on special occasions.";
//Museum 20th century
private String gen21="<center><strong>Opening hours</strong></center><br>"
	+ "Monday, from 2:30 p.m. to 7:30 p.m.<br>"
	+ "Tuesday, Wednesday, Friday, Sunday, from 9:30 a.m. to 7:30 p.m.<br>"
	+ "Thursday, Saturday, from 9:30 a.m. to 10:30 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Adult: 5€<br>"
	+ "Reduced: 3€";
//Museum of risorgimento
private String gen22="<center><strong>Opening hours</strong></center><br>"
	+ "Tuesday - Sunday, from 9:00 a.m. to 1:00 p.m. and from 2:00 p.m. to 5:30 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "Monday, January 1, May 1, December 25<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Adult: 2€<br>"
	+ "Reduced: 1€";
//Museum musical instrument
private String gen23="<center><strong>Opening hours</strong></center><br>"
	+ "Tuesday - Sunday, from 9:00 a.m. to 1:00 p.m. and from 2:00 p.m. to 5:30 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "Monday, January 1, Easter, May 1, December 25<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Adult: 5€<br>"
	+ "Reduced: 3€"
	+ "3 days ticket: 12€";
//Museum ancient art
private String gen24="<center><strong>Opening hours</strong></center><br>"
	+ "Tuesday - Sunday, from 9:00 a.m. to 5:30 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "Monday, January 1, Easter, May 1, December 25<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Adult: 5€<br>"
	+ "Reduced: 3€"
	+ "3 days ticket: 12€"
	+ "Free everyday from 4:30 p.m. and Tuesday from 2:00 p.m.";
//Monumental cemetry
private String gen25="<center><strong>Opening hours</strong></center><br>"
	+ "Tuesday - Sunday, from 8:00 a.m. to 6:00 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "Monday<br>";
//Modern art gallery
private String gen26="<center><strong>Opening hours</strong></center><br>"
	+ "Tuesday - Sunday, from 9:00 a.m. to 5:30 p.m. (last admission: 5:00 p.m.)<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "Monday, January 1, May 1, December 25<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Adult: 5€<br>"
	+ "Reduced: 3€ (over 65)"
	+ "3 days ticket: 12€"
	+ "Free from 4:30 p.m. everyday, Tuesday free from 2:30 p.m."
	+ "Free under 18 years old";
//Planetarium
private String gen27="<center><strong>Opening hours</strong></center><br>"
	+ "Opening times change according to the season. Please contact the Planetarium (phone: +39 02 8846 3340 )<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Adult: 5€<br>"
	+ "Reduced (over 65, younger than 18): 3€";
//Duomo
private String gen28="<center><strong>Opening hours</strong></center><br>"
	+ "<strong>Cathedral</strong><br>"
	+ "Monday - Sunday, from 7:00 a.m. to 6:40 p.m.<br>"
	+ "<strong>Rooftop</strong><br>"
	+ "Monday - Sunday, from 9:00 a.m. to 6:30 p.m. (last admission: 6:00 p.m.)<br>"
	+ "<strong>Santo Stefano's baptistery</strong><br>"
	+ "Monday - Sunday, from 9:00 a.m. to 7:00 p.m.<br>"
	+ "<strong>San Giovanni's baptistery</string><br>"
	+ "Tuesday - Sunday, from 10:00 a.m. to 6:00 p.m. (last admission: 4:50 p.m.)<br>"
	+ "Open also: December 26, January 6, Easter, April 25, June 2<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "<strong>Rooftop</strong><br>"
	+ "December 25, May 1<br>"
	+ "<strong>San Giovanni's baptistery</string><br>"
	+ "January 1, May 1, August 15, December 25<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "<strong>Cathedral</strong><br>"
	+ "Free <br>"
	+ "Photographic ticket: 2€<br>"
	+ "<strong>Rooftop</strong><br>"
	+ "With lift: 12€, Reduced: 6€<br>"
	+ "On foot: 7€, Reduced: 3,5€<br>"
	+ "<strong>Santo Stefano's baptistery</strong><br>"
	+ "Free<br>"
	+ "<strong>San Giovanni's baptistery</string><br>"
	+ "Adult: 6€<br>"
	+ "Reduced: 4€";
//Indro gardens
private String gen29="<center><strong>Opening hours</strong></center><br>"
	+ "January - February, from 6:30 a.m. to 8:00 p.m.<br>"
	+ "March - April, from 6:30 a.m. to 9:00 p.m.<br>"
	+ "May, from 6:30 a.m. to 10:00 p.m.<br>"
	+ "June - September, from 6:30 a.m. to 11:30 p.m.<br>"
	+ "October, from 6:30 a.m. to 9:00 p.m.<br>"
	+ "November - December, from 6:30 a.m. to 8:00 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free";
//Galleria
private String gen30="Not available";
//Diocesean museum
private String gen31="<center><strong>Opening hours</strong></center><br>"
	+ "Tuesday - Sunday, from 10:00 a.m. to 6:00 p.m. (last admission: 5:30 p.m.)<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "Monday, January 1, May 1, December 25, December 26<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Adult: 8€<br>"
	+ "Reduced: 5€<br>"
	+ "Tuesday: 4€<br>"
	+ "Free (untill 6 years old)";
//Columns
private String gen32="<center><strong>Opening hours</strong></center><br>"
	+ "Everyday, 24h<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free";
//Natural history
private String gen33="<center><strong>Opening hours</strong></center><br>"
	+ "Tuesday - Sunday, from 9:00 a.m. to 5:30 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "Monday<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Adult: 5€<br>"
	+ "Reduced: 3€ <br>"
	+ "Under 18: Free<br>"
	+ "Free every day from 4:30 p.m. and on Tuesday from 2:00 p.m.";
//Aquarium
private String gen34="<center><strong>Opening hours</strong></center><br>"
	+ "Tuesday - Sunday, from 9:00 a.m. to 5:30 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "Monday, January 1, May 1, December 25<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free";
//St mary
private String gen35="<center><strong>Opening hours</strong></center><br>"
	+ "From 8:30 a.m. to 12:00 a.m. and from 5:30 p.m. to 7:00 p.m."
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free";
//San maurizio
private String gen36="<center><strong>Opening hours</strong></center><br>"
	+ "Tuesday - Saturday, from 9:30 a.m. to 5:30 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free";
//Santa maria
private String gen37="<center><strong>Opening hours</strong></center><br>"
	+ "Monday - Friday, from 7:00 a.m. to 12:00 a.m. and from 3:00 p.m. to 7:00 p.m.<br>"
	+ "Saturday - Sunday, from 7:15 a.m. to 12:15 p.m. and from 3:30 p.m. to 9:00 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free";
//Manzoni
private String gen38="<center><strong>Opening hours</strong></center><br>"
	+ "<strong>Museum</strong><br>"
	+ "Tuesday - Friday, from 9:00 a.m. to 4:00 p.m.<br>"
	+ "<strong>Library</strong><br>"
	+ "From 9:00 a.m. to 12:00 and from 2:00 p.m. to 4:00 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Museum: Free";
//Boschi
private String gen39="<center><strong>Opening hours</strong></center><br>"
	+ "Tuesday - Sunday, from 10:00 a.m. to 6:00 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "January 1, May 1, December 25<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free<br>"
	+ "Advance booking is compulsory for groups (Tel: +39 02 2024 0568) - Max 18 persons";
//Brera picture
private String gen40="<center><strong>Opening hours</strong></center><br>"
	+ "Tuesday - Sunday, from 8:30 a.m. to 7:15 p.m. (last admission 6:40 p.m.)<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "Monday, January 1, May 1, December 25<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Adult: 10€<br>"
	+ "Reduced: 7€";
//Brera garden
private String gen41="<center><strong>Opening hours</strong></center><br>"
	+ "<strong>June - September</strong><br>"
	+ "Monday - Friday, from 9:00 a.m. to 12:30 p.m.<br>"
	+ "<strong>October - May</strong><br>"
	+ "Monday - Friday, from 9:00 a.m. to 5:30 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free";
//Branca tower
private String gen42="<center><strong>Opening hours</strong></center><br>"
	+ "<strong>Mid May - mid September<br>"
	+ "Tuesday, from 3:00 p.m. to 7:00 p.m. and from 8:30 p.m. to midnight<br>"
	+ "Wednesday, from 10:30 a.m. to 12:30, from 3:00 p.m. to 7:00 p.m., from 8:30 p.m. to midnight<br>"
	+ "Thursday - Friday, from 3:00 p.m. to 7:00 p.m. and from 8:30 p.m. to midnight<br>"
	+ "Saturday - Sunday, from 10:30 a.m. to 2:00 p.m., from 2:30 p.m. to 7:30 p.m., from 8:30 p.m. to midnight<br>"
	+ "<strong>Mid September - mid May<br>"
	+ "Wednesday, from 10:30 a.m. to 12:30 and from 4:00 p.m. to 6:30 p.m.<br>"
	+ "Saturday, from 10:30 a.m. to 1:00 p.m., from 3:00 p.m. to 6:30 p.m., from 8:30 p.m. to midnight<br>"
	+ "Sunday, from 10:30 a.m. to 2:00 p.m. and from 2:30 p.m. to 7:00 p.m.<br>"
	+ "<br>"
	+ "In case of bad weather Torre Branca will remain closed.<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "5€";
//Bramante
private String gen43="<center><strong>Opening hours</strong></center><br>"
	+ "Tuesday - Sunday, from 8:30 a.m. to 7:00 p.m.<br>"
	+ "Monday, from 9:00 a.m. to 1:00 p.m. and from 2:00 p.m. to 5:30 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Closesd</strong></center><br>"
	+ "December 25, Jenuary 1<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Adult: 10€<br>"
	+ "University students: 10€ (including a free ticket for the Ambrosiana picture gallery)<br>"
	+ "Under 14: free<br>"
	+ "Recuded: 8€<br>"
	+ "Combo-ticket: 20€ (Sacristy + Picture gallery)<br>"
	+ "Reduced combo-tiket: 15€ (under 18, over 65)";
//Sant ambrogio
private String gen44="Not available";
//Sant eustorgio
private String gen45="<center><strong>Opening hours</strong></center><br>"
	+ "Every day, from 10:00 a.m. to 6:00 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "<strong>Museum</strong><br>"
	+ "Adults: 6€<br>"
	+ "Reduced: 3€ (over 60, students)<br>"
	+ "Reduced: 1€ (students until 14 years old)";
//San simpliciano
private String gen46="<center><strong>Opening hours</strong></center><br>"
	+ "Not available<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free";
//San lorenzo
private String gen47="<center><strong>Opening hours</strong></center><br>"
	+ "Monday - Saturday, from 8:30 a.m. to 6:30 p.m.<br>"
	+ "Sunday, from 9:00 a.m. to 7:00 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free";
//San calimero
private String gen48="<center><strong>Opening hours</strong></center><br>"
	+ "Mondey - Sunday, from 8:30 a.m to 7:00 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Free";
//Bagatti
private String gen49="<center><strong>Opening hours</strong></center><br>"
	+ "Tuesday to Sunday, from 1 p.m. to 5:45 p.m.<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "Monday, January 1, January 6, April 25, May 1, June 2, August, November 1, December 7, December 8, December 25, December 26<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Regular: 9€<br>"
	+ "Reduced: 6€ (children 6-14, students with student card, over 65)<br>"
	+ "Free: under 5 years old<br>"
	+ "Wednesday: 6€";
//Archeological
private String gen50="<center><strong>Opening hours</strong></center><br>"
	+ "Tuesday to Sunday, from 9:00am to 5:30pm (last admission 4:30 p.m.)<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Adult: 5€<br>"
	+ "Reduced: 3€<br>"
	+ "3-days ticket: 12€<br>"
	+ "Admission free everyday in the last opening hour and every Tuesday from 2:00pm onward.";
//Ambrosiana
private String gen51="<center><strong>Opening hours</strong></center><br>"
	+ "Tuesday to Sunday, from 10:00 a.m to 6:00 p.m<br>"
	+ "During EXPO, open also on Mondey (from April 20 to October 26) and Tuesday evening until 10:00 p.m.)<br>"
	+ "<br>"
	+ "<center><strong>Closed</strong></center><br>"
	+ "Monday (see above for special opening during EXPO), 25th December, 1st May, Easter<br>"
	+ "<br>"
	+ "<center><strong>Ticket</strong></center><br>"
	+ "Adult: 15€<br>"
	+ "Reduced: 10€<br>"
	+ "Family discount: free until 14 years old, if accompanied by a paying adult<br>"
	+ "<br>"
	+ "<strong>Other</strong><br>"
	+ "Possibility to buy a combined ticket for 20€ (instead of 25€), visit include also the Bramante's Sacristy";





public static final int google_maps_api_key1=0x7f040001;

   public double l;
   public double lo;
//   private ProgressBar progbar;
   
   private ImageButton scatto;
   public static Vector<Attrazioni> serie;
   private Attrazioni novenove;
   private Attrazioni mia;
   private Attrazioni prima;
   private Attrazioni seconda;
   private Attrazioni terza;
   private Attrazioni quarta;
   private Attrazioni quinta;
   private Attrazioni sesta;
   private Attrazioni settima;
   private Attrazioni ottava;
   private Attrazioni nona;
   private Attrazioni santaMaria;
   private Attrazioni sforza;
   private Attrazioni duomo;
   private Attrazioni mezzanotte;
   private Attrazioni scala;
   private Attrazioni sanMaurizio;
   private Attrazioni ambrogio;
   private Attrazioni andrea;
   private Attrazioni pisogne;
   private Attrazioni Teatro_della_scala;
   private Attrazioni Triennale;
   private Attrazioni San_siro;
   private Attrazioni Simplon_gate;
   private Attrazioni Sforza_castle;
   private Attrazioni Science_museum;
   private Attrazioni San_nazaro;
   private Attrazioni San_vittore;
   private Attrazioni San_cristoforo;
   private Attrazioni San_carlo;
   private Attrazioni San_bernardino;
   private Attrazioni San_babila;
   private Attrazioni San_marco;
   private Attrazioni Royal_villa;
   private Attrazioni Royal_palace;
   private Attrazioni Porta_venezia;
   private Attrazioni Poldi_museum;
   private Attrazioni Parco_basiliche;
   private Attrazioni Palazzo_mezzanotte;
   private Attrazioni Palazzo_marino;
   private Attrazioni Museum_20;
   private Attrazioni Museum_risorgimento;
   private Attrazioni Museum_musical_instrument;
   private Attrazioni Museum_ancient_art;
   private Attrazioni Monumental_cemetry;
   private Attrazioni Modern_art;
   private Attrazioni Planetarium;
   private Attrazioni Duomo;
   private Attrazioni Indro;
   private Attrazioni Galleria;
   private Attrazioni Diocesean;
   private Attrazioni Columns;
   private Attrazioni Natural_history;
   private Attrazioni Aquarium;
   private Attrazioni Santa_maria;
   private Attrazioni San_maurizio;
   private Attrazioni Santa_maria2;
   private Attrazioni Manzoni;
   private Attrazioni Boschi;
   private Attrazioni Brera_picture;
   private Attrazioni Brera_garden;
   private Attrazioni Branca;
   private Attrazioni Bramante;
   private Attrazioni Sant_ambrogio;
   private Attrazioni Sant_eustorgio;
   private Attrazioni San_simpliciano;
   private Attrazioni San_lorenzo;
   private Attrazioni San_calimero;
   private Attrazioni Bagatti;
   private Attrazioni Archeological;
   private Attrazioni Ambrosiana;

	private Attrazioni tester;

   
   private BearingFrameLayout bearingFrameLayout;
   
   //quattro variabili che richiamano le classi predefinite per il gps:
   private LocationManager locationManager;

 
   public Vector<Attrazioni> serie()
   {
	   tester = new Attrazioni(0,0,"testnome","terstgen","testdesc","testtec",12);

	   mia = new Attrazioni(45.942907, 10.27775713,"Teatro della scala", gen12,desc12,tec12, 0.009999);
	   novenove = new Attrazioni(45.808173, 10.105013,"Teatro della scala", gen12,desc12,tec12, 0.009999);
	   Teatro_della_scala = new Attrazioni (45.467409, 9.189519,"Teatro della scala", gen1,desc1,tec1, 0.003494);
	   Triennale = new Attrazioni (45.472248, 9.173609, "Triennale", gen2,desc2,tec2, 0.007602);
	   San_siro = new Attrazioni (45.478143, 9.123852, "San Siro", gen3,desc3,tec3, 0.006444);
	   Simplon_gate = new Attrazioni (45.475746,	9.172376, "Simplon Gate", gen4,desc4,tec4, 0.003850);
	   Sforza_castle = new Attrazioni (45.470469, 9.179300, "Sforza Castle", gen5,desc5,tec5, 0.005417);
	   Science_museum = new Attrazioni (45.462912, 9.170642, "Science and Technology Museum Leonardo da Vinci", gen6,desc6,tec6,	0.005230);
	   San_nazaro = new Attrazioni (45.458510, 9.192418, "San Nazaro Maggiore", gen7,desc7,tec7, 0.004059);
	   San_vittore = new Attrazioni (45.463131, 9.170797, "San Vittore al Corpo", gen8,desc8,tec8, 0.003905);
	   San_cristoforo = new Attrazioni (45.447734, 9.154687, "San Cristoforo sul Naviglio", gen9,desc9,tec9, 0.006174);
	   San_carlo = new Attrazioni (45.466807, 9.195996, "San Carlo al Corso", gen10,desc10,tec10, 0.004457);
	   San_bernardino = new Attrazioni (45.462525, 9.195468, "San Bernardino delle ossa", gen11,desc11,tec11, 0.003450);
	   San_babila = new Attrazioni (45.466966, 9.198410, "San Babila", gen12,desc12,tec12, 0.003427);
	   San_marco = new Attrazioni (45.473244, 9.188487, "Basilica di San Marco", gen13,desc13,tec13, 0.002879);
	   Royal_villa = new Attrazioni (45.472481, 9.199725, "Royal villa and gardens", gen14,desc14,tec14, 0.006015);
	   Royal_palace = new Attrazioni (45.463130, 9.191181, "Royal palace", gen15,desc15,tec15, 0.003532);
	   Porta_venezia = new Attrazioni (45.474337, 9.205151, "Porta Venezia", gen16,desc16,tec16, 0.002999);
	   Poldi_museum = new Attrazioni (45.468654, 9.191456, "Poldi Pezzoli Museum", gen17,desc17,tec17, 0.003616);
	   Parco_basiliche = new Attrazioni (45.455805, 9.182239, "Parco delle Basiliche", gen18,desc18,tec18, 0.003414);
	   Palazzo_mezzanotte = new Attrazioni (45.465094, 9.183232, "Palazzo Mezzanotte", gen19,desc19,tec19, 0.003139);
	   Palazzo_marino = new Attrazioni (45.466810, 9.190421, "Palazzo Marino", gen20,desc20,tec20, 0.003047);
	   Museum_20 = new Attrazioni (45.463395, 9.190261, "Museum of Twentieth Century", gen21,desc21,tec21, 0.004647);
	   Museum_risorgimento = new Attrazioni (45.472085, 9.189786, "Museo del Risorgimento", gen22,desc22,tec22, 0.004176);
	   Museum_musical_instrument = new Attrazioni (45.470446, 9.179321, "Museum of Musical Instruments", gen23,desc23,tec23, 0.006725);
	   Museum_ancient_art = new Attrazioni (45.468466, 9.180636, "Museum of Ancient Arts", gen24,desc24,tec24, 0.004404);
	   Monumental_cemetry = new Attrazioni (45.487306, 9.177716, "Monumental Cemetry", gen25,desc25,tec25, 0.008554);
	   Modern_art = new Attrazioni (45.472477, 9.199783, "Modern Art Gallery", gen26,desc26,tec26, 0.005465);
	   Planetarium = new Attrazioni (45.473728, 9.203540, "Planetarium", gen27,desc27,tec27, 0.005715);
	   Duomo = new Attrazioni (45.464120, 9.191895, "Milan Cathedral (Duomo)", gen28,desc28,tec28, 0.011302);
	   Indro = new Attrazioni (45.474000, 9.199957, "Indro Montanelli Gardens", gen29,desc29,tec29, 0.010735);
	   Galleria = new Attrazioni (45.465642, 9.189822, "Galleria Vittorio Emanuele II", gen30,desc30,tec30, 0.003375);
	   Diocesean = new Attrazioni (45.454555, 9.181400, "Diocesean Museum", gen31,desc31,tec31, 0.004050);
	   Columns = new Attrazioni (45.458214, 9.181038, "Columns of San Lorenzo", gen32,desc32,tec32, 0.004059);
	   Natural_history = new Attrazioni (45.472772, 9.202394, "Natural History Museum", gen33,desc33,tec33, 0.007400);
	   Aquarium = new Attrazioni (45.474020, 9.180716, "Aquarium", gen34,desc34,tec34, 0.004736);
	   Santa_maria = new Attrazioni (45.470441, 9.185495, "Santa Maria del Carmine", gen35,desc35,tec35, 0.005301);
	   San_maurizio = new Attrazioni (45.465590, 9.178935, "San Maurizio al Monastero Maggiore", gen36,desc36,tec36, 0.003378);
	   Santa_maria2 = new Attrazioni (45.465954, 9.170951, "Santa Maria delle Grazie", gen37,desc37,tec37, 0.004922);
	   Manzoni = new Attrazioni (45.467830, 9.192171, "Manzoni House", gen38,desc38,tec38, 0.003099);
	   Boschi = new Attrazioni (45.478996, 9.211778, "House Museum Boschi di Stefano", gen39,desc39,tec39, 0.004070);
	   Brera_picture = new Attrazioni (45.471977, 9.188104, "Brera Picture Gallery", gen40,desc40,tec40, 0.004950);
	   Brera_garden = new Attrazioni (45.470647, 9.189237, "Brera Botanical Garden", gen41,desc41,tec41, 0.004316);
	   Branca = new Attrazioni (45.473275, 9.173025, "Branca Tower", gen42,desc42,tec42, 0.004047);
	   Bramante = new Attrazioni (45.466074, 9.171908, "Bramante's Sacristy", gen43,desc43,tec43, 0.005663);
	   Sant_ambrogio = new Attrazioni (45.462322, 9.175633, "Basilica of Sant'Ambrogio", gen44,desc44,tec44, 0.009415);
	   Sant_eustorgio = new Attrazioni (45.453960, 9.181054, "Basilica of Sant'Eustorgio", gen45,desc45,tec45, 0.007341);
	   San_simpliciano = new Attrazioni (45.473861, 9.184495, "Basilica of San Simpliciano", gen46,desc46,tec46, 0.006541);
	   San_lorenzo = new Attrazioni (45.458213, 9.182088, "Basilica of San Lorenzo Maggiore", gen47,desc47,tec47, 0.008162);
	   San_calimero = new Attrazioni (45.456572, 9.192990, "Basilica of San Calimero", gen48,desc48,tec48, 0.005833);
	   Bagatti = new Attrazioni (45.469459, 9.195073, "Bagatti Valsecchi Museum", gen49,desc49,tec49, 0.002575);
	   Archeological = new Attrazioni (45.465631, 9.178636, "Archaeological Museum", gen50,desc50,tec50, 0.002092);
	   Ambrosiana = new Attrazioni (45.463468, 9.185820, "Ambrosiana Library & Picture Gallery", gen51,desc51,tec51, 0.004137);

	   Attrazioni casa1 = new Attrazioni (45.94294244, 10.27767201, "casa1","a","a","a",0.1);
	   Attrazioni casa2 = new Attrazioni (45.94304244, 10.27767201, "casa2","a","a","a",0.1);
	   Attrazioni casa3 = new Attrazioni (45.94314244, 10.27767201, "casa3","a","a","a",0.1);
	   Attrazioni casa4 = new Attrazioni (45.94324244, 10.27767201, "casa4","a","a","a",0.1);
	   Attrazioni casa5 = new Attrazioni (45.94334244, 10.27767201, "casa5","a","a","a",0.1);
	   Attrazioni casa6 = new Attrazioni (45.94344244, 10.27767201, "casa6","a","a","a",0.1);
	   Attrazioni casa7 = new Attrazioni (45.94354244, 10.27767201, "casa7","a","a","a",0.1);
	   Attrazioni casa8 = new Attrazioni (45.95364244, 10.27767201, "casa8","a","a","a",0.1);
	   Attrazioni casa9 = new Attrazioni (45.94374244, 10.27767201, "casa9","a","a","a",0.1);
	   Attrazioni casa10 = new Attrazioni (45.94384244, 10.27767201, "casa10","a","a","a",0.1);
	   Attrazioni casa11 = new Attrazioni (45.94394244, 10.27767201, "casa11","a","a","a",0.1);


	   Attrazioni zero1 = new Attrazioni (45.567796, 10.241108, "vicina","a","a","a",0.1);
	   Attrazioni zero2 = new Attrazioni (   45.577796, 10.251108, "media","a","a","a",0.1);
	   Attrazioni zero3 = new Attrazioni (   45.587796, 10.261108, "distante","a","a","a",0.2);
	   Attrazioni zero4 = new Attrazioni (45.567797, 10.241118, "vicina","a","a","a",0.2);
	   Attrazioni zero5 = new Attrazioni (   45.577796, 10.250108, "media","a","a","a",0.2);
	   Attrazioni zero6 = new Attrazioni (   45.587796, 10.262108, "distante","a","a","a",0.2);
	   Attrazioni zero7 = new Attrazioni (45.567796, 10.241178, "vicina","a","a","a",0.2);

	   Attrazioni marina1 = new Attrazioni (45.806869, 10.068980,"marina1","a","a","a",0.2);
	   Attrazioni marina2 = new Attrazioni (45.804579, 10.068880,"marina2","a","a","a",0.2);
	   Attrazioni marina3 = new Attrazioni (45.806289, 10.068980,"marina3","a","a","a",0.2);
	   Attrazioni marina4 = new Attrazioni (45.806859, 10.067680,"marina4","a","a","a",0.2);
	   Attrazioni marina5 = new Attrazioni (45.806519, 10.068180,"marina5","a","a","a",0.2);
	   Attrazioni marina6 = new Attrazioni (45.806829, 10.066680,"marina6","a","a","a",0.2);
	   Attrazioni marina7 = new Attrazioni (45.806839, 10.065680,"marina7","a","a","a",0.2);

	   serie=new Vector<Attrazioni>();
	   serie.add(Teatro_della_scala);
	   serie.add(Triennale);
	   serie.add(San_siro);
	   serie.add(Simplon_gate);
	   serie.add(Sforza_castle);
	   serie.add(Science_museum);
	   serie.add(San_nazaro);
	   serie.add(San_vittore);
	   serie.add(San_cristoforo);
	   serie.add(San_carlo);
	   serie.add(San_bernardino);
	   serie.add(San_babila);
	   serie.add(San_marco);
	   serie.add(Royal_villa);
	   serie.add(Royal_palace);
	   serie.add(Porta_venezia);
	   serie.add(Poldi_museum);
	   serie.add(Parco_basiliche);
	   serie.add(Palazzo_mezzanotte);
	   serie.add(Palazzo_marino);
	   serie.add(Museum_20);
	   serie.add(Museum_risorgimento);
	   serie.add(Museum_musical_instrument);
	   serie.add(Museum_ancient_art);
	   serie.add(Monumental_cemetry);
	   serie.add(Modern_art);
	   serie.add(Planetarium);
	   serie.add(Duomo);
	   serie.add(Indro);
	   serie.add(Galleria);
	   serie.add(Diocesean);
	   serie.add(Columns);
	   serie.add(Natural_history);
	   serie.add(Aquarium);
	   serie.add(Santa_maria);
	   serie.add(San_maurizio);
	   serie.add(Santa_maria2);
	   serie.add(Manzoni);
	   serie.add(Boschi);
	   serie.add(Brera_picture);
	   serie.add(Brera_garden);
	   serie.add(Branca);
	   serie.add(Bramante);
	   serie.add(Sant_ambrogio);
	   serie.add(Sant_eustorgio);
	   serie.add(San_simpliciano);
	   serie.add(San_lorenzo);
	   serie.add(San_calimero);
	   serie.add(Bagatti);
	   serie.add(Archeological);
	   serie.add(Ambrosiana);
	   serie.add(mia);
	   serie.add(zero1);
	   serie.add(zero2);
	   serie.add(zero3);
	   serie.add(zero4);
	   serie.add(zero5);
	   serie.add(zero6);
	   serie.add(zero7);
	   serie.add(marina1);
	   serie.add(marina2);
	   serie.add(marina3);
	   serie.add(marina4);
	   serie.add(marina5);
	   serie.add(marina6);
	   serie.add(marina7);
	   serie.add(casa9);
	   serie.add(casa7);
	   serie.add(casa2);
	   serie.add(casa4);
	   serie.add(casa6);
	   serie.add(casa5);
	   serie.add(casa3);
	   serie.add(casa8);
	   serie.add(casa1);
	   serie.add(casa10);
	   serie.add(casa11);
	   serie.add(tester);
//
	   return serie;
   }

   @Override
   public void onCreate(Bundle savedInstanceState)
   //oncreate c'� in tutti i programmi
   {

      super.onCreate(savedInstanceState);
	   requestWindowFeature(Window.FEATURE_NO_TITLE);
      setContentView(R.layout.loading);

      ottenuteserie = new Vector<Attrazioni>();
	   cambio = 0;

//      progbar = (ProgressBar) findViewById(R.id.progressBar1);


      scatto = (ImageButton) findViewById(R.id.imageView2);
     // scatto.setOnClickListener(scattoSoloPerTest);
     // low=(ImageButton) findViewById(R.id.sotto);
      //ADD CODE HERE
	   tester = new Attrazioni(0,0,"testnome","terstgen","testdesc","testtec",12);

      mia = new Attrazioni(45.942907, 10.27775713,"Teatro della scala", gen12,desc12,tec12, 0.009999);
      novenove = new Attrazioni(45.808173, 10.105013,"Teatro della scala", gen12,desc12,tec12, 0.009999);
      Teatro_della_scala = new Attrazioni (45.467409, 9.189519,"Teatro della scala", gen1,desc1,tec1, 0.003494);
      Triennale = new Attrazioni (45.472248, 9.173609, "Triennale", gen2,desc2,tec2, 0.007602);
      San_siro = new Attrazioni (45.478143, 9.123852, "San Siro", gen3,desc3,tec3, 0.006444);
      Simplon_gate = new Attrazioni (45.475746,	9.172376, "Simplon Gate", gen4,desc4,tec4, 0.003850);
      Sforza_castle = new Attrazioni (45.470469, 9.179300, "Sforza Castle", gen5,desc5,tec5, 0.005417);
      Science_museum = new Attrazioni (45.462912, 9.170642, "Science and Technology Museum Leonardo da Vinci", gen6,desc6,tec6,	0.005230);
      San_nazaro = new Attrazioni (45.458510, 9.192418, "San Nazaro Maggiore", gen7,desc7,tec7, 0.004059);
      San_vittore = new Attrazioni (45.463131, 9.170797, "San Vittore al Corpo", gen8,desc8,tec8, 0.003905);
      San_cristoforo = new Attrazioni (45.447734, 9.154687, "San Cristoforo sul Naviglio", gen9,desc9,tec9, 0.006174);
      San_carlo = new Attrazioni (45.466807, 9.195996, "San Carlo al Corso", gen10,desc10,tec10, 0.004457);
      San_bernardino = new Attrazioni (45.462525, 9.195468, "San Bernardino delle ossa", gen11,desc11,tec11, 0.003450);
      San_babila = new Attrazioni (45.466966, 9.198410, "San Babila", gen12,desc12,tec12, 0.003427);
      San_marco = new Attrazioni (45.473244, 9.188487, "Basilica di San Marco", gen13,desc13,tec13, 0.002879);
      Royal_villa = new Attrazioni (45.472481, 9.199725, "Royal villa and gardens", gen14,desc14,tec14, 0.006015);
      Royal_palace = new Attrazioni (45.463130, 9.191181, "Royal palace", gen15,desc15,tec15, 0.003532);
      Porta_venezia = new Attrazioni (45.474337, 9.205151, "Porta Venezia", gen16,desc16,tec16, 0.002999);
      Poldi_museum = new Attrazioni (45.468654, 9.191456, "Poldi Pezzoli Museum", gen17,desc17,tec17, 0.003616);
      Parco_basiliche = new Attrazioni (45.455805, 9.182239, "Parco delle Basiliche", gen18,desc18,tec18, 0.003414);
      Palazzo_mezzanotte = new Attrazioni (45.465094, 9.183232, "Palazzo Mezzanotte", gen19,desc19,tec19, 0.003139);
      Palazzo_marino = new Attrazioni (45.466810, 9.190421, "Palazzo Marino", gen20,desc20,tec20, 0.003047);
      Museum_20 = new Attrazioni (45.463395, 9.190261, "Museum of Twentieth Century", gen21,desc21,tec21, 0.004647);
      Museum_risorgimento = new Attrazioni (45.472085, 9.189786, "Museo del Risorgimento", gen22,desc22,tec22, 0.004176);
      Museum_musical_instrument = new Attrazioni (45.470446, 9.179321, "Museum of Musical Instruments", gen23,desc23,tec23, 0.006725);
      Museum_ancient_art = new Attrazioni (45.468466, 9.180636, "Museum of Ancient Arts", gen24,desc24,tec24, 0.004404);
      Monumental_cemetry = new Attrazioni (45.487306, 9.177716, "Monumental Cemetry", gen25,desc25,tec25, 0.008554);
      Modern_art = new Attrazioni (45.472477, 9.199783, "Modern Art Gallery", gen26,desc26,tec26, 0.005465);
      Planetarium = new Attrazioni (45.473728, 9.203540, "Planetarium", gen27,desc27,tec27, 0.005715);
      Duomo = new Attrazioni (45.464120, 9.191895, "Milan Cathedral (Duomo)", gen28,desc28,tec28, 0.011302);
      Indro = new Attrazioni (45.474000, 9.199957, "Indro Montanelli Gardens", gen29,desc29,tec29, 0.010735);
      Galleria = new Attrazioni (45.465642, 9.189822, "Galleria Vittorio Emanuele II", gen30,desc30,tec30, 0.003375);
      Diocesean = new Attrazioni (45.454555, 9.181400, "Diocesean Museum", gen31,desc31,tec31, 0.004050);
      Columns = new Attrazioni (45.458214, 9.181038, "Columns of San Lorenzo", gen32,desc32,tec32, 0.004059);
      Natural_history = new Attrazioni (45.472772, 9.202394, "Natural History Museum", gen33,desc33,tec33, 0.007400);
      Aquarium = new Attrazioni (45.474020, 9.180716, "Aquarium", gen34,desc34,tec34, 0.004736);
      Santa_maria = new Attrazioni (45.470441, 9.185495, "Santa Maria del Carmine", gen35,desc35,tec35, 0.005301);
      San_maurizio = new Attrazioni (45.465590, 9.178935, "San Maurizio al Monastero Maggiore", gen36,desc36,tec36, 0.003378);
      Santa_maria2 = new Attrazioni (45.465954, 9.170951, "Santa Maria delle Grazie", gen37,desc37,tec37, 0.004922);
      Manzoni = new Attrazioni (45.467830, 9.192171, "Manzoni House", gen38,desc38,tec38, 0.003099);
      Boschi = new Attrazioni (45.478996, 9.211778, "House Museum Boschi di Stefano", gen39,desc39,tec39, 0.004070);
      Brera_picture = new Attrazioni (45.471977, 9.188104, "Brera Picture Gallery", gen40,desc40,tec40, 0.004950);
      Brera_garden = new Attrazioni (45.470647, 9.189237, "Brera Botanical Garden", gen41,desc41,tec41, 0.004316);
      Branca = new Attrazioni (45.473275, 9.173025, "Branca Tower", gen42,desc42,tec42, 0.004047);
      Bramante = new Attrazioni (45.466074, 9.171908, "Bramante's Sacristy", gen43,desc43,tec43, 0.005663);
      Sant_ambrogio = new Attrazioni (45.462322, 9.175633, "Basilica of Sant'Ambrogio", gen44,desc44,tec44, 0.009415);
      Sant_eustorgio = new Attrazioni (45.453960, 9.181054, "Basilica of Sant'Eustorgio", gen45,desc45,tec45, 0.007341);
      San_simpliciano = new Attrazioni (45.473861, 9.184495, "Basilica of San Simpliciano", gen46,desc46,tec46, 0.006541);
      San_lorenzo = new Attrazioni (45.458213, 9.182088, "Basilica of San Lorenzo Maggiore", gen47,desc47,tec47, 0.008162);
      San_calimero = new Attrazioni (45.456572, 9.192990, "Basilica of San Calimero", gen48,desc48,tec48, 0.005833);
      Bagatti = new Attrazioni (45.469459, 9.195073, "Bagatti Valsecchi Museum", gen49,desc49,tec49, 0.002575);
      Archeological = new Attrazioni (45.465631, 9.178636, "Archaeological Museum", gen50,desc50,tec50, 0.002092);
      Ambrosiana = new Attrazioni (45.463468, 9.185820, "Ambrosiana Library & Picture Gallery", gen51,desc51,tec51, 0.004137);
      
      Attrazioni casa1 = new Attrazioni (45.94294244, 10.27767201, "casa1","a","a","a",0.1);
      Attrazioni casa2 = new Attrazioni (45.94304244, 10.27767201, "casa2","a","a","a",0.1);
      Attrazioni casa3 = new Attrazioni (45.94314244, 10.27767201, "casa3","a","a","a",0.1);
      Attrazioni casa4 = new Attrazioni (45.94324244, 10.27767201, "casa4","a","a","a",0.1);
      Attrazioni casa5 = new Attrazioni (45.94334244, 10.27767201, "casa5","a","a","a",0.1);
      Attrazioni casa6 = new Attrazioni (45.94344244, 10.27767201, "casa6","a","a","a",0.1);
      Attrazioni casa7 = new Attrazioni (45.94354244, 10.27767201, "casa7","a","a","a",0.1);
      Attrazioni casa8 = new Attrazioni (45.95364244, 10.27767201, "casa8","a","a","a",0.1);
      Attrazioni casa9 = new Attrazioni (45.94374244, 10.27767201, "casa9","a","a","a",0.1);
      Attrazioni casa10 = new Attrazioni (45.94384244, 10.27767201, "casa10","a","a","a",0.1);
      Attrazioni casa11 = new Attrazioni (45.94394244, 10.27767201, "casa11","a","a","a",0.1);
       
      
      Attrazioni zero1 = new Attrazioni (45.567796, 10.241108, "vicina","a","a","a",0.1);
      Attrazioni zero2 = new Attrazioni (   45.577796, 10.251108, "media","a","a","a",0.1);
      Attrazioni zero3 = new Attrazioni (   45.587796, 10.261108, "distante","a","a","a",0.2);
      Attrazioni zero4 = new Attrazioni (45.567797, 10.241118, "vicina","a","a","a",0.2);
      Attrazioni zero5 = new Attrazioni (   45.577796, 10.250108, "media","a","a","a",0.2);
      Attrazioni zero6 = new Attrazioni (   45.587796, 10.262108, "distante","a","a","a",0.2);
      Attrazioni zero7 = new Attrazioni (45.567796, 10.241178, "vicina","a","a","a",0.2);
      
      Attrazioni marina1 = new Attrazioni (45.806869, 10.068980,"marina1","a","a","a",0.2);
      Attrazioni marina2 = new Attrazioni (45.804579, 10.068880,"marina2","a","a","a",0.2); 
      Attrazioni marina3 = new Attrazioni (45.806289, 10.068980,"marina3","a","a","a",0.2);
      Attrazioni marina4 = new Attrazioni (45.806859, 10.067680,"marina4","a","a","a",0.2);
      Attrazioni marina5 = new Attrazioni (45.806519, 10.068180,"marina5","a","a","a",0.2);
      Attrazioni marina6 = new Attrazioni (45.806829, 10.066680,"marina6","a","a","a",0.2);
      Attrazioni marina7 = new Attrazioni (45.806839, 10.065680,"marina7","a","a","a",0.2);
      
      serie=new Vector<Attrazioni>();
      serie.add(Teatro_della_scala);
      serie.add(Triennale);
      serie.add(San_siro);
      serie.add(Simplon_gate);
      serie.add(Sforza_castle);
      serie.add(Science_museum);
      serie.add(San_nazaro);
      serie.add(San_vittore);
      serie.add(San_cristoforo);
      serie.add(San_carlo);
      serie.add(San_bernardino);
      serie.add(San_babila);
      serie.add(San_marco);
      serie.add(Royal_villa);
      serie.add(Royal_palace);
      serie.add(Porta_venezia);
      serie.add(Poldi_museum);
      serie.add(Parco_basiliche);
      serie.add(Palazzo_mezzanotte);
      serie.add(Palazzo_marino);
      serie.add(Museum_20);
      serie.add(Museum_risorgimento);
      serie.add(Museum_musical_instrument);
      serie.add(Museum_ancient_art);
      serie.add(Monumental_cemetry);
      serie.add(Modern_art);
      serie.add(Planetarium);
      serie.add(Duomo);
      serie.add(Indro);
      serie.add(Galleria);
      serie.add(Diocesean);
      serie.add(Columns);
      serie.add(Natural_history);
      serie.add(Aquarium);
      serie.add(Santa_maria);
      serie.add(San_maurizio);
      serie.add(Santa_maria2);
      serie.add(Manzoni);
      serie.add(Boschi);
      serie.add(Brera_picture);
      serie.add(Brera_garden);
      serie.add(Branca);
      serie.add(Bramante);
      serie.add(Sant_ambrogio);
      serie.add(Sant_eustorgio);
      serie.add(San_simpliciano);
      serie.add(San_lorenzo);
      serie.add(San_calimero);
      serie.add(Bagatti);
      serie.add(Archeological);
      serie.add(Ambrosiana);
      /*serie.add(mia);
      serie.add(zero1);
      serie.add(zero2);
      serie.add(zero3);
      serie.add(zero4);
      serie.add(zero5);
      serie.add(zero6);
      serie.add(zero7);
      serie.add(marina1);
      serie.add(marina2);
      serie.add(marina3);
      serie.add(marina4);
      serie.add(marina5);
      serie.add(marina6);
      serie.add(marina7);
      serie.add(casa9);
      serie.add(casa7);
      serie.add(casa2);
      serie.add(casa4);
      serie.add(casa6);
      serie.add(casa5);
      serie.add(casa3);
      serie.add(casa8);
      serie.add(casa1);
      serie.add(casa10);
      serie.add(casa11);*/
	   serie.add(tester);
//     
      
   // create new MapView using your Google Maps API key
     // bearingFrameLayout = new BearingFrameLayout(this,
       //  getResources().getString(google_maps_api_key1));
     

   } 
   
 
  // @Override
   public void onStart() 
   {
      super.onStart(); 
      
     // altre parte presa dall'esempio: per ora non ci serve
      Criteria criteria = new Criteria();
      criteria.setAccuracy(Criteria.ACCURACY_FINE);
      criteria.setBearingRequired(true); 
      criteria.setCostAllowed(true); 
      criteria.setPowerRequirement(Criteria.POWER_LOW);
      criteria.setAltitudeRequired(false); 

//le righe seguenti sono sempre uguali per ogni programma con gps    
      locationManager = 
         (LocationManager) getSystemService(LOCATION_SERVICE);
      
      locationManager.addGpsStatusListener(gpsStatusListener);
     
            String provider = locationManager.getBestProvider(criteria, true);


      locationManager.requestLocationUpdates(provider, 0, 0,
          locationListener);


     // bearingFrameLayout.invalidate();
   } 
   

   
   private OnClickListener takePictureButtonListener =
		      new OnClickListener()
		      {
		         // launch image choosing activity
		         @Override
		         public void onClick(View v)
		         {
		            // create new Intent to launch the Slideshowplayer Activity
		            Intent takePicture =
		            		new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		                   
		            String value="pathname da definire";
					takePicture.putExtra(MediaStore.EXTRA_OUTPUT, value);
		          
		            		
		            startActivityForResult(takePicture, TAKE_PICTURE_ID); 
		         } // end method onClick
		      };
		      
   private OnClickListener scattoSoloPerTest = new OnClickListener()
   {
	   
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int niente =1;
		int ripetizione=0;
		cont =0;

		ottenuteserie = new Vector<Attrazioni>();

		/*AlertDialog.Builder dialogBuilder3 =
				new AlertDialog.Builder(RouteTracker.this);
		dialogBuilder3.setTitle("sono dentro sst");
		dialogBuilder3.setPositiveButton(
				R.string.button_ok, null);
		dialogBuilder3.show();*/

        for(int i=0;i<serie.size();i++)
        {
       	 Attrazioni questa = (Attrazioni) serie.get(i);
       	 double cl =questa.getLat();
       	 double clo =questa.getLon();
       	 double distanza = questa.getDistanza();
    	 double distLa= Math.abs(l - cl);
       	 double distLo= Math.abs(lo - clo);

       	    if((distLa<distanza)&(distLo<distanza)){
				//scrivi(questa);
       	    	niente=0;
       	    	cont++;
       	    	Double tot = distLa + distLo;
       	    	questa.setScarto(tot);

              	ottenuteserie.add(questa);
                Collections.sort(ottenuteserie, new Comparator<Attrazioni>() {
					public int compare(Attrazioni a1, Attrazioni a2) {
						Double d1 = Double.valueOf(a1.getScarto());
						Double d2 = Double.valueOf(a2.getScarto());
						return d1.compareTo(d2);
					}
				});
       	    }
        }
        
 
        
	    	Intent intent = new Intent(getApplicationContext(), Vids.class);
	    	startActivity(intent);

        
        if ((niente==1)&(ripetizione==0)){
   	    	
   	    	ripetizione=1;

   	    }
		
		
	}
   };


   public void updateLocation(Location location)
   {
		   // prende nuova posizione a ogni spostamento(updateLocation
	   // � richiamato a ogni spostamento
         l = location.getLatitude();
         lo = location.getLongitude() ;
         if(l!=0&lo!=0&cambio==0)
         {
			 cambio=1;

			 scatto = (ImageButton) findViewById(R.id.imageView2);
		//	 scatto.setBackgroundResource(R.drawable.findex);
             scatto.setOnClickListener(scattoSoloPerTest);
			 scatto.performClick();
			 scatto.setBackgroundResource(R.drawable.founded);

			  ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);
			 pb.setVisibility(View.INVISIBLE);
		  }
       // bearingFrameLayout.invalidate();
	   //scatto.performClick();
    }
   
 

  //ora 5 metodi standard  che non ci servono quindi lascio vuoti
   private final LocationListener locationListener =
      new LocationListener()
   {
    
	   public void onLocationChanged(Location location)
      {
            updateLocation(location); //richiama metodo a ogni cambiamento
      } 

      public void onProviderDisabled(String provider)
      {
      } 

      public void onProviderEnabled(String provider)
      {
      } 

      public void onStatusChanged(String provider,
         int status, Bundle extras)
      {
      } 
   }; 
   
   GpsStatus.Listener gpsStatusListener = new GpsStatus.Listener()
   {
      public void onGpsStatusChanged(int event) 
      {
         if (event == GpsStatus.GPS_EVENT_FIRST_FIX)
         {
           // gpsFix = true;
           // Toast results = Toast.makeText(RouteTracker.this, 
             //  "signa", 
              // Toast.LENGTH_SHORT);
            
            // center the Toast in the screen
          //  results.setGravity(Gravity.CENTER, 
            //   results.getXOffset() / 2, results.getYOffset() / 2);     
            //results.show(); // display the results
         } // end if
      } // end method on GpsStatusChanged
   }; // end anonymous inner class

  
   
   protected boolean isRouteDisplayed() 
   {
      return false; 
   } 
   
   protected void onDestroy() {
	    super.onDestroy();
	   
	  }
   
   public void onAccuracyChanged(Sensor sensor, int accuracy) {  }
   
   protected void onResume() {
	    super.onResume();
	  }
	 
	  protected void onPause() {
	    super.onPause();
	   }


	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
	}


     
} 


