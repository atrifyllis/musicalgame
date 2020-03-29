package gr.alx.game.util;

import gr.alx.game.ejb.GamePollDao;
import gr.alx.game.ejb.PollEntryDao;
import gr.alx.game.ejb.UserDao;
import gr.alx.game.model.GamePoll;
import gr.alx.game.model.GameUser;
import gr.alx.game.model.PollEntry;
import gr.alx.game.model.polldaddy.Answer;
import gr.alx.game.model.polldaddy.GetPollsResponse;
import gr.alx.game.model.polldaddy.Poll;
import gr.alx.game.model.polldaddy.Polls;
import gr.alx.game.service.PollDaddyService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alx
 * Date: 2/8/2013
 * Time: 9:49 πμ
 * To change this template use File | Settings | File Templates.
 */

@Named
    //@Singleton
    //@Startup
    //@DependsOn("DatabaseUsersGenerator")
public class PollDaddyMigrator {

    Logger logger = LoggerFactory.getLogger(PollDaddyMigrator.class);
    private final String pattern = "yyyy-MM-dd HH:mm:ss";
    private final SimpleDateFormat sdf = new SimpleDateFormat(pattern);

    @Inject
    PollEntryDao pollEntryDao;
    @Inject
    GamePollDao gamePollDao;

    @Inject
    PollDaddyService pollDaddyService;
    @Inject
    UserDao userDao;

    public Date getStartDate() {
        Date startDate = gamePollDao.findLatestDate();
        if (startDate == null) {
            startDate = new GregorianCalendar(2010, 1, 1).getTime();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, 1);   //add one day so we do not re-insert the last poll
        return cal.getTime();
    }
    @PostConstruct
    public void migratePollData() {

        Date startDate = getStartDate();
        Date endDate = new Date();

        GetPollsResponse getPollsResponse = pollDaddyService.getPollList();
        Polls pollList = getPollsResponse.getPdResponse().getDemands().getDemand().get(0).getPolls();
        List<Poll> polls = pollList.getPoll();

        for (Poll poll : polls) {
            String created = poll.getCreated();
            Date createdDate = getPollDate(created);
            if (isPollDateInsideLimits(startDate, endDate, createdDate)) {
                migratePoll(poll, createdDate);
            }
        }

    }

    private void migratePoll(Poll poll, Date createdDate) {
        GamePoll gamePoll = new GamePoll(createdDate);
        logger.info(String.valueOf(createdDate));
        gamePollDao.create(gamePoll);

        GetPollsResponse pollResults = pollDaddyService.getPollResults(poll.getId());
        List<Answer> answerList = pollResults.getPdResponse().getDemands().getDemand().get(0).getResult().getAnswers().getAnswer();
        for (Answer answer : answerList) {
            String text = answer.getText();
            logger.info("text is: "+ text);
            String username = text.substring(0, text.indexOf(" "));
            GameUser user = userDao.findByUsername(username);
            Document doc = Jsoup.parse(text, "UTF-8");
            Element link = doc.select("a").first();
            String url = link.attr("href");
            String title = link.text();
            logger.info("title is: "+title);
            int votes = answer.getTotal();
            PollEntry pollEntry = new PollEntry(user, title, url, gamePoll, votes);
            pollEntryDao.create(pollEntry);
        }
    }

    private boolean isPollDateInsideLimits(Date startDate, Date endDate, Date createdDate) {
        return createdDate != null && createdDate.after(startDate) && createdDate.before(endDate);
    }

    private Date getPollDate(String created) {
        Date date = null;
        try {
            date = sdf.parse(created);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
