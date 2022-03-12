package hammurabi;               // package declaration

import java.util.InputMismatchException;
import java.util.Random;         // imports go here
import java.util.Scanner;

public class Hammurabi {         // must save in a file named Hammurabi.java
    Random rand = new Random();  // this is an instance variable
    Scanner scanner = new Scanner(System.in);

    int population = 100;
    int bushels = 2800;
    int acres = 1000;
    int price = 19;
    int acresBought;
    int acresSold;

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getBushels() {
        return bushels;
    }

    public void setBushels(int bushels) {
        this.bushels = bushels;
    }

    public int getAcres() {
        return acres;
    }

    public void setAcres(int acres) {
        this.acres = acres;
    }

    public int getLandValue() {
        return price;
    }

    public void setLandValue(int landValue) {
        this.price = landValue;
    }


    public static void main(String[] args) { // required in every Java program
        new Hammurabi().playGame();
    }


    void playGame() {


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

        askHowManyAcresToBuy(19, 100);
        askHowManyAcresToSell(1000);
        howMuchGrainToFeedPeople(200);
        askHowManyAcresToPlant(10, 5, 5);
    }

    int getNumber(String message) {
        while (true) {
            System.out.print(message);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\"" + scanner.next() + "\" isn't a number!");
            }
        }
    }

    int askHowManyAcresToBuy(int price, int bushels) {
        getNumber("How many acres do you want to buy?");
        int acresBought = scanner.nextInt();
        System.out.println(acresBought);
        if ((price * acresBought) > bushels) {
            System.out.println("You don't have enough coin to pay for that, try again...");
        } else {
            bushels -= (price * acresBought);
            System.out.println(bushels);
        }
        return acresBought;
    }

    public int askHowManyAcresToSell(int acresOwned) {
        if (acresBought != 0) {
            System.out.println("Nope");
            System.exit(0);
        }
        getNumber("How many acres do you want to sell?");
        int acresSold = scanner.nextInt();
        if (acresSold > acresOwned) {
            System.out.println("You can't do that, try again...");
        } else {
            bushels = acresSold * price;
        }
        return acresSold;
    }


    int howMuchGrainToFeedPeople(int bushels) {
        getNumber("How much grain do you want to feed people?");
        int fedGrain = scanner.nextInt();
        if (fedGrain > bushels) {
            System.out.println("You don't have enough grain!");
        } else {
            bushels = bushels - fedGrain;
        }
        return fedGrain;
    }

    int askHowManyAcresToPlant(int acresOwned, int population, int bushels) {
        getNumber("How many acres do you want to plant?");
        int acresToPlant = scanner.nextInt();
        if (acresToPlant > acresOwned || acresToPlant > bushels || population == 0) {
            System.out.println("Nope");
        }
        return acresToPlant;
    }

}
//other methods go here
