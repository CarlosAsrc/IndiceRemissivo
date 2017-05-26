import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Interface {
	private Stopwords stopwords;
	private Texto texto;
	private IndiceRemissivo indice;
	private Scanner in;
	
	public Interface () throws IOException{
		this.stopwords = new Stopwords();
		this.texto = new Texto();
		this.indice = new IndiceRemissivo();
		this.in = new Scanner (System.in);
		load();
	}
	
	public void load (){
		// (CÓDIGO DA LEITURA DO ARQUIVO E INSERÇÃO DOS DADOS NOS OBJETOS "texto" E "indice")
		System.out.println("Informe o nome do arquivo seguido de '.txt'");
		String id = in.next(); 
		Path path = Paths.get(id);
		try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("utf8"))) {
			System.out.println("Carregando texto..");	  
			String line = null;
			while ((line = reader.readLine()) != null) {
				texto.add(line);
			}
		}
		catch(IOException e) {
			System.out.println("Arquivo não encontrado.");
			System.exit(0);
		}
		System.out.println("Texto carregado.\n"+texto.toString());	
		System.out.println("\nMontando Indice Remissivo...");	
		for(int i=0; i<texto.size(); i++){
			String line = texto.getLinha(i);
			String separadores = " .,-;'?!()[]{}$%#*:";
			String palavras[] = line.split("[" + Pattern.quote(separadores) + "]");
			for(int j=0; j<palavras.length; j++){
				//A parte mais complicada na manipulação das palavras acontece aqui:
				String palavra = palavras[j].toLowerCase();
				if(!stopwords.contains(palavra)) {
					if(!indice.contains(palavra)){
						ListaDeInteiros paginas = texto.paginasDaPalavra(palavra);
						indice.add(palavra, paginas);
					} else{
						indice.incrementaOcorrencia(palavra);
					}
				}
			}
		}	
		System.out.println(indice.toString());
		//for(int i=0; i<indice.size(); i++){
		//	System.out.println(indice.getPalavra(i));
		//}
		//System.out.println(texto.toString());
		//menu();
	}
	
	public void menu(){}
	
	
}
