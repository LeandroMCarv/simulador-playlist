package Playlist;

import java.io.Serializable;

public class Musica implements Serializable {

	
	private static final long serialVersionUID = 1L; //garantir que a compatibilidade se mantenha durante o processo de serialização e desserialização
	
	private String nome;
	private String cantor;
	private String tempo;
	private String album;
	private String genero;
	private Musica proximo;
	private Musica anterior;

	public Musica getAnterior() {
		return anterior;
	}

	public void setAnterior(Musica anterior) {
		this.anterior = anterior;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCantor() {
		return cantor;
	}
	
	public void setCantor(String cantor) {
		this.cantor = cantor;
	}
	
	public String getTempo() {
		return tempo;
	}
	
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	
	public Musica getProximo() {
		return proximo;
	}
	
	public void setProximo(Musica proximo) {
		this.proximo = proximo;
	}
	
	public Musica(String cantor,String nome,  String album,String genero,String tempo) {
		super();
		this.nome = nome;
		this.cantor = cantor;
		this.tempo = tempo;
		this.album = album;
		this.genero = genero;
		this.proximo = this; //apontam para si mesmo
		this.anterior = this;
	}
	
	public String toString() {
        String descricao = getCantor() + "- " + getNome() + "- " + getAlbum() + "- "
                + getGenero() + "- " + getTempo();
        return descricao;
    }
	
}
