package Playlist;

import java.io.*;

/*
 * Fiz uma classe que serve para leitura e escrita de objetos em arquivos salvos no mesmo diretório do projeto java
 * Serve para programas simples, provavelmente usado para estudos
 * Tem somente 2 métodos:
 * 1 - salvarArquivo() - Serve para salvar o objeto para o arquivo definido no construtor
 * 2 - carregarArquivo() - Serve para carregar os dados de um arquivo existente para um objeto de uma classe no programa
 */

public class ArquivoObjeto {

	private File arquivo;
	
	public ArquivoObjeto(String nomeArquivo, Object o) throws IOException {
		arquivo = new File("./" + nomeArquivo);
		if(!(arquivo.exists())) {
			arquivo.createNewFile();
			salvarArquivo(o); // Serve para inicializar o arquivo com a classe do objeto escolhido na primeira execução desse novo arquivo
		}
	}
 	
	public void salvarArquivo(Object o) {
		
		try(ObjectOutputStream arquivoEscrita = new ObjectOutputStream(new FileOutputStream(arquivo))) {
			
			arquivoEscrita.writeObject(o);
			
			arquivoEscrita.flush();
			arquivoEscrita.close();
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Object carregarArquivo() {
		
		try(ObjectInputStream arquivoLeitura = new ObjectInputStream(new FileInputStream(arquivo))) {
			
			Object o = arquivoLeitura.readObject();
			
			arquivoLeitura.close();
			
			return o;	// Lembrar de fazer typeCast para a classe do objeto desejado na chamada do método
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null; 	// Difícil chegar aqui. Para nas exceções antes
	
	}
	
}
