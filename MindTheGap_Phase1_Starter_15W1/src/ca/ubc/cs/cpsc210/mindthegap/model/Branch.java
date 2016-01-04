package ca.ubc.cs.cpsc210.mindthegap.model;

import ca.ubc.cs.cpsc210.mindthegap.parsers.BranchStringParser;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;


import java.util.Iterator;
import java.util.List;

/**
 * A branch of a line consisting of a path of lat/lon points.
 * These points are used to draw the branch on a map.  Note that the points used to
 * represent the branch are not necessarily co-located with stations.
 */
public class Branch implements Iterable<LatLon> {
    private List<LatLon> pts;
    private String lineString;

    /**
     * Constructs a Branch by parsing the points that define the branch from
     * the given string.
     *
     * @param lineString  string of coordinates representing points on branch
     */
    public Branch(String lineString) {
        this.pts = BranchStringParser.parseBranch(lineString);
    }

    /**
     * Get list of all points on this branch
     *
     * @return  all points on branch
     */
    public List<LatLon> getPoints() {
        return pts;   // stub
    }

    /**
     * Two branches are equal if their lists of points are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Branch))
            return false;

        Branch latLons = (Branch) o;

        return !(lineString != null ? !lineString.equals(latLons.lineString) : latLons.lineString != null);
    }

    /**
     * Two branches are equal if their lists of points are equal
     */
    @Override
    public int hashCode() {
        return lineString != null ? lineString.hashCode() : 0;
    }

    @Override
    public Iterator<LatLon> iterator() {
        // Do not modify the implementation of this method!
        return pts.iterator();
    }
}
