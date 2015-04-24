package dionarap;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import de.fhwgt.dionarap.model.objects.AbstractPawn;
import de.fhwgt.dionarap.model.objects.*;

/**
 * Spielfeld von DionaRap beinhaltet die Funktion zur erstellung des Schabrettes
 * 
 * @author Werner Steinbinder
 * @version Aufgabe 2
 * 
*/


public class Spielfeld extends JPanel{
	

	
	// Anzahl der Felder
	private static int zeilen = Hauptfenster.getZeilen();
	private static int spalten = Hauptfenster.getSpalten();
	
	//Array mit Schachbrettfeldern Zeile x Spalte
		private JLabel schachbrett [][];
	
	

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
		
		//Schachbrett erzeugen
		createSchachbrett();		
	}
	
	
	
	//Farbe für die Schrift entsprechend des Hintergrundes waehlen
	public Color farbe_invertieren(Color hintergrundfarbe){
		
		if(hintergrundfarbe.equals(Color.BLACK)){
			return Color.WHITE;
		}else{
			return Color.black;
		}
	}
	
		
	public void paintAllPawns(AbstractPawn[] pawns) {
		
        for(int i=0;i<pawns.length;i++){
                
                if(pawns[i] instanceof Destruction){
                	
                	schachbrett[pawns[i].getY()][pawns[i].getX()].setText("*");
                	schachbrett[pawns[i].getY()][pawns[i].getX()].setForeground(farbe_invertieren(schachbrett[pawns[i].getX()][pawns[i].getY()].getBackground()));
            
                }
                if(pawns[i] instanceof Obstacle){	
                	
                	schachbrett[pawns[i].getY()][pawns[i].getX()].setText("H");
                	schachbrett[pawns[i].getY()][pawns[i].getX()].setForeground(farbe_invertieren(schachbrett[pawns[i].getX()][pawns[i].getY()].getBackground()));
                	
                }
                if(pawns[i] instanceof Opponent){       
                	
                	schachbrett[pawns[i].getY()][pawns[i].getX()].setText("G");
                	schachbrett[pawns[i].getY()][pawns[i].getX()].setForeground(farbe_invertieren(schachbrett[pawns[i].getX()][pawns[i].getY()].getBackground()));
                }
                if(pawns[i] instanceof Player){
                	
                	schachbrett[pawns[i].getY()][pawns[i].getX()].setText("S");
                	schachbrett[pawns[i].getY()][pawns[i].getX()].setForeground(farbe_invertieren(schachbrett[pawns[i].getX()][pawns[i].getY()].getBackground()));                }
                
                if(pawns[i] instanceof Vortex){
                	schachbrett[pawns[i].getY()][pawns[i].getX()].setBackground(Color.GREEN);
                }
        }       
	}
		

	
	 public void clearSpielfeld() {
         for(int k=0; k < zeilen; k++){
                 for(int j=0; j < spalten; j++){
                         schachbrett[k][j].setText("");
                 } 
         }
	 }
	
	
	/**
	 * Fuegt Spielfelder zum Schachbrett hinzu
	 * @param size_spielfeld Groesse des Spielfelds
	 */	
	
	public void createSchachbrett(){
		
		// Layout und Array für Schachbrett Labels
		//zeilen = 10;
		//spalten = 10;
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