package gr.alx.game.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import gr.alx.game.model.polldaddy.*;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: alx
 * Date: 23/7/2013
 * Time: 7:52 μμ
 * To change this template use File | Settings | File Templates.
 */
public class PollDaddyService {

    Logger logger = LoggerFactory.getLogger(PollDaddyService.class);
    private final String apiKey = "138ba433-f16d-4268-fb28-000026b2cccd";
    private final String partnerUserID = "$P$BpPTrDE8ZzgeKh9O25736na8gaODQH/";
    private final String url = "https://api.polldaddy.com/";
    ClientConfig clientConfig;
    Client client;
    WebResource webResource;
    ClientResponse response;

    public void initServiceClient() {
        clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        clientConfig.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(clientConfig);
        webResource = client.resource(url);
    }

    public GetPollsResponse getPollList() {

        List<Demand> demandList = new ArrayList<>();
        Demand demand = new Demand("GetPolls");
        demandList.add(demand);
        Demands demands = new Demands(demandList);
        GetPollsRequest getPollsRequest = new GetPollsRequest(apiKey, partnerUserID, demands);

        initServiceClient();

        ClientResponse response = webResource.accept("application/json").type("application/json")
                .post(ClientResponse.class, getPollsRequest);


        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }

        return response.getEntity(GetPollsResponse.class);
    }

    public GetPollsResponse getPollResults(String pollId) {

        List<Demand> demandList = new ArrayList<>();
        Poll poll = new Poll(pollId);
        Demand demand = new Demand("GetPollResults", poll);
        demandList.add(demand);
        Demands demands = new Demands(demandList);
        GetPollsRequest getPollResultRequest = new GetPollsRequest(apiKey, partnerUserID, demands);

        ClientResponse response = webResource.accept("application/json").type("application/json")
                .post(ClientResponse.class, getPollResultRequest);

        return response.getEntity(GetPollsResponse.class);

    }

    public List<Answer> getPollWinners(List<Answer> answerList) {
        List<Answer> tiedWinningAnswerList = new ArrayList<>();
        Answer winningAnswer = new Answer();
        for (Answer answer : answerList) {
            int newTotal = answer.getTotal();
            String text = answer.getText();
            String username = text.substring(0, text.indexOf(" "));
            String link = text.substring(text.indexOf("-") + 1, text.length());

            if (winningAnswer.getTotal() < newTotal) {
                winningAnswer = answer;
            }

            if (winningAnswer.getTotal() <= newTotal) {
                tiedWinningAnswerList.add(answer);
            }
        }
        return tiedWinningAnswerList;
    }

    public TreeMap<String, Float> countTotalWinsPerUser() {
        TreeMap<String, Float> userWins = new TreeMap<>();

        GetPollsResponse pollsResponse = getPollList();
        Polls pollList = pollsResponse.getPdResponse().getDemands().getDemand().get(0).getPolls();
        List<Poll> polls = pollList.getPoll();
        for (Poll poll : polls) {
            GetPollsResponse pollResults = getPollResults(poll.getId());

            Answers answers = pollResults.getPdResponse().getDemands().getDemand().get(0).getResult().getAnswers();
            if (answers != null) {


                List<Answer> answerList = answers.getAnswer();
                List<Answer> pollWinners = getPollWinners(answerList);
                float score = 1f / pollWinners.size();
                logger.info("for poll: " + poll.getId());
                for (Answer pollWinner : pollWinners) {
                    String text = pollWinner.getText();
                    String username = text.substring(0, text.indexOf(" "));
                    Float userScore = userWins.get(username);
                    logger.info(username + " scored: " + score);
                    logger.info("before: " + userScore);
                    if (userScore == null) {
                        userScore = 1F;
                    } else {
                        userScore += score;
                    }
                    logger.info("after: " + userScore);
                    userWins.put(username, userScore);
                }
            }
        }
        return userWins;
    }
}
