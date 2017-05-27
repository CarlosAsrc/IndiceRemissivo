import java.util.regex.Pattern;


public class Testes {
	public static void main(String args[]){
		
		String linha = "carlos:andre.sousa?rodrigues";
		String separadores = " .,;'?!()[]{}$%#*:";
		String palavras[] = linha.split("[" + Pattern.quote(separadores) + "]");
		for(int i=0; i<palavras.length; i++){
			System.out.println(palavras[i]);
		}
		
		/*String teste = "carlos andre sousa rodrigues\ncarro";
		if(teste.contains("z")){System.out.println("contem");}
		else {System.out.println("Nao contem");}
		*/
		
		/*String palavra = "andre";
		String nome = "carlos andre sousa rodrigues";
		nome = nome.replaceAll("andre", "["+palavra+"]");
		System.out.println(nome);
		*/
	}
	
	
	
	/*public ListaDeInteiros paginasDaPalavra(String palavra){
		ListaDeInteiros lista = new ListaDeInteiros();
		Linha aux = head;
		String separadores = " .,-;'?!()[]{}$%#*:";
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
	}*/
}
