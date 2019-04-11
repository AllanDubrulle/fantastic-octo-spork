package sdd.AJ.painterBSP;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import sdd.AJ.painterBSP.BSPLib.heuristic.FirstHeuristic;
import sdd.AJ.painterBSP.BSPLib.heuristic.LinearHeuristic;
import sdd.AJ.painterBSP.console.DeterministHeuristicTester;
import sdd.AJ.painterBSP.console.RandomHeuristicTester;
import sdd.AJ.painterBSP.util.FileFormatException;
import sdd.AJ.painterBSP.util.IllustrationInputReader;

public class ScriptTableaux
{

    public static void main(String[] args)
    {
        String[] files = {"Small","Medium","Large","Huge"};
        String[] types = {"rectangles","ellipses","random"};
        for (String t : types)
        {
            for (String f : files)
            {
                String[] temp = {t,t+f};
                try
                {
                    createFile(temp);
                } 
                catch (IOException e)
                {
                
                } catch (FileFormatException e)
                {

                }
            }
        }

    }

    private static void createFile(String[] file) throws IOException, FileFormatException
    {
        IllustrationInputReader irr = new IllustrationInputReader("Scenes/"+file[0]+"/"+file[1]+".txt");
        //absolute path
        File ff=new File("/home/allan/Documents/fantastic-octo-spork/rapport/final/tableaux/"+file[1]+"Tableau.tex"); 
        ff.createNewFile();
        FileWriter ffw=new FileWriter(ff);
        DeterministHeuristicTester h1BSP = new DeterministHeuristicTester(irr.getSegments(),
                new FirstHeuristic());
        DeterministHeuristicTester linearBSP = new DeterministHeuristicTester(irr.getSegments(),
                new LinearHeuristic());
        RandomHeuristicTester randomBSP= new RandomHeuristicTester(irr.getSegments());
            
   
        ffw.write("\\begin{center}\n" + 
                "{\\tabulinesep=1.2mm\n" + 
                "\\begin{tabu}{|l|c|c|c|}\n" + 
                
                "  \\hline\n" + 
                "  & Heuristique linéaire  & Heuristique 1 & Heuristique aléatoire \\\\ \n" + 
                "  \\hline\n" + 
                    
                "  Taille &" + 
                String.format("%10.0f", linearBSP.sizeTest())+ "&" + 
                String.format("%10.0f", h1BSP.sizeTest())+ "&" +
                String.format("%10.1f", randomBSP.sizeTest()) + 
                "  \\\\ \n" + 
                "  \\hline\n" + 
                
                "  Hauteur &" + 
                String.format("%10.0f", linearBSP.heightTest())+ "&" + 
                String.format("%10.0f", h1BSP.heightTest())+ "&" +
                String.format("%10.1f", randomBSP.heightTest())+
                "  \\\\ \n" + 
                "  \\hline\n" + 
                    
                "  Temps constructeur (ms) &" + 
                String.format("%16.4f", linearBSP.constructorCpuTime())+ "&" + 
                String.format("%16.4f", h1BSP.constructorCpuTime())+ "&" +
                String.format("%16.4f", randomBSP.constructorCpuTime()) + 
                "  \\\\ \n" + 
                "  \\hline\n" + 
                    
                "  Temps peintre (ms) &" + 
                "  "+ String.format("%16.4f", linearBSP.painterCpuTime(0, 0, 1))+ "&" + 
                " "+ String.format("%16.4f", h1BSP.painterCpuTime(0, 0, 1))+ "&" +
                " "+ String.format("%16.4f", randomBSP.painterCpuTime(0, 0, 1))+  
                "  \\\\ \n" + 
                "  \\hline\n" + 
                "\\end{tabu}\n" + 
                "}\n" + 
                "\\end{center}\n\n");
        
        
        ffw.write("%%%Local Variables:\n" + 
                "%%% mode: latex\n" + 
                "%%% TeX-master: \"../rapportGp1\"\n" + 
                "%%% End:");
        ffw.close(); // fermer le fichier à la fin des traitements 
    }
}
