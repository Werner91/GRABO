package dionarap;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSlider;

import de.fhwgt.dionarap.model.data.MTConfiguration;

/**
 * Klasse ermoeglicht es Spieleinstellungen vorzunehmen, abgeleitet von <code>JDialog</code>
 * 
 * @author Werner Steinbinder
 * @version Aufgabe 6
 */

public class Spieleinstellungen extends JDialog implements ActionListener{
	
	private Hauptfenster hauptfenster;
	
	private MTConfiguration conf;
	private JTextField [] text = new JTextField[7];
	private JCheckBox ch_box1_zufaelligeWartezeit;
	private JCheckBox ch_box2_GegnerMeidenHindernis;
	private JCheckBox ch_box3_GegnerMeidenGegner;
	
	private JSlider wartezeit_slider;
	private JSlider verzoegerung_slider;
	private JSlider wartezeit_vor_dem_schuss;
	
	
	
	/**
	 * Konstruktor der Spieleinstellungen vom Typ <code>JPanel</code>
	 * @param parent Vaterfenster vom Typ <code>Hauptfenster</code>
	 */
	
	public Spieleinstellungen(Hauptfenster hauptfenster){
		this.hauptfenster = hauptfenster;
		this.setTitle("Spieleinstellungen");
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(11,2)); // neues Gridlayout mit 11 Zeilen und 2 Spalten
		
		/* Textfelder, Chockbox + Buttons */
		for(int i=0; i<4; i++){
			text[i] = new JTextField();
		}
		ch_box1_zufaelligeWartezeit = new JCheckBox("Zuf�llige Wartezeit der Gegner");
		ch_box2_GegnerMeidenHindernis = new JCheckBox("Gegner meiden Kollision mit Hindernise");
		ch_box3_GegnerMeidenGegner = new JCheckBox("Gegner meiden Kollsion mit andren Gegnern");
		
		JButton button1 = new JButton("�bernehmen");
		button1.setActionCommand("uebernehmen");
		button1.addActionListener(this);
		
		JButton button2 = new JButton("Abbrechen");
		button2.setActionCommand("abbrechen");
		button2.addActionListener(this);
		
		wartezeit_slider = new JSlider();
		wartezeit_slider.setMinimum(0);    //stellt den Minimalwert auf 0 ein
		wartezeit_slider.setMaximum(10000);  //stellt den Maximalwert auf 10000 ein
		wartezeit_slider.setMinorTickSpacing(500); //Abst�nde im Feinraster
		wartezeit_slider.setMajorTickSpacing(2000); //Abst�nde im Gro�raster
		wartezeit_slider.setSnapToTicks(false);  //deaktiviert das automatische Versetzen
		wartezeit_slider.setPaintTicks(true);    //Striche werden angezeigt
		wartezeit_slider.setPaintLabels(true);  //Zahlen werden angezeigt
		wartezeit_slider.setPaintTrack(true);    //Balken wird angezeigt
		
		
		
		verzoegerung_slider = new JSlider();
		verzoegerung_slider.setMinimum(0);    //stellt den Minimalwert auf 0 ein
		verzoegerung_slider.setMaximum(10000);  //stellt den Maximalwert auf 10000 ein
		verzoegerung_slider.setMinorTickSpacing(500); //Abst�nde im Feinraster
		verzoegerung_slider.setMajorTickSpacing(2000); //Abst�nde im Gro�raster
		verzoegerung_slider.setSnapToTicks(false);  //deaktiviert das automatische Versetzen
		verzoegerung_slider.setPaintTicks(true);    //Striche werden angezeigt
		verzoegerung_slider.setPaintLabels(true);  //Zahlen werden angezeigt
		verzoegerung_slider.setPaintTrack(true);    //Balken wird angezeigt
		
		
		wartezeit_vor_dem_schuss = new JSlider();
		wartezeit_vor_dem_schuss.setMinimum(0);    //stellt den Minimalwert auf 0 ein
		wartezeit_vor_dem_schuss.setMaximum(10000);  //stellt den Maximalwert auf 10000 ein
		wartezeit_vor_dem_schuss.setMinorTickSpacing(500); //Abst�nde im Feinraster
		wartezeit_vor_dem_schuss.setMajorTickSpacing(2000); //Abst�nde im Gro�raster
		wartezeit_vor_dem_schuss.setSnapToTicks(false);  //deaktiviert das automatische Versetzen
		wartezeit_vor_dem_schuss.setPaintTicks(true);    //Striche werden angezeigt
		wartezeit_vor_dem_schuss.setPaintLabels(true);  //Zahlen werden angezeigt
		wartezeit_vor_dem_schuss.setPaintTrack(true);    //Balken wird angezeigt
		
		
		
		
		
		/*Elemente zum Panel hinzufuegen */
		panel.add(new JLabel("Wartezeit der Gegner zu Beginn: "));
		panel.add(wartezeit_slider);
		panel.add(new JLabel("Verz�gerung eines Schuess: "));
		panel.add(verzoegerung_slider);
		panel.add(new JLabel("Wartezeit eines Gegner vor jedem Schuss: "));
		panel.add(wartezeit_vor_dem_schuss);
		
