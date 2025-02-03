package Playlist;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	
	private Scanner sc;
	
	public Menu() {
		this.sc = new Scanner(System.in);
	}
	
	public int leituraTeclado(String texto) {
		System.out.print(texto);
		int op = sc.nextInt();
		sc.nextLine();
		return op;
	}
	
	public String leituraTecladoChar(String texto) {
		System.out.print(texto);
		String string = sc.nextLine();
		return string;
	}
	
	public String leituraTecladoNome(String texto) {
		System.out.print(texto);
		String nome = sc.nextLine();
		return nome;
	}
	
	public Musica menuAdicionarMusica() {
		String artista, nome, album, genero, duracao;
		
		System.out.println("\n\n\t\t----------Adicionar Música----------\n");
		System.out.print("Digite o nome do artista: ");
		artista = sc.nextLine();
		System.out.print("\nDigite o nome da música: ");
		nome = sc.nextLine();
		System.out.print("\nDigite o nome do álbum: ");
		album = sc.nextLine();
		System.out.print("\nDigite o nome do gênero: ");
		genero = sc.nextLine();
		System.out.print("\nDigite a duração da música(mm:ss): ");
		duracao = sc.nextLine();
		
		Musica musica = new Musica(artista+" ", " "+nome+" ", " "+album+" ", " "+genero+" ", " "+duracao);
		
		return musica;
	}
	
	public static void menuPlaylist() {
		System.out.println("----------MENU----------");
		System.out.println("1-Criar Playlist");
		System.out.println("2-Vizualizar Playlists");
		System.out.println("3-Adicionar músicas a playlist");
		System.out.println("4-Remover playlist");
		System.out.println("5-Remover música da playlist");
		System.out.println("6-Escutar musica");
		System.out.println("7-Sair");
	}
	
	// ------------------------------ PLAYER ------------------------------
	
	public void consoleEspaco() {
		for(int i = 0; i < 25; i++)
			System.out.println();
	}
	
	public void criarPlaylist(Playlist Playlists) {
		String nome = leituraTecladoNome("Informe o nome da playlist: ");
		Playlists.inserirPlaylist(new Playlist(nome));
	}
	
	public void mostrarPlaylist(int op, Playlist Playlists) {
		Playlist playTemp = Playlists.buscarPlaylist(op-1);
		if(playTemp.estaVazia()) {
			System.out.println("Sem musica registrada!");
		}else {
			System.out.println(playTemp.toString());
		}
	}
	
	public void mostrarPlaylists(Playlist Playlists) {
		System.out.println(Playlists.toStringPlaylist());
	
}
	
	public Musica adicionarMusica() {
		
		System.out.println("Informe o nome da música:");
		String nome = sc.nextLine();
		System.out.println("Informe o nome do cantor(a)/banda:");
		String cantor = sc.nextLine();
		System.out.println("Informe o gênero da música:");
		String genero = sc.nextLine();
		System.out.println("Informe o album que a música faz parte:");
		String album = sc.nextLine();
		System.out.println("Informe o tempo da música no formato mm:ss :");
		String tempo = sc.next();
		Musica musicaNova = new Musica( cantor,nome, album, genero,tempo);
		return musicaNova;
	}
	
	public void imprimeTodasMusicasPlaylist(Playlist p) {
		System.out.println(p.toString());
	}
	
	public void imprimeTodasMusicas(ArrayList<Musica> todasMusicas) {
		int x=1;
		for(Musica m:todasMusicas) {
			System.out.println((x++)+"→"+m.toString());
		}
		System.out.println("\n");
	}
	
	public void tocarMusica(Playlist Playlists, Musica musica, int posPlaylist) {

	    int opPlaylist;
	    boolean estadoMusica = true; //musica rolando
	    Playlist playTemp = null;
		playTemp = Playlists.buscarPlaylist(posPlaylist);
		do {
		playTemp.imprimeFila(musica);
	    System.out.println("=== Tocando agora ===");
	    System.out.println("🎵 Música: " + musica.getNome());
	    System.out.println("🎤 Cantor(a)/Banda: " + musica.getCantor());
	    System.out.println("⏳ Duração: " + musica.getTempo());
	    System.out.println("💽 Álbum: " + musica.getAlbum());
	    System.out.println("🎧 Gênero: " + musica.getGenero());
	    System.out.println("===============================");
	    System.out.println("\n📃 Escolha uma opção:");
        if(estadoMusica==true) {
        	System.out.println("1 - ⏸️ Pausar");
        }
        else{
        	System.out.println("1 - ▶️ Continuar");
        }
        System.out.println("2 - ⏭️ Próxima música");
        System.out.println("3 - ⏮️ Música anterior");
        System.out.println("4 - ⏹ Sair");
        System.out.println("===============================");
        opPlaylist = sc.nextInt();
        switch(opPlaylist) {
        case 1:
        	if(estadoMusica == true) {
        		System.out.println("Musica pausada...");
        		estadoMusica = false;
        	}else {
        		System.out.println("Musica rolando...");
        		estadoMusica = true;
        	}
        	break;
        case 2:
        	musica = musica.getProximo();
        	estadoMusica = true;
        	break;
        case 3:
        	musica = musica.getAnterior();
        	estadoMusica = true;
        	break;
        case 4:
        	System.out.println("Saindo...");
        	break;
        }
        
		}while(opPlaylist!=4);
	}
	
}
