package Playlist;


import java.io.Serializable;


public class Playlist implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private Musica cabeca;
	private String nome;
	private Playlist proximo;
	private Playlist anterior;
	private Playlist cabecaPlaylist;
	
	
	public Playlist getCabecaPlaylist() {
		return cabecaPlaylist;
	}


	public void setCabecaPlaylist(Playlist cabecaPlaylist) {
		this.cabecaPlaylist = cabecaPlaylist;
	}


	public Playlist getProximo() {
		return proximo;
	}


	public void setProximo(Playlist proximo) {
		this.proximo = proximo;
	}


	public Playlist getAnterior() {
		return anterior;
	}


	public void setAnterior(Playlist anterior) {
		this.anterior = anterior;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Musica getCabeca() {
		return cabeca;
	}


	public void setCabeca(Musica cabeca) {
		this.cabeca = cabeca;
	}

	public Playlist(String nome) {  //criar uma playlist
		setCabeca(null);
		this.nome = nome;
		this.proximo = this;
		this.anterior = this;
		setCabecaPlaylist(null);
	}
	
	
	public boolean estaVaziaPlaylist() {
		return this.cabecaPlaylist == null;
	}
	
	public boolean estaVazia() {
		return this.cabeca == null;
	}
	
	public void inserirMusica(Musica novaMusica) { //inserir musica na playlist
		if(estaVazia()) {
			setCabeca(novaMusica);
		}else {
			Musica musUltima = this.cabeca.getAnterior();
			novaMusica.setProximo(this.cabeca);
			novaMusica.setAnterior(musUltima);
			musUltima.setProximo(novaMusica);
			this.cabeca.setAnterior(novaMusica);
			}
			
		}
	
	public void inserirPlaylist(Playlist novaPlaylist) {
        if (estaVaziaPlaylist()) {
            setCabecaPlaylist(novaPlaylist);
        } else {
            Playlist playUltima = this.cabecaPlaylist.getAnterior();
            novaPlaylist.setProximo(this.cabecaPlaylist);
            novaPlaylist.setAnterior(playUltima);
            playUltima.setProximo(novaPlaylist);
            this.cabecaPlaylist.setAnterior(novaPlaylist);
        }
    }
	
	
	public Musica buscarMusica(String nome) {
	    if (estaVazia()) {
	        return null;
	    }
	    Musica musBuscada = this.getCabeca();
	    do {
	        if (musBuscada.getNome().equals(nome)) {
	            return musBuscada; // retorna se encontrar a música
	        }
	        musBuscada = musBuscada.getProximo();
	    } while (musBuscada != this.cabeca); // garantir  que percorre até a cabeça

	    return null; // se não encontrar
	}
	
	public Playlist buscarPlaylist(String nome) {
        if (estaVaziaPlaylist()) {
            return null;
        }
        Playlist playBuscada = this.getCabecaPlaylist();
        do {
            if (playBuscada.getNome().equalsIgnoreCase(nome)) {
                return playBuscada;
            }
            playBuscada = playBuscada.getProximo();
        } while (playBuscada != this.cabecaPlaylist);
        return null;
    }
	
	public Musica buscarMusica(int numero) {
	    if (estaVazia()) {
	        return null;
	    }
	    Musica musBuscada = this.getCabeca();
	    int procura = 0;
	    do {
	    	if(procura==numero) {
	    		return musBuscada;
	    	}
	        musBuscada = musBuscada.getProximo();
	        procura++;
	    } while (musBuscada != this.cabeca); // garantir  que percorre até a cabeça

	    return null; // se não encontrar
	}
	
	public Playlist buscarPlaylist(int numero) {
		if(estaVaziaPlaylist()) {
			
			return null;
		}
		Playlist playBuscada = this.cabecaPlaylist;
		int procura = 0;
		do {
			if(procura == numero) {
				return playBuscada;
			}
			playBuscada = playBuscada.getProximo();
			procura++;
		}while(playBuscada != this.cabecaPlaylist);
		return null;
	}
	
	public boolean removerPlaylist(String nome) {
        if (estaVaziaPlaylist()) {
            return false;
        }
        Playlist playRemover = buscarPlaylist(nome);
        if (playRemover != null) {
            if (playRemover == this.cabecaPlaylist && playRemover.getProximo() == this.cabecaPlaylist) {
                this.cabecaPlaylist = null; // Lista ficou vazia
            } else if (playRemover == this.cabecaPlaylist) {
                Playlist ultima = this.cabecaPlaylist.getAnterior();
                this.cabecaPlaylist = this.cabecaPlaylist.getProximo();
                this.cabecaPlaylist.setAnterior(ultima);
                ultima.setProximo(this.cabecaPlaylist);
            } else {
                Playlist anterior = playRemover.getAnterior();
                Playlist proximo = playRemover.getProximo();
                anterior.setProximo(proximo);
                proximo.setAnterior(anterior);
            }
            playRemover.setProximo(null);
            playRemover.setAnterior(null);
            return true;
        }
        return false;
    }
	
	
	
	
	
	public boolean removerMusica(String nome) {
		Musica remMus = this.cabeca;
		if(!estaVazia()) {
			if(remMus.getNome().equalsIgnoreCase(nome)){ //valor esta no inicio
				if(remMus.getProximo() == this.cabeca) { //so tem a cabeca na playlist
					this.cabeca = null;
					return true;
				}else { //mais de uma elemento na playlist -- remover o primeiro
					Musica ultimo = this.cabeca.getAnterior();
					setCabeca(this.cabeca.getProximo());
					cabeca.setAnterior(ultimo); // nova cabeca aponta para o utlimo
					ultimo.setProximo(cabeca); //ultimo aponta para a nova cabeça
					remMus.setProximo(null);
					remMus.setAnterior(null);
					return true;
				}
			}else { //se n for a cabeça
				remMus = buscarMusica(nome);
				if(remMus != null) { //se encontrei a musica
					Musica musAnt = remMus.getAnterior();
					if(remMus.getProximo() != cabeca) { //ta no meio da lista
						musAnt.setProximo(remMus.getProximo());
						remMus.getProximo().setAnterior(musAnt);
						remMus.setProximo(null);
						remMus.setAnterior(null);
						return true;
					}else { //final da lista
						musAnt.setProximo(this.cabeca);
						this.cabeca.setAnterior(musAnt);
						remMus.setProximo(null);
						remMus.setAnterior(null);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void imprimeFila(Musica musica) {
	    int x = 1;
	    
	    if (musica == null || musica.getProximo() == null) {
	        System.out.println("A playlist está vazia ou incompleta.");
	        return;
	    }

	    System.out.println("\n\n==============FILA==============");
	    Musica musTemp = musica;

	    do {
	        System.out.println((x++) + "-> " + musTemp.getNome());
	        musTemp = musTemp.getProximo();  // Passa para a próxima música
	    } while (musTemp != musica);  // Continua até que a música atual seja tocada novamente

	    System.out.println("================================\n");
	}
	

	public String toString() {
		if(this.cabeca == null) {
			return "";
		}
		
		String retorno = "";
		Musica musTemp = this.cabeca;
		int i = 1;
		do {
			retorno += (i++) + "→" + musTemp.getCantor() + "- " + musTemp.getNome() + "- " + musTemp.getAlbum() + "- "
	                + musTemp.getGenero() + "- " + musTemp.getTempo() + "\n";
			musTemp = musTemp.getProximo();
		}while (musTemp != cabeca);
		
		return retorno;
	}
	
	public String toStringPlaylist() {
        if (this.cabecaPlaylist == null) {
            return "";
        }
        String retorno = "";
        Playlist playTemp = this.cabecaPlaylist;
        int i = 1;
        do {
            retorno += (i++) + "→" + playTemp.getNome() + "\n";
            playTemp = playTemp.getProximo();
        } while (playTemp != this.cabecaPlaylist);
        return retorno;
    }
	
	
	
}
