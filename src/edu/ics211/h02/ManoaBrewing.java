package edu.ics211.h02;

/**
 * Represents a ManoaBrewing.
 *
 * @author Christian Mancha
 */
public class ManoaBrewing implements IBrewery {
    private static ManoaBrewing instance;

    /**
     * Hidden default constructor, so nothing else can create a ManoaBrewing instance.
     */
    private ManoaBrewing() {}

    /**
     * There can only be one instance of ManoaBrewing.
     *
     * @return The instance of ManoaBrewing.
     */
    public static ManoaBrewing getInstance() {
        if (instance == null) {
            instance = new ManoaBrewing();
        }
        return instance;
    }

    /**
     * Brews the beer with the given type. Note the returned instance is a subclass of beer.
     *
     * @param name the name of the beer.
     * @param type the type of the beer.
     * @return the beer.
     */
    @Override
    public Beer brewBeer(String name, BeerType type) {
        if (type.equals(BeerType.PILSNER)) {
            return new Pilsner(name);
        } else if (type.equals(BeerType.BOHEMIAN_PILSNER)) {
            return new BohemianPilsner(name);
        } else if (type.equals(BeerType.INDIA_PALE_ALE)) {
            return new IndiaPaleAle(name);
        } else {
            return null;
        }
    }

    /**
     * Brews a Pilsner with the given name, ibu and abv.
     *
     * @param name the name of the beer.
     * @param ibu  The International Bitterness Units.
     * @param abv  The alcohol by volume.
     * @return a Pilsner.
     */
    @Override
    public Beer brewPilsner(String name, Integer ibu, Double abv) {
        return new Pilsner(name, ibu, abv);
    }

    /**
     * Brews a Bohemian Pilsner with the given name, ibu and abv.
     *
     * @param name the name of the beer.
     * @param ibu  The International Bitterness Units.
     * @param abv  The alcohol by volume.
     * @return a Bohemian Pilsner.
     */
    @Override
    public Beer brewBohemianPilsner(String name, Integer ibu, Double abv) {
        return new BohemianPilsner(name, ibu, abv);
    }

    /**
     * Brews an India Pale Ale with the given ibu and abv.
     *
     * @param name the name of the beer.
     * @param ibu  The International Bitterness Units.
     * @param abv  The alcohol by volume.
     * @return an India Pale Ale.
     */
    @Override
    public Beer brewIndiaPaleAle(String name, Integer ibu, Double abv) {
        return new IndiaPaleAle(name, ibu, abv);
    }
}
