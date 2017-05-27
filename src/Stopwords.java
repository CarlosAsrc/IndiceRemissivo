import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Stopwords {
	
	private class StopWord {
		public String element;
		public StopWord next;
		
		public StopWord(String element) {
			this.element = element;
			next = null;
		}
	}
	
	private StopWord head;
	private StopWord tail;
	private int count;
	
	public Stopwords() throws IOException {
		head = null;
		tail = null;
		count = 0;
		carregaDados();
	}
	
	public void carregaDados() throws  IOException{
		StopWord aux = new StopWord("");
		head = aux; tail = head; count++;
		Path path = Paths.get("stopwords.txt");
		try(Scanner sc = new Scanner (Files.newBufferedReader(path, Charset.forName("utf8")))){
			while(sc.hasNext()){
				 aux = new StopWord(sc.next());
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
		StopWord aux = head;
		while(aux != null) {
			if(aux.element.equalsIgnoreCase(palavra)){
				return true;
			}
			aux = aux.next;
		}
		return false;
	}
	
	public String toString() {
		StopWord aux = head;
		String resp="";
		while(aux != null){
			resp  = resp +"\n"+aux.element;
			aux = aux.next;
		}
		return resp;
	}
}
