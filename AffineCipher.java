
package affinecipher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;


public class AffineCipher {

    /**
     * @param args the command line arguments
     */
    
        
    String input;
    int shift;
    String command;
    String dictLocation;
    int multiple;
    
    public static void main(String[] args) {
        AffineCipher cphr = new AffineCipher();
        cphr.start();

        
        
    }
    
    
    public AffineCipher()
    {
        
        
        
    }
    
    public void start()
    {
        startOver();
        this.command = "a";
        while (!this.command.equals("stop"))
        {
            newCommand();
        switch (this.command)
        {
            case "startOver": startOver();
                break;
            case "showLetterFrequency": showLetterFrequency();
                break;
            case "guess": changeshift();
                guess();
                break;
            case "showBestGuess": showBestGuess();
                break;
            case "showValues": showValues();
                break;
            default: 
                System.out.println("Error: Invalid Command\n");
                break;
                
        }
        }
    }
    public void showValues()
    {
        
        Hashtable<Integer, Hashtable<Integer,Integer>> show = bestguess();
        System.out.println("");
        int max = 0;
        int maxA = 0;
        int maxShift = 0;
        for (Integer i: show.keySet())
        {
            System.out.println("Mulitply value = " + i);
            System.out.println(" ");
            for (Integer j: show.get(i).keySet())
            {
                if (show.get(i).get(j) > 0)
                {
                    if (show.get(i).get(j) > max)
                    {
                        max = show.get(i).get(j);
                        maxA = i;
                        maxShift = j;
                    }
                    System.out.println("addition value = " + j + "  " + "number of words = " + show.get(i).get(j));
                }
            }
            System.out.println(" ");
        }
        System.out.println("max values: words correct: " + max + " multiple value " + maxA + " shift value " + maxShift);
        
    }
    public void showBestGuess()
    {
        
        Hashtable<Integer, Hashtable<Integer,Integer>> show = bestguess();
        System.out.println("");
        int max = 0;
        int maxA = 0;
        int maxShift = 0;
        for (Integer i: show.keySet())
        {
            for (Integer j: show.get(i).keySet())
            {
                if (show.get(i).get(j) > 0)
                {
                    if (show.get(i).get(j) > max)
                    {
                        max = show.get(i).get(j);
                        maxA = i;
                        maxShift = j;
                    }
                    
                }
            }
        }
        
        System.out.println("max values: words correct: " + max + " multiple value " + maxA + " shift value " + maxShift);
        setStuff(maxA, maxShift);
        guess();
    }
    
    public Hashtable<Integer, Hashtable<Integer,Integer>> bestguess()
    {
        
        Hashtable<String,Integer> frequencytable = readfile(dictLocation);
        Hashtable<Integer, Hashtable<Integer,Integer>> guesses = new Hashtable<>();
        LinkedList<Integer> aValues = new LinkedList<>();
        for (int a = 1; a < 26; a = a + 2)
        {
            aValues.add(a);
        }
        aValues.remove(aValues.indexOf(13));
        for (Integer a: aValues)
        {
            Hashtable<Integer,Integer> temp = new Hashtable<>();
            this.multiple = a;
            for (int shifttemp = 1; shifttemp < 26; shifttemp++)
            {
                
            
                
                this.shift = shifttemp;
                temp.put(shift,0);
                LinkedList<String> test = shiftBack();
                for (int wordlength = 2; wordlength < 11; wordlength++)
                {
            
                    for (int start = 0; start < test.size() - wordlength; start++)
                    {
                        String word = "";
                
                        for (int spot = 0; spot <= wordlength; spot++)
                        {
                            word += test.get(spot + start);
                        }
//                        System.out.println(word);
                
                        if (frequencytable.containsKey(word))
                        {
                            temp.put(shift, temp.get(shift) + 1);
                        }
                    }
                }
        }
            guesses.put(multiple, temp);
        }
        return guesses;
    }
    
    private HashMap<String,Integer> makeLetterFrequency(String string) 
    {
        HashMap<String,Integer> table = new HashMap<>();
        for (int i = 0; i < string.length(); i++)
        {
            if (table.containsKey(Character.valueOf(string.charAt(i)).toString()))
            {
            table.put(Character.valueOf(string.charAt(i)).toString(), table.get(Character.valueOf(input.charAt(i)).toString()) + 1);
            }
            else
            {
                table.put(Character.valueOf(string.charAt(i)).toString(), 1);
            }
        }
        return table;
    }
    
    public void showLetterFrequency()
    {
        HashMap map = makeLetterFrequency(input);
        LinkedList<String> lst = new LinkedList<>();
        for (Object s : map.keySet())
        {
            System.out.print(s.toString());
            System.out.print(" ");
            System.out.println(map.get(s).toString());
        }
    }
    
