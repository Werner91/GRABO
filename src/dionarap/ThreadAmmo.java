package dionarap;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;


/**
 * Klasse realisiert den Thread der fuer das Blinken 
 * der Munitionsanzeige zustaendig ist, abgeleitet von <code>Thread</code>
 * 
 * @author Werner Steinbinder
 * @version Aufgabe 6
 */


class ThreadAmmo extends Thread{
	
	private Hauptfenster hauptfenster;
	private static final int blinktime = 500;
	
	
	/**
	 * Konstruktor im Munitions-blink-panel
	 * @param hauptfenster
	 */
	ThreadAmmo(Hauptfenster hauptfenster){
		this.hauptfenster = hauptfenster;
	}
	
	
	
	/**
	 * Thread ist fuer das Blinken der Munitionsanzeige zuständig
	 */
	public void run(){
		/* so lang Munition == 0 und noch keine 5 Sekunden vergangen sind */
		for(int i=0; (i<6) && (hauptfenster.getDionaRapModel().getShootAmount() == 0); i++){
			//JLabel[] ammo = hauptfenster.getToolbar().getMuniJLabelArr();
			for(int k=0; k < 3; k++){
				/* abwechselnd Rand hinzufuegeb / entfernen */
				if((i % 2) == 0){
				
					
					hauptfenster.getToolbar().getMuniJPanel().setBorder(BorderFactory.createTitledBorder(null, "Munition", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, null, Color.RED));
				
				}else{
					
					hauptfenster.getToolbar().getMuniJPanel().setBorder(BorderFactory.createTitledBorder(null, "Munition", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, null, Color.BLACK));
					
				}
				hauptfenster.getToolbar().getMuniJPanel().updateUI();
			}
			try{
				/*Thread schlafen legen*/
				Thread.sleep(blinktime);
			}catch (InterruptedException ex){
				System.out.println("Catched exception in Thread Ammo.");
				System.out.println(ex.getStackTrace());
			}
		}
	}
}