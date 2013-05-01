package edu.lmu.cs.eccms;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import edu.lmu.cs.eccms.Index;
import edu.lmu.cs.eccms.Eccms;

/**
 * Simple test using the WicketTester
 */
public class TestIndex {
    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new Eccms());
    }

    @Test
    public void homepageRendersSuccessfully() {
        // start and render the test page
        tester.startPage(Index.class);

        // assert rendered page class
        tester.assertRenderedPage(Index.class);
    }
}
