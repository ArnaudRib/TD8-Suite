import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.regex.*;


public class DictionnaireIA {

	public static int k=0;
	public static Pattern pattern;
	public static Matcher matcher;
	public static boolean egal=false;
	public static Path path=Paths.get("/Users/Arnaud/Desktop/algorithmie/WorkSpace/TD8/dict.txt");
	public static String Interdit="";
	
	public static boolean find(String mot){
		Charset charset=Charset.forName("UTF-8");
		Path path= Paths.get("/Users/Arnaud/Desktop/algorithmie/WorkSpace/TD8/dict.txt");
		boolean test=false;
		try (BufferedReader reader=Files.newBufferedReader(path, charset)){
			String line=null;
			while ((line=reader.readLine())!=null){
				if (mot.equals(line)){
					test=true;
				}
			}
			reader.close();
			return test;
		}catch (IOException x){
			System.err.format("IOException: %s%n", x);
			return test;
		}
	}
	
	public static String chose(){
		Charset charset=Charset.forName("UTF-8");
		String motaleatoire="";
		int k=0, compteur=0;
		try (BufferedReader reader=Files.newBufferedReader(path, charset)){
			String line="";
			while ((line=reader.readLine())!=null){
				k++;
			}
			reader.close();
			int i= (int)(Math.random()*(k-1)); //random un chiffre dans la range du fichier	
			BufferedReader reader1=Files.newBufferedReader(path, charset);
			while ((line=reader1.readLine())!=null){
				compteur++;
				if (compteur==i){
					return line;
				}
			}
			reader1.close();
			return line;
		}catch (IOException x){
			System.err.format("IOException: %s%n", x);
			return motaleatoire;
		}
	}
	
	public static String evaluer(String motgenere, String motvoulu){
		String code="";

		if (motvoulu.length()==motgenere.length()){
			for (int i=0; i<motvoulu.length(); i++){
				if (motvoulu.charAt(i)==motgenere.charAt(i)){
					code+= "o";
				}
				if (motvoulu.charAt(i)!=motgenere.charAt(i) && motgenere.indexOf(motvoulu.charAt(i))!=-1){
					code+= "-";
				}
				if (motgenere.indexOf(motvoulu.charAt(i))==-1){
					code+="x";
				}
			}	
		}
		return code;
	}

	public static String IA(String motIA, String Motgenere){
		String Evaluation = evaluer(Motgenere,motIA);
		String Expression="";

		for (k=0; k<7;k++) {
			if (motIA.charAt(k)!='-'){
				if (Evaluation.charAt(k)=='x'){
					Interdit+=motIA.charAt(k);
				}	
			}
		}
		for (k=0; k<7;k++) {
			if (motIA.charAt(k)!='-'){
				if (Evaluation.charAt(k)=='o'){
					Expression+=motIA.charAt(k);
				}
				if (Evaluation.charAt(k)=='-'){
					Expression+="[^"+Interdit+motIA.charAt(k)+"]";
				}
				if (Evaluation.charAt(k)=='x'){
					Expression+="[^"+Interdit+"]";
				}
			}
		}
		pattern=Pattern.compile(Expression+"$");
		String testmot="";
		Charset charset=Charset.forName("UTF-8");
		try (BufferedReader reader=Files.newBufferedReader(path, charset)){
			String line=null;
			while ((line=reader.readLine())!=null){
				testmot=line;
				matcher=pattern.matcher(testmot);
				if(matcher.find()){
					break;
				}
			}
			reader.close();
		}catch (IOException x){
			System.err.format("IOException: %s%n", x);
		}
		System.out.println(motIA);
		System.out.println(evaluer(Motgenere, motIA)+"\n");
		if (motIA.equals(Motgenere)) {
			egal=true;
		}
		return testmot;
	}
	
	public static void main(String[] args) {
		String Motgenere = chose();
		System.out.println("Le mot généré aléatoirement est : "+Motgenere+"\n");
		String motIA=chose();
		int k=0;
		
		while (!egal){
			motIA=IA(motIA, Motgenere);
			k++;
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("Le mot trouvé par l'ordinateur est : " +motIA);
		System.out.println("Trouver le mot lui a pris " +k+" essais.");
		
	}

}