package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;

/* Starter code for PS8.
 */

/* The user inputs two integers, say k and m,  with 0≤k≤2×10^6 and 0≤m≤10^5.
Your program should then list,  in decreasing order of rating,  the m highest rated movies among those that had at least k reviews.
 Your  program  should  loop,  accepting  input  and  producing  lists until at least one of the input numbers is 0.
 The user inputs two integers, say k and m,  with 0≤k≤2×10^6 and 0≤m≤10^5.  Your program should then list,  in decreasing order of rating,
 them highest rated movies among those that had at least k reviews.
Your  program  should  loop,  accepting  input  and  producing  lists until at least one of the input numbers is 0.

 */

public class MovieRanker {

    public static void main(String[] args) {
        byte contin = 1;

        //File file = new File("../resource/asnlib/publicdata/ratings.tsv");
        File file = new File("ratings.tsv");
        ArrayList<MovieRating> rl = new ArrayList<MovieRating>();

        do {

            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] tkns = line.split("\\t"); // tabs separate tokens
                    MovieRating nr = new MovieRating(tkns[0], tkns[1], tkns[2]);
                    rl.add(nr);
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            int minVotes = 1;
            int numRecords = 1;
            Scanner input = new Scanner(System.in);

            MaxHeap<MovieRating> myHeap = new MaxHeap<MovieRating>();

            for (int i = 0; i < rl.size(); i++) {
                myHeap.insert(rl.get(i));
            }

            System.out.println();
            System.out.println("Enter minimum vote threshold and number of records:");
            minVotes = input.nextInt();
            numRecords = input.nextInt();
            if (minVotes * numRecords == 0)
                break;
            long startTime = System.currentTimeMillis();

            /* Fill in code to determine the top numRecords movies that have at least
             * minVotes votes. For each record mr, in decreasing order of average rating,
             * execute a System.out.println(mr).
             * Do not sort the movie list!
             */
            int counter = 0;
            for (int i = 0; i < rl.size(); i++) {
                MovieRating temp = myHeap.removemax();
                if (temp.getVotes() > minVotes) {
                    System.out.println(temp.toString());
                    counter++;
                }
                if (counter >= numRecords) {
                    break;
                }
            }

            System.out.println();
            long readTime = System.currentTimeMillis();
            System.out.println("Time: " + (System.currentTimeMillis() - startTime) + " ms");


            System.out.println("Do you wish to rerun the ranker? 1 = yes, 0 = no.");
            System.out.println();
            contin = input.nextByte();

            if(contin > 0)
                System.out.println("You have selected to rerun the program.");
            else
                System.out.println("You have selected to stop the program. Have a nice day.");

        } while(contin > 0);

    }
}
