
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
		
		public String toString(){
			return element+" ("+ocorrencias+")"+": "+paginas.toString();
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
	
	
	
	public void addOrder(String palavra, ListaDeInteiros paginas) {
        if (count == 0) {
            add(palavra, paginas);
        }
        else if (palavra.compareTo(head.element) < 0) { // insercao no inicio
            add(0, palavra, paginas);
        }
        else if (palavra.compareTo(tail.element) > 0) { // insercao no final
            add(palavra, paginas);
        } 
        else { // insercao no meio - procurar a posicao
            Word ant = head;
            Word aux = head.next;
            Word n = new Word(palavra, paginas);
            while (aux != null) {
                if (palavra.compareTo(aux.element) < 0) {
                    n.next = aux;
                    ant.next = n;
                    count++;
                    break;
                }
                ant = aux;
                aux = aux.next;
            }
        }
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
	
	public void add(int index, String palavra, ListaDeInteiros paginas) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        Word n = new Word(palavra, paginas);
        if (index == 0) { 
            n.next = head;
            head = n;
            if (tail == null) {
                tail = n;
            }
        } else if (index == count) { 
            tail.next = n;
            tail = n;
        } else {
            Word aux = head;
            for (int i = 0; i < index - 1; i++) {
                aux = aux.next;
            }
            n.next = aux.next;
            aux.next = n;
        }
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
	
	public void incrementaOcorrencia(String palavra){
		Word aux = head;
		while(aux != null){
			if(aux.element.equals(palavra)){
				aux.ocorrencias++;
				return;
			}
			aux = aux.next;
		}
	}
	
	public String getPalavra(int index){
		if(count==0 || index<0 || index > count){return null;}
		Word aux = head;
		int cont = 0;
		while(cont != index){
			aux = aux.next;
			cont++;
		}
		return aux.element;
	}
	
	public String getPalavra(String palavra){
		if(palavra.equals(null) || palavra.equals("")){return "Palavra nula.";}
		Word aux = head;
		while(aux != null){
			if(aux.element.equals(palavra)){
				return aux.toString();
			}
			aux = aux.next;
		}
		return "Palavra não encontrada!";
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
	
	public String paginaComplexa(int totalPaginas){
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
		return paginaComplexa+";"+wordsIndexadasComplexa;
	}
	
	public String toString(){
		Word aux = head;
		String resp = "\nINDICE REMISSIVO\nFormato:     Palavra (ocorrencias): |pagina|..";
		while(aux != null){
			resp = resp+"\n"+aux.toString();
			aux = aux.next;
		}
		return resp;
	}
	
	public int size(){
		return count;
	}	
	
}

