package dionarap;

import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import javax.swing.*;


/**
 * Klasse realisiert die Buttons zur Steuerung, abgeleitet von <code>JPanel</code>
 * 
 * @author Werner Steinbinder
 * @version Aufgabe 2
 */



public class Tastatur extends JPanel{
	
	/**
	 * Konstruktor der Tastatur vom Typ <code>JPanel</code>
	 * Legt das Layout fest und ordnet die Steuertasten entprechend des Nummernblocks der Tastatur an.
	 */	
	
	
	public Tastatur(){
		
		/* 
		 * Rufe Konstruktor auf, erzeuge JPanel mit GridLayout
		 * GridLayout(int AnzahlZeilen, int AnzahlSpalten, int hAbstand, int vAbstand)
		 */
		super(new GridLayout(3,3,0,0));
		
		/* Elemte von rechts nach links dem Layout hinzufuegen */
		this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		//9 Buttons mit jeweils einer Zahl erzeugen
		for(int Button_nr=9; Button_nr>= 1; Button_nr--){
			add(new JButton("" + Button_nr));
		}
	}
}