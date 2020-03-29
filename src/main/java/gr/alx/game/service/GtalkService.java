package gr.alx.game.service;

import gr.alx.game.model.GameUser;
import org.apache.commons.lang.time.DateUtils;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.DNSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//TODO: why was it a service in the first place?
//@Service("gtalkService")
public class GtalkService {

    static XMPPConnection connection = new XMPPConnection("xmpp.l.google.com");
    Logger logger = LoggerFactory.getLogger(GtalkService.class);

    public Map<String, String> retrieveGtalkStatuses(List<GameUser> users) {

        Map<String, String> statuses = new HashMap<String, String>();

        try {
            // You have to put this code before you login(Required for Gtalk and not for Jabber.org)
            SASLAuthentication.supportSASLMechanism("PLAIN", 0);

            DNSUtil.HostAddress address = DNSUtil.resolveXMPPDomain("xmpp.l.google.com");
            logger.info(address.getHost() + " - " + address.getPort());
            String javaHome = System.getProperty("java.home");
            StringBuilder buffer = new StringBuilder();
            buffer.append(javaHome).append(File.separator).append("lib");
            buffer.append(File.separator).append("security");
            buffer.append(File.separator).append("cacerts");
            logger.info(buffer.toString());
            Thread.sleep(3000);
            // Connect
            connection.connect();
            // Login with appropriate credentials
            connection.login("otinanism@gmail.com", "Gopen123");
            // Get the user's roster
            Roster roster = connection.getRoster();

            Thread.sleep(3000);
            logger.info(String.valueOf(connection.getPort()));
            for (GameUser user : users) {
                String name = user.getUsername();
                String email = user.getEmail();
                Presence presence = roster.getPresence(email);
                String status = presence.getStatus();
                // get youtube url from status
                status = extractValidYoutubeUrl(status != null ? status : "");
                if (status != null && !status.equals("")) {
                    statuses.put(name, status);
                }
            }
        } catch (XMPPException e) {
            e.printStackTrace();
			logger.error("Exception Thrown", e);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return statuses;

    }
//TODO: this method needs refactoring
//	public Map<String,String> filterOutYesterdayGtalkStatuses(Map<String, String> statuses, GameData data) {
//
//		Map<String, GameUser> users = data.getUsers();
//		Map<String, String> filteredStatuses = new HashMap<String, String>();
//
//		for (Entry<String, String> entry : statuses.entrySet()) {
//
//			String name = entry.getKey();
//			String status = entry.getValue();
//
//			// filter out statuses that are same as yesterday
//            if (!users.get(name).getStatus().equals(status) || isSameDay(data.getDate())) {
//                filteredStatuses.put(name, status);
//            }
//
//        }
//
//		return filteredStatuses;
//	}

    private static boolean isSameDay(Date yesterday) {
        return DateUtils.isSameDay(yesterday, new Date());
    }

    public String extractValidYoutubeUrl(String url) {
        String pattern = "https?:\\/\\/(?:[0-9A-Z-]+\\.)?(?:youtu\\.be\\/|youtube\\.com\\S*[^\\w\\-\\s])([\\w\\-]{11})("
                + "?=[^\\w\\-]|$)(?![?=&+%\\w]*(?:['\"][^<>]*>|<\\/a>))[?=&+%\\w]*";
        String result = "";
        Pattern compiledPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compiledPattern.matcher(url);
        if (matcher.find()) {
            result = url.substring(matcher.start(), matcher.end());
            if (!result.contains("https"))
                result = result.replace("http:", "https:");
        }
        return result;
    }
}
