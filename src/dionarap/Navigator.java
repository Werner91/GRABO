package dionarap;

import javax.swing.*;
import java.awt.*;


/**
*Realisierung des Navigationsfensters
*
*@author Werner Steinbinder
*@version Aufgabe 2
*
*/

public class Navigator extends JWindow{
	

	private JPanel rahmen;
	
	/**
	 * Konstruktor des Navigationsfensters vom Typ <code>JWindow</code>
	 * @param parent Vaterfenster vom Typ <code>Hauptfenster</code>
	 */	
	public Navigator(JFrame parent){
		
		
		this.setSize(250, 250);
		//Instanz von JPanel erzeugen
		rahmen = new JPanel();
		//Layout Manager auf GridLayout setzen
		rahmen.setLayout(new BorderLayout());
		
		//dem JWindow die Tastatur hinzufügen mit einem 3x3 Gitter
		rahmen.add(new Tastatur());
		//Rahmen erstellen und rot faerben + Rahmendicke - noetig da JWindow keine Rahmen besitzt
		rahmen.setBorder(BorderFactory.createLineBorder(Color.red,1));
		
		// Rahmen zu JWindow hinzufuegen
		this.getContentPane().add(rahmen);
						
		//Tastaturblock rechts neben Spielfeld platzieren
		this.setLocation( (parent.getLocation().x + parent.getWidth() + 40), parent.getLocation().y);
				
		this.setVisible(true);
		
	}
	
	
}