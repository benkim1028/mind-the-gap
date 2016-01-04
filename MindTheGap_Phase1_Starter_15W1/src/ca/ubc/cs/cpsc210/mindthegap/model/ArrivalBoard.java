package ca.ubc.cs.cpsc210.mindthegap.model;

import java.util.*;

/**
 * Represents an arrivals board for a particular station, on a particular line,
 * for trains traveling in a particular direction (as indicated by platform prefix).
 *
 * Invariant: maintains arrivals in order of time to station
 * (first train to arrive will be listed first).
 */
public class ArrivalBoard implements Iterable<Arrival> {
    private List<Arrival> arrivals;
    private Line line;
    private String travelDirn;
    private Integer numArrivals;


    /**
     * Constructs an arrival board for the given line with an empty list of arrivals
     * and given travel direction.
     *
     * @param line        line on which arrivals listed on this board operate (cannot be null)
     * @param travelDirn  the direction of travel
     */
    public ArrivalBoard(Line line, String travelDirn) {
        this.line = line;
        this.travelDirn = travelDirn;
        this.arrivals = new ArrayList<Arrival>();
    }

    public Line getLine() {
        return line;   // stub
    }

    public String getTravelDirn() {
        return travelDirn;   // stub
    }


    /**
     * Get total number of arrivals posted on this arrival board
     *
     * @return  total number of arrivals
     */
    public int getNumArrivals() {
        return numArrivals;   // stub
    }

    /**
     * Add a train arrival this arrivals board.
     *
     * @param arrival  the arrival to add to this arrivals board
     */
    public void addArrival(Arrival arrival) {
        int index = 0;
        for (Arrival a : arrivals) {
            if (arrival.getTimeToStationInMins() < a.getTimeToStationInMins())
                arrivals.add(index,arrival);
                index++;
            if (arrivals.size() <= index + 1)
                arrivals.add(arrival);
        }
    }

    /**
     * Clear all arrivals from this arrival board
     */
    public void clearArrivals() {
        arrivals.clear();
    }

    /**
     * Two ArrivalBoards are equal if their lines are equal and travel directions are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrivalBoard)) return false;

        ArrivalBoard arrivals = (ArrivalBoard) o;

        return !(getLine() != null ? !getLine().equals(arrivals.getLine()) : arrivals.getLine() != null);
    }

    /**
     * Two ArrivalBoards are equal if their lines are equal and travel directions are equal
     */
    @Override
    public int hashCode() {
        return getLine() != null ? getLine().hashCode() : 0;
    }

    @Override
    /**
     * Produces an iterator over the arrivals on this arrival board
     * ordered by time to arrival (first train to arrive is produced
     * first).
     */
    public Iterator<Arrival> iterator() {
        // Do not modify the implementation of this method!
        return arrivals.iterator();
    }
}
