package dionarap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;


/**
* Klasse realisiert die Toolbar, abgeleitet von <code>JToolBar</code>
*
* @author Werner Steinbinder
* @version Aufgabe 6
*/


public class Toolbar extends JToolBar{
	
	
	private static final long serialVersionUID = 1L;
	
	
	private Hauptfenster hauptfenster;
	private JButton toolbar_neuesspiel;
	private JButton settings;
	private JPanel punktestand;
	private JTextField punktestandtext;
	private JPanel munition;
	private drawAmmoImage munition_arr[] = new drawAmmoImage[3];
	private JProgressBar fortschrittsbalken;
	private JPanel spielfortschritt;
	private int ammoCounter = 0;
	private int ammocount;
	private boolean toMuchAmmo = false;
	
	public Toolbar(Hauptfenster _hauptfenster){
		hauptfenster = _hauptfenster;
		
		/* Toolbar soll vom Benutzer nicht bewegent werden koennen */
		this.setFloatable(false);
		
		/* Button neues Spiel */		
		toolbar_neuesspiel = new JButton("Neues Spiel");
		toolbar_neuesspiel.setActionCommand("new_game");
		/* Button ist nur aktiv wenn das Spiel gewonnen / verloren wurde */
		toolbar_neuesspiel.setEnabled(false);
		toolbar_neuesspiel.addActionListener(new ListenerToolbar());
		this.add(toolbar_neuesspiel);
		
		/* Feld Punktestand */
        punktestand = new JPanel();
        /* Rahmenbeschriftung, Tooltiptext */
        punktestand.setBorder(BorderFactory.createTitledBorder("Punktestand"));
        punktestand.setToolTipText("Zeigt den aktuellen Punktestand an");
        
        /* Punktestandtext */
        punktestandtext = new JTextField();
        /* Textfeld ist nicht editierbar, Breite von 5 Spalten, aktuellen Punktestand setzen */
        punktestandtext.setEditable(false);
        punktestandtext.setColumns(5);
        setScoreFieldText();
        
        /* Text zum Panel, Panel zur Toolbar hinzufuegen */
        punktestand.add(punktestandtext);
        this.add(punktestand);
        
        /* Munitionsanzeige */
        munition = new JPanel();
        munition.setToolTipText("Zeigt die verfuegbare Munition an");
        munition.setBorder(BorderFactory.createTitledBorder("Munition"));

        munition.setLayout(new GridLayout(1, 3, 3, 3));
        munition.setPreferredSize(new Dimension(125,30));
        for(int i=0;i<3;i++){
        	munition_arr[i] = new drawAmmoImage();
        	munition_arr[i].setPreferredSize(new Dimension(30,30));
        	
        }
        
        paintMunitionsAnzeige();
        this.add(munition);
        
        
        /* Spielfortschritt */
        spielfortschritt = new JPanel();
        /* Rahmenbeschriftung, Tooltiptext */
        spielfortschritt.setToolTipText("Zeigt den aktuellen Spielfortschritt an");
        spielfortschritt.setBorder(BorderFactory.createTitledBorder("Spielfortschritt"));
        /* Fortschrittsbalken initialisieren mit min / max Werte */
        fortschrittsbalken = new JProgressBar(0,100);
        /* Wert setzen */
        fortschrittsbalken.setValue(hauptfenster.getGameProgress());
        /* Prozentzahl anzeigen */
        fortschrittsbalken.setStringPainted(true);
        fortschrittsbalken.setPreferredSize(new Dimension(100,20));
        spielfortschritt.add(fortschrittsbalken);
        this.add(spielfortschritt);
        
        
        /*Button Settings */
		settings = new JButton("Settings");
		settings.setActionCommand("settings");
		settings.addActionListener(new ListenerToolbar());
		this.add(settings);
        
		
	}
	
	
	
	/**
	 * Methode aktualisiert die Toolbar
	 */
	public void updateToolbar(){
		setScoreFieldText();
		setProgressBarValue();

		paintMunitionsAnzeige();
	}
	

	
	
	/**
	 * Methode stellt die Munitionsanzeige in der Toolbar dar
	 */
		
