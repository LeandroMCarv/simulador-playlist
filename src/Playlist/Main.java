package Playlist;

import java.io.IOException;
import java.util.*;

public class Main  {
	
	public static void main(String[] args) throws IOException {
		int op;
		
		ArrayList<Musica> todasMusicas = new ArrayList<Musica>();
		Playlist Playlists = new Playlist("");
		
		Menu menu = new Menu();
		
		ArquivoObjeto arquivoObjeto = new ArquivoObjeto("dados.dat", new Playlist(""));
		ArquivoTexto arquivoTexto = new ArquivoTexto();
		
		Playlists = (Playlist) arquivoObjeto.carregarArquivo();
		todasMusicas = arquivoTexto.carregarArquivo("musicas.txt");
		
		do {
			Menu.menuPlaylist();
			op = menu.leituraTeclado("\n\nEscolha uma opção: ");
			
			switch(op) {
				case 1:
					menu.criarPlaylist(Playlists);
					menu.leituraTecladoChar("\n\nPlaylist Criado com Sucesso!\n\nAperte qualquer letra...");
					break;
				case 2:
					if(!Playlists.estaVaziaPlaylist()) {
						menu.mostrarPlaylists(Playlists);
						int opPlaylist = menu.leituraTeclado("Escolha o número referente a  Playlist: ");
						
						menu.mostrarPlaylist(opPlaylist, Playlists);
						
						menu.leituraTecladoChar("\n\nAperte qualquer letra...");
						
					}else {
						System.out.println("Nenhuma playlist registrada!\n");
						menu.leituraTecladoChar("\n\nAperte qualquer letra...");
					}
					break;
				case 3:
					
					int opAdicionarMusica = menu.leituraTeclado("1-Adicionar musica do banco de dados \n2-Adicionar musica própria:\n");
					switch(opAdicionarMusica) {
					case 1:
						menu.mostrarPlaylists(Playlists);
						int opPlaylist = menu.leituraTeclado("Escolha o número referente a  Playlist: ");
						boolean playlistEncontrada = false;
						
								menu.imprimeTodasMusicas(todasMusicas);
								int opmusica1 = menu.leituraTeclado("Qual o numero da musica desejada?");
								Playlist playTemp = Playlists.buscarPlaylist(opPlaylist-1);
								playTemp.inserirMusica(todasMusicas.get(opmusica1-1));
								playlistEncontrada = true;
					
						if(!playlistEncontrada) {
							System.out.println("Playlist não encontrada!");
						}
						menu.leituraTecladoChar("\n\nMúsica Adicionada com Sucesso!\n\nAperte qualquer letra...");
						break;
					case 2:
						menu.mostrarPlaylists(Playlists);

						opPlaylist = menu.leituraTeclado("Escolha o número referente a  Playlist: ");
						playlistEncontrada = false;

								Musica musicaNova = menu.adicionarMusica();
								
								playTemp = Playlists.buscarPlaylist(opPlaylist-1);
								playTemp.inserirMusica(musicaNova);
								playlistEncontrada = true;
						
						if(!playlistEncontrada) {
							System.out.println("Playlist não encontrada!");
						}
						menu.leituraTecladoChar("\n\nMúsica Adicionada com Sucesso!\n\nAperte qualquer letra...");
						break;
					default:
						System.out.println("Opção inválida!!");
						break;
					}
					break;
					
				case 4:
					menu.mostrarPlaylists(Playlists);
					int opPlaylist = menu.leituraTeclado("Escolha o número referente a  Playlist: ");
					Playlist playTemp = null;
					playTemp = Playlists.buscarPlaylist(opPlaylist-1);
					Playlists.removerPlaylist(playTemp.getNome());
					
					menu.leituraTecladoChar("\n\nPlaylist Removida com Sucesso!\n\nAperte qualquer letra...");
					
					break;
				case 5:
					menu.mostrarPlaylists(Playlists);
					opPlaylist = menu.leituraTeclado("Escolha o número referente a  Playlist: ");
					playTemp = null;
					playTemp = Playlists.buscarPlaylist(opPlaylist-1);

							menu.imprimeTodasMusicasPlaylist(playTemp);
							int opmusica = menu.leituraTeclado("Qual o numero da musica desejada?");
							
							Musica musTemp = playTemp.getCabeca();
							
							for(int i = 0; i < opmusica-1; i++) {
								musTemp = musTemp.getProximo();
							}
							
							String musicaRemover = musTemp.getNome();
									
							if(playTemp.removerMusica(musicaRemover)) {
								System.out.println("Musica removida com sucesso");
								
							}else {
								System.out.println("Musica não encontrada!");
							}	
					break;
					
				case 6:
					menu.mostrarPlaylists(Playlists);
					
					int numeroPlaylist = menu.leituraTeclado("Escolha o número referente a  Playlist: ");
					
					playTemp = Playlists.buscarPlaylist(numeroPlaylist-1);
					if(playTemp.getCabeca()!=null) { //verifica se a playlist existe
						Musica musEscutar = null;
						musTemp = playTemp.getCabeca();  //Mostrar as opções de musicas 
						int contMusica = 1;
						do {
							System.out.println((contMusica++)+"→"+"Música da Playlist " + playTemp.getNome() + " : " + musTemp.getNome() + 
		                            ", Cantor: " + musTemp.getCantor() + 
		                            ", Tempo: " + musTemp.getTempo() +
		                            ", Álbum: " + musTemp.getAlbum() + 
		                            ", Gênero: " + musTemp.getGenero());
							musTemp = musTemp.getProximo();
						}while(musTemp!= playTemp.getCabeca());
						
						int numeroMusica = menu.leituraTeclado("Qual o numero da musica desejada?");
						musEscutar = playTemp.buscarMusica(numeroMusica-1);
						if(musEscutar != null) { //achou algo
							menu.tocarMusica(Playlists, musEscutar,numeroPlaylist-1);
						}else {
							System.out.println("Musica não encontrada na playlist!");
						}
					}else {
						System.out.println("Playlist nao encontrada!");					
					}
					break;
				
				case 7:
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção inválida...");
			}
			
			arquivoObjeto.salvarArquivo(Playlists);
			arquivoTexto.salvarArquivo(todasMusicas);
			
			menu.consoleEspaco();
			
		}while(op != 7);
	
	}
	
}