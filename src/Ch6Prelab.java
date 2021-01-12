/*
 * Chapter 6 Pre-lab
 * Description: Pokemon game
 * Name: Mohammad Abdelrahman
 * ID: 920158652
 * Class: CSC 211-02
 * Semester: fall 2020
 */
/**
 * In this prelab, there is nothing broken to fix, it is working just fine. I want you guys
 * to go through the program and answer the questions I've left in the comments. Please put ALL of your answers
 * in BLOCK COMMENTS. I will remove points if you use line comments over block comments.
 *
 * For your submission, you will need to submit this java file with the header. You WILL lose points if you do not
 * have the header.
 */

package prelabs;

import java.util.*;

public class Ch6Prelab {
    // Pokemon stats
    public static int p1MaxHp = 10, p1CurrHp = 10, p2MaxHp = 8, p2CurrHp = 8;
    public static int p1MinDmg = 2, p1MaxDmg = 4, p2MinDmg = 1, p2MaxDmg = 3;
    public static String p1Name = "Pikachu", p2Name = "Spearow";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int turn = 1;
        while (p1CurrHp > 0 && p2CurrHp > 0) {
            String currPlayer = p1Name;
            // What is the '%' operator called, and what does it do?
            if (turn%2 == 0) currPlayer = p2Name;

            // If I wrote these like System.out.println(printHealthBar(p1CurrHp, p1MaxHp, p1Name));
            // would that work? Why or why not.
            printHealthBar(p1CurrHp, p1MaxHp, p1Name);
            printHealthBar(p2CurrHp, p2MaxHp, p2Name);

            System.out.printf("%s's turn.\n", currPlayer.toUpperCase());

            // What does turn%2 calculate? What is the importance of it?
            if (turn%2 != 0) {
                System.out.println("Select an ability by entering 1-4.");
                printAbilities();

                int n = sc.nextInt();

                // What is happening on line 44? Include, in your comment, the flow of calls starting in main()
                // More explicitly, starting from main, what function do we go in to first? How do we get back to main?
                // ex: main() --> foo() --> bar() --> main()
                p2CurrHp = takeDmg(p2CurrHp, useAbility(p1Name, n));
            } else {
                int dmg = calculateDmg(p2MinDmg,p2MaxDmg);
                System.out.printf("%s attacks %s for %d damage!\n", p2Name.toUpperCase(), p1Name.toUpperCase(), dmg);
                p1CurrHp = takeDmg(p1CurrHp, dmg);
            }

            turn++;
        }
    }

    public static String getAbility(int n) {
        // In your block comments, rewrite this switch statement in if-else/if-else if form
        // Compare the two forms, which do you think is better?
        switch(n) {
            case 1:
                return "Scratch";
            case 2:
                return "Tackle";
            case 3:
                return "Lightning Bolt";
            case 4:
                return "Thunder";
        }

        return "???";
    }

    // What does the 'void' keyword mean?
    public static void printAbilities() {
        System.out.println("1. Scratch\t\t3. Lightning Bolt");
        System.out.println("2. Tackle\t\t4. Thunder");
    }

    public static int useAbility(String name, int n) {
        int dmg = 0;
        // In your block comments, rewrite this switch statement in if-else/if-else if form
        // Compare the two forms, which do you think is better?
        switch (n) {
            case 1:
                dmg = calculateDmg(p1MinDmg-1, p1MaxDmg-1);
                break;
            case 2:
            case 3:
            case 4:
                dmg = calculateDmg(p1MinDmg+n, p1MaxDmg+n);
                break;
            default:
                System.out.println("Your Pokemon doesn't understand your command.");
                return 0;
        }

        System.out.printf("%s used %s for %d damage!\n",name.toUpperCase(), getAbility(n).toUpperCase(), dmg);
        return dmg;
    }

    public static int calculateDmg(int min, int max) {
            return (int) (Math.random()*(max-min+1))+min; // return a random number between min and max inclusive
    }

    public static int takeDmg(int currHp, int dmg) {
        return currHp-dmg;
    }

    public static void printHealthBar(int currHp, int maxHp, String name) {
        System.out.println(name.toUpperCase());
        System.out.print("[ ");
        for (int i = 1; i <= maxHp; i++) { // loop through the maxHp of each pokemon
            if(i <= currHp){ // prints "+" for each Hp the pokemon has
                System.out.print("+");
            }
            else{ // prints "-" for the remaining of the pokemon Hp
                System.out.print("-");
            }
        }
        System.out.println(" ]");
    }


}