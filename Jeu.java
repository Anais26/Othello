
import java.util.Scanner;
import java.awt.Font;
import MG2D.*;
import MG2D.geometrie.*;

public class Jeu {
	Plateau p;
	Fenetre f;
	Souris souris;
	int nbjoueur = 2;
	int nbCol = 0, nbLig = 0;
        Couleur couleurJ1 = new Couleur(255,255,255);
        Couleur couleurJ2 = new Couleur(0,0,0);
	Couleur couleurCase = new Couleur(100,100,200);
        int tailleCase=50;

	public int getNbCol() {
		return nbCol;
	}

	public int getNbLig() {
		return nbLig;
	}

	public Plateau getP() {
		return p;
	}

	public void setP(Plateau p) {
		this.p = p;
	}

	public Souris getSouris() {
		return souris;
	}

	public int getNbjoueur() {
		return nbjoueur;
	}

	public Couleur getCouleurJ1() {
		return couleurJ1;
	}

	public Couleur getCouleurJ2() {
		return couleurJ2;
	}

	public int getTailleCase() {
		return tailleCase;
	}

	public void init() {// permet d'initialiser de la partie
		

		Texte texteLigne = new Texte();
		texteLigne.setTexte("Combien voulez vous de lignes compris 4 et 9");
		int lig,col;
		Scanner sc = new Scanner(System.in);

		Fenetre fenetre = new Fenetre("Option Othello", 500,100);

		/*permet d'utiliser le clavier*/
		Clavier cl = fenetre.getClavier();
		Texte texteColonne = new Texte();
		Font police = new Font("Nimbus Mono L", Font.TYPE1_FONT, 13);
		texteColonne.setTexte("Combien voulez vous de colonnes  compris 4 et 9");
		texteColonne.setA(new Point(250,80));
		texteColonne.setPolice(police);
		fenetre.ajouter(texteColonne);
		Texte texteExplicatif = new Texte();
		texteExplicatif.setTexte("utiliser le pavé numérique pour choisir");
		texteExplicatif.setA(new Point(250,30));
		fenetre.ajouter(texteExplicatif);
		while(true){
		    try{
		        Thread.sleep(1);
		    }
		    catch(Exception ex){}
		    
		    if(cl.getNumpad4Tape() || cl.getQTape()){
		        lig=4;
		        break;
		    }
		    if(cl.getNumpad5Tape() || cl.getSTape()){
		        lig=5;
		        break;
		    }
		    if(cl.getNumpad6Tape() || cl.getDTape()){
		        lig=6;
		        break;
		    }
		    if(cl.getNumpad7Tape() || cl.getATape()){
		        lig=7;
		        break;
		    }
		    if(cl.getNumpad8Tape() || cl.getZTape()){
		        lig=8;
		        break;
		    }
		    if(cl.getNumpad9Tape() || cl.getETape()){
		        lig=9;
		        break;
		    }
		}
		fenetre.effacer();
		fenetre.rafraichir();
		texteLigne.setA(new Point(250,80));
		texteLigne.setPolice(police);
		fenetre.ajouter(texteLigne);
		fenetre.ajouter(texteExplicatif);
		while(true){
		    try{
		        Thread.sleep(1);
		    }
		    catch(Exception ex){}
		    if(cl.getNumpad4Tape() || cl.getQTape()){
		        col = 4;
		        break;
		    }
		    if(cl.getNumpad5Tape() || cl.getSTape()){
		        col = 5;
		        break;
		    }
		    if(cl.getNumpad6Tape() || cl.getDTape()){
		        col = 6;
		        break;
		    }
		    if(cl.getNumpad7Tape() || cl.getATape()){
		        col = 7;
		        break;
		    }
		    if(cl.getNumpad8Tape() || cl.getZTape()){
		        col = 8;
		        break;
		    }
		    if(cl.getNumpad9Tape() || cl.getETape()){
		        col = 9;
		        break;
		    }
		}
		p = new Plateau(col, lig);
		nbCol = col;
		nbLig = lig;
		fenetre.fermer();
		f = new Fenetre("Othello", nbCol*tailleCase, nbLig*tailleCase+tailleCase);
		souris = f.getSouris(); 
		for(int j=0;j<nbLig;j++){
		    for(int i=0;i<nbCol;i++){
		        Point point = new Point(i*tailleCase,j*tailleCase);
		        Carre carre=new Carre(couleurCase,point,tailleCase);
		        f.ajouter(carre);
		    }
		}
		
		// placer les pions de depart
		p.setXY(col / 2-1, lig / 2, 1);//haut gauche
		p.setXY(col / 2 , lig / 2 -1, 1);//bas droite
		p.setXY(col / 2-1, lig / 2 - 1, 2);//bas gauche
		p.setXY(col / 2 , lig / 2, 2);//haut droite
		afficherPlateau(1);
	}

	public void afficherPlateau(int numJoueur){
		Point centrePion;
		Cercle pion;
		int nb;
		Couleur couleur;
		f.effacer();
		f.rafraichir();
		for(int j=0;j<nbLig;j++){
		    for(int i=0;i<nbCol;i++){
		        Point point = new Point(i*tailleCase,j*tailleCase);
		        Carre carre=new Carre(couleurCase,point,tailleCase);
		        f.ajouter(carre);
		    }
		}
                Texte joueur = new Texte();
		if(numJoueur==1){
	                joueur.setTexte("Joueur blanc de jouer");
		} else{
	                joueur.setTexte("Joueur noir de jouer");
		}
                joueur.setA(new Point((nbCol*tailleCase)/2,nbLig*tailleCase+(tailleCase/2)));
		f.ajouter(joueur);
		for(int i=0;i<nbLig;i++){
			for(int j=0;j<nbCol;j++){
				nb=p.getXY(j,i).getContenu();
				if(nb!=0){
				    centrePion=new Point(j*tailleCase+(tailleCase/2), i*tailleCase+(tailleCase/2));
				    if(nb==1){couleur=couleurJ1;}
				    else{couleur=couleurJ2;}
				    pion=new Cercle(couleur,centrePion,(int)(tailleCase*0.7)/2,true);
				    f.ajouter(pion);
				}
			}
		}
	}

