package dionarap;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.*;

import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.objects.AbstractPawn;
import de.fhwgt.dionarap.model.objects.Ammo;
import de.fhwgt.dionarap.controller.DionaRapController;
import de.fhwgt.dionarap.model.objects.Player;
import de.fhwgt.dionarap.model.data.Grid;
import de.fhwgt.dionarap.model.data.MTConfiguration;
//import de.fhwgt.dionarap.MenuBar;
/**
*
* Hauptfenster von DionaRap. Beinhaltet die main() Mehtode
*
* @author Werner Steinbinder
* @version Aufgabe 6
*
*
*/


public class Hauptfenster extends JFrame {
	
	
	private Spielfeld spielfeld;

	private int y = 10;
	private int x = 10;
	private int gegner = 2;
	private int hindernisse = 4;
	private int ammo_count = 3;
	private int ammo_start = 3;
	private AbstractPawn[] pawns;
	
	private Navigator navigator;
	private Toolbar toolbar;
	private MenuBar menubar;
	
	/* Theme beim Spielstart */
	private String theme = "Dracula";
	
	/*Model + Controler */
	private DionaRapModel DionaRap_Model;
	private DionaRapController DionaRap_Controller;
	
	/* Multithreading-Konfiguration */
	private static MTConfiguration MTConf = new MTConfiguration();
	
	/* Flag ob Spieleinstellungen angepasst wurden */
	private boolean game_settings_changed = false;
	
	/* Thread - binken der Munitionsanzeuge */
	private Thread t_ammo;
	
	
	
	
	
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
		//this.addKeyListener(new ListenerKeyEvent()); //Listener für bewegung mit der Tastatur
		/* Toolbar hinzufuegen */
		toolbar = new Toolbar(this);
		this.add(toolbar, BorderLayout.NORTH);
		this.addComponentListener(new ListenerFenster(navigator));
		
		/*Menuleiste hinzufuegen */
		this.setJMenuBar(menubar = new MenuBar(this));
		
		
		this.pack(); //BorderLayout auf nötige größe skalieren
		this.setVisible(true); //Fenster sichtbar machen
		//this.requestFocus();	

