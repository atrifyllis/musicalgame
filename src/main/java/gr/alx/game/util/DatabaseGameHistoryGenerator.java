package gr.alx.game.util;

import gr.alx.game.ejb.GamePollDao;
import gr.alx.game.ejb.PollEntryDao;
import gr.alx.game.ejb.UserDao;
import gr.alx.game.model.GamePoll;
import gr.alx.game.model.GameUser;
import gr.alx.game.model.PollEntry;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: trifyllalex
 * Date: 9/7/2013
 * Time: 2:17 μμ
 * To change this template use File | Settings | File Templates.
 */
@Singleton
@Startup
//@DependsOn("DatabaseUsersGenerator")
public class DatabaseGameHistoryGenerator {

    @Inject
    UserDao userDao;
    @Inject
    PollEntryDao pollEntryDao;
    @Inject
    private GamePollDao gamePollDao;

    @PostConstruct
    public void initGameUsers() {
        Date date = new GregorianCalendar(2010, 1, 1).getTime();
        if (pollEntryDao.findByDate(date).size() == 0) {
            GameUser user = userDao.findByUsername("" +
                    "Alexandra");

            GamePoll poll = new GamePoll(date);
            gamePollDao.create(poll);

            PollEntry history = new PollEntry(user, "test", "http://youtube.com", poll, 0);
            pollEntryDao.create(history);

            user = userDao.findByUsername("Alexandros");
            history = new PollEntry(user, "test", "http://youtube.com", poll, 0);
            pollEntryDao.create(history);

            user = userDao.findByUsername("Eleni");
            history = new PollEntry(user, "test", "http://youtube.com", poll, 0);
            pollEntryDao.create(history);

            user = userDao.findByUsername("Ifi");
            history = new PollEntry(user, "test", "http://youtube.com", poll, 0);
            pollEntryDao.create(history);
        }
    }
}
