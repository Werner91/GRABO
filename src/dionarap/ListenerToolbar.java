package dionarap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


/**
 * Klasse realisiert den Listener fuer die Toolbar. Die Klasse implementiert
 * dazu das Interface <code>ActionListener</code>. 
 *   
 * @author Werner Steinbinder
 * @version Aufgabe 4
 */
public class ListenerToolbar implements ActionListener {

	/**
	 * Eventhanlder fuer das Druecken der Toolbarbuttons <code>actionPerfomred</code>
	 * @param e Button der das Event ausgeloest hat
	 */
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		Hauptfenster hauptfenster = (Hauptfenster) button.getTopLevelAncestor();
		
		/* neues Spiel */
		if(button.getActionCommand() == "new_game"){
			System.out.println("Toolbar " + button.getActionCommand());
			/* neues Spiel -> Model und Controller neu initialisieren + Spielfeld neu darstellen */
			hauptfenster.startNewGame();
			//Button in der Toolbar deaktivieren
			hauptfenster.getToolbar().setButtonNSDisabled();
			hauptfenster.getToolbar().setButtonSettingsDisabled();
		}
		
		if(button.getActionCommand() == "settings"){
			new Settings(hauptfenster);	
		}
		
		hauptfenster.requestFocus();
	}
	
	
}