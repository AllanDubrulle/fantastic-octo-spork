package sdd.AJ.painterBSP.console;

import java.io.IOException;
import java.util.Scanner;

import sdd.AJ.painterBSP.BSPLib.Heuristic.FirstHeuristic;
import sdd.AJ.painterBSP.BSPLib.Heuristic.LinearHeuristic;
import sdd.AJ.painterBSP.util.FileFormatException;
import sdd.AJ.painterBSP.util.IllustrationInputReader;

public class TestConsole
{
    static BSPDeterministHeuristicTester h1BSP,linearBSP;
    private static BSPUnDeterministHeuristicTester randomBSP;
    private static double x,y,angle;
    private static String line;

    /**
     * Initialize the three testers for the scene given in argument.
     * initialize some variable used for the display and the input reader.
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
       try
       {
            IllustrationInputReader irr = new IllustrationInputReader(args[0]);
            h1BSP = new BSPDeterministHeuristicTester(irr.getSegments(),new FirstHeuristic());
            linearBSP = new BSPDeterministHeuristicTester(irr.getSegments(),new LinearHeuristic());
            randomBSP= new BSPUnDeterministHeuristicTester(irr.getSegments());

            line= "";
            for (int i = 0 ; i <=60 ; i++ )
                line+="-";

            Scanner sc = new Scanner(System.in);
            int chose = -1;

            while (chose!=0)
            {
                chose = menuChose(sc);
                if (chose==4 || chose ==5)
                    displayEyeMenu(sc);
                System.out.printf("%22s|", "");
                System.out.printf("%-12s|", "H lineaire");
                System.out.printf("%-12s|", "H Premiere");
                System.out.printf("%-11s|\n", "H aleatoire");
                System.out.println(line);
                displayTests(chose);
                chose = isEnd(sc);
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
    private static void displayEyeMenu(Scanner sc)
    {
        while (true)
        {
            System.out.println("Veuillez choisir les coordonnées de l'oeil.");
            System.out.println("Veuillez taper les coordonnées dans l'ordre suivant");
            System.out.println("Coordonnée x ; Coordonné y ; Angle");
            try
            {
                String recu = sc.nextLine().trim(); // changeer le nom
                String[] values = recu.split(";");
                if(values.length==3)
                {
                    x = Double.parseDouble(values[0].trim());
                    y = Double.parseDouble(values[1].trim());
                    angle = Double.parseDouble(values[2].trim());
                    break;
                }
                System.out.println("Format d'entrées non respecte");
            }
            catch (java.lang.NumberFormatException e)
            {
                System.out.println("Pas un nombre.");
                System.out.println("Entree invalide. Veuillez taper un chiffre entre 1 et 5.");
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
                System.out.println("0- fin");
                System.out.println("1- Continuer");
                String recu = sc.nextLine().trim();
                res = Integer.parseInt(recu);
                if (res>=0 && res <=1)
                    break;
                System.out.println("Chiffre non compris entre 0 et 1");
            }
            catch (java.lang.NumberFormatException e)
            {
                System.out.println("Pas un chiffre.");

            }
            System.out.println("Entree invalide. Veuillez taper un chiffre entre 0 et 1.\n");
        }
        return res;

    }

    /**
     * @param sc - a Scanner object that can read input of user
     * this function asks the user to input a integer between 1 and 5
     * if the input doesn't correspond whit that we repeat the message.
     */

    private static int menuChose(Scanner sc)
    {

        int res = 0;
        while (true)
        {
            try
            {
                System.out.println("Veuillez choisir la donnée à analyser");
                System.out.println("En tapant le chiffre correspondant:");
                System.out.println("1- Taille de l'arbre");
                System.out.println("2- Hauteur de l'arbre");
                System.out.println("3- Temps cpu du constructeur");
                System.out.println("4- Temps cpu de l'algo du peintre");
                System.out.println("5- Tout");
                String recu = sc.nextLine().trim();
                res = Integer.parseInt(recu);
                if (res>=1 && res <=5)
                    break;
                System.out.println("Chiffre non compris entre 1 et 5");
            }
            catch (java.lang.NumberFormatException e)
            {
                System.out.println("Pas un chiffre.");

            }
            System.out.println("Entree invalide. Veuillez taper un chiffre entre 1 et 5.\n");

        }
        return res;
    }
    /**
     * @param chose - a integer between 1 and 5
     *  that correspond with a information about the BSPTREE
     * if chose equal to 5 we display all information.
     */
    private static void displayTests(int chose)
    {
        switch(chose)
        {
            case 1:
                System.out.printf("%-22s|", "Taille");
                System.out.printf("%-12.0f|", linearBSP.sizeTest());
                System.out.printf("%-12.0f|", h1BSP.sizeTest());
                System.out.printf("%-11.1f|\n", randomBSP.sizeTest());
                System.out.println(line);
                break;
            case 2:
                System.out.printf("%-22s|", "Hauteur");
                System.out.printf("%-12.0f|", linearBSP.heightTest());
                System.out.printf("%-12.0f|", h1BSP.heightTest());
                System.out.printf("%-11.1f|\n", randomBSP.heightTest());
                System.out.println(line);
                break;
            case 3:
                System.out.printf("%-22s|", "Temps constructeur(ms)");
                System.out.printf("%-12.4f|", linearBSP.constructorCpuTime());
                System.out.printf("%-12.4f|", h1BSP.constructorCpuTime());
                System.out.printf("%-11.4f|\n", randomBSP.constructorCpuTime());
                System.out.println(line);
                break;
            case 4:
                System.out.printf("%-22s|", "Temps peintre(ms)");
                System.out.printf("%-12.4f|", linearBSP.painterCpuTime(x, y, angle));
                System.out.printf("%-12.4f|", h1BSP.painterCpuTime(x, y, angle));
                System.out.printf("%-11.4f|\n", randomBSP.painterCpuTime(x, y, angle));
                System.out.println(line);
                break;
            case 5:
                for (int i = 0; i<=4;i++)
                    displayTests(i);
        }

    }
}
