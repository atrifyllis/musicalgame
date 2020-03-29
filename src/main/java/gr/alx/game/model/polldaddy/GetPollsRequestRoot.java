package gr.alx.game.model.polldaddy;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: alx
 * Date: 23/7/2013
 * Time: 8:18 μμ
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement(name = "pdRequest")
public class GetPollsRequestRoot {

    private String partnerGUID;
    private String userCode;
    private Demands demands;

    public GetPollsRequestRoot() {
    }

    public GetPollsRequestRoot(String partnerGUID, String userCode, Demands demands) {
        this.partnerGUID = partnerGUID;
        this.userCode = userCode;
        this.demands = demands;
    }

    public String getPartnerGUID() {
        return partnerGUID;
    }

    public void setPartnerGUID(String partnerGUID) {
        this.partnerGUID = partnerGUID;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Demands getDemands() {
        return demands;
    }

    public void setDemands(Demands demands) {
        this.demands = demands;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GetPollsRequestRoot{");
        sb.append("partnerGUID='").append(partnerGUID).append('\'');
        sb.append(", userCode='").append(userCode).append('\'');
        sb.append(", demands=").append(demands);
        sb.append('}');
        return sb.toString();
    }
}
