package dionarap;

import de.fhwgt.dionarap.model.listener.DionaRapListener;
import de.fhwgt.dionarap.model.events.DionaRapChangedEvent;
import de.fhwgt.dionarap.model.events.GameStatusEvent;


/**
 * Klasse realisiert den Listener fuer Events die durch das Aendern des DionaRapModels entstehen - implementiert
 * das Interface <code>DionaRapListener</code>. 
 * @author Werner Steinbinder
 * @version Aufgabe 4
 */


public class ListenerModel implements DionaRapListener{
	
	private Hauptfenster hauptfenster;
	private Spielfeld spielfeld;
	private MenuBar menubar;
	
	//Speil gewonnen / Spiel verloren
	private boolean game_running = true;
	private boolean game_lost = false;
	
	
	
	
	
	public ListenerModel(Hauptfenster _hauptfenster, Spielfeld _spielfeld){
		hauptfenster = _hauptfenster;
		spielfeld = _spielfeld;
		
	}

	
	
	/**
	 * Eventhandler fuer das Event <code>modelChanged</code>.
	 * Bei Veraenderungen am <code>DionaRapModel</code> wird das Spielfeld aktualisiert
	 * @param e Aenderungsereignis vom Typ <code>DionaRapChangedEvent</code>
	 */
	public void modelChanged(DionaRapChangedEvent e) {
		/* Spiel laueft */
		if(game_running){
			/* Zeichne Figuren neu, setze aktuellen Spielstand / Fortschritt */
			hauptfenster.repaintGame();
			hauptfenster.getToolbar().updateToolbar();
		}
	
	}
	

	
	
	/**
	 * Eventhandler fuer das Event <code>statusChanged</code>.
	 * Event tritt auf wenn das Spiel gewonnen / verloren wurde
	 * @param e Spielstatusereignis vom Typ <code>GameStatusEvent</code>
	 */
	public void statusChanged(GameStatusEvent e) {
		
		game_running = false;
		
		//Spiel wurde gewonnen
		if(e.isGameWon()){
			game_lost = false;
			System.out.println("Spiel gewonnen");
		}
		else if(e.isGameOver()){
			game_lost = true;
			System.out.println("Game Over");
		}
		
		//Buttons in der Toolbar aktivieren
		hauptfenster.getToolbar().setButtonSettingsEnabled();
		hauptfenster.getToolbar().setButtonNSEnabled();
		
		
		hauptfenster.getToolbar().updateToolbar();
		// Gewinner/Verlierer Icon setzen
		spielfeld.gameStatusEnd(hauptfenster.getPlayer(), game_lost); 
		
		 // den "Spieleinstellungen" und "LevelReader"-Button auf aktiv setzen
		hauptfenster.getMenubar().setGameSettingsEnabled();
		hauptfenster.getMenubar().setLevelReaderEnabled();
		
		
		/* zeige Gewonnen / Verloren Dialog an */ 
		hauptfenster.drawGameResultDialog(game_lost);
		
		
	}
	
	
}