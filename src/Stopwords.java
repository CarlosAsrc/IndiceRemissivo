import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Stopwords {
	
	private class Word {
		public String element;
		public Word next;
		
		public Word(String element) {
			this.element = element;
			next = null;
		}
	}
	
	private Word head;
	private Word tail;
	private int count;
	
	public Stopwords() throws IOException {
		head = null;
		tail = null;
		count = 0;
		carregaDados();
	}
	
	public void carregaDados() throws  IOException{
		Path path = Paths.get("stopwords.txt");
		try(Scanner sc = new Scanner (Files.newBufferedReader(path, Charset.forName("utf8")))){
			while(sc.hasNext()){
				Word aux = new Word(sc.next());
				if (head == null) {
		            head = aux;
		        } else {
		            tail.next = aux;
		        }
		        tail = aux;
		        count++;
			}
		}
		catch(IOException e) {
			System.out.println("Stopwords não encontradas.");
			System.exit(0);
		}
	}
	
	public boolean contains(String palavra) {
		Word aux = head;
		while(aux != null) {
			if(aux.element.equals(palavra)){
				return true;
			}
			aux = aux.next;
		}
		return false;
	}
	
	public String toString() {
		Word aux = head;
		String resp="";
		while(aux != null){
			resp  = resp +"\n"+aux.element;
			aux = aux.next;
		}
		return resp;
	}
}
