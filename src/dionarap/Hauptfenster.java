package dionarap;

import java.awt.BorderLayout;
import java.io.File;

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
* @version Aufgabe 4
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
	private String theme = "Dracula";
	private Toolbar toolbar;
	
	
	
	public static void main(String args[]){
		new Hauptfenster(); // Erzeuge das Hauptfenster
	}
	
	
	
	//Konstruktor für das Hauptfenster
	public Hauptfenster(){
	

		super("DionaRap"); //Fenster erstellen und Fenster einen Name geben
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(500,500); //größe des Fensters
		this.setResizable(false); // größe kann nich verändert werden
		
		this.setLayout(new BorderLayout());
		this.init_dionarap();
		this.pack(); //BorderLayout auf nötige größe skalieren

		this.setLocationRelativeTo(null); //Ort des Fensters auf dem Bildschirm festlegen
		this.navigator = new Navigator(this);//Navigator erzeugen
		this.addComponentListener(new ListenerFenster(navigator));
		this.addKeyListener(new ListenerKeyEvent()); //Listener für bewegung mit der Tastatur
		/* Toolbar hinzufuegen */
		toolbar = new Toolbar(this);
		this.add(toolbar, BorderLayout.NORTH);
		this.setVisible(true); //Fenster sichtbar machen
		//this.requestFocus();	
		this.pack(); //BorderLayout auf nötige größe skalieren
	}

	
	public void init_dionarap(){
		
		//Spielelogik DionaRapModel initialisieren
		DionaRap_Model = new DionaRapModel(y,x,gegner,hindernisse);
		//Controller von DionaRap initialisieren
		DionaRap_Controller = new DionaRapController(DionaRap_Model);
		//Wenn das Spiel nach einem Vorherigen Spiel neu gestartet wird, muss das Spielfeld erst gelöscht werden
		if(spielfeld != null){
			this.remove(spielfeld);
		}
		
		spielfeld = new Spielfeld(this); //Spielfeld erzeugen
		
		/* Listener fuer das Model registrieren */
		DionaRap_Model.addModelChangedEventListener(new ListenerModel(this, spielfeld));
		
		// Frage alle Spielfiguren von DionaRapModel ab und befülle Array damit
		pawns = this.DionaRap_Model.getAllPawns(); 
		
		//Schachbrettmuster dem Hauptfenster hinzufügen
		this.add(BorderLayout.CENTER, spielfeld.getHintergrund());
		
		//Zeichne alle Figuren auf das Spielfeld
		this.spielfeld.paintAllPawns(pawns);
		
		/* Toolbar aktualisieren */
		if(toolbar != null){
			toolbar.updateToolbar();			
		}
		
		
	}
	
	
	 public void drawGameResultDialog(Boolean game_lost){
		/* Gewonnen / Verloren Icons, Ausgabestrings, Dialogrueckgabewert */
		String theme = this.getTheme();
		String path_icons = "icons"+File.separator+theme+File.separator;
		ImageIcon gameover = new ImageIcon(path_icons + "gameover.gif");
		ImageIcon win = new ImageIcon(path_icons + "win.gif");		
		int playerchoice;
		String[] playerchoicestrings = {"Neues Spiel", "Abbrechen"};
		
		if(game_lost){
			playerchoice = JOptionPane.showOptionDialog(this, "Game Over", "Spiel verloren!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, gameover, playerchoicestrings, "Neues Spiel");
		}
		else {
			playerchoice = JOptionPane.showOptionDialog(this, "Gewonnen", "Spiel gewonnen!", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, win, playerchoicestrings, "Neues Spiel");
		}
		
		/* Werte gedrueckten Dialogbutton aus */
		if(playerchoice == 0){
			startNewGame();
			
			//Buttons in der Toolbar deaktivieren
			getToolbar().setButtonNSDisabled();
			getToolbar().setButtonSettingsDisabled();
		}		
	 }
	
	 public void startNewGame(){
		/* neues Spiel -> Model und Controller neu initialisieren + Spielfeld neu darstellen */
		init_dionarap();	
	 }
	
	
	
	//loesche Spielfeld und Zeichne Figuren neu
	public void repaintGame(){
		
		spielfeld.clearSpielfeld();
		pawns = DionaRap_Model.getAllPawns();
		spielfeld.paintAllPawns(pawns);
		
	}

	
	
	/**
	 * Gibt den aktuellen Spielfortschritt zurueck
	 * @return int progress
	 */
	public int getGameProgress(){
		// Berechnet die Prozent an restlichen Gegnern
		float progress = ((gegner - (float)DionaRap_Model.getOpponentCount()) / gegner) * 100;
		return (int)progress;
	}
	
	
	/**
	 * Gibt Toolbar zurueck. 
	 * @return Toolbar
	 */
	public Toolbar getToolbar(){
		return this.toolbar;
	}
	
	
	/**
	 *Gibt die Position des Spielers zurueck
	 * @return x- und y-Koordinate des Spielers
	 */
	public Player getPlayer(){
		return this.DionaRap_Model.getPlayer();
	}
	
	/**
	 *Gibt den DionaRap Controller zurueck
	 * @return  DionaRap_Controller
	 */
	public DionaRapController getDionaRapController(){
		return DionaRap_Controller;
	}
	
	
	/**
	 *Gibt das Theme zurueck
	 * @return String theme 
	 */
	public String getTheme(){
		return theme;
	}
	
	/**
	 * Gibt gibt die y-Koordinate des Felds zurueck. 
	 * @return y
	 */
	public static int  getZeilen(){
		return y;
	}
	
	/**
	 * Gibt gibt die x-Koordinate des Felds zurueck. 
	 * @return x
	 */
	public static int getSpalten(){
		return x;
	}
	
	/**
	 * Gibt das Model zurueck. 
	 * @return DionaRapModel
	 */
	public DionaRapModel getDionaRapModel(){
		return DionaRap_Model;
	}
	
}