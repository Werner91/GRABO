package dionarap;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.fhwgt.dionarap.levelreader.LevelReader;
import de.fhwgt.dionarap.model.data.MTConfiguration;


/**
 * Klasse realisiert das Menu, abgeleitet von <code>JMenuBar</code>, implementiert <code>ActionListener</code>
 * 
 * @author Werner Steinbinder
 * @version Aufgabe 5
 */


public class MenuBar extends JMenuBar implements ActionListener{
	
	private Hauptfenster hauptfenster;
	
	//Flag Toolbar sichtbar
	private boolean toolbarsichtbar = true;
	//Flag Toolbar oben / unten 
	private boolean showtoolbarontop  = true;
	//Flag Navigator sichtbar
	private boolean navigatorsichtbar = true;
	//Look and Feels Array
	private UIManager.LookAndFeelInfo[] lookandfeeluimanagerinfoarray;
	//Look and Feel RadioBUtton Array
	private JRadioButtonMenuItem lookandfeelradiobuttonarray[];
	//aktiver Look and Feel Radiobutton
	private int active_radiobutton = 0;
	//Anzahl an vorhandenen Look and Feels
	private int lookandfeelcounter;
	
	///Menuleiste Elemente
	private JMenu ansicht;
	private JMenu hilfe;
	private JMenu konfiguration;
	
	//Ansicht Elemente
	private JMenu toolbarposition;
	private JMenu lookandfeelmenu;
	private JMenuItem toolbartop;
	private JMenuItem toolbarbottom;
	private JCheckBoxMenuItem toolbaranzeigen;
	private JCheckBoxMenuItem navigatoranzeigen;
	
	// Konfiguration Elemente
	private JMenuItem leveleinlesen;
	private JMenuItem spieleinstellungen;
	

	//Hilfe Elemeten
	private JMenuItem spielbeschreibung;
	
	
	/**
	 * Konstruktor der Menuleiste vom Typ <code>JMenuBar</code>
	 * @param parent Vaterfenster vom Typ <code>Hauptfenster</code>
	 */	
	public MenuBar(Hauptfenster _hauptfenster){
		
		this.hauptfenster = _hauptfenster;
		
		//Menuleistenelemente
		ansicht = new JMenu("Ansicht");
		konfiguration = new JMenu("Konfiguration");
		hilfe = new JMenu("Hilfe");
	
		/*Menu Ansicht*/
		
		//Toolbar anzeigen
		toolbaranzeigen = new JCheckBoxMenuItem("Toolbar anzeigen"); //Auswahl möglichkeit "Toolbar anzeigen"
		toolbaranzeigen.setState(true);
		toolbaranzeigen.addActionListener(this);
		ansicht.add(toolbaranzeigen); //Toolbar anzeigen zum Reiter Ansicht hinzufügen
	
		//Toolbarposition
		toolbarposition = new JMenu("Toolbar Position");
		toolbartop = new JMenuItem("oben"); //Untermenü von "Toolbar Position"
		toolbarbottom = new JMenuItem("unten"); //Untermenü von "Toolbar Position"
		toolbarposition.add(toolbartop);
		toolbarposition.add(toolbarbottom);
		toolbartop.addActionListener(this);
		toolbarbottom.addActionListener(this);
		//deaktivieren da Toolbar standardmaessig oben
		toolbartop.setEnabled(false);
		ansicht.add(toolbarposition);
		ansicht.add(new JSeparator()); // erstellt eine horizontale trennlinie
		
		//Navigator anzeigen
		navigatoranzeigen = new JCheckBoxMenuItem("Navigator anzeigen");
		navigatoranzeigen.setState(true);
		navigatoranzeigen.addActionListener(this);
		ansicht.add(navigatoranzeigen); //Unterpunkt "Navigator Anzeigen" zum Reiter Ansicht hinzufügen
		ansicht.add(new JSeparator()); //Erstellt eine Horizontale Trennlinie
		
		//Look and Feel
		lookandfeelmenu = new JMenu("Look and Feel");
		/* Frage vom UIManager alle installierten Look and Feels ab */
		lookandfeeluimanagerinfoarray = UIManager.getInstalledLookAndFeels();
		lookandfeelcounter =lookandfeeluimanagerinfoarray.length;
		lookandfeelradiobuttonarray = new JRadioButtonMenuItem[lookandfeelcounter];
		for(int i = 0; i < lookandfeelcounter; i++){
			//fuelle RadioButtonMenu mit installierten Look and Feels
			lookandfeelradiobuttonarray[i] = new JRadioButtonMenuItem(lookandfeeluimanagerinfoarray[i].getName());
			lookandfeelmenu.add(lookandfeelradiobuttonarray[i]);
			//fuege Menupunkt hinzu
			lookandfeelmenu.add(lookandfeelradiobuttonarray[i]);
			lookandfeelradiobuttonarray[i].addActionListener(this);
			//lege aktuelles look and feel fest
			if(UIManager.getLookAndFeel().getName().equals(lookandfeeluimanagerinfoarray[i].getName())){
				lookandfeelradiobuttonarray[i].setSelected(true);
				active_radiobutton = i;
			}
		}
		ansicht.add(lookandfeelmenu);
		
		
		
		/* Menu Konfiguration*/
		leveleinlesen = new JMenuItem ("Level Einlesen");
		leveleinlesen.addActionListener(this);
		konfiguration.add(leveleinlesen);
		konfiguration.add(new JSeparator());
		//Spieleinstellungen - MenuItem, Listener, deaktivieren, hinzufuegen
		spieleinstellungen = new JMenuItem("Spieleinstellungen");
		spieleinstellungen.addActionListener(this);
		spieleinstellungen.setEnabled(false);
		konfiguration.add(spieleinstellungen);
		
		
		/* Menu Hilfe */
		// Spielbeschreibung  - MenuItem, Listener, hinzufuegen
		spielbeschreibung = new JMenuItem("Spielbeschreibung");
		spielbeschreibung.addActionListener(this);
		hilfe.add(spielbeschreibung);
		
		
		/* JMenus zur Menuleiste hinzufuegen */
		this.add(ansicht);
		this.add(konfiguration);
		this.add(hilfe);
	}	
	
