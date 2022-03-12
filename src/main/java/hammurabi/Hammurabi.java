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
    int landValue = 19;

    public static void main(String[] args) { // required in every Java program
        new Hammurabi().playGame();
    }

    void playGame() {
        // declare local variables here: grain, population, etc.

    }
        // statements go after the declarations
        int getNumber (String message){
            while (true) {
                System.out.print(message);
                try {
                    return scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("\"" + scanner.next() + "\" isn't a number!");
                }
            }
        }




        //other methods go here
        Integer plagueDeaths ( int population){
            //Each year, there is a 15% chance of a horrible plague. When this happens,
            // half your people die. Return the number of plague deaths (possibly zero).
            int number_of_plague = 0;
            if (rand.nextInt(100) > 85) {
                number_of_plague = Math.floorDiv(population,2);
            }
            return number_of_plague;
        }
        Integer starvationDeaths ( int population, int bushelsFedToPeople) {
            //Each person needs 20 bushels of grain to survive. If you feed them more than this,
            //they are happy, but the grain is still gone. You don't get any benefit from having
            //happy subjects. Return the number of deaths from starvation (possibly zero).
            int deaths =0;
            int fullPeople = Math.floorDiv(bushelsFedToPeople,20);
            if(fullPeople >= 0 && fullPeople < population) {
                deaths = population-(fullPeople);
            }
              return deaths;
        }

        boolean uprising(int population, int howManyPeopleStarved) {
            //Return true if more than 45% of the people starve. (This will cause you to be immediately
            // thrown out of office, ending the game.)
            return howManyPeopleStarved > (population * .45);
        }

        Integer immigrants(int population, int acresOwned, int grainInStorage){
            //Nobody will come to the city if people are starving (so don't call this method). If everyone
            // is well-fed, compute how many people come to the city as:
            return(((20 * acresOwned)+ (grainInStorage)) / ((100 * population) + 1));
        }

    }
