
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
			ocorrencias = 0;
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
	
	public void add(String linha, ListaDeInteiros paginas) {
        Word aux = new Word(linha, paginas);
        if (head == null) {
            head = aux;
        } else {
            tail.next = aux;
        }
        tail = aux;
        count++;
    }
	
	
}

