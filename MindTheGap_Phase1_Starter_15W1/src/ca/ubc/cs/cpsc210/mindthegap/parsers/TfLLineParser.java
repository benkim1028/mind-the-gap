package ca.ubc.cs.cpsc210.mindthegap.parsers;

import ca.ubc.cs.cpsc210.mindthegap.model.*;
import ca.ubc.cs.cpsc210.mindthegap.parsers.exception.TfLLineDataMissingException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A parser for the data returned by TFL line route query
 */
public class TfLLineParser extends TfLAbstractParser {

    /**
     * Parse line from JSON response produced by TfL.  No stations added to line if TfLLineDataMissingException
     * is thrown.
     *
     * @param lmd              line meta-data
     * @return                 line parsed from TfL data
     * @throws JSONException   when JSON data does not have expected format
     * @throws TfLLineDataMissingException when
     * <ul>
     *  <li> JSON data is missing lineName, lineId or stopPointSequences elements </li>
     *  <li> for a given sequence: </li>
     *    <ul>
     *      <li> the stopPoint array is missing </li>
     *      <li> all station elements are missing one of name, lat, lon or stationId elements </li>
     *    </ul>
     * </ul>
     */
    public static Line parseLine(LineResourceData lmd, String jsonResponse)
            throws JSONException, TfLLineDataMissingException {
        Boolean acc = false;
        Boolean tflexcthrown = false;
        Station stnToBeAdded;
        JSONObject obj = new JSONObject(jsonResponse);

        String lineName = obj.getString("lineName");
        String lineID = obj.getString("lineId");

        Line l = new Line(lmd,lineID,lineName);

        JSONArray branches = obj.getJSONArray("lineStrings");

        String parsedBranches = branches.toString();

        BranchStringParser.parseBranch(parsedBranches);

        for(int i=0; i < branches.length(); i++) {
            String branchString = branches.getString(i);
            Branch b = new Branch(branchString);
            l.addBranch(b);
        }

        JSONArray stopPointSequence = obj.getJSONArray("stopPointSequences");

        for(int i=0; i < stopPointSequence.length(); i++) {
            JSONObject objWStopArray = stopPointSequence.getJSONObject(i);


            if(!objWStopArray.has("stopPoint"))
            {tflexcthrown = true;
                throw new TfLLineDataMissingException();}
            JSONArray stopPointArray = objWStopArray.getJSONArray("stopPoint");


            for(int n=0; n< stopPointArray.length(); n++) {
                JSONObject objWStations = stopPointArray.getJSONObject(n);
                if(objWStations.has("name") && objWStations.has("stationId") && objWStations.has("lat") && objWStations.has("lon"))
                {acc = true;}

                // delete parsename?

                String stationName = parseName(objWStations.getString("name"));
                String stationId = objWStations.getString("stationId");
                Double latitude = objWStations.getDouble("lat");
                Double longitude = objWStations.getDouble("lon");

                if(StationManager.getInstance().getStationWithId(stationId) == null) {
                    LatLon cOS = new LatLon(latitude,longitude);
                    stnToBeAdded = new Station(stationId, stationName, cOS);}

                else {stnToBeAdded = StationManager.getInstance().getStationWithId(stationId);}

                l.addStation(stnToBeAdded);

            }
            if(!acc)
            {l.clearStations();
                throw new TfLLineDataMissingException();}

        }

        return l;
    }
}
