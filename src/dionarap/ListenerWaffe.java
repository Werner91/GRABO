package dionarap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import de.fhwgt.dionarap.controller.DionaRapController;

/**
 * Klasse realisiert den Listener der auf einen Schuss reagiert. Die Klasse implementiert
 * das Interface <code>ActionListener</code>. 
 * @author Werner Steinbinder
 * @version Aufgabe 3
 */

public class ListenerWaffe implements ActionListener{
	

	
	public void actionPerformed(ActionEvent e){
		JButton source = (JButton) e.getSource();

		//benoetige DionaRapController um Spieler zu bewegen 
		Hauptfenster mainWindow = (Hauptfenster) source.getTopLevelAncestor().getParent();
		DionaRapController controller = (DionaRapController) mainWindow.getDionaRapController();
		
		controller.shoot();
		
		System.out.println("Shoot " + source.getActionCommand());
		//source.requestFocus();
	}
	
}