package gr.alx.game.ejb;

import gr.alx.game.model.GamePoll;
import gr.alx.game.model.PollEntry;
import gr.alx.game.model.GameUser;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class PollEntryDaoTest {
    @Inject
    private PollEntryDao pollEntryDao;

    @Inject
    private GamePollDao gamePollDao;

    @Inject
    private UserDao userDao;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(UserDao.class, PollEntryDao.class, GamePollDao.class)
                .addPackage(GameUser.class.getPackage())
                .addAsManifestResource("test-persistence.xml",
                        ArchivePaths.create("persistence.xml"))
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testIsDeployed() {
        Assert.assertNotNull(pollEntryDao);
    }

    @Test
    @Transactional
    public void testAddPoll(){
        Date date = new GregorianCalendar(2010, 1, 1).getTime();
        GamePoll poll = gamePollDao.findByDate(date);
        GameUser user = userDao.findByUsername("Kotz");
        PollEntry pollEntry = new PollEntry(user, "test"+new Date(), "test", poll, 1);
        pollEntryDao.create(pollEntry);
        List<PollEntry> pollEntries = pollEntryDao.findByDate(date);
        assertTrue(pollEntries.contains(pollEntry));
        pollEntryDao.delete(pollEntry);

    }
    @Test
    public void testListGameHistoriesForSpecificDate() {
        Date date = new GregorianCalendar(2010, 1, 1).getTime();
        List<PollEntry> histories = pollEntryDao.findByDate(date);
        assertNotNull(histories);
        assertThat(histories.size(), is(4));
    }

    @Test
    public void testFindByUrl(){
        String url = "https://www.youtube.com/watch?v=kt-UERu0cNU";
        PollEntry entry = pollEntryDao.findByUrl(url);
        assertNotNull(entry);
        assertThat(entry.getUser().getUsername(), is("Nota"));
    }

    @Test(expected = Exception.class)
    public void testFindByNonExistingUrl(){
        String url = "https://www.youtube.com/watch?v=kt-UERu39485";
        PollEntry entry = pollEntryDao.findByUrl(url);
        assertNull(entry);
    }

    @Test
    public void testFindByTitle(){
        String title = "Beastie Boys - Intergalactic";
        PollEntry entry = pollEntryDao.findByTitle(title);
        assertNotNull(entry);
        assertThat(entry.getUser().getUsername(), is("Alexandros"));
    }

    @Test(expected = Exception.class)
    public void testFindByNonExistingTitle(){
        String title = "testsong";
        PollEntry entry = pollEntryDao.findByTitle(title);
        assertNull(entry);
    }
}
