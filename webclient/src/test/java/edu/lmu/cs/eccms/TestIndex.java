package edu.lmu.cs.eccms;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestIndex {
    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new Eccms());
    }

    @Ignore
    @Test
    public void homepageRendersSuccessfully() {
        // start and render the test page
        tester.startPage(LoginPage.class);

        // assert rendered page class
        tester.assertRenderedPage(LoginPage.class);
    }
}
