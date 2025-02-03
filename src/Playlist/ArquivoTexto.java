package Playlist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class ArquivoTexto {
	
	private File arquivo;

	public ArrayList<Musica> carregarArquivo(String nomeArquivo) throws FileNotFoundException {
	
		this.arquivo = new File("./"+nomeArquivo);
	
		try(BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {	// Tentamos ler o arquivo de entrada
			
			ArrayList<Musica> musicas = new ArrayList<>();
			
			String linha;
			
			while((linha = leitor.readLine()) != null) {	// Lê as linhas restantes, que são os dados das músicas, até o final do arquivo	
					
				String[] dados = linha.split(";");
				
				musicas.add(new Musica(dados[0], dados[1], dados[2], dados[3], dados[4]));
			}
			
			return musicas;	// Retornamos a lista com todos os dados das músicas do arquivo
			
		} catch(FileNotFoundException e) {
			throw e;
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void salvarArquivo(ArrayList<Musica> musicas) throws IOException {
		
		BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo));
			
		for(int i = 0; i < musicas.size(); i++) {
			escritor.write(musicas.get(i).getCantor() + ";" + musicas.get(i).getNome() + ";"
					+ musicas.get(i).getAlbum() + ";" + musicas.get(i).getGenero() + ";"
							+ musicas.get(i).getTempo() + "\n");
		}
		
		escritor.close();
	}

}
