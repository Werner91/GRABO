package dionarap;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

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
		
		//Array mit den Buttons zur Steuerung
		JButton button [] = new JButton[10];
	
		
		//9 Buttons mit jeweils einer Zahl erzeugen
		for(int button_nr=9; button_nr>= 1; button_nr--){
			button[button_nr] = new JButton("" + button_nr);
			button[button_nr].setPreferredSize(new Dimension(50, 50));
			
			if(button_nr == 5){
				button[button_nr].addActionListener(new ListenerWaffe()); // ActionListener Waffe dem Button zuweisen
			}else{
				button[button_nr].addActionListener(new ListenerBewegung()); // ActionListener Bewegen dem Button zuweisen
			}
			
			add(button[button_nr]);
		}
	}
}