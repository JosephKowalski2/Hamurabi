package hammurabi;               // package declaration

import java.sql.SQLOutput;
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


    public static void main(String[] args) { // required in every Java program
        new Hammurabi().playGame();
    }


    void playGame() {

//other methods go here


        int years = 1;
        int population = 100;
        int bushels = 2800;
        int acres = 1000;
        int price = 19;
        int acresBought;
        int acresSold;
        int number_of_plague;
        int bushelsFedToPeople;
        int harvest = 0;
        int deaths = 0;
        int immigrants = 0;
        int grainEaten = 0;
        int acresPlanted;
        int bushelsUsedAsSeed;
        int totalDeaths = 0;
        int totalImmigration = 0;
        int totalGrainEatingByRats = 0;

        while (years <= 10) {
            System.out.println("O great Hammurabi!");
            System.out.println("You are in year " + years + " of your 10 year rule.");
            System.out.println("In the previous year, " + deaths + " people starved to death.");
            System.out.println("In the previous year " + immigrants + " people entered the kingdom.");
            System.out.println("The population is now " + population + ".");
            System.out.println("We harvested " + harvest + " bushels.");
            System.out.println("Rats destroyed " + grainEaten + " bushels, leaving " + bushels + " bushels in storage.");
            System.out.println("The city owns " + acres + " acres of land.");
            System.out.println("Land is currently worth " + price + " bushels per acre.");
            System.out.println("You can buy " + (bushels / price) + " acres of land.");
            acresBought = askHowManyAcresToBuy(price, bushels);
            acres += acresBought;
            bushels -= acresBought * price;
            System.out.println("You have " + (acres) + " acres now. And " + bushels + " bushels.");
            if (acresBought == 0) {
                acresSold = askHowManyAcresToSell(acres);
                acres -= acresSold;
                bushels += acresSold * price;
                System.out.println("You currently have " + (bushels) + " bushels after you sold land.");
            }
            System.out.println("It takes " + population * 20 + " bushels to feed everyone.");
            bushelsFedToPeople = howMuchGrainToFeedPeople(bushels);
            bushels -= bushelsFedToPeople;
            System.out.println("After you fed your people you have " + bushels + " remaining.");
            System.out.println("*** Remember, you need 2 bushels per acre, current is " + bushels + " " +
                    "\n and you need 10 people per acre, current population is " + population);
            acresPlanted = askHowManyAcresToPlant(acres, population, bushels);
            bushels -= acresPlanted * 2;
            number_of_plague = plagueDeaths(population);
            population -= number_of_plague;
            if (number_of_plague > 0) {
                System.out.println("*********************************************************************************");
                System.out.println("*********************************************************************************");
                System.out.println("***********COVID HAS SWEPT THROUGH THE KINGDOM! " + number_of_plague + " people have died...***********");
                System.out.println("*********************************************************************************");
                System.out.println("*********************************************************************************");
            }
            deaths = starvationDeaths(population, bushelsFedToPeople);
            population -= deaths;
            totalDeaths = deaths + number_of_plague;
            if (uprising(population, deaths)) break;
            if (deaths == 0) {

                immigrants = immigrants(population, acres, bushels);

            }
            population += immigrants;
            bushelsUsedAsSeed = acresPlanted * 2;
            harvest = harvest(acresPlanted, bushelsUsedAsSeed);
            bushels += harvest;
            grainEaten = grainEatenByRats(bushels);
            bushels -= grainEaten;
            price = newCostOfLand();
            totalImmigration += immigrants;
            totalGrainEatingByRats += grainEaten;
            years++;
            if (years == 10) {

                break;
            }

        }

        if (uprising(population, deaths)) {
            System.out.println("The people threw you out because " + deaths + " people died from starvation... " +
                    "Maybe being king doesn't suite you... try Zip Code instead..." + "\n You have lasted " + years + " year(s)! A total of " + totalDeaths + " people" +
                    " died.\n " + totalImmigration + " people have chosen to come to your lousy kingdom and the " +
                    "final population was " + population + ".\n Somehow mutant rats ate a total of " + totalGrainEatingByRats +
                    " bushels... gross...\n Leaving " + bushels + " in storage.\n Finally you monopolized "
                    + acres + " acres of land.");
        } else {
            System.out.println("All has come to an end! You have lasted " + years
                    + " year(s)!\n A total of " + totalDeaths + " people died.\n " + totalImmigration +
                    " people have chosen to come to your amazing kingdom and the final population was "
                    + population + ".\n Somehow mutant rats ate a total of " + totalGrainEatingByRats +
                    " bushels... gross...\n Leaving " + bushels + " in storage.\n Finally you monopolized "
                    + acres + " acres of land.");
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
        return howManyPeopleStarved > (population * .45);
    }

    public Integer immigrants(int population, int acresOwned, int grainInStorage) {
        //Nobody will come to the city if people are starving (so don't call this method). If everyone
        // is well-fed, compute how many people come to the city as:
        return (((20 * acresOwned) + (grainInStorage)) / ((100 * population)) + 1);
    }

    public int harvest(int acres, int bushelsUsedAsSeed) {
        int harvest;
        int harvestPercent = rand.nextInt(1, 7) + 1;
        harvest = acres * harvestPercent - bushelsUsedAsSeed; //look at bushelsUsedAsSeed
        return harvest;
    }

    public int grainEatenByRats(int bushels) {
        int grainEaten = 0;
        if (rand.nextInt(100) > 60) {
            grainEaten = (rand.nextInt(10, 30) * bushels) / 100;

        }
        return grainEaten;
    }

    public int newCostOfLand() {
        return rand.nextInt(17, 24);
    }

}

