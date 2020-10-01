
public class Ligne {
private Case t[];
	
	/*constructeur par defaut*/
	Ligne(){
		t=new Case[1];
		t[0]=new Case();
	}
	
	/*constructeur avec un entier comme parametre*/
	Ligne(int n){
		t=new Case[n];
		for(int i=0;i<n;i++){
			t[i]=new Case();
		}
	}
	
	public Case getX(int i){//getter
		return t[i];
	}
	
	public void setX(int position, int valeur){//setter
		t[position].setContenu(valeur);
	}

	public void setLigne(String valInit){//setter
		t=new Case[valInit.length()];
		for (int i=0;i<valInit.length();i++){
			t[i]=new Case(Integer.parseInt(valInit.substring(i,i+1)));
		}
	}
	
	public int getTaille(){//getter
		return t.length;
	}
	
	public String toString(){//retourne le contenu
		String chaine="";
		for(int i=0;i<t.length;i++){
			if(t[i].getContenu()==0){
				chaine=chaine + ". ";
			}
			else{
				chaine=chaine + t[i].getContenu() + " ";
			}
		}
		return(chaine);
	}
}
