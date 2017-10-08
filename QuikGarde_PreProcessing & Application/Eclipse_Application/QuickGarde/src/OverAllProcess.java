import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

		public class OverAllProcess {

			public static void main (String args[]){
				@SuppressWarnings("resource")
				Scanner reader = new Scanner (System.in);
				System.out.println("Post:");
				String myString = reader.nextLine();
				myString = myString.replaceAll("([-][_][-])","");
				myString = myString.replaceAll("[^a-zA-Z0-9'-]","");
				myString = myString.replaceAll("[ ]+","").trim();
				System.out.println("Cleaning Output:");
				System.out.println(myString);
				
				String delimeter ="";
				String [] words =myString.split(delimeter);
				String [] trimmedArray=new String[words.length];
				for (int i=0; i< words.length; i++){
					trimmedArray[i] = words[i].trim();
				}
				
				
				System.out.println("Tokenization Output:");
					for (
							int i=0; i<words.length; i++){
						System.out.println(words[i]);
			
					}
					
					Set<String> set = new HashSet<String>();
					Collections.addAll(set,trimmedArray);
					System.out.println("List of Array:");
					System.out.println(set);
					
			}
			}