		this.addKeyListener(new ListenerKeyEvent()); //Listener für bewegung mit der Tastatur
		this.addMouseListener(new ListenerMaus(this));
		this.requestFocus(); //Um den Focus
	}

	
	public void init_dionarap(){
		
		//Spielelogik DionaRapModel initialisieren
		DionaRap_Model = new DionaRapModel(y,x,gegner,hindernisse);
		//Controller von DionaRap initialisieren
		DionaRap_Controller = new DionaRapController(DionaRap_Model);
		
		//*** Wenn das Spiel nach einem Vorherigen Spiel neu gestartet wird, muss das Spielfeld erst gelöscht werden
		if(spielfeld != null){
			this.remove(spielfeld);
		}
		//Anzahl der Munition zu beginn des Spiels
		DionaRap_Model.setShootAmount(ammo_start);
		
		//Ammo-Objekte auf dem Spielfeld anlegen
		for(int i = 1; i <= ammo_count; i++ ){
		DionaRap_Model.addAmmo(new Ammo());
		}
		
		spielfeld = new Spielfeld(this); //Spielfeld erzeugen
		
		//Schachbrettmuster dem Hauptfenster hinzufügen
		this.add(BorderLayout.CENTER, spielfeld.getHintergrund());
		
		/* Listener fuer das Model registrieren */
		DionaRap_Model.addModelChangedEventListener(new ListenerModel(this, spielfeld));
		
		// Frage alle Spielfiguren von DionaRapModel ab und befülle Array damit
		pawns = this.DionaRap_Model.getAllPawns(); 
		
		//Zeichne alle Figuren auf das Spielfeld
		this.spielfeld.paintAllPawns(pawns);
		
		
		/* Multithreading Konfiguration initialisieren (bzw. Werte aus dem Einstellungsdialog uebernehmen) und aktivieren*/
		if(game_settings_changed == false){
			this.init_MTConfiguration();
		}
		DionaRap_Controller.setMultiThreaded(MTConf);
		
		/* Toolbar aktualisieren */
		if(toolbar != null){
			toolbar.updateToolbar();			
		}
		
	}
	
	
	/**
	 * Initialisiere die Multithreading-Einstellungen
	 */
	private void init_MTConfiguration(){
		MTConf.setAlgorithmAStarActive(true);
		MTConf.setAvoidCollisionWithObstacles(true);
		MTConf.setAvoidCollisionWithOpponent(true);
		MTConf.setMinimumTime(800);
		MTConf.setShotGetsOwnThread(true);
		MTConf.setOpponentStartWaitTime(6000);
		MTConf.setOpponentWaitTime(4000);
		MTConf.setShotWaitTime(1000);
		MTConf.setRandomOpponentWaitTime(false);
		MTConf.setDynamicOpponentWaitTime(false);
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
			
			
			//Buttons in der Toolbar deaktivieren
			getToolbar().setButtonNSDisabled();
			getToolbar().setButtonSettingsDisabled();
			startNewGame();
		}		
	 }
	
	 /**
	  * Methode startet ein neues Spiel - Model + Controller werden
	  * initialisiert, Spielfeld wird neu gezeichnet und die Toolbar
	  * positioniert
	  */
	 public void startNewGame(){
		 
		/* neues Spiel -> Model und Controller neu initialisieren + Spielfeld neu darstellen */
		init_dionarap();	
		/* Button neues Spiel deaktivieren, packen + Navigator positionieren */
		this.getToolbar().setButtonNSDisabled();
		
		//this.getToolbar().setButtonSettingsDisabled();
		this.getMenubar().setGameSettingsDisabled();
		this.getMenubar().setLevelReaderDisabled();
		this.pack();
		this.navigator.setNavLocation(); // Navigator neu Positionieren
		this.requestFocus();
	 }
	
	
	 /**
	  * Methode um die Toolbar zu positionieren
	  * @param top zeige Toolbar oben (true) oder unten an (false)
	  */
	 public void setToolbarPosition(boolean top){
		 //toolbar oben anzeigen
		 if(top){
			 this.remove(toolbar);
			 this.add(toolbar, BorderLayout.NORTH);
			 this.pack();
		 }
		 //Toolbar unten anzeigen
		 else{
			 this.remove(toolbar);
			 this.add(toolbar, BorderLayout.SOUTH);
			 this.pack();
		 }
	 }
	 
	 
	 
	 
	//loesche Spielfeld und Zeichne Figuren neu
	public void repaintGame(){
		
		spielfeld.clearSpielfeld();
		pawns = DionaRap_Model.getAllPawns();
		spielfeld.paintAllPawns(pawns);
		
	}

	
	
	/**
	 * Gibt Navigator zurueck. 
	 * @return Navigator
	 */
	public Navigator getNavigator(){
		return this.navigator;
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
	 * Gibt das Spielverzeichnis zurueck
	 * @return String spieleverzeichnis
	 */
	public static String getGameDirectory(){
		String gamedirectory = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		return (gamedirectory + separator);
	}
	
	
	
	/**
	 * Gibt die Anzahl der Hindernisse zurueck.
	 * @return hindernisse 
	 * 
	 */
	public int getHindernisse(){
		return hindernisse;
	}
	
	/**
	 * Gibt Toolbar zurueck. 
	 * @return Toolbar
	 */
	public Toolbar getToolbar(){
		return this.toolbar;
	}
	
	
	/**
     * get-Methode, gibt die Menuebar zurueck
     * @return MenueLeiste
     */
    public MenuBar getMenubar() {
        return menubar;
    }
	
	
	
	/**
	 * Gibt Spielfeld zurueck. 
	 * @return Spielfeld
	 */
	public Spielfeld getSpielfeld(){
		return this.spielfeld;
	}

	
	
	
	/**
	 * Gibt Array mit Spielfiguren zurueck. 
	 * @return all Pawns
	 */
	public AbstractPawn[] getPawns(){
		return this.DionaRap_Model.getAllPawns();
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
	 * Setzt das Theme
	 * @param String theme
	 */
	public void setTheme(String theme){
		this.theme = theme;
		this.spielfeld.changeTheme();
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
	public int  getZeilen(){
		return y;
	}
	
	/**
	 * Gibt gibt die x-Koordinate des Felds zurueck. 
	 * @return x
	 */
	public int getSpalten(){
		return x;
	}
	
	
	/**
	 * Gibt Multithreading Konfiguration zurueck. 
	 * @return MTConf
	 */
	public MTConfiguration getMTConfiguration(){
		return Hauptfenster.MTConf;
	}		

	
	
	/**
	 * Gibt das Model zurueck. 
	 * @return DionaRapModel
	 */
	public DionaRapModel getDionaRapModel(){
		return DionaRap_Model;
	}

	/**
	 * Gibt den Thread fuer das Blinken der Munitionsanzeige zurueck
	 * @return Thread
	 */
	public Thread getThreadt_ammo(){
		return t_ammo;		
	}

	
	/**
	 * Methode startet den Thread fuer das Blinken der Munitionsanzeige
	 */
	public void createThreadt_ammo(){
		t_ammo = new ThreadAmmo(this);
		t_ammo.start();
	}
	
	/**
	 * Methode setzt das Flag fuer die Spieleinstellungen auf true wenn
	 * diese geaendert wurden
	 */
	public void setFlagGameSettingsChanged(){
		this.game_settings_changed = true;
	}
	
	
	/**
	 * Methode aktualisiert die Groesse des Spielfelds, Anzahl an Gegnern + Hindernisse
	 * @param int y, int x, int opponents, int obstacles - Groesse des Felds in x- und y-Richtung, Anzahl an Gegner + Hindernisse
	 */
	public void updateGameSettings(int y, int x, int opponents, int obstacles){
		this.y = y;
		this.x = x;
		this.gegner = opponents;
		this.hindernisse = obstacles;
	}
	
	
}