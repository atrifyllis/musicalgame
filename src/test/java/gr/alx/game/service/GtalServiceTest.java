package gr.alx.game.service;

import gr.alx.game.model.GameUser;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: alx
 * Date: 19/7/2013
 * Time: 10:32 μμ
 * To change this template use File | Settings | File Templates.
 */
public class GtalServiceTest {

    Logger logger = LoggerFactory.getLogger(GtalServiceTest.class);

    @Test
    public void testRetrievingGtalkStatuses(){
        List<GameUser> users = new ArrayList<>();
        GameUser user = new GameUser("Alexandros", "otinanism@gmail.com");
        users.add(user);
        GtalkService gtalkService = new GtalkService();
        Map<String, String> statuses = gtalkService.retrieveGtalkStatuses(users);
        assertNotNull(statuses);

    }
}
