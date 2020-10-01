
public class Plateau {
	private Ligne t[];
	private int NbPionJoueur1; //Nb pion du J1
	private int NbPionJoueur2;
	private int NbPositionPossibleJoueur1; // nb de position possible pour le J1
	private int NbPositionPossibleJoueur2;
	private int posPossiblej1[][];// contient toutes les positions ou le J1 peut jouer col dans la dim 1 lig dans la dim 2
	private int posPossiblej2[][];
	

	/* constructeur par defaut */
	Plateau() {
		t = new Ligne[1];
		t[0] = new Ligne();
	}

	/* constructeur avec deux entier comme parametre */
	Plateau(int col, int lig) {
		t = new Ligne[lig];
		for (int k = 0; k < lig; k++) {
			t[k] = new Ligne(col);
		}
	}

	public Case getXY(int x, int y) {// getter
		return t[y].getX(x);
	}

	public void setXY(int x, int y, int value) {// setter
		t[y].setX(x, value);
	}

	public int getNbPionJoueur1() {
		return NbPionJoueur1;
	}

	public int getNbPionJoueur2() {
		return NbPionJoueur2;
	}

	public int getNbPositionPossibleJoueur1() {
		return NbPositionPossibleJoueur1;
	}

	public int getNbPositionPossibleJoueur2() {
		return NbPositionPossibleJoueur2;
	}

	public int[][] getPosPossiblej1() {
		return posPossiblej1;
	}

	public int[][] getPosPossiblej2() {
		return posPossiblej2;
	}
	public String ajoutPion(int numColonne, int numLigne, int numJoueur) {// permet d'ajouter un pion

		setXY(numColonne, numLigne, numJoueur);
		retournerPion(numColonne, numLigne, 1, 0, numJoueur); // retourne les pions a droite
		retournerPion(numColonne, numLigne, -1, 0, numJoueur); // retourne les pions a gauche
		retournerPion(numColonne, numLigne, 0, 1, numJoueur); // retourne les pions au-dessus
		retournerPion(numColonne, numLigne, 0, -1, numJoueur); // retourne les pions en dessous
		retournerPion(numColonne, numLigne, 1, 1, numJoueur); // retourne les pions diagonale haut droit
		retournerPion(numColonne, numLigne, -1, 1, numJoueur); // retourne les pions diagonale haut gauche
		retournerPion(numColonne, numLigne, 1, -1, numJoueur); // retourne les pions diagonale bas droit
		retournerPion(numColonne, numLigne, -1, -1, numJoueur); // retourne les pions diagonale bas gauche
		return "OK";
	}

	public void retournerPion(int x, int y, int deplX, int deplY, int joueur) {
		boolean trouve = false;
		int posX, posY;
		if (cherchePion(x, y, deplX, deplY, joueur)) {
			posX = x + deplX;
			posY = y + deplY;
			while (!trouve) {
				System.out.println("retourner : "+posX+"/"+posY);
				if (getXY(posX, posY).getContenu() == joueur) {
					trouve = true;
				}
				setXY(posX, posY, joueur);
				posX += deplX;
				posY += deplY;
			}
		}
	}

