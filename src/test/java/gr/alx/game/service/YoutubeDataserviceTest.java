package gr.alx.game.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: alx
 * Date: 14/7/2013
 * Time: 8:51 μμ
 * To change this template use File | Settings | File Templates.
 */
public class YoutubeDataserviceTest {
    @Test
    public void testRetrieveTitleFromYoutubeUrl() {
        YoutubeDataService s = new YoutubeDataService();
        String title = s.getTitleFromUrl("https://www.youtube.com/watch?v=eEfw5-iePfo");
        assertEquals("Τρύπες - Καινούργια Ζάλη (στίχοι/lyrics)", title);
    }
}
