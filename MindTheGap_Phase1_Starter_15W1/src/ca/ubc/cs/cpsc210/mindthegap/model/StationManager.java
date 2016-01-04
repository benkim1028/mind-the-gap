package ca.ubc.cs.cpsc210.mindthegap.model;

import ca.ubc.cs.cpsc210.mindthegap.model.exception.StationException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import ca.ubc.cs.cpsc210.mindthegap.util.SphericalGeometry;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static ca.ubc.cs.cpsc210.mindthegap.util.SphericalGeometry.distanceBetween;

/**
 * Manages all tube stations on network.
 *
 * Singleton pattern applied to ensure only a single instance of this class that
 * is globally accessible throughout application.
 */
public class StationManager implements Iterable<Station> {
    public static final int RADIUS = 10000;
    private static StationManager instance;
    private Set<Station> stns;
    private Station select;

    /**
     * Constructs station manager with empty set of stations and null as the selected station
     */
    private StationManager() {
        select = null;
        this.stns = new HashSet<Station>();
    }

    /**
     * Gets one and only instance of this class
     *
     * @return  instance of class
     */
    public static StationManager getInstance() {
        // Do not modify the implementation of this method!
        if(instance == null) {
            instance = new StationManager();
        }

        return instance;
    }

    public Station getSelected() {
        return select;
    }

    /**
     * Get station with given id or null if no such station is found in this manager
     *
     * @param id  the id of this station
     *
     * @return  station with given id or null if no such station is found
     */
    public Station getStationWithId(String id) {
        for (Station s : stns) {
            if (stns.contains(s.getID()))
                return s;
        }

        return null;
    }

    // this taste... it's of a spy

    /**
     * Set the station selected by user
     *
     * @param selected   station selected by user
     * @throws StationException when station manager doesn't contain selected station
     */
    public void setSelected(Station selected) throws StationException {
        this.select = selected;
    }

    /**
     * Clear selected station (selected station is null)
     */
    public void clearSelectedStation() {
        this.select = null;
    }

    /**
     * Add all stations on given line. Station added only if it is not already in the collection.
     *
     * @param line  the line from which stations are to be added
     */
    public void addStationsOnLine(Line line) {
        for (Station s : line)
            if (!stns.contains(s))
                stns.add(s);
    }

    /**
     * Get number of stations managed
     *
     * @return  number of stations added to manager
     */
    public int getNumStations() {
        return stns.size();
    }

    /**
     * Remove all stations from station manager
     */
    public void clearStations() {
        stns.clear();
    }

    /**
     * Find nearest station to given point.  Returns null if no station is closer than RADIUS metres.
     *
     * @param pt  point to which nearest station is sought
     * @return    station closest to pt but less than 10,000m away; null if no station is within RADIUS metres of pt
     */
    public Station findNearestTo(LatLon pt) {

        Double minVal = 10000.0;
        Station stack = null;

        for (Station s : stns) {
            if (distanceBetween(s.getLocn(), pt) < minVal) {
                minVal = distanceBetween(s.getLocn(), pt);
                stack = s;
            }
        }
        return stack;

    }

    @Override
    public Iterator<Station> iterator() {
        // Do not modify the implementation of this method!
        return stns.iterator();
    }
}
