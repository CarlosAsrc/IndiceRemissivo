import java.io.IOException;

public class Interface {
	private Stopwords stopwords;
	private Texto texto;
	private IndiceRemissivo indice;
	
	public Interface () throws IOException{
		this.stopwords = new Stopwords();
		this.texto = null;
		this.indice = null;
		load();
	}
	
	public void load (){
		// (C�DIGO DA LEITURA DO ARQUIVO E INSER��O DOS DADOS NOS OBJETOS "texto" E "indice")
		
		menu();
	}
	
	public void menu(){}
	
	
}