		panel.add(new JLabel());
		panel.add(ch_box1_zufaelligeWartezeit);
		ch_box1_zufaelligeWartezeit.addActionListener(this);
			
		panel.add(new JLabel());
		panel.add(ch_box2_GegnerMeidenHindernis);
		
		panel.add(new JLabel());
		panel.add(ch_box3_GegnerMeidenGegner);
		
		
		
		panel.add(new JLabel("Anzahl Zeilen des Spielfeldes: "));
		panel.add(text[0]);
		panel.add(new JLabel("Anzahl Spalten des Spielfeldes: "));
		panel.add(text[1]);
		panel.add(new JLabel ("Anzahl Hindernisse: "));
		panel.add(text[2]);
		panel.add(new JLabel("Anzahl Gegner: "));
		panel.add(text[3]);
		panel.add(button1);
		panel.add(button2);
		
		/*aktuelle Werte auslesne + setzen */
		setCurrentGameSettings();
		
		/* Panel hinzufuegen, Position Groesse + Packet + anzeigen */
		this.add(panel);
		this.setLocationRelativeTo(hauptfenster);
		this.pack();
		this.setVisible(true);
		
	}
	
	
	
	/**
	 * Methode liesst die aktuellen Spieleinstellungen aus und setzt
	 * diese Werte in die Textfelder
	 */
	private void setCurrentGameSettings(){

		/* aktuelle Konfiguration auslesen */
		this.conf = this.hauptfenster.getMTConfiguration();

		
		/* Werte setzen */

		wartezeit_slider.setValue(Integer.valueOf(conf.getOpponentStartWaitTime()));
		verzoegerung_slider.setValue(conf.getShotWaitTime());
		wartezeit_vor_dem_schuss.setValue(conf.getOpponentWaitTime());
		text[0].setText(String.valueOf(hauptfenster.getDionaRapModel().getGrid().getGridSizeY()));
		text[1].setText(String.valueOf(hauptfenster.getDionaRapModel().getGrid().getGridSizeX()));
		text[2].setText(String.valueOf(hauptfenster.getHindernisse()));
		text[3].setText(String.valueOf(hauptfenster.getDionaRapModel().getOpponentCount()));

		/* Checkboxen anhaken */
		if(conf.isRandomOpponentWaitTime()){
			ch_box1_zufaelligeWartezeit.setSelected(true);
		    
		}
		if(conf.isAvoidCollisionWithObstacles()){
			ch_box2_GegnerMeidenHindernis.setSelected(true);
		}
		if(conf.isAvoidCollisionWithOpponent()){
			ch_box3_GegnerMeidenGegner.setSelected(true);
		}
	}
	
	
	
	/**
     * Eventhandler fuer das Event <code>actionPerformed</code>,
     * Events zu den Spieleinstellungen werden verarbeitet
     */
	
	
	public void actionPerformed(ActionEvent e) {
		
		/* Button Abbrechen*/
		if(e.getActionCommand() == "abbrechen"){
			Spieleinstellungen.this.dispose(); // Fenster wieder Schlie�en
		}
		
		
		/* Button Uebernehmen */
		if(e.getActionCommand() == "uebernehmen"){
			/*neue Werte setzen */
			this.conf = this.hauptfenster.getMTConfiguration();
			conf.setOpponentStartWaitTime(wartezeit_slider.getValue());
			conf.setShotWaitTime(verzoegerung_slider.getValue());
			conf.setOpponentWaitTime(wartezeit_vor_dem_schuss.getValue());
			conf.setRandomOpponentWaitTime(ch_box1_zufaelligeWartezeit.isSelected());
			conf.setAvoidCollisionWithOpponent(ch_box2_GegnerMeidenHindernis.isSelected());
			conf.setAvoidCollisionWithObstacles(ch_box3_GegnerMeidenGegner.isSelected());
			hauptfenster.updateGameSettings( //updateGameSettings(int y, int x, int opponents, int obstacles)
					Integer.parseInt(text[0].getText()), 
					Integer.parseInt(text[1].getText()),
					Integer.parseInt(text[3].getText()),
					Integer.parseInt(text[2].getText()));
			hauptfenster.setFlagGameSettingsChanged();
			
			
			/* Werte gesetze - starte neues Spiel */
			JOptionPane.showMessageDialog(Spieleinstellungen.this, "Neues Spiel mit diesen Einstellungen wird gestartet ");
			Spieleinstellungen.this.dispose();
			hauptfenster.getMenubar().setGameSettingsDisabled();
			hauptfenster.getMenubar().setLevelReaderDisabled();
			hauptfenster.startNewGame();
		}
		
		/* Checkbox "Zufaellige Wartezeit der Gegner */
		if(e.getSource() == ch_box1_zufaelligeWartezeit){
			if(ch_box1_zufaelligeWartezeit.isSelected()){
				wartezeit_slider.setEnabled(false);
				wartezeit_vor_dem_schuss.setEnabled(false);
			}else{
				wartezeit_slider.setEnabled(true);
				wartezeit_vor_dem_schuss.setEnabled(true);
			}
		}	
	}
}