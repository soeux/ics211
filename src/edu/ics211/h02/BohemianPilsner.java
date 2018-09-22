package edu.ics211.h02;

/**
 * Creates IndiaPaleAle Beers.
 *
 * @author Christian Mancha
 */
public class BohemianPilsner extends Pilsner {
    /**
     * Exists for BohemianPilsner to set the correct type of beer.
     *
     * @param name The name of the Pilsner.
     * @param ibu  The IBU of the Pilsner.
     * @param abv  The ABV of the Pilsner.
     */
    public BohemianPilsner(String name, Integer ibu, Double abv) {
        super(name, BeerType.BOHEMIAN_PILSNER, ibu, abv);

        // make a check for IBU and ABV
        if (ibu < 35 || ibu > 45) {
            throw new IllegalArgumentException("IBU must be between 35 and 45.");
        }

        if (abv < 4.2 || abv > 5.4) {
            throw new IllegalArgumentException("ABV must be between 4.2 and 5.4");
        }
    }

    /**
     * Existis for BohemianPilsner to set the correct type of beer.
     *
     * @param name The name of the Pilsner.
     */
    public BohemianPilsner(String name) {
        // generate random – valid – IBU and ABV values
        super(name, BeerType.BOHEMIAN_PILSNER, (int) (Math.random() * ((45 - 35) + 1)) + 35, (Math.random() * (5.4 - 4.2)) + 4.2);
    }
}
