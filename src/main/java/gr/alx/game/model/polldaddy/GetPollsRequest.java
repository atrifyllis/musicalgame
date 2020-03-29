package gr.alx.game.model.polldaddy;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: alx
 * Date: 24/7/2013
 * Time: 1:00 πμ
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class GetPollsRequest {
    GetPollsRequestRoot pdRequest;

    public GetPollsRequest() {
    }

    public GetPollsRequest(String apiKey, String partnerUserID, Demands demands) {
        this.pdRequest = new GetPollsRequestRoot(apiKey, partnerUserID, demands);
    }

    public GetPollsRequestRoot getPdRequest() {
        return pdRequest;
    }

    public void setPdRequest(GetPollsRequestRoot pdRequest) {
        this.pdRequest = pdRequest;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GetPollsRequest{");
        sb.append("pdRequest=").append(pdRequest);
        sb.append('}');
        return sb.toString();
    }
}
