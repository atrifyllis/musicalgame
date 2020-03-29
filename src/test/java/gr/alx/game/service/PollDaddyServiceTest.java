package gr.alx.game.service;

import gr.alx.game.model.polldaddy.Answer;
import gr.alx.game.model.polldaddy.GetPollsResponse;
import gr.alx.game.model.polldaddy.Poll;
import gr.alx.game.model.polldaddy.Polls;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: alx
 * Date: 14/7/2013
 * Time: 8:51 μμ
 * To change this template use File | Settings | File Templates.
 */
public class PollDaddyServiceTest {
    Logger logger = LoggerFactory.getLogger(PollDaddyServiceTest.class);
    private PollDaddyService pollDaddyService;

    @Before
    public void setUp() {
        pollDaddyService = new PollDaddyService();
        pollDaddyService.initServiceClient();
    }

    @Test
    public void testRetrievePollsFromPollDaddyListNotEmpty() {
        GetPollsResponse getPollsResponse = pollDaddyService.getPollList();
        assertNotNull(getPollsResponse);
        Polls pollList = getPollsResponse.getPdResponse().getDemands().getDemand().get(0).getPolls();
        List<Poll> polls = pollList.getPoll();
        for (Poll poll : polls) {
            logger.info(poll.toString());
        }
        assertTrue(Long.parseLong(pollList.getTotal()) > 0);

    }

    @Test
    public void testGetPollResultsCorrectText() {
        GetPollsResponse pollResults = pollDaddyService.getPollResults("7269971");
        List<Answer> answerList = pollResults.getPdResponse().getDemands().getDemand().get(0).getResult().getAnswers().getAnswer();
        assertEquals(answerList.get(0).getText(), "Eleni <a href=\"https://www.youtube.com/watch?v=HdhtM-5nx9U\">James - Waltzing Along</a>");
    }

    @Test
    public void testGetGetPollWinnersWhenUniqueWinner() {
        GetPollsResponse pollResults = pollDaddyService.getPollResults("7269971");
        logger.info(pollResults.toString());
        List<Answer> answerList = pollResults.getPdResponse().getDemands().getDemand().get(0).getResult().getAnswers().getAnswer();
        for (Answer answer : answerList) {
            logger.info(answer.getText() + " --- " + answer.getTotal());
        }
        List<Answer> pollWinners = pollDaddyService.getPollWinners(answerList);
        assertTrue(pollWinners.size() == 1);
    }

    @Test
    public void testGetPollWinnersWhenTwoWinners() {
        GetPollsResponse pollResults = pollDaddyService.getPollResults("7258704");
        logger.info(pollResults.toString());
        List<Answer> answerList = pollResults.getPdResponse().getDemands().getDemand().get(0).getResult().getAnswers().getAnswer();

        List<Answer> pollWinners = pollDaddyService.getPollWinners(answerList);
        for (Answer pollWinner : pollWinners) {
            logger.info(pollWinner.getText() + " --- " + pollWinner.getTotal());
        }
        assertTrue(pollWinners.size() == 2);
    }

//    @Test
    public void testCountTotalWinsPerUser() {
        Map<String, Float> winsPerUser = pollDaddyService.countTotalWinsPerUser();
        for (Map.Entry<String, Float> userScore : winsPerUser.entrySet()) {
            logger.info(userScore.getKey() + " ---  " + userScore.getValue());
        }
    }
}
