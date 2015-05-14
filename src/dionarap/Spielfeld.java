package dionarap;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import de.fhwgt.dionarap.model.objects.*;

/**
 * Spielfeld von DionaRap beinhaltet die Funktion zur erstellung des Schabrettes
 * 
 * @author Werner Steinbinder
 * @version Aufgabe 4
 * 
*/


public class Spielfeld extends JPanel{
	
	private Hauptfenster hauptfenster;

	
	// Anzahl der Felder
	private static int zeilen = Hauptfenster.getZeilen();
	private static int spalten = Hauptfenster.getSpalten();

	//Array mit Schachbrettfeldern Zeile x Spalte
	private JLabel schachbrett [][];
	
	//Icons
	private ImageIcon iconAmmo;
	private ImageIcon iconDestruction;
	private ImageIcon iconGameOver;
	private ImageIcon iconObstacle;
	private ImageIcon iconOpponent;
	private ImageIcon iconPlayer;
	private ImageIcon iconPlayer1;	//links unten
	private ImageIcon iconPlayer2;	//unten
	private ImageIcon iconPlayer3;	//unten rechts
	private ImageIcon iconPlayer4;	//links
	private ImageIcon iconPlayer6;	//rechts
	private ImageIcon iconPlayer7;	//oben links
	private ImageIcon iconPlayer8;	//oben
	private ImageIcon iconPlayer9;	//oben rechts
	private ImageIcon iconPlayerLost; 
	private ImageIcon iconVortex;
	
	

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
	
	public Spielfeld(Hauptfenster _hauptfenster){
		new JPanel();
		hauptfenster = _hauptfenster;
		
		//Schachbrett erzeugen
		createSchachbrett();
		//Icons erzeugen
		createIcons();
	}
	
	
	//Erzeuge Instanzen der Icons
	private void createIcons(){
		
		String theme = this.hauptfenster.getTheme();
		
		//verzeichnis zum Ordner in dem sich die Icons befinden
		String verzeichnis = "icons"+File.separator+theme+File.separator;
		
		iconAmmo = new ImageIcon(verzeichnis+"ammo.png");
		iconDestruction = new ImageIcon(verzeichnis+"destruction.gif");
		iconGameOver = new ImageIcon(verzeichnis+"gameover.gif");
		iconObstacle = new ImageIcon(verzeichnis + "obstacle.gif");
		iconOpponent = new ImageIcon(verzeichnis + "opponent.gif");
		iconPlayer = new ImageIcon(verzeichnis + "player.gif");
		iconPlayer1 = new ImageIcon(verzeichnis + "player1.gif");
		iconPlayer2 = new ImageIcon(verzeichnis + "player2.gif");
		iconPlayer3 = new ImageIcon(verzeichnis + "player3.gif");
		iconPlayer4 = new ImageIcon(verzeichnis + "player4.gif");
		iconPlayer6 = new ImageIcon(verzeichnis + "player6.gif");
		iconPlayer7 = new ImageIcon(verzeichnis + "player7.gif");
		iconPlayer8 = new ImageIcon(verzeichnis + "player8.gif");
		iconPlayer9 = new ImageIcon(verzeichnis + "player9.gif");
		iconPlayerLost = new ImageIcon(verzeichnis + "loss.gif");
		iconVortex = new ImageIcon(verzeichnis + "vortex.gif");
		
		
	}
	
	
	/*
	
	//Farbe für die Schrift entsprechend des Hintergrundes waehlen
	public Color farbe_invertieren(Color hintergrundfarbe){
		
		if(hintergrundfarbe.equals(Color.BLACK)){
			return Color.WHITE;
		}else{
			return Color.black;
		}
	}
	
	*/
		
	public void paintAllPawns(AbstractPawn[] pawns) {
		
        for(int i=0;i<pawns.length;i++){
                
                if(pawns[i] instanceof Destruction){
                	
                	schachbrett[pawns[i].getY()][pawns[i].getX()].setIcon(iconDestruction);
                	//schachbrett[pawns[i].getY()][pawns[i].getX()].setForeground(farbe_invertieren(schachbrett[pawns[i].getX()][pawns[i].getY()].getBackground()));
            
                }
                if(pawns[i] instanceof Obstacle){	
                	
                	schachbrett[pawns[i].getY()][pawns[i].getX()].setIcon(iconObstacle);
                	//schachbrett[pawns[i].getY()][pawns[i].getX()].setForeground(farbe_invertieren(schachbrett[pawns[i].getX()][pawns[i].getY()].getBackground()));
                	
                }
                if(pawns[i] instanceof Opponent){       
                	
                	schachbrett[pawns[i].getY()][pawns[i].getX()].setIcon(iconOpponent);
                	//schachbrett[pawns[i].getY()][pawns[i].getX()].setForeground(farbe_invertieren(schachbrett[pawns[i].getX()][pawns[i].getY()].getBackground()));
                }
                if(pawns[i] instanceof Player){
                	
                	Player pawnPlayer = (Player) pawns[i];
                	//Blickrichtung der Spielfigur
                	int blickrichtung = pawnPlayer.getViewDirection();
                	
                	
                	if(blickrichtung == 1){
    					schachbrett[pawns[i].getY()][pawns[i].getX()].setIcon(iconPlayer1);
    				}
                	else if(blickrichtung == 2){
    					schachbrett[pawns[i].getY()][pawns[i].getX()].setIcon(iconPlayer2);
    				}
                	else if(blickrichtung == 3){
    					schachbrett[pawns[i].getY()][pawns[i].getX()].setIcon(iconPlayer3);
    				}
                	else if(blickrichtung == 4){
    					schachbrett[pawns[i].getY()][pawns[i].getX()].setIcon(iconPlayer4);
    				}
                	else if(blickrichtung == 6){
    					schachbrett[pawns[i].getY()][pawns[i].getX()].setIcon(iconPlayer6);
    				}
                	else if(blickrichtung == 7){
    					schachbrett[pawns[i].getY()][pawns[i].getX()].setIcon(iconPlayer7);
    				}
                	else if(blickrichtung == 8){
    					schachbrett[pawns[i].getY()][pawns[i].getX()].setIcon(iconPlayer8);
    				}
                	else if(blickrichtung == 9){
    					schachbrett[pawns[i].getY()][pawns[i].getX()].setIcon(iconPlayer9);
    				}
                	
                	
                }
                if(pawns[i] instanceof Vortex){
                	schachbrett[pawns[i].getY()][pawns[i].getX()].setIcon(iconVortex);
                }          
        }
	}
		

	
	 public void clearSpielfeld() {
         for(int k=0; k < zeilen; k++){
                 for(int j=0; j < spalten; j++){
                         schachbrett[k][j].setIcon(null);
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
	
	/**
	 * Setzt das Gewonnen / Verloren SpielerIcon auf das Spielfeld
	 * @param Player spieler Spielerfigur
	 * @param boolean game_lost Wurde Spiel gewonnen / verloren
	 */
	public void gameStatusEnd(Player spieler, boolean game_lost){
		/* Spiel wurde verloren */
		if(game_lost){
			this.schachbrett[spieler.getY()][spieler.getX()].setIcon(iconPlayerLost);
		}
		/* Spiel gewonnen */
		else {
			this.schachbrett[spieler.getY()][spieler.getX()].setIcon(iconPlayer);
		}
	}
	
	
}