	public void paintMunitionsAnzeige(){
	
		ammocount = hauptfenster.getDionaRapModel().getShootAmount(); // Anzahl an verfuegbaren Schüssen
		
		/* Hat sich die Munitionsanzahl veraendert */
		if(ammocount != this.ammoCounter){
			/*aktualisieren Zaehler + setze Array zurueck*/
			this.ammoCounter = ammocount;
			/*Munitions anzeige nochmal komplett löschen*/
			for(int i=0; i<3; i++){
				munition.remove(munition_arr[i]);
			}
			
			
			/*Anzahl an Munition > 3 -> zwei icons + anzahl wie viele schüsse insgesamt*/
			if(ammocount > 3 ){
				setToMuchAmmo(true);
				munition_arr[0] = new drawAmmoImage();	
				munition.add(munition_arr[0]);
				setToMuchAmmo(false);
				for(int i = 1; i < 3; i++){
					munition_arr[i] = new drawAmmoImage();
					munition.add(munition_arr[i]);
				}
			}
			
			/* Anzahl an Munition <= 3 && >= 0 -> zeige zwischen 1-3 Icons an */
			else if(ammocount <= 3 && ammocount >= 0){
				setToMuchAmmo(false);
				for(int i=0; i < ammocount; i++){
					munition_arr[i] = new drawAmmoImage();
					munition.add(munition_arr[i]);
				}
			}	
		}
				
		//Anzeige an Look & Feel anpassen
		munition.updateUI();
		
	}
	
	
	
	/******************** 2D Graphics ************/
	public class drawAmmoImage extends JPanel
	{
		
		String theme = hauptfenster.getTheme();
		String pathIcon = "icons"+File.separator+theme+File.separator + "ammo.png";
		boolean _toMuchAmmo = getToMuchAmmo();
		
		drawAmmoImage() {}
		
		
		public void paintComponent(Graphics g)
		{   Graphics2D g2d = (Graphics2D) g;
		   
		    
		    super.paintComponent(g);
		    

		    if(getAmmoCount() > 3 && _toMuchAmmo == true){
		    	g2d.drawString("x" + Integer.toString(getAmmoCount()), 25, 25);

		    }else{
		    	Image ammo_img = Toolkit.getDefaultToolkit().getImage(pathIcon);
		    	g2d.drawImage(ammo_img, 0 ,0 , 25, 25, this);
		    }
		    g2d.finalize();
		}
	}
	
	
	

	/**
 	* Methode um den aktuellen Punktestand zu setzen
 	*/
	public void setScoreFieldText(){
		punktestandtext.setText(String.valueOf(hauptfenster.getDionaRapModel().getScore()));
	}

	
	
	
	/**
	 * Methode um den aktuellen Fortschritt zu setzen
	 */
	public void setProgressBarValue(){
		fortschrittsbalken.setValue(hauptfenster.getGameProgress());
		
	}
	
	
	/**
	 * Methode setzt das Flag ob mehr oder weniger als 3 Schuss vorhanden sind
	 */
	public void setToMuchAmmo(boolean _trueFalse){
		toMuchAmmo = _trueFalse;
	}
	
	
	/**
	 * Methode gibt das Flag zurueck ob mehr oder weniger als 3 Schuss vorhanden sind
	 */
	public boolean getToMuchAmmo(){
		return toMuchAmmo;
	}
	
	
	/**
	 * Methode gibt die Anzahl an Schüssen zurück
	 */
	public int getAmmoCount(){
		return ammocount;
	}
	
	
	/**
	 * Methode macht die Toolbar sichtbar
	 */
	public void showToolbar(){
		this.setVisible(true);
	}
	
	
	/**
	 * Methode macht die Toolbar unsichtbar
	 */
	public void hideToolbar(){
		this.setVisible(false);
	}
	
	/**
	 * Methode gibt das Array fuer die Labels der Munitionsanzeige zurueck
	 * @return JLabel[]
	 */
	public JPanel[] getMuniJLabelArr(){
		return munition_arr;
	}
	
	
	/**
	 * Methode gibt das Panel fuer die Munitionsanzeige zurueck
	 * @return JPanel gibt das JPanel der Munitionsanzeige zurueck
	 */
	public JPanel getMuniJPanel(){
		return munition;		
	}
	
	
	
	/**
	 * Methode um den Button "Settings" zu aktivieren
	 * (aktiv wenn Spiel gewonnen / verloren wurde)
	 */
	public void setButtonSettingsEnabled() {
		settings.setEnabled(true);
	}
	
	
	/**
	 * Methode um den Button "Settings" zu deaktivieren
	 * (aktiv wenn Spiel gewonnen / verloren wurde)
	 */
	public void setButtonSettingsDisabled() {
		settings.setEnabled(false);
	}
	
	
	
	/**
	 * Methode um den Button "Neues Spiel" zu aktivieren
	 * (aktiv wenn Spiel gewonnen / verloren wurde)
	 */
	public void setButtonNSEnabled() {
		toolbar_neuesspiel.setEnabled(true);
	}
	
	
	/**
	 * Methode um den Button "Neues Spiel" zu deaktivieren
	 * (aktiv wenn Spiel gewonnen / verloren wurde)
	 */
	public void setButtonNSDisabled() {
		toolbar_neuesspiel.setEnabled(false);
	}
	
	
	
}