package ca.ubc.cs.cpsc210.mindthegap.tests.parsers;

import ca.ubc.cs.cpsc210.mindthegap.parsers.BranchStringParser;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

/**
 * Created by Ford on 11/5/15.
 */
public class BranchStringParserTest {
    String branch = "[[[0.093493,51.6037],[0.091015,51.5956],[0.088596,51.5857]]]";
    LatLon oh = new LatLon(51.6037,0.093493);
    LatLon my = new LatLon(51.5956,0.091015);
    LatLon god = new LatLon(51.5857,0.088596);
    List<LatLon> dingle = new ArrayList<LatLon>();

    @Before
    public void setUp() throws JSONException {
        dingle.add(oh);
        dingle.add(my);
        dingle.add(god);
    }

    @Test
    public void testBranchStringParser() throws JSONException {

        assertEquals(dingle, BranchStringParser.parseBranch(branch));
    }
}
