package dionarap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import de.fhwgt.dionarap.controller.DionaRapController;
import de.fhwgt.dionarap.model.objects.AbstractPawn;
import de.fhwgt.dionarap.model.objects.Obstacle;




/**
 * Klasse realisiert den Listener fuer Mausereignisse. Die Klasse ist abgeleitet
 * von  <code>MouseAdapter</code> und implementiert
 * das Interface <code>ActionListener</code>. 
 *   
 * @author Werner Steinbinder
 * @version Aufgabe 5
 */

public class ListenerMaus extends MouseAdapter implements ActionListener{
	
	private Hauptfenster hauptfenster;
	private DionaRapController DRController;
	
	private JLabel[][] labelArray;
	private int playerposition_x;
	private int playerposition_y;
	
	private JPopupMenu popupMenu;
	private JMenuItem dracula;
	private JMenuItem spaceWars;
	private JMenuItem squareHead;
	
	private String gamedirectory = Hauptfenster.getGameDirectory();
	private String separator = System.getProperty("file.separator");
	
	
	/**
     * Konstruktor der Klasse ListenerMaus
     * @param Hauptfenster
     */
	public ListenerMaus(Hauptfenster _hauptfenster){
		
		this.hauptfenster = _hauptfenster;
		
		/*Popup Menu beim rechtsklick auf das Spielfeld*/
		popupMenu = new JPopupMenu("Thema");
		/*fuege Eintraege zum Menu hinzu */
		popupMenu.add(dracula = new JMenuItem("Dracula", (Icon) new ImageIcon(gamedirectory + "icons" + separator + "Dracula" + separator + "player.gif")));
		dracula.addActionListener(this);
		popupMenu.add(spaceWars = new JMenuItem("SpaceWars", (Icon) new ImageIcon(gamedirectory + "icons" + separator + "SpaceWars" + separator + "player1.gif")));
		spaceWars.addActionListener(this);
		popupMenu.add(squareHead = new JMenuItem("SquareHead", (Icon) new ImageIcon(gamedirectory + "icons" + separator + "SquareHead" + separator + "player.gif")));
		squareHead.addActionListener(this);
		
		
		if(this.hauptfenster.getTheme().equals("Dracula")){
			dracula.setEnabled(false);
		}
		else if(this.hauptfenster.getTheme().equals("SpaceWars")){
			spaceWars.setEnabled(false);
		}
		else if(this.hauptfenster.getTheme().equals("SquareHead")){
			squareHead.setEnabled(false);
		}
	}
	
	
	/**
     * Eventhandler fuer das Event <code>actionPerformed</code>
     * setzt das gewaehlte Theme
     */
	
	public void actionPerformed(ActionEvent e){
		/* setze Theme Dracula */
		if(e.getSource() == dracula){
			hauptfenster.setTheme("Dracula");
			dracula.setEnabled(false);
			spaceWars.setEnabled(true);
			squareHead.setEnabled(true);
		}
		/* setze Theme SpaceWars */
		if(e.getSource() == spaceWars){
			hauptfenster.setTheme("SpaceWars");
			dracula.setEnabled(true);
			spaceWars.setEnabled(false);
			squareHead.setEnabled(true);
		}
		/* setze Theme Squaredhead */
		if(e.getSource() == squareHead){
			hauptfenster.setTheme("Squarehead");
			dracula.setEnabled(true);
			spaceWars.setEnabled(true);
			squareHead.setEnabled(false);
		}
	}
	
	
	/**
	 * Eventhandler fuer das Event <code>mouseClicked</code>
	 * Rechtsklick zeigt PopupMenu an, Linksklick bewegt Spieler / schiesst
	 */
	
	public void mouseClicked(MouseEvent e){
		/*Rechtsklick*/
		if(e.getButton() == 3){
			/*zeige Popupmenu an*/
			popupMenu.show(e.getComponent(), e.getX(), e.getY());
		}
		//Linksklick
		else if(e.getButton() == 1) {	
			/* groesse des Spielfelds */
			int size_x = hauptfenster.getDionaRapModel().getGrid().getGridSizeX();
			int size_y = hauptfenster.getDionaRapModel().getGrid().getGridSizeY();
			/* lege Spielfeld an */
			labelArray = new JLabel[size_x][size_y];
			labelArray = hauptfenster.getSpielfeld().getSpielfeldArray();
			/* aktuelle Spielerposition*/
			playerposition_x = hauptfenster.getPlayer().getX();
			playerposition_y = hauptfenster.getPlayer().getY();
			DRController = (DionaRapController) hauptfenster.getDionaRapController();
			/* pruefen auf welches Label der Linksklick gemacht wurde */
			for(int i = 0; i < size_y; i++){
				for(int j = 0; j < size_x; j++){
				
					if(e.getSource().equals(labelArray[i][j])){
						
						/*es wurde auf den Spieler geklickt - schiessen */
						if( i == playerposition_y && j == playerposition_x){
							DRController.shoot();
						
						//break;
						}
						/* es wurde "links unten geklickt */
						else if((playerposition_y - i == -1) && (playerposition_x - j == 1)){
						
							DRController.movePlayer(1);
							System.out.println("links unten (1)");
						}
						/*es wurde "unten"geklickt*/
						else if((playerposition_y - i == -1) && (playerposition_x - j == 0)){
							DRController.movePlayer(2);
							System.out.println("unten (2)");
						}
						/*es wurde rechts unten geklickt*/
						else if((playerposition_y - i == -1) && (playerposition_x - j == -1)){
							DRController.movePlayer(3);
							System.out.println("rechts unten (3)");
						}
						/*es wurde links geklickt*/
						else if((playerposition_y - i == 0) && (playerposition_x - j == 1)){
							DRController.movePlayer(4);
							System.out.println("links (4)");
						}
						/*es wurde rechts geklickt*/
						else if((playerposition_y - i == 0) && (playerposition_x - j == -1)){
							DRController.movePlayer(6);
							System.out.println("rechts (6)");
						}
						/*es wurde oben links geklickt*/
						else if((playerposition_y - i == 1) && (playerposition_x - j == 1)){
							DRController.movePlayer(7);
							System.out.println("links oben (7)");
						}
						/*es wurde oben geklickt*/
						else if((playerposition_y - i == 1) && (playerposition_x - j == 0)){
							DRController.movePlayer(8);
							System.out.println("oben (8)");
						}
						/*es wurde oben rechts geklickt*/
						else if((playerposition_y - i == 1) && (playerposition_x - j == -1)){
							DRController.movePlayer(9);
							System.out.println("rechts oben (9)");
						}else{
							
							System.out.println("es wurde an eine Falsche stelle geklickt");
						}
					}
				}
			}
			
			
			
			
			
		}
	}
	
	
	
	/**
	 * Methode prueft ob an der geklickten Stelle sich ein Hindernis befindet
	 * @param i x-Koordinate des Klicks
	 * @param j j-Koordinate des Klicks
	 * @return true falls sich an der geklickten Stelle ein Hindernis befindet
	 */
	private boolean isThereAnObstacle(int i, int j){
		AbstractPawn[] dionaRap_Pawns = hauptfenster.getPawns();
		for(int k=0;k < dionaRap_Pawns.length;k++){
			if(dionaRap_Pawns[k] instanceof Obstacle){
				/* erfrage Position der Figur */
				int posX = dionaRap_Pawns[k].getX();
				int posY = dionaRap_Pawns[k].getY();							
				if((posY == i) && (posX == j)){
					return true;
				}									
			}
		}
		return false;
	}
	
	
	
}