	public String plein() {// test si le tableau est plein
		boolean plein = true;
		int taille = t[0].getTaille();
		NbPionJoueur1 = 0;
		NbPionJoueur2 = 0;
		NbPositionPossibleJoueur1 = 0;
		NbPositionPossibleJoueur2 = 0;
		posPossiblej1=new int[taille*t.length][2];
		posPossiblej2=new int[taille*t.length][2];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < t.length; j++) {
				switch (this.getXY(i, j).getContenu()) {//
				case 0:
					plein = false;
					break;
				case 1:
					NbPionJoueur1++;
					break;
				case 2:
					NbPionJoueur2++;
					break;
				default:
					break;
				}
				if (positionPossible(i, j, 1)) {
					posPossiblej1[NbPositionPossibleJoueur1][0]=i;
					posPossiblej1[NbPositionPossibleJoueur1][1]=j;
					NbPositionPossibleJoueur1++;
					//System.out.println("J1  " + i+j);
				}
				if (positionPossible(i, j, 2)) {
					posPossiblej2[NbPositionPossibleJoueur2][0]=i;
					posPossiblej2[NbPositionPossibleJoueur2][1]=j;
					NbPositionPossibleJoueur2++;
					//System.out.println("J2  " + i+j);
				}
			}
		}
	/*	if (NbPionJoueur1 == 0 || NbPositionPossibleJoueur1 == 0) {
			return "joueur1 Perdu";
		} else if (NbPionJoueur2 == 0 || NbPositionPossibleJoueur2 == 0) {
			return "joueur2 perdu";
		} else */
		if (plein == false) {
			return "partie en cours";
		} else
			return "plein";
	}

	public Boolean positionPossible(int x, int y, int joueur) {
		if (getXY(x, y).getContenu() != 0)
			return false; // position deja occupee
		if (cherchePion(x, y, 1, 0, joueur) || cherchePion(x, y, 1, 1, joueur) || cherchePion(x, y, 1, -1, joueur)
				|| cherchePion(x, y, 0, -1, joueur) || cherchePion(x, y, 0, 1, joueur)
				|| cherchePion(x, y, -1, -1, joueur) || cherchePion(x, y, -1, 0, joueur)
				|| cherchePion(x, y, -1, 1, joueur))
			return true;
		else
			return false;
	}

	public Boolean cherchePion(int x, int y, int deplX, int deplY, int joueur) {
		int posX = x + deplX;// donne la direction du test
		int posY = y + deplY;// donne la direction du test
		boolean pastrouve = true;

		if (posX >= 0 && posX < t[0].getTaille() && posY >= 0 && posY < t.length) {// verifie qu'on ne sort pas du
																					// tableau
			if (getXY(posX, posY).getContenu() == joueur || getXY(posX, posY).getContenu() == 0)
				return false; // la case a cote appartient au joueur ou est vide
			else {
				while (pastrouve) {
					posX = posX+deplX;
					posY = posY+deplY;
					System.out.println("cherchePion : "+posX+"/"+posY+"/"+deplX+"/"+deplY+"/"+t[0].getTaille()+"/"+t.length);
					if (posX >= 0 && posX < t[0].getTaille() && posY >= 0 && posY < t.length) {// verifie qu'on ne sort
																								// pas du tableau
						if (getXY(posX, posY).getContenu() == joueur) { // on a trouve un pion du joueur sur la
							return true; // trajectoire, la position est bonne

						} else {
							if (getXY(posX, posY).getContenu() == 0) // il y a une case vide entre deux, le
																		// positionnement est incorrect
								return false;
						}

					}
					else{
						return false;
					}
				}
			}
		}
		return false; // je n'ai pas trouve de pion du joueur sur la trajectoire

	}

	public int compter(int numColonne, int numLigne, int xdepl, int ydepl) {// compte le nombre de pion aligne
		int posX = numColonne + xdepl;// donne la direction du test
		int posY = numLigne + ydepl;// donne la direction du test
		int nb = 0;
		boolean memeCouleur = true;
		while (memeCouleur) {
			if (posX >= 0 && posX < t[0].getTaille() && posY >= 0 && posY < t.length) {// verifie qu'on ne sort pas du
																						// tableau
				if (this.getXY(numColonne, numLigne).getContenu() == this.getXY(posX, posY).getContenu()) {// test si le
																											// pion est
																											// du meme
																											// joueur
																											// que le
																											// precedent
					nb++;
					posX += xdepl;
					posY += ydepl;
				} else {
					memeCouleur = false;
				}
			} else {
				memeCouleur = false;
			}
		}
		return nb;
	}

	public String gagne(int numColonne, int numLigne) {// test si le pion fait gagne la partie
		int horizontal = 1;
		int vertical = 1;
		int diagonal1 = 1;
		int diagonal2 = 1;
		// on utilise la methode compter dans toutes les directions
		horizontal = horizontal + compter(numColonne, numLigne, 1, 0) + compter(numColonne, numLigne, -1, 0);// compte
																												// le
																												// nombre
																												// de
																												// pion
																												// horizontalement
		vertical = vertical + compter(numColonne, numLigne, 0, -1);// compte le nombre de pion verticalement
		diagonal1 = diagonal1 + compter(numColonne, numLigne, 1, 1) + compter(numColonne, numLigne, -1, -1);// compte le
																											// nombre de
																											// pion
																											// diagonalement
		diagonal2 = diagonal2 + compter(numColonne, numLigne, -1, 1) + compter(numColonne, numLigne, 1, -1);// compte le
																											// nombre de
																											// pion
																											// diagonalement
		if (horizontal >= 4 || vertical >= 4 || diagonal1 >= 4 || diagonal2 >= 4) {// test si il y en a au moins 4 pions
																					// d'alignes
			return "le joueur numero " + this.getXY(numColonne, numLigne).getContenu() + " a gagne";// indique le numero
																									// du gagnant
		}
		return "";
	}

	public String toString() {// retourne le contenu
		String aff = "  ";
		for (int col = 0; col < t[0].getTaille(); col++) {
			aff += (char) ((int) 'A' + col) + " ";// note le numero des colonnes
			// (int) 'A' donne le code ascii de A
			// (char) 123 donne le caractere correspondant au code ascii 123
		}
		aff += "\n";
		for (int lig = t.length - 1; lig >= 0; lig--) {
			aff += lig + " ";// note le numero des lignes
			for (int col = 0; col < t[0].getTaille(); col++) {
				aff += this.getXY(col, lig).getContenu() + " ";// rempli le tableau
			}
			aff += "\n";
		}
		return aff;
	}
}
