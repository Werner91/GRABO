package dionarap;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.*;


/**
 * Klasse realisiert die Buttons zur Steuerung, abgeleitet von <code>JPanel</code>
 * 
 * @author Werner Steinbinder
 * @version Aufgabe 4
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
		
		//Verzeichnis in dem sich die icons befinden
		String verzeichnis = "icons"+File.separator+"Navigator"+File.separator;
		
		//Array mit den Namen der Icons
		String tastatur_icons [] = {"taste1.gif", "taste2.gif", "taste3.gif", "taste4.gif", "taste5.gif", "taste6.gif", "taste7.gif", "taste8.gif", "taste9.gif"};
	
		
		//9 Buttons erzeugen
		for(int button_nr=8; button_nr>= 0; button_nr--){
			button[button_nr] = new JButton();
			button[button_nr].setIcon(new ImageIcon(verzeichnis+tastatur_icons[button_nr]));
			button[button_nr].setActionCommand(String.valueOf(button_nr+1));	
			button[button_nr].setPreferredSize(new Dimension(50, 50));
			//button[button_nr].setMargin(new Insets(0, 0, 0, 0));
			
			if(button_nr == 4){
				button[button_nr].addActionListener(new ListenerWaffe()); // ActionListener Waffe dem Button zuweisen
			}else{
				button[button_nr].addActionListener(new ListenerBewegung()); // ActionListener Bewegen dem Button zuweisen
			}
			 
			add(button[button_nr]);
		}
	}
}