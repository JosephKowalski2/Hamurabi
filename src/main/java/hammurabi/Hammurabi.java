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

    public static void main(String[] args) { // required in every Java program
        new Hammurabi().playGame();
    }


    void playGame() {

// initial values @ start of game
        int years = 1;
        int population = 100;
        int bushels = 2800;
        int acres = 1000;
        int price = 19;
// game values
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


//game loop
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

//ask users to buy acres
            System.out.println("You can buy " + (bushels / price) + " acres of land.");
            acresBought = askHowManyAcresToBuy(price, bushels);
            acres += acresBought;
            bushels -= acresBought * price;
            System.out.println("You have " + (acres) + " acres now. And " + bushels + " bushels in the storage.");

 //if they haven't bought acres, ask if they want to sell
            if (acresBought == 0) {
                acresSold = askHowManyAcresToSell(acres);
                acres -= acresSold;
                bushels += acresSold * price;
                System.out.println("You currently have " + (bushels) + " bushels and " + acres + " acres after you sold land.");
            }

//ask how much grain to feed people
            System.out.println("It takes " + population * 20 + " bushels to feed everyone.");
            bushelsFedToPeople = howMuchGrainToFeedPeople(bushels);
            bushels -= bushelsFedToPeople;
            System.out.println("After you fed your people you have " + bushels + " bushels remaining.");
            System.out.println("*** Remember, you need 2 bushels per acre, current is " + bushels + " " +
                    "\n and you need 1 peron per 10 acre, current population is " + population + ".");


//ask how many acres to plant
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


//starvation
            deaths = starvationDeaths(population, bushelsFedToPeople);
            population -= deaths;
            totalDeaths = deaths + number_of_plague;


//uprising
            if (uprising(population, deaths)) break;


  //immigration
            if (deaths == 0) {

                immigrants = immigrants(population, acres, bushels);

            }
            population += immigrants;


//harvest
            bushelsUsedAsSeed = acresPlanted * 2;
            harvest = harvest(acresPlanted, bushelsUsedAsSeed);
            bushels += harvest;


//rats
            grainEaten = grainEatenByRats(bushels);
            if (grainEaten > 0) {
                System.out.println("*********************************************************************************");
                System.out.println("*********************************************************************************");
                System.out.println("*****A SWARM OF RELENTLESS RATS HAVE APPEARED! " + grainEaten + " bushels has been eaten...*****");
                System.out.println("*********************************************************************************");
                System.out.println("*********************************************************************************");
            }
            bushels -= grainEaten;


//new cost of land
            price = newCostOfLand();
            totalImmigration += immigrants;
            totalGrainEatingByRats += grainEaten;

//end loop
            years++;


            if (years == 10) {

                break;
            }

        }

 //system out final summary
        if (uprising(population, deaths)) {
            System.out.println("The people threw you out because " + deaths + " people died from starvation... " +
                    "Maybe being king doesn't suite you... try Zip Code instead..." + "\n You have lasted " + years + " year(s)! A total of " + totalDeaths + " people" +
                    " died.\n " + totalImmigration + " people have chosen to come to your lousy kingdom and the " +
                    "final population was " + population + ".\n Somehow mutant rats ate a total of " + totalGrainEatingByRats +
                    " bushels... gross...\n Leaving " + bushels + " in storage.\n Finally you monopolized "
                    + acres + " acres of land.");

        } else if (deaths < 10 && ((acres/population)*100) > 10 && years == 10){
            System.out.println("All has come to an end! KING OF THE NORTH!!! YOU WILL BE REMEMBERED!!! You have lasted " + years
                    + " year(s)!\n A total of " + totalDeaths + " people died.\n " + totalImmigration +
                    " people have chosen to come to your amazing kingdom and the final population was "
                    + population + ".\n Somehow mutant rats ate a total of " + totalGrainEatingByRats +
                    " bushels... gross...\n Leaving " + bushels + " in storage.\n Finally you monopolized "
                    + acres + " acres of land.");

        }else if (deaths < 15 && ((acres/population)*100) > 5 && years == 10){
            System.out.println("All has come to an end! You were an average king...You have lasted " + years
                    + " year(s)!\n A total of " + totalDeaths + " people died.\n " + totalImmigration +
                    " people have chosen to come to your amazing kingdom and the final population was "
                    + population + ".\n Somehow mutant rats ate a total of " + totalGrainEatingByRats +
                    " bushels... gross...\n Leaving " + bushels + " in storage.\n Finally you monopolized "
                    + acres + " acres of land.");

        }else if (deaths < 20 && ((acres/population)*100) > 0 && years == 10){
            System.out.println("All has come to an end! Your people weren't happy but I guess you did it... king...You have lasted " + years
                    + " year(s)!\n A total of " + totalDeaths + " people died.\n " + totalImmigration +
                    " people have chosen to come to your amazing kingdom and the final population was "
                    + population + ".\n Somehow mutant rats ate a total of " + totalGrainEatingByRats +
                    " bushels... gross...\n Leaving " + bushels + " in storage.\n Finally you monopolized "
                    + acres + " acres of land.");

        }else {
            System.out.println("All has come to an end! You call yourself a king? Psh... You have lasted " + years
                    + " year(s)!\n A total of " + totalDeaths + " people died.\n " + totalImmigration +
                    " people have chosen to come to your amazing kingdom and the final population was "
                    + population + ".\n Somehow mutant rats ate a total of " + totalGrainEatingByRats +
                    " bushels... gross...\n Leaving " + bushels + " in storage.\n Finally you monopolized "
                    + acres + " acres of land.");
        }
    }

