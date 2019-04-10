package sdd.AJ.painterBSP;

import java.io.IOException;
import java.util.Scanner;

import sdd.AJ.painterBSP.BSPLib.Heuristic.FirstHeuristic;
import sdd.AJ.painterBSP.BSPLib.Heuristic.LinearHeuristic;
import sdd.AJ.painterBSP.console.DeterministHeuristicTester;
import sdd.AJ.painterBSP.console.RandomHeuristicTester;
import sdd.AJ.painterBSP.util.FileFormatException;
import sdd.AJ.painterBSP.util.IllustrationInputReader;

public class TestConsole
{
    static DeterministHeuristicTester h1BSP,linearBSP;
    private static RandomHeuristicTester randomBSP;
    private static double x,y,angle;
    private static String line;

    /**
     * Initialize the three testers for the scene given in argument.
     * Initialize variables used for displaying and the input reader.
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
       try
       {
            IllustrationInputReader irr = new IllustrationInputReader(args[0]);
            System.out.println("Le programme effectue les tests initiaux.");
            System.out.println("Ceux-ci peuvent prendre du temps si le" +
                               " fichier charg\u00e9 est lourd.");

            h1BSP = new DeterministHeuristicTester(irr.getSegments(),
                                                   new FirstHeuristic());
            linearBSP = new DeterministHeuristicTester(irr.getSegments(),
                                                       new LinearHeuristic());
            randomBSP= new RandomHeuristicTester(irr.getSegments());

            line= "";
            for (int i = 0 ; i <=76 ; i++ )
                line+="-";

            Scanner sc = new Scanner(System.in);
            int chosen = -1;

            while (chosen!=0)
            {
                chosen = choiceMenu(sc);
                if (chosen!=0)
                {
                    if (chosen==4 || chosen ==5)
                        displayEyeMenu(sc);
                    System.out.printf("%25s|", "");
                    System.out.printf("%-16s|", "H. lin\u00e9aire");
                    System.out.printf("%-16s|", "H. premi\u00e8re");
                    System.out.printf("%-16s|%n", "H. al\u00e9atoire");
                    System.out.println(line);
                    displayTests(chosen);
                    chosen = isEnd(sc);
                }
            }
            System.out.println("Fin.");
            sc.close();
        }
        catch (IOException e)
        {
            System.out.println("Fichier introuvable. Arr\u00eat du programme.");
        }
        catch (FileFormatException e)
        {
            System.out.println("Fichier non conforme au format attendu. "+
                               "Arr\u00eat du programme.");
        }

    }
    
    /**
     * 
     * @param sc - a Scanner object that can read input of user
     * this function asks the user to input three double corresponding at positions of a eye.
     * These doubles must be separated by symbol ";".
     * if there is more than three inputs or at least one input is not a double,
     * programme is repeated.
     */
    private static void displayEyeMenu(Scanner sc)
    {
        while (true)
        {
            System.out.println("Veuillez choisir les coordonn\u00e9es de l'oeil.");
            System.out.println("Veuillez taper les coordonn\u00e9es dans l'ordre suivant");
            System.out.println("Coordonn\u00e9e x ; Coordonn\u00e9e y ; Angle");
            try
            {
                String input = sc.nextLine().trim(); // changeer le nom
                String[] values = input.split(";");
                if(values.length==3)
                {
                    x = Double.parseDouble(values[0].trim());
                    y = Double.parseDouble(values[1].trim());
                    angle = Double.parseDouble(values[2].trim());
                    break;
                }
                System.out.println("Format d'entr\u00e9es non respect\u00e9.");
            }
            catch (java.lang.NumberFormatException e)
            {
                System.out.println("Entr\u00e9e invalide."+
                                   " Veuillez taper un chiffre entre 1 et 5.");
                System.out.println("");
            }

        }

    }
    /**
     * @param sc - a Scanner object that can read input of user
     * this function asks the user to input a integer between 0 and 1
     * if the input doesn't correspond whit that we repeat the message.
     */
    private static int isEnd(Scanner sc)
    {
        int res = 0;
        while (true)
        {
            try
            {
                System.out.println("Voulez-vous continuer?");
                System.out.println("0 - Fin");
                System.out.println("1 - Continuer");
                System.out.print("Votre choix: ");
                String input = sc.nextLine().trim();
                res = Integer.parseInt(input);
                if (res>=0 && res <=1)
                    break;
                System.out.println("Chiffre non compris entre 0 et 1");
            }
            catch (java.lang.NumberFormatException e)
            {
                System.out.println("Pas un chiffre.");

            }
            System.out.println("Entr\u00e9e invalide."+
                              " Veuillez taper un chiffre entre 0 et 1.");
        }
        return res;
    }

