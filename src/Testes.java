import java.util.regex.Pattern;

public class Testes {
	public static void main(String args[]){
		String linha = "carlos:andre.sousa?rodrigues";
		String separadores = " .,;'?!()[]{}$%#*:";
		String palavras[] = linha.split("[" + Pattern.quote(separadores) + "]");
		for(int i=0; i<palavras.length; i++){
			System.out.println(palavras[i]);
		}
	}
}
