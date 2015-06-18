package dionarap;

import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import de.fhwgt.dionarap.model.data.MTConfiguration;

/**
 * Settingsfenster um die gewählten Spieleinstellungen abzufragen, abgeleitet von <code>JFrame</code>
 * 
 * @author Werner Steinbinder
 * @version Aufgabe 6
 * 
*/



public class Settings extends JFrame{
	
	private Hauptfenster hauptfenster;
	private MTConfiguration conf;
	private HashMap<String, String> map;
	private JTable table_settings;
	private int iterator;
	private Object data [][];
	
	public Settings(Hauptfenster _hauptfenster){
		
		hauptfenster = _hauptfenster;
		conf = hauptfenster.getMTConfiguration();
		this.setTitle("Settings");
		map  = new HashMap<String, String>();
		this.init_Settings_window();
		
		String ueberschrift [] = {"Name", "Wert"};
		table_settings = new JTable(data, ueberschrift);
		
		this.add(new JScrollPane(table_settings));
		this.setLocationRelativeTo(hauptfenster);
		this.setSize(300, 300);
		this.pack();
		this.setVisible(true);
	}
	
	
	public void  init_Settings_window(){
		
		map.put("Wartezeit der Gegner zu Beginn", String.valueOf(conf.getOpponentStartWaitTime()));
		map.put("Verzögerung eines Schuess", String.valueOf(conf.getShotWaitTime()));
		map.put("Wartezeit eines Gegner vor jedem Schuss", String.valueOf(conf.getOpponentWaitTime()));
		map.put("Anzahl Zeilen des Spielfeldes", String.valueOf(hauptfenster.getDionaRapModel().getGrid().getGridSizeY()));
		map.put("Anzahl Spalten des Spielfeldes", String.valueOf(hauptfenster.getDionaRapModel().getGrid().getGridSizeX()));
		map.put("Anzahl Hindernisse", String.valueOf(hauptfenster.getHindernisse()));
		map.put("Anzahl Gegner", String.valueOf(hauptfenster.getDionaRapModel().getOpponentCount()));
		
		if(conf.isRandomOpponentWaitTime()){
			map.put("Zufällige Wartezeit der Gegner", "EIN");
		}else{
			map.put("Zufällige Wartezeit der Gegner", "AUS");
		}
		
		if(conf.isAvoidCollisionWithObstacles()){
			map.put("Gegner meiden Hindernise", "EIN");
		}else{
			map.put("Gegner meiden Hindernise", "AUS");
		}
		
		
		if(conf.isAvoidCollisionWithOpponent()){
			map.put("Gegner meiden Kollision mit anderen Gegnern", "EIN");
		}else{
			map.put("Gegner meiden Kollision mit anderen Gegnern", "AUS");
		}
		
		data = new Object[map.size()][2];
		for(Map.Entry<String, String> entry : map.entrySet()){
			data[iterator][0] = entry.getKey();
			data[iterator][1] = entry.getValue();
			iterator++;
		}	
	}
}