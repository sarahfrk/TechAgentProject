package Traitement;

import java.io.*;
import java.util.*;

//./Vendeurs/Historique/HVendeur0.txt
//./Vendeurs/Historique/THVendeur.txt

public class TriFichier {
	public static void trier() throws IOException {
		String inputFile = "./Vendeurs/Historique/HVendeur0.txt";
        String outputFile = "./Vendeurs/Historique/THVendeur.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            String currentLine;

            // Tableau pour stocker les lignes
            List<String> lines = new ArrayList<String>();

            // Lire chaque ligne et la stocker dans le tableau
            while ((currentLine = reader.readLine()) != null) {
                lines.add(currentLine);
            }

            // Trier le tableau en fonction de la quatrième colonne
            Collections.sort(lines, new Comparator<String>() {
                public int compare(String o1, String o2) {
                    String[] o1Parts = o1.split("\\s+");
                    String[] o2Parts = o2.split("\\s+");
                    return o2Parts[3].compareTo(o1Parts[3]);
                }
            });

            // Écrire les lignes triées dans le fichier de sortie
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }

            // Fermer les flux de lecture et d'écriture
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
   public static void main(String[] args) throws IOException {
      
   
   }
}
