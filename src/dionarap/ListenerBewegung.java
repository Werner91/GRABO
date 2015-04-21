package dionarap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import de.fhwgt.dionarap.controller.DionaRapController;


/**
 * Klasse realisiert den Listener fuer die Bewegungsbuttons. Die Klasse implementiert
 * dazu das Interface <code>ActionListener</code>. 
 *   
 * @author Werner Steinbinder
 * @version Aufgabe 3
 */

public class ListenerBewegung implements ActionListener{

	
	/**
	 * Eventhanlder fuer das Druecken der Bewegungsbuttons <code>actionPerfomred</code>
	 * 
	 * @param e Button der das Event ausgeloest hat
	 * 
	 * Stellt Quelle des Events fest
     * Stellt Referenz zu Hauptfenster her
     * Holt Spiel-Controller von Hauptfenster
     * Bewegt Spieler anhand gedrückter Taste
	 * 
	 */
	
	public void actionPerformed(ActionEvent e){
		
		JButton source = (JButton) e.getSource();

		//benoetige DionaRapController um Spieler zu bewegen 
		Hauptfenster mainWindow = (Hauptfenster) source.getTopLevelAncestor().getParent();
		DionaRapController controller = (DionaRapController) mainWindow.getDionaRapController();
		controller.movePlayer(Integer.parseInt(e.getActionCommand()));
       

        System.out.println("Move " + source.getActionCommand());
		mainWindow.requestFocus();
		

	}
	
}


