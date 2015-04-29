package dionarap;

import de.fhwgt.dionarap.model.listener.DionaRapListener;
import de.fhwgt.dionarap.model.events.DionaRapChangedEvent;
import de.fhwgt.dionarap.model.events.GameStatusEvent;


/**
 * Klasse realisiert den Listener fuer Events die durch das Aendern des DionaRapModels entstehen - implementiert
 * das Interface <code>DionaRapListener</code>. 
 * @author Werner Steinbinder
 * @version Aufgabe 3
 */


public class ListenerModel implements DionaRapListener{
	
	private Hauptfenster hauptfenster;
	
	public ListenerModel(Hauptfenster _hauptfenster){
		hauptfenster = _hauptfenster;
	}

	
	
	/**
	 * Eventhandler fuer das Event <code>modelChanged</code>.
	 * Bei Veraenderungen am <code>DionaRapModel</code> wird das Spielfeld aktualisiert
	 * @param e Aenderungsereignis vom Typ <code>DionaRapChangedEvent</code>
	 */
	public void modelChanged(DionaRapChangedEvent e) {
		
			// Zeichne Figuren neu
			hauptfenster.repaintGame();
	
	}
	

	
	
	/**
	 * Eventhandler fuer das Event <code>statusChanged</code>.
	 * Event tritt auf wenn das Spiel gewonnen / verloren wurde
	 * @param e Spielstatusereignis vom Typ <code>GameStatusEvent</code>
	 */
	public void statusChanged(GameStatusEvent e) {
	
	}
	
	
}