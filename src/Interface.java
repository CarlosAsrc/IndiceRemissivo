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
		this.texto = null;
		this.indice = null;
		this.in = new Scanner (System.in);
		load();
	}
	
	public void load (){
		// (CÓDIGO DA LEITURA DO ARQUIVO E INSERÇÃO DOS DADOS NOS OBJETOS "texto" E "indice")
		System.out.println("Informe o nome do arquivo seguido de '.txt'");
		String id = in.next(); 
		Path path = Paths.get(id);
		try(Scanner sc = new Scanner (Files.newBufferedReader(path, Charset.forName("utf8")))){
			while(sc.hasNext()){
				String linha = sc.nextLine();
				texto.add(linha);
				String separadores = " .,;'?!()[]{}$%#*:";
				String palavras[] = linha.split("[" + Pattern.quote(separadores) + "]");
				
				
			}
		}
		catch(IOException e) {
			System.out.println("Arquivo não encontrado.");
			System.exit(0);
		}
				
		menu();
	}
	
	public void menu(){}
	
	
}
