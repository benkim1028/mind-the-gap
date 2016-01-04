package ca.ubc.cs.cpsc210.mindthegap.parsers;


import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Parser for route strings in TfL line data
 */
public class BranchStringParser {

    /**
     * Parse a branch string obtained from TFL
     *
     * @param branch  branch string
     * @return       list of lat/lon points parsed from branch string
     */
    public static List<LatLon> parseBranch(String branch) {
        if (branch.length() == 0)
            return new ArrayList<LatLon>();
        try {
            String balls = branch.substring(1, branch.length() - 1);
            JSONArray array = new JSONArray(balls);
            List<LatLon> LLS = new ArrayList<LatLon>();
            for (int i = 0; i < array.length(); i++) {
                JSONArray pt = array.getJSONArray(i);
                Double long1 = pt.getDouble(0);
                Double lat1 = pt.getDouble(1);
                LatLon foo = new LatLon(lat1, long1);
                LLS.add(foo);
            }
            return LLS;
        }
        catch (JSONException e) {
            return new ArrayList<LatLon>();
        }
//        List<LatLon> LLS = new ArrayList<LatLon>();
//        List<Character> bin = new ArrayList<Character>();
//        List<Character> ticker = new ArrayList<Character>();
//
//        StringBuffer longitude = new StringBuffer("");
//        StringBuffer latitude = new StringBuffer("");
//        char previous = '-';
//
//        Double long1 = 0.0;
//        Double lat1 = 0.0;
//        Boolean lat = false;
//        Boolean lon = true;
//
//        char[] str2CharArray = branch.toCharArray();

//        for (char c : str2CharArray) {
//            if (lon)
//                longitude.append(c);
//            if (lat)
//                latitude.append(c);
//            if (c == ',' && previous != ']') {
//                longitude.delete(0,4);
//                longitude.deleteCharAt(longitude.length() - 1);
//                long1 = long1.parseDouble(longitude.toString());
//                lon = false;
//                lat = true;
//                longitude = new StringBuffer("x"); }
//            if (c == ']' && previous != ',' && previous != ']') {
//                latitude.deleteCharAt(latitude.length() - 1);
//                lat1 = lat1.parseDouble(latitude.toString());
//                LatLon LL = new LatLon(lat1,long1);
//                LLS.add(LL);
//                lon = true;
//                lat = false;
//                latitude = new StringBuffer(""); }
//            previous = c; }
//        return LLS;
    }
}
