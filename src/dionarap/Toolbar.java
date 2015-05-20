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
* @version Aufgabe 4
*/


public class Toolbar extends JToolBar{
	
	
	private static final long serialVersionUID = 1L;
	
	
	private Hauptfenster hauptfenster;
	private JButton toolbar_neuesspiel;
	private JButton settings;
	private JPanel punktestand;
	private JTextField punktestandtext;
	private JPanel munition;
	private JLabel munition_arr[] = new JLabel[3];;
	private JProgressBar fortschrittsbalken;
	private JPanel spielfortschritt;
	
	
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
        for(int i=0;i<3;i++){
        	munition_arr[i] = new JLabel();
        	munition_arr[i].setPreferredSize(new Dimension(30,30));
        }
        
        //new Smilie();
        
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
		/* Button ist nur aktiv wenn das Spiel gewonnen / verloren wurde */
		settings.setEnabled(false);
		settings.addActionListener(new ListenerToolbar());
		this.add(settings);
        
		
	}
	
	
	
	/**
	 * Methode aktualisiert die Toolbar
	 */
	public void updateToolbar(){
		setScoreFieldText();
		setProgressBarValue();
		//new Smilie();
		paintMunitionsAnzeige();
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
	
	
	
	/**
	 * Methode stellt die Munitionsanzeige in der Toolbar dar
	 */
	
	
	
	public void paintMunitionsAnzeige(){
		String theme = hauptfenster.getTheme();
		String pathIcon = "icons"+File.separator+theme+File.separator + "ammo.png";
		ImageIcon icon_munition = new ImageIcon(pathIcon);
		
		
		int ammocount = hauptfenster.getDionaRapModel().getShootAmount();
		
		// Anzahl an Munition < 0 -> unendlich -> zeige alle Icons an 
		if(ammocount < 0){				
			munition_arr[0].setBorder(null);
			munition.add(munition_arr[0]);
			for(int i=0;i<3;i++){
				munition_arr[i].setIcon(icon_munition);
				munition_arr[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				munition.add(munition_arr[i]);
		
			}
		}
		
	}
	
	
	
	/*
	private class Smilie extends JPanel
	{
		Smilie() {}
		
		public void paintComponent(Graphics g)
		{   Graphics2D g2d = (Graphics2D) g;
		    int x = 20;
		    int y = 20;
            int abstand = 0;
		    
		    super.paintComponent(g);
		    // Smilie
			g2d.drawOval(x-10,y-10,20,20);
			g2d.setColor(Color.yellow);
			g2d.fillOval(x-10,y-10,20,20);
			// Augen
			g2d.setColor(Color.black);
			g2d.fillRect(x-6,y-5,4,5);
			g2d.fillRect(x+3,y-4,4,5);
	       // Mund
			g2d.drawArc(x-7,y-7,14,14,225,100); 
			
			
		}	
		
	}
	
	
	
	
	public void paintChildren(Graphics g){
		
		 Graphics2D g2d = (Graphics2D) g;
		    int x = 250;
		    int y = 20;
		    int abstand = 0;
		    
		    int ammocount = hauptfenster.getDionaRapModel().getShootAmount();
		    super.paintComponent(g);
			// Anzahl an Munition < 0 -> unendlich -> zeige alle Icons an 
			if(ammocount < 0){				
				munition_arr[0].setBorder(null);
				munition.add(munition_arr[0]);
				for(int i=0;i<3;i++){
					//munition_arr[i].setIcon(icon_munition);
					munition_arr[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					//x = munition_arr[i].getX();
					//y = munition_arr[i].getY();
					
					//munition.add(munition_arr[i]);
		    
					super.paintChildren(g);
		    // Smilie
			g2d.drawOval(x,y,20,20);
			g2d.setColor(Color.yellow);
			g2d.fillOval(x,y,20,20);
			// Augen
			g2d.setColor(Color.black);
			g2d.fillRect(x-6,y-5,4,5);
			g2d.fillRect(x+3,y-4,4,5);
	        // Mund
			g2d.drawArc(x-7,y-7,14,14,225,100); 
			//abstand += 15;
			//x += abstand;
		
			munition.add(munition_arr[i]);
				}
			}
	}
	//}
	'/
	


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
	
	
}