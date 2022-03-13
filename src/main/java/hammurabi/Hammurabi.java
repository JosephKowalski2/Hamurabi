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

        int years = 1;
        int population = 100;
        int bushels = 2800;
        int acres = 1000;
        int price = 19;
        int acresBought = 0;
        int acresSold = 0;
        int number_of_plague = 0;
        int bushelsFedToPeople = 0;
        int harvest = 0;
        int deaths = 0;
        int immigrants = 0;
        int grainEaten = 0;
        int acresPlanted = 0;

        while (years <= 10) {
        System.out.println("O great Hammurabi!");
        System.out.println("You are in year" + " " + years + " " + "of your 10 year rule.");
        System.out.println("In the previous year," + " " + deaths + " " + "people starved to death.");
        System.out.println("In the previous year" + " " + immigrants + " " + "people entered the kingdom.");
        System.out.println("The population is now" + " " + population + " " + ".");
        System.out.println("We harvested" + " " + harvest + " " + "bushels at 3 bushels per acre.");
        System.out.println("Rats destroyed" + " " + grainEaten + " " + "bushels, leaving" + " " + bushels + " " + "bushels in storage.");
        System.out.println("The city owns" + " " + acres + " " + "acres of land.");
        System.out.println("Land is currently worth" + " " + price + " " + "bushels per acre.");
        // declare local variables here: grain, population, etc.
        // statements go after the declations

            acresBought = askHowManyAcresToBuy(price, bushels);
            acres -= acresBought;
            if (acresBought == 0) {
                acresSold = askHowManyAcresToSell(acres);
                acres -= acresSold;
            }
            bushelsFedToPeople = howMuchGrainToFeedPeople(bushels);
            bushels -= bushelsFedToPeople;
            acresPlanted = askHowManyAcresToPlant(acres, population, bushels);
            acres -= acresPlanted;
            number_of_plague = plagueDeaths(population);
            population -= number_of_plague;
            deaths = starvationDeaths(population, bushelsFedToPeople);
            population -= deaths;
            if (uprising(population, deaths)) break;
            immigrants = immigrants(population, acres, bushels);
            population += immigrants;
            harvest = harvest(acres, bushels);
            bushels += harvest;
            grainEaten = grainEatenByRats(bushels);
            bushels -= grainEaten;
            price = newCostOfLand();
            years++;
        }
    }

    public int getNumber(String message) {
        while (true) {
            System.out.print(message);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\"" + scanner.next() + "\" isn't a number!");
            }
        }
    }

    public int askHowManyAcresToBuy(int price, int bushels) {
        int acresBought = getNumber("How many acres do you want to buy? \n");
        while ((price * acresBought) > bushels) {
            acresBought = getNumber("You are broke, how many acres CAN you buy??? \n");
        }

        return acresBought;
    }

    public int askHowManyAcresToSell(int acresOwned) {
        int acresSold = getNumber("How many acres do you want to sell? \n");
        while (acresSold > acresOwned) {
            acresSold = getNumber("You don't have enough land to do that...try again...\n");
        }
        return acresSold;
    }


    public int howMuchGrainToFeedPeople(int bushels) {
        int bushelsFedToPeople = getNumber("How much grain do you want to feed your people? \n");
        while (bushelsFedToPeople > bushels) {
            bushelsFedToPeople = getNumber("You don't have enough grain to feed the people, how much CAN you actually feed them? \n");
        }
        return bushelsFedToPeople;
    }

    public int askHowManyAcresToPlant(int acresOwned, int population, int bushels) {
        int acresToPlant = getNumber("How many acres do you want to plant? \n");
        while (acresToPlant > acresOwned || acresToPlant > (bushels / 2) || (population / 10) < acresToPlant) {
            acresToPlant = getNumber("Nope, try again.... \n");
        }
        return acresToPlant;
    }

    public Integer plagueDeaths(int population) {
        //Each year, there is a 15% chance of a horrible plague. When this happens,
        // half your people die. Return the number of plague deaths (possibly zero).
        int number_of_plague = 0;
        if (rand.nextInt(100) > 85) {
            number_of_plague = Math.floorDiv(population, 2);
        }
        return number_of_plague;
    }

    public Integer starvationDeaths(int population, int bushelsFedToPeople) {
        //Each person needs 20 bushels of grain to survive. If you feed them more than this,
        //they are happy, but the grain is still gone. You don't get any benefit from having
        //happy subjects. Return the number of deaths from starvation (possibly zero).
        int deaths = 0;
        int fullPeople = Math.floorDiv(bushelsFedToPeople, 20);
        if (fullPeople >= 0 && fullPeople < population) {
            deaths = population - (fullPeople);
        }
        return deaths;
    }

    public boolean uprising(int population, int howManyPeopleStarved) {
        //Return true if more than 45% of the people starve. (This will cause you to be immediately
        // thrown out of office, ending the game.)
        if(howManyPeopleStarved > (population * .45)){
            System.out.println("You failed, and were ousted from office...");
            return true;
        }return false;
    }

    public Integer immigrants(int population, int acresOwned, int grainInStorage) {
        int immigrants = (((20 * acresOwned) + (grainInStorage)) / ((100 * population) + 1));
        //Nobody will come to the city if people are starving (so don't call this method). If everyone
        // is well-fed, compute how many people come to the city as:
        return immigrants;
    }

    public int harvest(int acres, int bushelsUsedAsSeed) {
        int harvest = 0;
        int harvestPercent = rand.nextInt(1, 6) + 1;
        harvest = acres * harvestPercent - bushelsUsedAsSeed; //look at bushelsUsedAsSeed
        return harvest;
    }

    public int grainEatenByRats(int bushels) {
        int grainEaten = 0;
        if (rand.nextInt(100) > 60) {
            grainEaten = (rand.nextInt(10, 30) + 1) * bushels;
        }
        return grainEaten;
    }

    public int newCostOfLand() {
        return rand.nextInt(17, 23) + 17;
    }

}

