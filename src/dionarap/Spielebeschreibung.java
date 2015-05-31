package dionarap;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;



/**
 * Klasse realisiert die Darstellung der Spielbeschreibung, abgeleitet von  <code>JDialog</code>
 * 
 * @author Werner Steinbinder
 * @version Aufgabe 5
 */

public class Spielebeschreibung extends JDialog{
	
	private Hauptfenster hauptfenster;
	private URL url;
	
	/**
	 * Konstruktor der Spielebeschreibung vom Typ <code>JDialog</code>
	 * @param parent Vaterfenster vom Typ <code>Hauptfenster</code>
	 */	
	
	public Spielebeschreibung (Hauptfenster _hauptfenster){
		this.hauptfenster = _hauptfenster;
		this.setLayout(new BorderLayout());
		this.setTitle("Spielebeschreibung");
		
		String gamedirectory = Hauptfenster.getGameDirectory(); //Verzeichnis in dem sich das Projekt befindet wiedergeben
		String separator = System.getProperty("file.separator");
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false); //Schreibzugriff in den Editor deaktivieren
		/*URL Objekt erstellen */
		try{
			//Windows - file:///
			//Linux - file://
			url = new URL("file:///" + gamedirectory + "html" + separator + "spielbeschreibung.html");
			
		}catch(MalformedURLException ex){
			System.err.println("File kann nicht gelesen werden: " + url);
		}
		
		//zeige Seite an
		try{
			editorPane.setPage(url);
		
		}catch(IOException ex){
			System.err.println("File kann nicht gelesen werden: " + url);
		}
		
		/* Scrollbalken einfuegen */
		JScrollPane editorScrollPane = new JScrollPane(editorPane);
		editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(editorScrollPane, "Center");
		
		
		/*
		//Schließen button unten einfügen
		final JButton closeButton = new JButton("Schlie\u00dfen");
		closeButton.addActionListener(new ActionListener() {
		      @Override
	            public void actionPerformed(final ActionEvent arg0) {
	            	Spielebeschreibung.this.dispose();
	            }
	     });
	     this.add(closeButton, "South");            
	        
	     */
	
		
		
		
		/* Groesse, Ausrichtung + anzeigen */
		 
		this.setSize(650, 700);
		this.setLocationRelativeTo(hauptfenster);
		this.setVisible(true);
		
	}
}