	public static void main(String[] args) {
		boolean pasfini = true, positionIncorrect = true;
		int joueurEnCours = 1;
		int col = 0, lig = 0;
		String reponse, saisie;
		Jeu j = new Jeu();
		char colEnLettre, ligEnLettre;
		j.init();// initialisation de la partie
		Souris souris=j.getSouris();
		Point pos;
		while (pasfini) {
			System.out.println(j.getP().toString());
			positionIncorrect = true;
			while (positionIncorrect) {
				/*System.out.println("joueur " + joueurEnCours + " choisissez votre position (ex : d3)");// demande au //
																										// de jouer
				Scanner sc = new Scanner(System.in);// recuperation de la reponse
				saisie = sc.next();
				colEnLettre = (saisie.toUpperCase().charAt(0)); // je recupere le 1er caractere apres avoir transformer
																// en majuscule
				col = (int) colEnLettre - (int) 'A'; // je transforme la lettre en numero de colonne
				ligEnLettre = saisie.charAt(1);

				lig = (int) ligEnLettre - (int) '0';// je transforme ligEnLettre en numero de colonne*/
				while(!souris.getClicGauche()){
					try{
						Thread.sleep(1);
					}
					catch(Exception ex){}
				}
				//System.out.println("j'ai cliqué");
				pos=souris.getPosition();
				//System.out.println(pos.getX()+","+pos.getY());
				col=pos.getX()/j.tailleCase;
				lig=pos.getY()/j.tailleCase;
				System.out.println("col = " + col + "lig = " + lig);
				if (col >= 0 && col < j.getNbCol() && lig >= 0 && lig < j.getNbLig()) {
					positionIncorrect = false;
					if (j.getP().positionPossible(col, lig, joueurEnCours) == false) {
						System.out.println("Vous ne pouvez pas jouer la");
						positionIncorrect = true;
					} else {
						System.out.println("Je pose votre pion");
					}
				} else {
					System.out.println("Vous etes en dehors du tableau");
				}

			}
			j.getP().ajoutPion(col, lig, joueurEnCours);// ajoute un pion et recois la reponse
			joueurEnCours = (joueurEnCours + 1) % j.getNbjoueur();// changement de joueur
			if (joueurEnCours == 0) {// permet d'eviter qu'un joueur est nommee 0 qui est le nombre quand il n'y a
										// pas de pion
				joueurEnCours = j.getNbjoueur();
			}
			j.afficherPlateau(joueurEnCours);
			System.out.println(j.getP());// affiche le tableau

			reponse = j.getP().plein();
			if (reponse != "partie en cours") {
				pasfini = false;
				System.out.println(reponse);
				Fenetre fenetreVictoire= new Fenetre("victoire",400,50);
				Texte victoire = new Texte();
				if(j.getP().getNbPionJoueur1()>j.getP().getNbPionJoueur2()){
					victoire.setTexte("victoire du joueur blanc");
				} else{
					victoire.setTexte("victoire du joueur noir");
				}
				victoire.setA(new Point(200,25));
                 		fenetreVictoire.ajouter(victoire);
			} else if (joueurEnCours == 1 && (j.getP().getNbPionJoueur1() == 0)) {
				System.out.println("Joueur 1 perdu");
				pasfini = false;
				Fenetre fenetreVictoire= new Fenetre("victoire",400,50);
				Texte victoire = new Texte();
				victoire.setTexte("Joueur blanc perdu");
				victoire.setA(new Point(200,25));
                 		fenetreVictoire.ajouter(victoire);
			} else if (joueurEnCours == 1 && j.getP().getNbPositionPossibleJoueur1() == 0){
				joueurEnCours = (joueurEnCours + 1) % j.getNbjoueur();// changement de joueur
				if (joueurEnCours == 0) {// permet d'eviter qu'un joueur est nommee 0 qui est le nombre quand il n'y a
											// pas de pion
					joueurEnCours = j.getNbjoueur();
				}
			} else if (joueurEnCours == 2 && (j.getP().getNbPionJoueur2() == 0)) {
				System.out.println("Joueur 2 perdu");
				pasfini = false;
				Fenetre fenetreVictoire= new Fenetre("victoire",400,50);
				Texte victoire = new Texte();
				victoire.setTexte("Joueur noir perdu");
				victoire.setA(new Point(200,25));
                 		fenetreVictoire.ajouter(victoire);
			} else if (joueurEnCours == 2 && j.getP().getNbPositionPossibleJoueur2() == 0){
				joueurEnCours = (joueurEnCours + 1) % j.getNbjoueur();// changement de joueur
				if (joueurEnCours == 0) {// permet d'eviter qu'un joueur est nommee 0 qui est le nombre quand il n'y a
											// pas de pion
					joueurEnCours = j.getNbjoueur();
				}
			}

			j.afficherPlateau(joueurEnCours);

		}

	}
}
