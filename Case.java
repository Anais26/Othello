

public class Case {
private int contenu;//creation des attributs prive
	
	/*constructeur par defaut*/
	Case(){
		contenu=0;
	} 

	/*constructeur avec un parametres*/
	Case(int nb){
		contenu=nb;
	}
	
	public int getContenu(){//getter
		return contenu;
	}

	public void setContenu(int contenu){//setter
		this.contenu=contenu;
	}
	
	public String toString(){//retourne le contenu
		if(contenu==0)
			return(".");
		else
			return("" + contenu );
	}
}

