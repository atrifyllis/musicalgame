package gr.alx.game.service;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import gr.alx.game.model.YoutubeDataResponse;

/**
 * Created with IntelliJ IDEA.
 * User: alx
 * Date: 13/7/2013
 * Time: 6:46 μμ
 * To change this template use File | Settings | File Templates.
 */
public class YoutubeDataService {

    private final String format = "json";

    public String getTitleFromUrl(String url) {

        Client client = Client.create();

        WebResource webResource = client.resource("http://www.youtube.com/oembed?url=" + url + "&format=" + format);

        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        YoutubeDataResponse output = response.getEntity(YoutubeDataResponse.class);

        return response == null ? "" : output.getTitle();
    }

}
