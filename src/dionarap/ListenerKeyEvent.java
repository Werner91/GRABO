package dionarap;

import java.awt.event.KeyEvent;


import java.awt.event.KeyListener;

import javax.swing.JButton;

import de.fhwgt.dionarap.controller.DionaRapController;
import dionarap.Hauptfenster;



/**
 * Klasse realisiert den Listener fuer Tastaturereignisse. Die Klasse implementiert
 * dazu das Interface <code>KeyListener</code>. 
 *   
 * @author Werner Steinbinder
 * @version Aufgabe 3
 */


public class ListenerKeyEvent implements KeyListener{
	
	/**
     * Veranlasst Spielerbewegung entsprechend gedrückter Taste auf der Tastatur
     * Steuerung über Numpad oder Zahlen
     */
	
	
	@Override
	public void keyTyped(KeyEvent e){
	
		Hauptfenster hauptfenster = (Hauptfenster) e.getSource();
		DionaRapController dr_controller = hauptfenster.getDionaRapController();

		/* Taste 5 - Schuss */
		if(e.getKeyChar() == '5'){
			/*Prüfen ob Munition vorhanden ist */
			if (hauptfenster.getDionaRapModel().getShootAmount() == 0) { // Erzuegen den "Blink"-Threads, falls Munition = 0
                if(hauptfenster.getThreadt_ammo() == null) {
                    hauptfenster.createThreadt_ammo();
                }/* oder nicht mehr Aktiv ist */ 
                else if(!(hauptfenster.getThreadt_ammo().isAlive())) {
                    hauptfenster.createThreadt_ammo();
                }
            }
			/*Wenn Munition vorhanden ist*/
			if(hauptfenster.getDionaRapModel().getShootAmount() > 0){
				dr_controller.shoot();
			}
			System.out.println("Shot " + e.getKeyChar());
		}
		/* Taste 1-4 + 6-9 */
		else if(e.getKeyChar() != '5' &&  ('1' <= e.getKeyChar() && e.getKeyChar() <= '9')){
		
			/* bewege Spieler in entsprechende Richtun */
			dr_controller.movePlayer(Character.getNumericValue(e.getKeyChar()));
			System.out.println("Move " + e.getKeyChar());
		}
		/* beliebige andere Taste */
		else {
			System.out.println(e.getKeyChar());
		}

	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub		
	}
	
	
}