package dionarap;

import javax.swing.*;
import java.awt.*;
import de.fhwgt.dionarap.controller.DionaRapController;

/**
*Realisierung des Navigationsfensters
*
*@author Werner Steinbinder
*@version Aufgabe 4
*
*/

public class Navigator extends JWindow {
	

	private JPanel rahmen;

	
	/**
	 * Konstruktor des Navigationsfensters vom Typ <code>JWindow</code>
	 * @param parent Vaterfenster vom Typ <code>Hauptfenster</code>
	 */	
	public Navigator(JFrame parent){
		
		//Konstruktor des Vaterfensters
		super(parent);
		
		this.setSize(250, 250);
		
		//Polygon mit einer achteckform initialisieren
		Polygon achteck = newPolygon();
		this.setShape(achteck);
		
		//Instanz von JPanel erzeugen
		rahmen = new JPanel();
		//Layout Manager auf GridLayout setzen
		rahmen.setLayout(new BorderLayout());
		
		//dem JWindow die Tastatur hinzufügen mit einem 3x3 Gitter
		rahmen.add(new Tastatur());
		
		//Rahmen erstellen und rot faerben + Rahmendicke - noetig da JWindow keine Rahmen besitzt
		//rahmen.setBorder(BorderFactory.createLineBorder(Color.red,1));
		
		// Rahmen zu JWindow hinzufuegen
		this.getContentPane().add(rahmen);
		//Navigationsfenster entsprechende des Hauptfensters setzen
		setNavLocation();
		//sichtbar machen
		this.setVisible(true);
		
	}
	
	
	/**
	 * Methode zum erzeugen des Polygons fuer das Achteck
	 * @return Polygon mit achteckform
	 */
	private Polygon newPolygon(){
		Polygon achteck = new Polygon();
		achteck.addPoint(83, 0);
		achteck.addPoint(166, 0);
		achteck.addPoint(249, 83);
		achteck.addPoint(249, 166);
		achteck.addPoint(166, 249);
		achteck.addPoint(83, 249);
		achteck.addPoint(0, 166);
		achteck.addPoint(0, 83);
		
		return achteck;
	}
	
     
	
	public void setNavLocation(){
		//Tastaturblock rechts neben Spielfeld platzieren
		this.setLocation( (getParent().getLocation().x + getParent().getWidth() + 40), getParent().getLocation().y);
		//System.out.println(getParent());
	}
	
	/**
	 * Methode macht den Navigator sichtbar
	 */
	public void showNavigator(){
		this.setVisible(true);
	}


	/**
	 * Methode macht den Navigator unsichtbar
	 */
	public void hideNavigator(){
		this.setVisible(false);
	}
	
}