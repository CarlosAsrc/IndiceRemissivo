
//CARLOS ANDRÉ
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
	private int numeroStopwords;
	private int totalPalavras;
	
	public Interface () throws IOException{
		this.stopwords = new Stopwords();
		this.texto = new Texto();
		this.indice = new IndiceRemissivo();
		this.in = new Scanner (System.in);
		numeroStopwords = 0;
		totalPalavras = 0;
		loadText();
		loadIndice();
		menu();
	}
	
	public void loadText(){
		boolean flag;
		do{
			flag = true;
			System.out.println("Informe o nome do arquivo seguido de '.txt'");
			String id = in.next(); 
			Path path = Paths.get(id);
			try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("utf8"))) {
				System.out.println("Carregando arquivo...");	  
				String line = null;
				while ((line = reader.readLine()) != null) {
					texto.add(line);
				}
			}
			catch(IOException e) {
				System.out.println("Arquivo não encontrado!");
				flag = false;
			}
		} while (flag == false);
		System.out.println("Arquivo carregado.");
	}
	
	
	public void loadIndice(){
		System.out.println("\nMontando Indice Remissivo...");	
		for(int i=0; i<texto.size(); i++){
			
			if(i == (texto.size()/4)){System.out.print("25%..");}
			if(i == (texto.size()/2)){System.out.print("\n50%..");}
			
			String line = texto.getLinha(i);
			String separadores = " .,-_;'?!()[]/{}$%#*:\"";
			String palavras[] = line.split("[" + Pattern.quote(separadores) + "]");
			for(int j=0; j<palavras.length; j++){
				//A parte mais complicada na manipulação das palavras acontece aqui:
				String palavra = palavras[j].toLowerCase();
				if(!palavra.equals("")){totalPalavras++;}
				if(!stopwords.contains(palavra)) {
					if(!indice.contains(palavra)){
						ListaDeInteiros paginas = texto.paginasDaPalavra(palavra);
						indice.addOrder(palavra, paginas);
					} 
					else {
						indice.incrementaOcorrencia(palavra);
					}
				} 
				else {
					if(!palavra.equals("")){numeroStopwords++;}
				}
			}
		}
		System.out.println("\nÍndice Remissivo completo!  "+indice.size()+" Palavras indexadas."+" Linhas: "+texto.size());
	}
	
	public void testes(){
		//RESULTADOS (ESSE MÉTODO SERÁ DELETADO E CONVERTIDO NAS OPÇÕES DO MENU):
				//System.out.println(indice.toString());
				//System.out.println("\nTotal de palavras: "+totalPalavras);
				//System.out.println("Total palavras do indice remissivo (sem repetir): "+indice.size());
				//System.out.println("total Stopwords: "+numeroStopwords);
				//System.out.println("pagina complexa: "+indice.paginaComplexa(texto.getTotalPaginas()));
				//System.out.println("palavra java na pagina 2: "+texto.pesquisarPalavra("jaVa", 2));
	}
	
	public void menu(){
		String opcao = "";
		do{
			System.out.println("\nEscolha uma das opções abaixo:"+
							   "\n1 - Exibir indice remissivo."+
							   "\n2 - Exibir percentual de stopwords no texto."+
							   "\n3 - Exibir palavra mais frequente."+
							   "\n4 - Pesquisar sobre determinada palavra."+
							   "\n5 - Ver pagina mais complexa."+
							   "\n0 - Sair.");
			opcao = in.next();
			
			switch (opcao){
			case "1": 
				//Temporario:
				System.out.println(indice.toString());
				//menuOrdemAlfabetica();
				break;
			case "2": 
				menuPercentualStopwords();
				break;
			case "3": 
				menuPalavraFrequente();
				break;
			case "4":
				menuPesquisarPalavra();
				break;
			case "5": 
				menuPaginaComplexa();
			}
		}while(!opcao.equals("0"));
	}
	
	public void menuOrdemAlfabetica(){}
	
	public void menuPercentualStopwords(){
		double percentual = (numeroStopwords * 100.0) / totalPalavras;
		System.out.println("Total de palavras: "+totalPalavras+"\nNumero de palavras que eram Stopwords: "+numeroStopwords+"\nNumero de palavras aceitas: "+(totalPalavras-numeroStopwords)+"\nPercentual de Stopwords: "+percentual+"%");
	}
	
	public void menuPalavraFrequente(){}
	
	public void menuPesquisarPalavra(){}
	
	public void menuPaginaComplexa(){}
}
