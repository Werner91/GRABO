package dionarap;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * Spielfeld von DionaRap beinhaltet die Funktion zur erstellung des Schabrettes
 * 
 * @author Werner Steinbinder
 * @version Aufgabe 2
 * 
*/


public class Spielfeld extends JPanel{
	

	
	//Array mit Schachbrettfeldern Zeile x Spalte
	private JLabel[][] schachbrett ;
	
	// Anzahl der Felder
	private int zeilen = 0;
	private int spalten = 0;
	
	

	//getter Methode für hintergrund
	public JPanel getHintergrund(){
		return this;
	}
	
	
	/**
	 * 
	 * Konstruktor des Spielfeldes / Schachbretts
	 * @param Konstruktor des Spielfeldes
	 * 
	 */
	
	public Spielfeld(){
		new JPanel();
		//hauptfenster = _hauptfenster;
		createSchachbrett();
	}
	
	
	
	/**
	 * Fuegt Spielfelder zum Schachbrett hinzu
	 * @param size_spielfeld Groesse des Spielfelds
	 */	
	
	public void createSchachbrett(){
		
		// Layout und Array für Schachbrett Labels
		zeilen = 10;
		spalten = 15;
		this.setLayout( new GridLayout(zeilen, spalten)); 
		schachbrett = new JLabel [zeilen][spalten];
		
		
		for(int y_achse = 0; y_achse < zeilen; y_achse++){
			for(int x_achse = 0; x_achse < spalten; x_achse++){
				// Label mit 50 x 50 pixel anlegen
				schachbrett[y_achse][x_achse] = new JLabel();
				schachbrett[y_achse][x_achse].setPreferredSize(new Dimension(50,50));
				
				
				//Labels bzw. Spielfeld einfärben. Mit Modulo 2 prüfen ob schwarz oder weis
				if((y_achse % 2) == 0){
					if((x_achse % 2) == 0){
						schachbrett[y_achse][x_achse].setBackground(Color.black);
					}
					else {
						schachbrett[y_achse][x_achse].setBackground(Color.white);						
					}
				}
				else {
					if((x_achse % 2) == 0){
						schachbrett[y_achse][x_achse].setBackground(Color.white);
					}
					else {
						schachbrett[y_achse][x_achse].setBackground(Color.black);						
					}					
				}
				
				/* Label deckend darstellen */
				schachbrett[y_achse][x_achse].setOpaque(true);
				//Das Array mit den Labels zum Panel hinzufügen
				this.add(schachbrett[y_achse][x_achse]);
			}
		}
	}
}