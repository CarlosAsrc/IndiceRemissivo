import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Texto {
	
	private class Linha {
		public String element;
		public Linha next;
		public int pagina;
		
		public Linha(String element, int pagina) {
			this.element = element;
			next = null;
			this.pagina = pagina;
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
		int pagina = (count / 40)+1;
        Linha aux = new Linha(linha, pagina);
        if (head == null) {
            head = aux;
        } else {
            tail.next = aux;
        }
        tail = aux;
        count++;
    }
	
	public String getLinha(int index){
		if(count==0 || index<0 || index > count){}
		Linha aux = head;
		int cont = 0;
		while(cont != index){
			aux = aux.next;
			cont++;
		}
		return aux.element;
	}
	
	
	public ListaDeInteiros paginasDaPalavra(String palavra){
		ListaDeInteiros lista = new ListaDeInteiros();
		Linha aux = head;
		String separadores = " .,;'?!()[]{}$%#*:";
		String [] palavrasSeparadas;
		while(aux != null){
				palavrasSeparadas = aux.element.split("[" + Pattern.quote(separadores) + "]");
				String linhaComPalavras = "";
				for(int i=0; i<palavrasSeparadas.length; i++){
					linhaComPalavras = palavrasSeparadas[i] + " ";
				}
				
				if(linhaComPalavras.contains(palavra)){
					lista.add(aux.pagina);
					int paginaAnterior = aux.pagina;
					while(aux != null && aux.pagina == paginaAnterior){
						aux = aux.next;
					}
				} else {
					aux = aux.next;
				}	
			}
			return lista;
		}
	
	public int size(){
		return count;
	}
	
	public String toString(){
		String txt = "";
		Linha aux = head;
		while(aux != null){
			txt = txt + "\n" +aux.element;
			aux = aux.next;
		}
		return txt;
	}
}