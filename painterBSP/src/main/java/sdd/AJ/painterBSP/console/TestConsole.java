package sdd.AJ.painterBSP.console;

import java.io.IOException;
import java.util.Scanner;

import sdd.AJ.painterBSP.BSPLib.Heuristic.FirstHeuristic;
import sdd.AJ.painterBSP.BSPLib.Heuristic.LinearHeuristic;
import sdd.AJ.painterBSP.util.FileFormatException;
import sdd.AJ.painterBSP.util.IllustrationInputReader;

public class TestConsole
{

    public static void main(String[] args)
    {
       try
        {
            IllustrationInputReader irr = new IllustrationInputReader(args[0]);
            BSPTester h1BSP = new BSPTester(irr.getSegments(),new FirstHeuristic());
            BSPTester linearBSP = new BSPTester(irr.getSegments(),new LinearHeuristic()); 
            int choix = menuChoixAnalyse();
            switch (choix)
            {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5: 
                    break;
            }
                 
        }
        catch (IOException e)
        {
            System.out.println("Fichier introuvable");
        } 
        catch (FileFormatException e)
        {
            e.getMessage();
            e.printStackTrace();
        }

    }
    
    private static int menuChoixAnalyse()
    {
        Scanner sc = new Scanner(System.in);
        int res = 0;
        while (true)
        {
            System.out.println("Veuillez choisir la donnée à analyser.");
            System.out.println("Taper le chiffre correspondant:");
            System.out.println("1- Taille de l'arbre");
            System.out.println("2- Hauteur de l'arbre");
            System.out.println("3- Temps cpu du constructor");
            System.out.println("4- Temps cpu de l'algo du peintre");
            System.out.println("5- Tout");  
            try
            {
                String recu = sc.nextLine().trim();
                res = Integer.parseInt(recu);
                if (res>=1 && res <=5)
                    break;
                System.out.println("Chiffre non compris entre 1 et 5");
                System.out.println("Entree invalide. Veuillez taper un chiffre entre 1 et 5.");
                System.out.println("");
            }
            catch (java.lang.NumberFormatException e)
            {
                System.out.println("Pas un chiffre.");
                System.out.println("Entree invalide. Veuillez taper un chiffre entre 1 et 5.");
                System.out.println("");
            }

        }
        sc.close();
        return res;
    }
}
