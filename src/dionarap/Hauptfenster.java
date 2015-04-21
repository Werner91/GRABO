package dionarap;

import java.awt.BorderLayout;
import javax.swing.*;

/**
*
* Hauptfenster von DionaRap. Beinhaltet die main() Mehtode
*
* @author Werner Steinbinder
* @version Aufgabe 2
*
*
*/


public class Hauptfenster extends JFrame {
	
	
	private Spielfeld spielfeld;

	
	public static void main(String args[]){
		new Hauptfenster(); // Erzeuge das Hauptfenster
	}
	
	
	
	//Konstruktor f�r das Hauptfenster
	public Hauptfenster(){
	

		super("DionaRap"); //Fenster erstellen und Fenster einen Name geben
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500); //gr��e des Fensters
		
		
		this.setLayout(new BorderLayout());
		spielfeld = new Spielfeld(); //Spielfeld erzeugen
		this.add(BorderLayout.CENTER, spielfeld.getHintergrund());	//Schachbrettmuster dem Fenster hinzuf�gen
		this.pack(); //BorderLayout auf n�tige gr��e skalieren
	

		this.setLocationRelativeTo(null); //Ort des Fensters auf dem Bildschirm festlegen
		new Navigator(this);//Navigator erzeugen
		this.setVisible(true); //Fenster sichtbar machen
	}
	
}