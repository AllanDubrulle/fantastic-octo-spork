package sdd.AJ.painterBSP.console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import sdd.AJ.painterBSP.BSPLib.Heuristic.FirstHeuristic;
import sdd.AJ.painterBSP.BSPLib.Heuristic.LinearHeuristic;
import sdd.AJ.painterBSP.util.FileFormatException;
import sdd.AJ.painterBSP.util.IllustrationInputReader;
import sdd.AJ.painterBSP.util.Segment;

public class TestConsole
{
    private static BSPTester h1BSP;
    private static BSPTester linearBSP;
    private static BSPTester randomBSP;
    private static String line;
    
    
    public static void main(String[] args)
    {
       try
       {
            IllustrationInputReader irr = new IllustrationInputReader(args[0]);
            h1BSP = new BSPTester(irr.getSegments(),new FirstHeuristic());
            linearBSP = new BSPTester(irr.getSegments(),new LinearHeuristic()); 
            ArrayList<Segment> temp= (ArrayList<Segment>) irr.getSegments();
            Collections.shuffle(temp);
            randomBSP= new BSPTester(temp,new FirstHeuristic());
            
            line= "";
            for (int i = 0 ; i <=60 ; i++ )
                line+="-";
            
            Scanner sc = new Scanner(System.in);
            int choix = -1;
           
            while (choix!=0)
            {
                choix = menuChoixAnalyse(sc);
                System.out.printf("%22s|", "");
                System.out.printf("%-12s|", "H lineaire");
                System.out.printf("%-12s|", "H Premiere");
                System.out.printf("%-11s|\n", "H aleatoire");
                System.out.println(line);
                affichageTest(choix);
                choix = isEnd(sc);
            }
            System.out.println("fin");
            sc.close();
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
    
    private static int isEnd(Scanner sc)
    {
        int res = 0;
        while (true)
        {
            System.out.println("Voulez-vous continuer?");
            System.out.println("0- fin");
            System.out.println("1- Continuer"); 
            try
            {
                String recu = sc.nextLine().trim();
                res = Integer.parseInt(recu);
                if (res>=0 && res <=1)
                    break;
                System.out.println("Chiffre non compris entre 0 et 1");
                System.out.println("Entree invalide. Veuillez taper un chiffre entre 0 et 1.");
                System.out.println("");
            }
            catch (java.lang.NumberFormatException e)
            {
                System.out.println("Pas un chiffre.");
                System.out.println("Entree invalide. Veuillez taper un chiffre entre 0 et 1.");
                System.out.println("");
            }

        }
        return res;
        
    }

    
    private static int menuChoixAnalyse(Scanner sc)
    {
        
        int res = 0;
        while (true)
        {
            System.out.println("Veuillez choisir la donnée à analyser.");
            System.out.println("Taper le chiffre correspondant:");
            System.out.println("1- Taille de l'arbre");
            System.out.println("2- Hauteur de l'arbre");
            System.out.println("3- Temps cpu du constructeur");
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
        return res;
    }
    
    private static void affichageTest(int choix)
    {
        switch(choix)
        {
            case 1:
                System.out.printf("%-22s|", "Taille");
                System.out.printf("%-12d|", linearBSP.sizeTest());
                System.out.printf("%-12d|", h1BSP.sizeTest());
                System.out.printf("%-11d|\n", randomBSP.sizeTest());
                System.out.println(line);
                break;
            case 2:
                System.out.printf("%-22s|", "Hauteur");
                System.out.printf("%-12d|", linearBSP.heightTest());
                System.out.printf("%-12d|", h1BSP.heightTest());
                System.out.printf("%-11d|\n", randomBSP.heightTest());
                System.out.println(line);
                break; 
            case 3:
                System.out.printf("%-22s|", "Temps constructeur(ms)");
                System.out.printf("%-12d|", linearBSP.constructorCpuTime());
                System.out.printf("%-12d|", h1BSP.constructorCpuTime());
                System.out.printf("%-11d|\n", randomBSP.constructorCpuTime());
                System.out.println(line);
                break;
            case 4:
                System.out.printf("%-22s|", "Temps peintre(ms)");
                System.out.printf("%-12d|", linearBSP.painterCpuTime(0, 0, 1));
                System.out.printf("%-12d|", h1BSP.painterCpuTime(0, 0, 1));
                System.out.printf("%-11d|\n", randomBSP.painterCpuTime(0, 0, 1));
                System.out.println(line);
                break; 
            case 5:
                for (int i = 0; i<=4;i++)
                    affichageTest(i);
        }
        
    }
}