    /**
     * @param sc - a Scanner object that can read input of user
     * this function asks the user to input a integer between 0 and 5
     * if the input doesn't correspond whit that we repeat the message.
     */

    private static int choiceMenu(Scanner sc)
    {

        int res = 0;
        while (true)
        {
            try
            {
                System.out.println("Veuillez choisir la donn\u00e9e à analyser");
                System.out.println("en tapant le chiffre correspondant:");
                System.out.println("0 - Sortir du programme");
                System.out.println("1 - Taille de l'arbre");
                System.out.println("2 - Hauteur de l'arbre");
                System.out.println("3 - Temps CPU du constructeur");
                System.out.println("4 - Temps CPU de l'algorithme du peintre");
                System.out.println("5 - Tout");
                System.out.print("Votre choix : ");

                String input = sc.nextLine().trim();
                res = Integer.parseInt(input);
                if (res>=0 && res <=5)
                    break;
                System.out.printf("Chiffre non compris entre 0 et 5.");
            }
            catch (java.lang.NumberFormatException e)
            {
                System.out.printf("Pas un chiffre.");

            }
            System.out.println("Entr\u00e9e invalide."+
                              " Veuillez taper un chiffre entre 0 et 5.");

        }
        return res;
    }
    /**
     * @param chosen - a integer between 1 and 5
     *  that correspond with a information about the BSPTREE
     * if chosen is equal to 5 we display all information.
     */
    private static void displayTests(int chosen)
    {
        switch(chosen)
        {
            case 1:
                System.out.printf("%-25s|", "Taille");
                System.out.printf("%-16.0f|", linearBSP.sizeTest());
                System.out.printf("%-16.0f|", h1BSP.sizeTest());
                System.out.printf("%-16.1f|%n", randomBSP.sizeTest());
                System.out.println(line);
                break;
            case 2:
                System.out.printf("%-25s|", "Hauteur");
                System.out.printf("%-16.0f|", linearBSP.heightTest());
                System.out.printf("%-16.0f|", h1BSP.heightTest());
                System.out.printf("%-16.1f|%n", randomBSP.heightTest());
                System.out.println(line);
                break;
            case 3:
                System.out.printf("%-25s|", "Temps constructeur(ms)");
                System.out.printf("%-16.4f|", linearBSP.constructorCpuTime());
                System.out.printf("%-16.4f|", h1BSP.constructorCpuTime());
                System.out.printf("%-16.4f|%n", randomBSP.constructorCpuTime());
                System.out.println(line);
                break;
            case 4:
                System.out.printf("%-25s|", "Temps peintre(ms)");
                System.out.printf("%-16.4f|", linearBSP.painterCpuTime(x, y, angle));
                System.out.printf("%-16.4f|", h1BSP.painterCpuTime(x, y, angle));
                System.out.printf("%-16.4f|%n", randomBSP.painterCpuTime(x, y, angle));
                System.out.println(line);
                break;
            case 5:
                for (int i = 1; i<=4;i++)
                    displayTests(i);
        }

    }
}
