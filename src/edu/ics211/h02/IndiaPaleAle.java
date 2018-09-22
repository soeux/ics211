package edu.ics211.h02;

/**
 * what does it do?
 *
 * @author Christian Mancha
 */
public class IndiaPaleAle extends Beer {
    /**
     * Constructor that takes the name, IBU and ABV to create a new IndiaPaleAle.
     *
     * @param name Name of the IndiaPaleAle.
     * @param ibu  IBU of the IndiaPaleAle.
     * @param abv  ABV of the IndiaPaleAle.
     */
    public IndiaPaleAle(String name, Integer ibu, Double abv) {
        super(name, BeerType.INDIA_PALE_ALE, ibu, abv);

        // check if IBU and ABV are within acceptable values.
        if (ibu < 40 || ibu > 100) {
            throw new IllegalArgumentException("IBU must be between 40 and 100");
        }

        if (abv < 5.0 || abv > 10.0) {
            throw new IllegalArgumentException("ABV must be between 5.0 and 10.0");
        }
    }

    /**
     * Constructor that takes name and type to create a new IndiaPaleAle.
     *
     * @param name Name of the IndiaPaleAle.
     */
    public IndiaPaleAle(String name) {
        // generate random – valid – IBU and ABV values
        super(name, BeerType.INDIA_PALE_ALE, (int) (Math.random() * ((100 - 40) + 1)) + 40, (Math.random() * (10.0 - 5.0)) + 5.0);
    }
}
