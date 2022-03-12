package hammurabi;               // package declaration
import java.util.InputMismatchException;
import java.util.Random;         // imports go here
import java.util.Scanner;

public class Hammurabi {         // must save in a file named Hammurabi.java
    Random rand = new Random();  // this is an instance variable
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) { // required in every Java program
        new Hammurabi().playGame();
    }

    void playGame() {
        int population = 100;
        int bushels = 2800;
        int acres = 1000;
        int landValue = 19;



        System.out.println("O great Hammurabi!");
        System.out.println("You are in year one of your 10 year rule.");
        System.out.println("In the previous year, 0 people starved to death.");
        System.out.println("In the previous year 5 people entered the kingdom.");
        System.out.println("The population is now 100.");
        System.out.println("We harvested 3000 bushels at 3 bushels per acre.");
        System.out.println("Rats destroyed 200 bushels, leaving 2800 bushels in storage.");
        System.out.println("The city owns 1000 acres of land.");
        System.out.println("Land is currently worth 19 bushels per acre.");
        // declare local variables here: grain, population, etc.
        // statements go after the declations
    }

    int getNumber(String message) {
        while (true) {
            System.out.print(message);
            try {
                return scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("\"" + scanner.next() + "\" isn't a number!");
            }
        }
    }
    //other methods go here
}