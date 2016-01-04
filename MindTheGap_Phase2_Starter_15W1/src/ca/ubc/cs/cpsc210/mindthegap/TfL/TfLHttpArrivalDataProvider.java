package ca.ubc.cs.cpsc210.mindthegap.TfL;

import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Wrapper for TfL Arrival Data Provider
 */
public class TfLHttpArrivalDataProvider extends AbstractHttpDataProvider {
    private Station stn;

    public TfLHttpArrivalDataProvider(Station stn) {
        super();
        this.stn = stn;
    }

    @Override
    /**
     * Produces URL used to query TfL web service for expected arrivals at
     * station specified in call to constructor.
     *
     * @returns URL to query TfL web service for arrival data
     */
    protected URL getURL() throws MalformedURLException {

        StringBuilder sbline = new StringBuilder();

        List<String> lineId = new LinkedList<>();

        for (Line L : stn.getLines()) {

            String LID = L.getId();

            lineId.add(LID);

        }
        for (String s : lineId) {

            sbline.append(s+",");
        }

        String line = sbline.toString();

        String request = "https://api.tfl.gov.uk/Line/" + line + "/Arrivals?stopPointId=" + stn.getID() + "&app_id=&app_key=";

        return new URL(request);
    }
}
