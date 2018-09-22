package edu.ics211.h02;

/**
 * Create Pilsner Beers.
 *
 * @author Christian Mancha
 */
public class Pilsner extends Beer {
    /**
     * Creates a Pilsner object.
     *
     * @param name The name of the Pilsner.
     * @param ibu  The IBU of the Pilsner.
     * @param abv  The ABV of the Pilsner.
     */
    public Pilsner(String name, Integer ibu, double abv) {
        super(name, BeerType.PILSNER, ibu, abv);

        // check if the IBU and ABV are within acceptable range, if not throw IllegalArgumentException.
        if (ibu < 25 || ibu > 45) {
            throw new IllegalArgumentException("Pilsner IBU needs to be between 25 and 45");
        }

        if (abv < 4.2 || abv > 6.0) {
            throw new IllegalArgumentException("Pilsner ABV needs to be between 4.2 and 6.0");
        }
    }

    /**
     * Creates a Pilsner object with random IBU value in range of 25 to 45 and ABV value in range of 4.2 to 6.0.
     *
     * @param name The name of the Pilsner.
     */
    public Pilsner(String name) {
        super(name, BeerType.PILSNER, (int) (Math.random() * ((45 - 25) + 1)) + 25, (Math.random() * (6.0 - 4.2)) + 4.2);
        // # credit https://www.mkyong.com/java/java-generate-random-integers-in-a-range/
        // for generating a random ABV (Math.random() * ((6.0 - 4.2) + 1)) + 4.2 generates numbers greater than 6.0
    }

    /**
     * Exists for BohemianPilsner to set the correct type of beer.
     *
     * @param name The name of the Pilsner.
     * @param type The type of the Pilsner.
     * @param ibu  The IBU of the Pilsner.
     * @param abv  The ABV of the Pilsner.
     */
    protected Pilsner(String name, BeerType type, Integer ibu, double abv) {
        super(name, type, ibu, abv);
    }

    /**
     * Exists for BohemianPilsner to set the correct type of beer.
     *
     * @param name The name of the Pilsner.
     * @param type The type of the Pilsner.
     */
    protected Pilsner(String name, BeerType type) {
        super(name, type);
    }
}
