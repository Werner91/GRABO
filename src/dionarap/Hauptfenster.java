package dionarap;

import java.awt.BorderLayout;

import javax.swing.*;

import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.objects.AbstractPawn;
import de.fhwgt.dionarap.controller.DionaRapController;
import de.fhwgt.dionarap.model.objects.Player;

/**
*
* Hauptfenster von DionaRap. Beinhaltet die main() Mehtode
*
* @author Werner Steinbinder
* @version Aufgabe 3
*
*
*/


public class Hauptfenster extends JFrame {
	
	
	private Spielfeld spielfeld;
	private DionaRapModel DionaRap_Model;
	private DionaRapController DionaRap_Controller;
	private static int y = 10;
	private static int x = 10;
	private int gegner = 4;
	private int hindernisse = 4;
	private AbstractPawn[] pawns;
	private Navigator navigator;
	
	
	
	public static void main(String args[]){
		new Hauptfenster(); // Erzeuge das Hauptfenster
	}
	
	
	
	//Konstruktor für das Hauptfenster
	public Hauptfenster(){
	

		super("DionaRap"); //Fenster erstellen und Fenster einen Name geben
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500); //größe des Fensters
		
		
		this.setLayout(new BorderLayout());
		this.init_dionarap();
		this.pack(); //BorderLayout auf nötige größe skalieren
	

		this.setLocationRelativeTo(null); //Ort des Fensters auf dem Bildschirm festlegen
		this.navigator = new Navigator(this);//Navigator erzeugen
		this.addComponentListener(new ListenerFenster(navigator));
		this.addKeyListener(new ListenerKeyEvent()); //Listener für bewegung mit der Tastatur
		this.setVisible(true); //Fenster sichtbar machen
		//this.requestFocus();	
	}

	
	public void init_dionarap(){
		
		//Spielelogik DionaRapModel initialisieren
		DionaRap_Model = new DionaRapModel(y,x,gegner,hindernisse);
		//Controller von DionaRap initialisieren
		DionaRap_Controller = new DionaRapController(DionaRap_Model);
		
		spielfeld = new Spielfeld(); //Spielfeld erzeugen
		
		/* Listener fuer das Model registrieren */
		DionaRap_Model.addModelChangedEventListener(new ListenerModel(this));
		
		// Frage alle Spielfiguren von DionaRapModel ab und befülle Array damit
		pawns = this.DionaRap_Model.getAllPawns(); 
		
		//Schachbrettmuster dem Hauptfenster hinzufügen
		this.add(BorderLayout.CENTER, spielfeld.getHintergrund());
		
		//Zeichne alle Figuren auf das Spielfeld
		this.spielfeld.paintAllPawns(pawns);
		
	}
	
	//loesche Spielfeld und Zeichne Figuren neu
	public void repaintGame(){
		
		spielfeld.clearSpielfeld();
		pawns = DionaRap_Model.getAllPawns();
		spielfeld.paintAllPawns(pawns);
		
	}
	
	
	
	public Player getPlayer(){
		return this.DionaRap_Model.getPlayer();
	}
	
	public DionaRapController getDionaRapController(){
		return DionaRap_Controller;
	}
	
	public static int  getZeilen(){
		return y;
	}
	
	public static int getSpalten(){
		return x;
	}
	
}