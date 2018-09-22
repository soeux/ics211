package edu.ics211.h02;

/**
 * Outlines what a Beer is and sets out getters/setters methods as well as a comparator.
 *
 * @author Christian Mancha
 */
public abstract class Beer implements Comparable<Beer> {

    // vars
    private String name;
    private BeerType type;
    protected Integer ibu;
    protected Double abv;

    // constructors

    /**
     * default constructor
     */
    public Beer() {}

    /**
     * Constructor that takes name and type to create a new Beer.
     *
     * @param name Name of the Beer.
     * @param type Type of the Beer. See BeerTypes.
     */
    public Beer(String name, BeerType type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Constructor that takes the name, type, IBU and ABV to create a new Beer.
     *
     * @param name Name of the Beer.
     * @param type Type of the beer. See BeerTypes.
     * @param ibu IBU of the Beer.
     * @param abv ABV of the Beer.
     */
    public Beer(String name, BeerType type, Integer ibu, Double abv) {
        this.name = name;
        this.type = type;
        this.ibu = ibu;
        this.abv = abv;
    }

    // setter
    /**
     * Set the name of a Beer.
     *
     * @param name New name of the Beer.
     */
    public void setName(String name) {
        this.name = name;
    }

    // getters
    /**
     * Get the name of a Beer.
     *
     * @return The name of Beer.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the type of a Beer.
     *
     * @return The type of Beer.
     */
    public BeerType getType() {
        return type;
    }

    /**
     * Get the IBU of a Beer.
     *
     * @return The IBU of the Beer.
     */
    public Integer getIbu() {
        return ibu;
    }

    /**
     * Get the ABV of a Beer.
     *
     * @return The ABV of the Beer.
     */
    public Double getAbv() {
        return abv;
    }

    /**
     * Compares beers' name.
     *
     * @param o Object being compared.
     * @return true if both Beers are the same, false otherwise.
     */
    @Override
    public int compareTo(Beer o) {
        return this.name.compareTo(o.name);
    }
}
