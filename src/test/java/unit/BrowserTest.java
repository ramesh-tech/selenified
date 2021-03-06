package unit;

import com.coveros.selenified.Browser;
import com.coveros.selenified.exceptions.InvalidBrowserException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

public class BrowserTest {

    @Test
    public void browsersTest() throws InvalidBrowserException {
        assertEquals(Browser.lookup("CHROME"), Browser.BrowserName.CHROME);
        assertEquals(Browser.lookup("chrome"), Browser.BrowserName.CHROME);
        assertEquals(Browser.lookup("cHroMe"), Browser.BrowserName.CHROME);
    }

    @Test
    public void eachBrowsersTest() throws InvalidBrowserException {
        assertEquals(Browser.lookup("NONE"), Browser.BrowserName.NONE);
        assertEquals(Browser.lookup("HTMLUNIT"), Browser.BrowserName.HTMLUNIT);
        assertEquals(Browser.lookup("FIREFOX"), Browser.BrowserName.FIREFOX);
        assertEquals(Browser.lookup("CHROME"), Browser.BrowserName.CHROME);
        assertEquals(Browser.lookup("INTERNETEXPLORER"), Browser.BrowserName.INTERNETEXPLORER);
        assertEquals(Browser.lookup("EDGE"), Browser.BrowserName.EDGE);
        assertEquals(Browser.lookup("OPERA"), Browser.BrowserName.OPERA);
        assertEquals(Browser.lookup("SAFARI"), Browser.BrowserName.SAFARI);
        assertEquals(Browser.lookup("PHANTOMJS"), Browser.BrowserName.PHANTOMJS);
    }

    @Test(expectedExceptions = InvalidBrowserException.class)
    public void browsersInvalidTest() throws InvalidBrowserException {
        Browser.lookup("HELLOWORLD");
        fail("Expected an InvalidBrowserException");
    }

    @Test(expectedExceptions = InvalidBrowserException.class)
    public void checkNullBrowserTest() throws InvalidBrowserException {
        new Browser(null);
        fail("Expected an InvalidBrowserException");
    }

    @Test(expectedExceptions = InvalidBrowserException.class)
    public void checkInvalidBrowserTest() throws InvalidBrowserException {
        new Browser("HELLOWORLD");
        fail("Expected an InvalidBrowserException");
    }

    @Test
    public void checkBrowserNameTest() throws InvalidBrowserException {
        Browser browser = new Browser("FIREFOX");
        assertEquals(browser.getName(), Browser.BrowserName.FIREFOX);
        assertNull(browser.getVersion());
        assertNull(browser.getPlatform());
        assertNull(browser.getScreensize());
    }

    @Test(expectedExceptions = InvalidBrowserException.class)
    public void checkBrowserDetailsBadNameTest() throws InvalidBrowserException {
        new Browser("name=HELLOWORLD");
        fail("Expected an InvalidBrowserException");
    }

    @Test(expectedExceptions = InvalidBrowserException.class)
    public void checkBrowserDeviceNoNameTest() throws InvalidBrowserException {
        new Browser("version=1.0.5");
        fail("Expected an InvalidBrowserException");
    }

    @Test
    public void checkBrowserVersionTest() throws InvalidBrowserException {
        Browser browser = new Browser("name=Firefox&version=1.0.5");
        assertEquals(browser.getName(), Browser.BrowserName.FIREFOX);
        assertEquals(browser.getVersion(), "1.0.5");
        assertNull(browser.getPlatform());
        assertNull(browser.getScreensize());
    }

    @Test
    public void checkBrowserPlatformTest() throws InvalidBrowserException {
        Browser browser = new Browser("name=Firefox&platform=Windows 10");
        assertEquals(browser.getName(), Browser.BrowserName.FIREFOX);
        assertNull(browser.getVersion());
        assertEquals(browser.getPlatform(), Platform.WIN10);
        assertNull(browser.getScreensize());
    }

    @Test
    public void checkBrowserScreensizeTest() throws InvalidBrowserException {
        Browser browser = new Browser("name=Firefox&screensize=100x200");
        assertEquals(browser.getName(), Browser.BrowserName.FIREFOX);
        assertNull(browser.getVersion());
        assertNull(browser.getPlatform());
        assertEquals(browser.getScreensize(), "100x200");
    }

    @Test
    public void checkBrowserScreensizeMaximumTest() throws InvalidBrowserException {
        Browser browser = new Browser("name=Firefox&screensize=maximum");
        assertEquals(browser.getName(), Browser.BrowserName.FIREFOX);
        assertNull(browser.getVersion());
        assertNull(browser.getPlatform());
        assertEquals(browser.getScreensize(), "maximum");
    }

    @Test
    public void checkBrowserScreensizeMaximumCaseTest() throws InvalidBrowserException {
        Browser browser = new Browser("name=Firefox&screensize=maxImum");
        assertEquals(browser.getName(), Browser.BrowserName.FIREFOX);
        assertNull(browser.getVersion());
        assertNull(browser.getPlatform());
        assertEquals(browser.getScreensize(), "maxImum");
    }

    @Test
    public void checkBrowserScreensizeBadTest() throws InvalidBrowserException {
        Browser browser = new Browser("name=Firefox&screensize=large");
        assertEquals(browser.getName(), Browser.BrowserName.FIREFOX);
        assertNull(browser.getVersion());
        assertNull(browser.getPlatform());
        assertNull(browser.getScreensize());
    }

    @Test
    public void checkBrowserAllDetailsTest() throws InvalidBrowserException {
        Browser browser = new Browser("name=Firefox&version=1.0.5&platform=Windows 10&screensize=maximum");
        assertEquals(browser.getName(), Browser.BrowserName.FIREFOX);
        assertEquals(browser.getVersion(), "1.0.5");
        assertEquals(browser.getPlatform(), Platform.WIN10);
        assertEquals(browser.getScreensize(), "maximum");
    }

    @Test
    public void checkBrowserDetailsNameTest() throws InvalidBrowserException {
        Browser browser = new Browser("FIREFOX");
        assertEquals(browser.getDetails(), "Firefox");
    }

    @Test
    public void checkBrowserDetailsVersionTest() throws InvalidBrowserException {
        Browser browser = new Browser("name=Firefox&version=1.0.5");
        assertEquals(browser.getDetails(), "Firefox 1.0.5");
    }

    @Test
    public void checkBrowserDetailsPlatformTest() throws InvalidBrowserException {
        Browser browser = new Browser("name=Firefox&platform=Windows 10");
        assertEquals(browser.getDetails(), "Firefox WIN10");
    }

    @Test
    public void checkBrowserDetailsScreensizeTest() throws InvalidBrowserException {
        Browser browser = new Browser("name=Firefox&screensize=100x200");
        assertEquals(browser.getDetails(), "Firefox 100x200");
    }

    @Test
    public void checkBrowserDetailsScreensizeMaximumTest() throws InvalidBrowserException {
        Browser browser = new Browser("name=Firefox&screensize=maximum");
        assertEquals(browser.getDetails(), "Firefox maximum");
    }

    @Test
    public void checkBrowserDetailsAllDetailsTest() throws InvalidBrowserException {
        Browser browser = new Browser("name=Firefox&version=1.0.5&platform=Windows 10&screensize=100x200");
        assertEquals(browser.getDetails(), "Firefox 1.0.5 WIN10 100x200");
    }
}