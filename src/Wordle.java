import java.io.*;
import java.util.*;

public class Wordle {

    public static void main(String[] args) throws IOException {

        //first, loop dict.txt elements with while. Then push it each element to List.
        //every list's elements append to Array.
        List<String> wordList = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new FileReader("dict.txt"));
        String line = bf.readLine();
        while (line != null) {
            wordList.add(line);
            line = bf.readLine();
        }
        bf.close();
        String[] words = wordList.toArray(new String[0]);
        String answer = words[(int) (Math.random() * words.length)];

        System.out.println(answer);

        //"ls" for sequence. It is constant
        //"g" for remaining right. it will decrease in every transaction.
        // so (ls-g+1) will give us to sequence.
        //"Guess" String variable. It's value will come to user.
        int ls = 6;
        int g = 6;
        String guess = "";


        while (!guess.equals(answer) && g > 0) {

            //for loop for "args" to return 6 times.
            for (int k = 0; k <= 5; k++) {
                guess = args[k].toUpperCase(Locale.ROOT);

                System.out.print("Try:" + (ls - g + 1) + " (" + guess + "):");

                //guess length must be 5.
                if (guess.length() != 5) {
                    System.out.println("The length of answer must be five!");
                    g--;
                    System.out.println("You have " + g + " guesses remaning.");
                    System.out.println("");

                    continue;
                }

                //boolean for there is no word in dict.
                boolean inDict = false;

                //return to array with for loop.
                //if there is guess in dict, boolean will be true.
                for (String a : words) {
                    if (a.equals(guess)) {
                        inDict = true;
                        break;
                    }
                }


                if (!inDict) {
                    System.out.println("The word does not exist in the dictionary!");

                }

                else {
                    //if the guess in dict time to check.
                    //there is 2 for loop for "right position" and "wrong position".
                    //"noExist" for there is no latter.
                    System.out.println("\n");
                    for (int i = 0; i < answer.length(); i++) {
                        boolean notExist = false;
                        for (int j = 0; j < answer.length(); j++) {
                            if (guess.charAt(i) == answer.charAt(i)) {
                                System.out.println((i + 1) + ". letter exist and located in right position.");
                                notExist = true;
                                break;
                            }
                            if (guess.charAt(i) == answer.charAt(j)) {
                                System.out.println((i + 1) + ". letter exist but located in wrong position.");
                                notExist = true;
                                break;
                            }
                        }
                        if (!notExist) {
                            System.out.println((i + 1) + ". letter does not exist.");
                        }
                    }
                }

                //for remaining information.
                if (!guess.equals(answer)) {
                    g--;
                    System.out.println("You have the last " + g + " guesses left\n");
                }
                //for true guess.
                if (guess.equals(answer)) {
                    System.out.println("Congratulations! You guess right in" + (ls - g + 1) + "st shot!");
                }
            }

        }

        //for lost your all remaining right.
        if (g == 0) {
            System.out.println("You exceeded maximum try shot!");
            System.out.println("You failed! The key answer is " + answer);
        }

    }

}