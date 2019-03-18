import java.io.IOException;
//import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;
import java.util.HashSet;

public class countBases{
    public static void main(String[] args) throws IOException {
        PileupTool taskPro = new PileupTool();
        String a = args[0];
        taskPro.readmpileupLines(a);

        /*Doc taskDoc = new ToolDoc();
        taskDoc.printHelp();
        taskDoc.printFunctions();
        ABCScan taskCallVar = new ABCScan();
        taskCallVar.pileup2Snp("An example mpileup"); */
        
        /*for(String s: x){
                System.out.println(s);
        }*/
    }
}

class PileupTool {
        public static void readmpileupLines(String filename) throws IOException {
                File pilef = null;
                try {
                    pilef = new File(filename);
                    Scanner getcontent = new Scanner(pilef);
                    Pattern pattern = Pattern.compile("[\\+\\-]([0-9]\\d*)(\\w*)");
                    char Bkey;
                    
                    String bases;
                    String Rbases;
                    String Z;
                    
                    char referenceB;

                    while (getcontent.hasNextLine()){
                        String line = getcontent.nextLine();
                        String[] columns = line.split("\\t");
                        String mapPointBases = columns[4];
                        
                        // ArrayIndexOutOfBoundsException
                        
                        String nBases = mapPointBases.replace("$", "");
                        nBases = nBases.replaceAll("\\^.", "");
                        Matcher matcher = pattern.matcher(nBases);
                        
                        while (matcher.find()) {
                            bases =  matcher.group(2);
                            int bn = Integer.parseInt(matcher.group(1));
                            Z = matcher.group(1); 
                            Rbases =  bases.substring(0,bn);
                            
                            nBases = nBases.replaceFirst(Z + Rbases, "");
                        }
                        nBases = nBases.replace("+", "");
                        nBases = nBases.replace("-", "");
                        referenceB = columns[2].charAt(0);
                        
                        HashMap<String, String> bq = countAltBase(nBases, columns[5]);    // Base quality was stored in HashMap
                        HashMap<Character, Integer> outStats = countBase(referenceB, nBases);
                        Set<Entry<Character, Integer>> Qset = outStats.entrySet();
                        
                        System.out.print(columns[0] + "\t" + columns[1] + "\t" + columns[2] + "\t" + columns[3] + "\t");
                        for(Entry<Character, Integer> ent: Qset){
                            Bkey = ent.getKey();
                            System.out.print(  ent.getKey() + ":" + ent.getValue() + "\t");
                        }
                        System.out.print("\n");
                    }
                } catch (IOException e2){
                    e2.printStackTrace();
                }
      }
      
      public static HashMap<Character, Integer> countBase(Character refBase, String inputS){
          int num = 0;
          String base;
          char transBase;
          
          HashMap<Character, Integer> baseNum = new HashMap<Character, Integer>();
          baseNum.put('A', 0);
          baseNum.put('C', 0);
          baseNum.put('T', 0);
          baseNum.put('G', 0);
          baseNum.put('N', 0);
          char[] chars = inputS.toCharArray();
  
          for(int i=0; i < chars.length; i++){
              base = String.valueOf(chars[i]);
              base = base.toUpperCase();
              
              if (base.equals(".") || base.equals(",")){
                  base = String.valueOf(refBase);
              }
              
              transBase = base.charAt(0);
              num = baseNum.get(transBase) + 1;
              baseNum.put(transBase, num);
          }
          return baseNum;
     }
     
     public static HashMap<String, String> countAltBase(String inputS, String inputQ){
         HashMap<String, String> Qualities = new HashMap<String, String>();
         
         int rawQ, realQ;
         char[] qs = inputQ.toCharArray();
         
         char[] chars = inputS.toCharArray();
         for(int i = 0; i < chars.length; i++){
             char xq = qs[i];
             
             rawQ = xq;             
             realQ = rawQ-33;             
             String x = String.valueOf(chars[i]);
             
             if (Qualities.containsKey(x)){
                 String a = Qualities.get(x);
                 Qualities.put(x, a+","+ realQ);
             } else {
                 String qa = String.valueOf(realQ);
                 Qualities.put(x, qa);
             }
         }
         return Qualities;
    }
          
 
}

class ToolDoc{
    public static void printHelp(){
        System.out.println("It is help method in Doc.");
    }
    public static void printFunctions(){
        System.out.println("They are functions in Doc.");
    }
}
class ABCScan{
    public static void pileup2Snp(String pileup){
        System.out.println("It is a test of " + pileup);
    }
}
