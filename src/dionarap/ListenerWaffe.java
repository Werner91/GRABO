package dionarap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import de.fhwgt.dionarap.controller.DionaRapController;

/**
 * Klasse realisiert den Listener der auf einen Schuss reagiert. Die Klasse implementiert
 * das Interface <code>ActionListener</code>. 
 * @author Werner Steinbinder
 * @version Aufgabe 6
 */

public class ListenerWaffe implements ActionListener{
	
	/**
	 * Eventhanlder fuer das Druecken der Bewegungsbuttons <code>actionPerfomred</code>
	 * Schusstaste 5
	 * @param e Button der das Event ausgeloest hat
	 */
	
	public void actionPerformed(ActionEvent e){
		JButton source = (JButton) e.getSource();

		//benoetige DionaRapController um Spieler zu bewegen 
		Hauptfenster hauptfenster = (Hauptfenster) source.getTopLevelAncestor().getParent();
		DionaRapController DRController = (DionaRapController) hauptfenster.getDionaRapController();
		
		/*pruefe ob Munition vorhanden - falls nicht erzeuge Thread um Anzeige zum Blinken zu bringen */
		if(hauptfenster.getDionaRapModel().getShootAmount() == 0){
			/*erzeuge neuen THread falls dieser noch nicht besteht */
			if(hauptfenster.getThreadt_ammo() == null){
				hauptfenster.createThreadt_ammo();
			
			}/* oder nicht mehr aktiv ist */
			else if(!(hauptfenster.getThreadt_ammo().isAlive())){
				hauptfenster.createThreadt_ammo();				
			}
		}
		/*Wenn munition vorhanden ist */
		if((hauptfenster.getDionaRapModel().getShootAmount()) > 0){
			DRController.shoot();
		}
		
		System.out.println("Shoot " + source.getActionCommand());
		hauptfenster.requestFocus();
	}
}



