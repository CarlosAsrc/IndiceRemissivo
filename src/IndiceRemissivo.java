
public class IndiceRemissivo {
	
	private class Word {
		public String element;
		public ListaDeInteiros paginas;
		public Word next;
		public int ocorrencias;
		
		public Word(String element, ListaDeInteiros paginas) {
			this.element = element;
			this.paginas = paginas;
			paginas = null;
			next = null;
			ocorrencias = 1;
		}
	}
		
	private Word head;
	private Word tail;
	private int count;
	
	public IndiceRemissivo() {
		head = null;
		tail = null;
		count = 0;
	}
	
	public void add(String palavra, ListaDeInteiros paginas) {
		Word aux = new Word(palavra, paginas);
        if (head == null) {
            head = aux;
        } else {
            tail.next = aux;
        }
        tail = aux;
        count++;
    }
	
	public boolean contains(String palavra){
		if(palavra == null){return false;}
		Word aux = head;
		while(aux != null){
			if(aux.element.equals(palavra)){
				return true;
			}
			aux = aux.next;
		}
		return false;
	}
	
	public Word getWord(String element){
		Word aux = head;
		while(aux != null){
			if(aux.element.equals(element)){
				return aux;
			}
			aux = aux.next;
		}
		return null;
	}
	
	public void incrementaOcorrencia(String element){
		Word aux = head;
		while(aux != null){
			if(aux.element.equals(element)){
				aux.ocorrencias++;
				return;
			}
			aux = aux.next;
		}
	}
	
	public String getPalavra(int index){
		if(count==0 || index<0 || index > count){}
		Word aux = head;
		int cont = 0;
		while(cont != index){
			aux = aux.next;
			cont++;
		}
		return aux.element;
	}
	
	public String palavraMaisFrequente(){
		Word aux = head;
		Word maior = aux;
		while(aux != null){
			if(aux.ocorrencias > maior.ocorrencias){
				maior = aux;
			}
			aux = aux.next;
		}
		return maior.element +" ("+ maior.ocorrencias+" vezes)";
	}
	
	public int paginaComplexa(int totalPaginas){
		Word aux;
		int paginaComplexa = 1;
		int wordsIndexadasAtual;
		int wordsIndexadasComplexa = 0;
		for(int i=1; i <= totalPaginas; i++){
			wordsIndexadasAtual = 0;
			aux = head;
			while(aux != null){
				if(aux.paginas.contains(i)){
					wordsIndexadasAtual++;
				}
				aux = aux.next;
			}
			if(wordsIndexadasAtual > wordsIndexadasComplexa){
				wordsIndexadasComplexa = wordsIndexadasAtual;
				paginaComplexa = i;
			}
		}
		return paginaComplexa;
	}
	
	
	
	public String toString(){
		Word aux = head;
		String resp = "\nINDICE REMISSIVO\nFormato:     Palavra (ocorrencias): |pagina|..";
		while(aux != null){
			resp = resp+"\n"+aux.element+"("+aux.ocorrencias+")"+": "+aux.paginas.toString();
			aux = aux.next;
		}
		return resp;
	}
	
	public int size(){
		return count;
	}
	
}