	/**
     * Methode, deaktiviert das "Spieleinstellungens-Element" 
     */
    public void setGameSettingsDisabled() {
        spieleinstellungen.setEnabled(false);
    }
	
	
	
	
	/**
     * Methode, setzt das "Spieleinstellungens-Element" auf aktiv
     */
    public void setGameSettingsEnabled() {
        spieleinstellungen.setEnabled(true);
    }
	
	
		
	/**
	 * Eventhandler fuer das Event <code>actionPerformed</code>,
	 * Events zu den Elementen in der Menuleisten werden verarbeitet
	 */
	
	public void actionPerformed(ActionEvent e){
		
		/* welches Menuleistenelement hat das Event ausgeloest */
		
		/* Toolbar ein- / ausblenden */
		if(e.getSource() == toolbaranzeigen){
			//toolbar ausblenden
			if(toolbarsichtbar){
				hauptfenster.getToolbar().hideToolbar();
				toolbarsichtbar = false;
				toolbarposition.setEnabled(false);
			}
			//Toolbar einblenden
			else{
				hauptfenster.getToolbar().showToolbar();
				toolbarsichtbar = true;
				toolbarposition.setEnabled(true);
			}
			hauptfenster.pack();
		}
		
		
		/* Toolbar oben positionieren */
		if(e.getSource() == toolbartop){
			showtoolbarontop = true;
			hauptfenster.setToolbarPosition(showtoolbarontop);
			toolbartop.setEnabled(false);//Ansicht->Toolbarposition->oben (deaktiveren) 
			toolbarbottom.setEnabled(true);//Ansicht->Toolbarposition->unten (aktiveren) 
			hauptfenster.pack();
		}
		
		/* Toolbar unten positionnieren */
		if(e.getSource() == toolbarbottom){
			showtoolbarontop = false;
			hauptfenster.setToolbarPosition(showtoolbarontop);
			toolbartop.setEnabled(true);//Ansicht->Toolbarposition->oben (aktiveren) 
			toolbarbottom.setEnabled(false);//Ansicht->Toolbarposition->unte (deaktiveren) 
			hauptfenster.pack();
		}
		
		
		
		/*Navigator anzeigen oder ausblenden */
		if(e.getSource() == navigatoranzeigen){
			/*Navigator ausblenden*/
			if(navigatorsichtbar){
				hauptfenster.getNavigator().hideNavigator();
				navigatorsichtbar = false;
			}
			/*Navigator einblenden*/
			else{
				hauptfenster.getNavigator().showNavigator();
				navigatorsichtbar = true;
			}
			hauptfenster.pack();
		}
		
		
		/* Look and Feel */
		/* gehe alle RadioButtons durch */
		for(int i = 0; i < lookandfeelcounter; i++){
			if(e.getSource() == lookandfeelradiobuttonarray[i]){
				lookandfeelradiobuttonarray[active_radiobutton].setSelected(false);
				active_radiobutton = i;
				try{
					UIManager.setLookAndFeel(lookandfeeluimanagerinfoarray[i].getClassName());
					SwingUtilities.updateComponentTreeUI(hauptfenster);
				}catch(ClassNotFoundException e1){
					//TODO Auto-generated catch block
					e1.printStackTrace();
				}catch(InstantiationException e1){
					//TODO Auto-generated catch block
					e1.printStackTrace();
				}catch(IllegalAccessException e1){
					//TODO Auto-generated catch block
					e1.printStackTrace();
				}catch(UnsupportedLookAndFeelException e1){
					//TODO Auto-generated catch block
					e1.printStackTrace();
				}
				hauptfenster.pack();
			}
		}
		
		/* Levelreader XML einlesen */
		if(e.getSource() == leveleinlesen){
			/*Filechooser mit XML-Filter */
			JFileChooser filechooser = new JFileChooser(Hauptfenster.getGameDirectory() + "levels");
			filechooser.setFileFilter(new XMLExtensionFileFilter("XML", new String[]{"xml"}));
			int returnvalue = filechooser.showOpenDialog(hauptfenster);
			if(returnvalue == JFileChooser.APPROVE_OPTION){
				/* lösche die Labels + icons vom Spielfeld */
				//hauptfenster.getSpielfeld().clearSpielfeld();
				hauptfenster.getSpielfeld().removeSpielfeldLabels();
				/* starte Levelreader (erwartet Instanz des DionaRap Models + MTConfiguration) und uebergebe ausgewählte Datei */
				LevelReader levelreader = new LevelReader(hauptfenster.getMTConfiguration(), hauptfenster.getDionaRapModel());
				levelreader.readLevel(filechooser.getSelectedFile().toString());
				/* neues Spielfeld + zeichne Icons */
				hauptfenster.getSpielfeld().createSchachbrett();
				hauptfenster.getSpielfeld().repaintPawns();
				/* setze neue Multithreading Konfiguration */
				hauptfenster.getDionaRapController().setMultiThreaded(hauptfenster.getDionaRapModel().getActiveConfiguration());
				/*Toolbar aktualisieren */
				hauptfenster.getToolbar().updateToolbar();
				/* Hauptfenster packen + Navigator neu positionieren (Spielfeldgroesse hat sich evtl. geändert)*/
				hauptfenster.pack();
				hauptfenster.getNavigator().setNavLocation();
				
				
				//soll nachdem ein Level eingelesen wurde beim naechsten neuen Spiel diese Daten uebernommen werden oder "standartspiel" gestartet werden?
				/* aktualisiere Spieleinstellungen mit den Einstellungen aus der XML */
				hauptfenster.updateGameSettings(
					hauptfenster.getDionaRapModel().getGrid().getGridSizeY(),
					hauptfenster.getDionaRapModel().getGrid().getGridSizeX(),
					hauptfenster.getDionaRapModel().getOpponentCount(),
					hauptfenster.getDionaRapModel().getObstacles().size()
				);
				
				hauptfenster.setFlagGameSettingsChanged();
			}
		}
		
		/* Spieleinstellungen-Dialog anzeigen */
			if(e.getSource() == spieleinstellungen){
				new Spieleinstellungen(hauptfenster);
			}
		
			
			
		/* anzeigen der Spielbeschreibung */
		if(e.getSource() == spielbeschreibung){
			new Spielebeschreibung(hauptfenster);
		}
	}
	
}