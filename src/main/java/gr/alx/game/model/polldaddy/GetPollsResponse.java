package gr.alx.game.model.polldaddy;

/**
 * Created with IntelliJ IDEA.
 * User: alx
 * Date: 23/7/2013
 * Time: 9:13 μμ
 * To change this template use File | Settings | File Templates.
 */
public class GetPollsResponse {

    GetPollsResponseRoot pdResponse;

    public GetPollsResponseRoot getPdResponse() {
        return pdResponse;
    }

    public void setPdResponse(GetPollsResponseRoot pdResponse) {
        this.pdResponse = pdResponse;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GetPollsResponse{");
        sb.append("pdResponse=").append(pdResponse);
        sb.append('}');
        return sb.toString();
    }
}