//scanner
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
//acres to buy
    public int askHowManyAcresToBuy(int price, int bushels) {
        int acresBought = getNumber("How many acres do you want to buy? \n");
        while (((price * acresBought) > bushels) || (acresBought < 0)) {
            acresBought = getNumber("You can't do that, how many acres CAN you buy??? \n");
        }return acresBought;
    }
//acres to sell
    public int askHowManyAcresToSell(int acresOwned) {
        int acresSold = getNumber("How many acres do you want to sell? \n");
        while ((acresSold > acresOwned) || (acresSold < 0))  {
            acresSold = getNumber("That makes no sense...try again...\n");
        }
        return acresSold;
    }

//grain to feed
    public int howMuchGrainToFeedPeople(int bushels) {
        int bushelsFedToPeople = getNumber("How much grain do you want to feed your people? \n");
        while ((bushelsFedToPeople > bushels) || bushelsFedToPeople < 0) {
            bushelsFedToPeople = getNumber("Are you serious?, how much are you actually going to feed them? \n");
        }
        return bushelsFedToPeople;
    }
//acres to plant
    public int askHowManyAcresToPlant(int acresOwned, int population, int bushels) {
        int acresToPlant = getNumber("How many acres do you want to plant? \n");
        while (acresToPlant > acresOwned || acresToPlant > (bushels / 2) || (population * 10) < acresToPlant || (acresToPlant < 0)) {
            acresToPlant = getNumber("Nope, try again.... \n");
        }
        return acresToPlant;
    }
//chance for plague
    public Integer plagueDeaths(int population) {
        //Each year, there is a 15% chance of a horrible plague. When this happens,
        // half your people die. Return the number of plague deaths (possibly zero).
        int number_of_plague = 0;
        if (rand.nextInt(100) > 85) {
            number_of_plague = Math.floorDiv(population, 2);

        }
        return number_of_plague;
    }
//starvation
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
//uprising
    public boolean uprising(int population, int howManyPeopleStarved) {
        //Return true if more than 45% of the people starve. (This will cause you to be immediately
        // thrown out of office, ending the game.)
        return howManyPeopleStarved > (population * .45);
    }
//immigrants
    public Integer immigrants(int population, int acresOwned, int grainInStorage) {
        //Nobody will come to the city if people are starving (so don't call this method). If everyone
        // is well-fed, compute how many people come to the city as:
        return (((20 * acresOwned) + (grainInStorage)) / ((100 * population)) + 1);
    }
//harvest
    public int harvest(int acres, int bushelsUsedAsSeed) {
        int harvest;
        int harvestPercent = rand.nextInt(1, 7) + 1;
        harvest = acres * harvestPercent - bushelsUsedAsSeed; //look at bushelsUsedAsSeed
        return harvest;
    }
//grain eaten by rats
    public int grainEatenByRats(int bushels) {
        int grainEaten = 0;
        if (rand.nextInt(100) > 60) {
            grainEaten = (rand.nextInt(10, 30) * bushels) / 100;

        }
        return grainEaten;
    }
//new cost of land
    public int newCostOfLand() {
        return rand.nextInt(17, 24);
    }

}

