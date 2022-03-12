package hammurabi;               // package declaration
import java.util.Random;         // imports go here
import java.util.Scanner;

public class Hammurabi {         // must save in a file named Hammurabi.java
    Random rand = new Random();  // this is an instance variable
    Scanner scanner = new Scanner(System.in);
    int population = 100;
    int bushels = 2800;
    int acres = 1000;
    int landValue = 19;


    public static void main(String\[\] args) { // required in every Java program
        new Hammurabi().playGame();
    }

    void playGame() {
        int population = 100;
        int bushels = 2800;
        int acres = 1000;
        int landValue = 19;
        // declare local variables here: grain, population, etc.
        // statements go after the declations
    }

    //other methods go here
}