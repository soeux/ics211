package edu.ics211.h03;
// 20180901 - updated h02 to h03

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// 20180901 - updated h01 to h02
import edu.ics211.h02.AbvComparator;
import edu.ics211.h02.Beer;
// 20180906 - unused import statement
//import edu.ics211.h02.IBrewery;
import edu.ics211.h02.IbuComparator;
import edu.ics211.h02.ManoaBrewing;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

/**
 * Represents a FlightSorterTest.
 *
 * @author Cam Moore
 */
public class FlightSorterTest {
    private ArraySorter<Beer> sorter;
    private ManoaBrewing brewrey;
    private Beer[] shortFlight;
    private Beer[] longFlight;
    private Beer[] veryLongFlight;
    private IbuComparator ibuC;
    private AbvComparator abvC;

    /** Sets up the brewer. */
    @Before
    public void initialize() {
        sorter = new ArraySorter<Beer>();
        brewrey = ManoaBrewing.getInstance();
        ibuC = new IbuComparator();
        abvC = new AbvComparator();
        shortFlight = new Beer[5];
        for (int i = 0; i < shortFlight.length; i++) {
            // 20180904 - IBrewery has randomBeerType(), therefore ManoaBrewing inherits it
            // and there's no need to rewrite the code in this file
            shortFlight[i] = brewrey.brewBeer("short beer" + i, brewrey.randomBeerType());
        }
        longFlight = new Beer[1000];
        for (int i = 0; i < longFlight.length; i++) {
            longFlight[i] = brewrey.brewBeer("long beer" + i, brewrey.randomBeerType());
        }
        veryLongFlight = new Beer[15000];
        for (int i = 0; i < veryLongFlight.length; i++) {
            veryLongFlight[i] = brewrey.brewBeer("very long beer" + i, brewrey.randomBeerType());
        }
    }

    /**
     * Test method for {@link edu.ics211.h03.ArraySorter<>#insertionSort(E[], java.util.Comparator)}.
     */
    @Test
    public void testInsertionSort() {
        Beer[] copy = Arrays.copyOf(shortFlight, shortFlight.length);
        sorter.insertionSort(copy, ibuC);
        final double shortTime = sorter.getSortTime();
        assertTrue("Flight isn't sorted", isSorted(copy, ibuC));
        sorter.insertionSort(copy, ibuC);
        assertEquals("Wrong number of comparisons",
                sorter.getNumberOfComparisons(), shortFlight.length - 1);
        assertEquals("Wrong number of swaps", sorter.getNumberOfSwaps(), 0);
        sorter.insertionSort(copy, abvC);
        assertTrue("Flight isn't sorted", isSorted(copy, abvC));
        assertTrue("Wrong number of comparisons", sorter.getNumberOfComparisons()
                >= shortFlight.length - 1);
        sorter.insertionSort(copy, abvC);
        assertEquals("Wrong number of comparisons",
                sorter.getNumberOfComparisons(), shortFlight.length - 1);
        assertEquals("Wrong number of swaps", sorter.getNumberOfSwaps(), 0);
        copy = Arrays.copyOf(longFlight, longFlight.length);
        sorter.insertionSort(copy, abvC);
        final double longTime = sorter.getSortTime();
        assertTrue("Bad timing", longTime > shortTime);
        copy = Arrays.copyOf(veryLongFlight, veryLongFlight.length);
        sorter.insertionSort(copy, abvC);
        final double veryLongTime = sorter.getSortTime();
        assertTrue("Bad timing", veryLongTime > longTime);
    }

    /**
     * Test method for {@link edu.ics211.h03.ArraySorter<>#bubbleSort(E[], java.util.Comparator)}.
     */
    @Test
    public void testBubbleSort() {
        Beer[] copy = Arrays.copyOf(shortFlight, shortFlight.length);
        sorter.bubbleSort(copy, ibuC);
        final double shortTime = sorter.getSortTime();
        assertTrue("Flight isn't sorted", isSorted(copy, ibuC));
        sorter.bubbleSort(copy, ibuC);
        assertEquals("Wrong number of comparisons",
                sorter.getNumberOfComparisons(), shortFlight.length - 1);
        assertEquals("Wrong number of swaps", sorter.getNumberOfSwaps(), 0);
        sorter.bubbleSort(copy, abvC);
        assertTrue("Flight isn't sorted", isSorted(copy, abvC));
        assertTrue("Wrong number of comparisons", sorter.getNumberOfComparisons()
                >= shortFlight.length - 1);
        sorter.bubbleSort(copy, abvC);
        assertEquals("Wrong number of comparisons",
                sorter.getNumberOfComparisons(), shortFlight.length - 1);
        assertEquals("Wrong number of swaps", sorter.getNumberOfSwaps(), 0);
        copy = Arrays.copyOf(longFlight, longFlight.length);
        sorter.bubbleSort(copy, abvC);
        final double longTime = sorter.getSortTime();
        assertTrue("Bad timing", longTime > shortTime);
        copy = Arrays.copyOf(veryLongFlight, veryLongFlight.length);
        sorter.bubbleSort(copy, abvC);
        final double veryLongTime = sorter.getSortTime();
        assertTrue("Bad timing", veryLongTime > longTime);
    }

