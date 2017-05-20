import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Texto {
	
	private class Linha {
		public String element;
		public Linha next;
		
		public Linha(String element) {
			this.element = element;
			next = null;
		}
	}
	
	private Linha head;
	private Linha tail;
	private int count;
	
	public Texto() {
		head = null;
		tail = null;
		count = 0;
	}
	
	public void add(String linha) {
        Linha aux = new Linha(linha);
        if (head == null) {
            head = aux;
        } else {
            tail.next = aux;
        }
        tail = aux;
        count++;
    }
	
	public String toString(){
		String txt = "";
		Linha aux = head;
		while(aux != null){
			txt = txt + "\n" +aux.element; 
		}
		return txt;
	}
}