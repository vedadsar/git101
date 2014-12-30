import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Klasa koja iscrtava krug, u zavisnosti od booleana isFilled iscratava
 * popunjen ili prazan krug. Klasa ima vise parametara, svaki cu objasniti u komentarima ispod.
 * 
 * @author vedadzornic
 *
 */
public class Circle {

	private int x;		//x koordinate kruga ( x koordinate pocinje sa gornje lijeve strane (fillOval).
	private int y;		// Identicno kao za x koordinatu.
	private int radius; // Radijus kruga
	private boolean isFilled;	//boolean vrijednost da znamo da li cemo iscrtati popunjen ili prazan krug.
	private Color color;		// Boja kruga
	private int speedX;			// Brzina kruga po X osi
	private int speedY;			// Brzina kruga po Y osi
	private Dimension windowDimension;	// Dimenzije prozora unutar kojeg se krug krece
	private int centerX ;		//X koordinata centra kruga	
	private int centerY ;		//Y koordinata centra kruga	
	private int halfRadius;		//Poluprecnik kruga.

	/**
	 * Konstruktor koji prima vise varijabli ( gore navedena svaka ) i definise krug
	 * u odnosu na varijable.
	 *
	 */
	public Circle(int x, int y, int radius, boolean isFilled, Color color,
			int speedX, int speedY, Dimension windowDimension) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.isFilled = isFilled;
		this.color = color;
		this.speedX = speedX;			
		this.speedY = speedY;
		this.windowDimension = windowDimension;
		this.centerX = x + radius / 2;	// Koordinate X u centru
		this.centerY = y + radius / 2;	// Koordinate Y u centru kruga
		this.halfRadius = radius / 2;	// polovina radijusa

	}

	/**
	 * Funkcija koja iscrtava nas krug.
	 * @param g
	 */
	public void draw(Graphics g) {

		move();			// Funkcija move.

		g.setColor(color);	// odabir boje za crtanje, iz konstruktora.
		if (isFilled == true) {	// Provjeravamo da li korisnik zeli popunjen krug ili samo okvir.
			g.fillOval(x, y, radius, radius); // Iscrtavanje popunjenog kruga
		} else {
			g.drawOval(x, y, radius, radius); // Iscrtavanje okvira kruga
		}
	}
	/**
	 * Funkcija move provjerava da li je krug udario od okvir prozora.
	 * Ukoliko jeste mjenja pravac kretanja kruga
	 */
	private void move() {
		//if provjerava da li je loptica udarila u zid po X koordinatama.
		if (x < 0 || (x + radius) >= windowDimension.getWidth())
			speedX *= -1; // Mjenja smjer po X osi
		
		// If provjerava da li je loptica udarila u zid po Y koordinatama.
		if (y < 0 || (y + radius + 24) >= windowDimension.getHeight())
			speedY *= -1; // Mjenja smjer po Y osi

		x = x + speedX; 	// Pomjera koordinate po x osi, s brzinom speedX
		y = y + speedY;		// Pomjera koordinate po Y osi, sa brzinom speedY
		centerX = centerX + speedX;
		centerY = centerY + speedY;
	}

	/**
	 * Funkcija provjerava da li su se dvije loptice sudarile.
	 * 
	 * @param other
	 */
	public void checkCollision(Circle other) {
		int distance = (int) Math.sqrt(
				  Math.pow(other.centerX - this.centerX, 2)
				+ Math.pow(other.centerY - this.centerY, 2));
		
		if(distance <= (this.halfRadius+other.halfRadius))	{
			speedX *= -1;
			speedY *= -1;
			other.speedX *= -1;
			other.speedY *= -1;

		}
			

	}
}