    public void startOver()
    {
        Scanner scanner = new Scanner( System.in );
        input = scanner.nextLine();
        dictLocation = scanner.nextLine();
    }
    public LinkedList<Integer> makeList()
    {
        LinkedList<String> temp = new LinkedList<>();
        for (int i = 0; i < this.input.length(); i++)
        {
            temp.add(Character.valueOf(input.charAt(i)).toString());
        }
        LinkedList<Integer> intlst = new LinkedList<>();
        for (int i = 0; i < temp.size(); i++)
        {
            switch (temp.get(i))
            {
                case "a": intlst.add(0);
                    break;
                case "b": intlst.add(1);
                    break;
                case "c": intlst.add(2);
                    break;
                case "d": intlst.add(3);
                    break;
                case "e": intlst.add(4);
                    break;
                case "f": intlst.add(5);
                    break;
                case "g": intlst.add(6);
                    break;
                case "h": intlst.add(7);
                    break;
                case "i": intlst.add(8);
                    break;
                case "j": intlst.add(9);
                    break;
                case "k": intlst.add(10);
                    break;
                case "l": intlst.add(11);
                    break;
                case "m": intlst.add(12);
                    break;
                case "n": intlst.add(13);
                    break;
                case "o": intlst.add(14);
                    break;
                case "p": intlst.add(15);
                    break;
                case "q": intlst.add(16);
                    break;
                case "r": intlst.add(17);
                    break;
                case "s": intlst.add(18);
                    break;
                case "t": intlst.add(19);
                    break;
                case "u": intlst.add(20);
                    break;
                case "v": intlst.add(21);
                    break;
                case "w": intlst.add(22);
                    break;
                case "x": intlst.add(23);
                    break;
                case "y": intlst.add(24);
                    break;
                case "z": intlst.add(25);
                    break;
            }
        }
        return intlst;
    }
    
    public void guess()
    {
        LinkedList<String> lst = shiftBack();
        System.out.println(" ");
        for (int i = 0; i < lst.size(); i++)
        {
            System.out.print(lst.get(i));
        }
    }
    
    public Hashtable<String,Integer> readfile(String location)
    {
        Hashtable<String,Integer> table = new Hashtable<>();
        try (BufferedReader br = new BufferedReader(new FileReader(location)))//the dictionary file goes here, 
		{
 
			String sCurrentLine;
 
			while ((sCurrentLine = br.readLine()) != null) {
				table.put(sCurrentLine, 1);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		}  
        return table;
    }
    
    
    
    public LinkedList<Integer> shift()
    {
        LinkedList<Integer> templst = makeList();
        for (int i = 0; i < templst.size(); i++)
        {
            templst.set(i, ((templst.get(i) * multiple + shift)%26));
        }
        return templst;
    }
    
    public LinkedList<String> shiftBack()
    {
        LinkedList<Integer> templst = shift();
        LinkedList<String> output = new LinkedList<>();
        for (int i = 0; i < templst.size(); i++)
        {
            switch (templst.get(i))
            {
                case 0: output.add("a");
                    break;
                case 1: output.add("b");
                    break;
                case 2: output.add("c");
                    break;
                case 3: output.add("d");
                    break;
                case 4: output.add("e");
                    break;
                case 5: output.add("f");
                    break;
                case 6: output.add("g");
                    break;
                case 7: output.add("h");
                    break;
                case 8: output.add("i");
                    break;
                case 9: output.add("j");
                    break;
                case 10: output.add("k");
                    break;
                case 11: output.add("l");
                    break;
                case 12: output.add("m");
                    break;
                case 13: output.add("n");
                    break;
                case 14: output.add("o");
                    break;
                case 15: output.add("p");
                    break;
                case 16: output.add("q");
                    break;
                case 17: output.add("r");
                    break;
                case 18: output.add("s");
                    break;
                case 19: output.add("t");
                    break;
                case 20: output.add("u");
                    break;
                case 21: output.add("v");
                    break;
                case 22: output.add("w");
                    break;
                case 23: output.add("x");
                    break;
                case 24: output.add("y");
                    break;
                case 25: output.add("z");
                    break;
            }
        }
        return output;
    }
    
    
    public void newCommand()
    {
        Scanner scanner = new Scanner( System.in );
        command = scanner.nextLine();
    }
    public void changeshift()
    {
        Scanner scanner = new Scanner( System.in );
        multiple = scanner.nextInt();
        shift = scanner.nextInt();
    }
    
    public void setStuff(int a, int shift)
    {
        this.multiple = a;
        this.shift = shift;
    }
    
}
