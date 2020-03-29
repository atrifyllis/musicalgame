package gr.alx.game.controllers;

import gr.alx.game.ejb.GamePollDao;
import gr.alx.game.ejb.UserDao;
import gr.alx.game.model.GamePoll;
import gr.alx.game.model.GameUser;
import gr.alx.game.model.PollEntry;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class WelcomePageTest {

    Logger logger = LoggerFactory.getLogger(WelcomePageTest.class);
    private static String WEBAPP_SRC = "src/main/webapp";
    @Drone
    private WebDriver browser;
    @ArquillianResource
    private URL baseUrl;


    private WelcomePage welcomePage;

    @Deployment(testable = false)
    static public WebArchive createDeployment() {
        File[] libs = Maven.resolver()
                .loadPomFromFile("pom.xml")
                .resolve("org.primefaces:primefaces", "org.primefaces.themes:bootstrap")
                .withTransitivity().asFile();
        return ShrinkWrap
                .create(WebArchive.class, "music-game.war")
//                .addPackages(true, "gr")
                .addClasses(WelcomeController.class, UserDao.class, GameUser.class, PollEntry.class, GamePoll.class, GamePollDao.class)
                .addAsLibraries(libs)
                .addAsResource("test-persistence.xml",
                        "META-INF/persistence.xml").as(ExplodedImporter.class)
                .importDirectory(WEBAPP_SRC).as(WebArchive.class);
    }

    @Before
    public void initializeTestingPage() {

        welcomePage = PageFactory.initElements(browser, WelcomePage.class);
    }

    @Test
    public void testIsDeployed() {
        browser.navigate().to(baseUrl);
    }

    @Test
    public void testIsContentDivRendered() {
        assertTrue(welcomePage.getContentDiv().isDisplayed());
    }

    @Test
    public void testUserTableHasRows() {
        assertTrue(welcomePage.getUserTableRows().size() > 0);
    }

    @Test
    public void testUserTableIsPopulatedWithDBData() {
        //this is not needed when running with firefox, only with chrome in debug mode!
        browser.get(baseUrl.toString());
        WebElement row = (WebElement) welcomePage.getUserTableRows().get(welcomePage.getUserTableRows().size() - 1);
        List<WebElement> td = row.findElements(By.tagName("td"));
        logger.info(td.get(0).getText());
        assertEquals("test", td.get(0).getText());
    }

    @Test
    public void testPollTableIsPopulatedWithDBData() {
        //this is not needed when running with firefox, only with chrome in debug mode!
        browser.get(baseUrl.toString());
        WebElement row = (WebElement) welcomePage.getUserTableRows().get(1);
        List<WebElement> td = row.findElements(By.tagName("td"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(td.get(0).getText());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertNotNull(date);
        assertTrue(date.before(new Date()));
    }
}