    /**
     * Test method for {@link edu.ics211.h03.ArraySorter<>#selectionSort(E[], java.util.Comparator)}.
     */
    @Test
    public void testSelectionSort() {
        Beer[] copy = Arrays.copyOf(shortFlight, shortFlight.length);
        sorter.selectionSort(copy, ibuC);
        final double shortTime = sorter.getSortTime();
        assertTrue("Flight isn't sorted", isSorted(copy, ibuC));
        sorter.selectionSort(copy, ibuC);
        assertEquals("Wrong number of comparisons",
                sorter.getNumberOfComparisons(), (4 + 3 + 2 + 1));
        assertEquals("Wrong number of swaps", sorter.getNumberOfSwaps(), 0);
        sorter.selectionSort(copy, abvC);
        assertTrue("Flight isn't sorted", isSorted(copy, abvC));
        assertTrue("Wrong number of comparisons", sorter.getNumberOfComparisons()
                >= shortFlight.length - 1);
        sorter.selectionSort(copy, abvC);
        assertEquals("Wrong number of comparisons",
                sorter.getNumberOfComparisons(), (4 + 3 + 2 + 1));
        assertEquals("Wrong number of swaps", sorter.getNumberOfSwaps(), 0);
        copy = Arrays.copyOf(longFlight, longFlight.length);
        sorter.selectionSort(copy, abvC);
        final double longTime = sorter.getSortTime();
        assertTrue("Bad timing", longTime > shortTime);
        copy = Arrays.copyOf(veryLongFlight, veryLongFlight.length);
        // 20180921 - selectionSort seemed to be dependent upon the changes i made in bubbleSort. was this on purpose?
        // sorter.bubbleSort(copy, abvC);
        sorter.selectionSort(copy, abvC);
        final double veryLongTime = sorter.getSortTime();
        assertTrue("Bad timing", veryLongTime > longTime);
    }

    /**
     * Tests the times for sorting sorted arrays.
     */
    @Test
    public void testCompareSortTimes() {
        Beer[] copy = Arrays.copyOf(veryLongFlight, veryLongFlight.length);
        sorter.insertionSort(copy, abvC);
        sorter.insertionSort(copy, abvC);
        final double insertionTime = sorter.getSortTime();
        copy = Arrays.copyOf(veryLongFlight, veryLongFlight.length);
        sorter.bubbleSort(copy, abvC);
        sorter.bubbleSort(copy, abvC);
        final double bubbleTime = sorter.getSortTime();
        copy = Arrays.copyOf(veryLongFlight, veryLongFlight.length);
        sorter.selectionSort(copy, abvC);
        sorter.selectionSort(copy, abvC);
        final double selectionTime = sorter.getSortTime();
        assertTrue("Not BigO(N)", bubbleTime < selectionTime);
        assertTrue("Not BigO(N)", insertionTime < selectionTime);
    }

    // 20180904 - why does this method exist when another one does in IBrewery, therefore ManoaBrewing?
//    private String randomBeerType() {
//        int val = (int) (Math.random() * 3);
//        switch (val) {
//            case 0:
//                return IBrewery.PILSNER;
//            case 1:
//                return IBrewery.BOHEMIAN_PILSNER;
//            case 2:
//                return IBrewery.INDIA_PALE_ALE;
//            default:
//                return IBrewery.INDIA_PALE_ALE;
//        }
//    }

    private boolean isSorted(Beer[] data, Comparator<Beer> comp) {
        for (int i = 0; i < data.length - 1; i++) {
            if (comp.compare(data[i], data[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }
}