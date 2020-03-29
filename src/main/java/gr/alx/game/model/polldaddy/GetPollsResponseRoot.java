package gr.alx.game.model.polldaddy;

/**
 * Created with IntelliJ IDEA.
 * User: alx
 * Date: 25/7/2013
 * Time: 7:57 μμ
 * To change this template use File | Settings | File Templates.
 */
public class GetPollsResponseRoot {

    private String partnerGUID;
    private String partnerUserID;
    private String userCode;
    private Demands demands;

    public GetPollsResponseRoot() {
    }

    public String getPartnerGUID() {
        return partnerGUID;
    }

    public void setPartnerGUID(String partnerGUID) {
        this.partnerGUID = partnerGUID;
    }

    public String getPartnerUserID() {
        return partnerUserID;
    }

    public void setPartnerUserID(String partnerUserID) {
        this.partnerUserID = partnerUserID;
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
        final StringBuffer sb = new StringBuffer("GetPollsResponseRoot{");
        sb.append("partnerGUID='").append(partnerGUID).append('\'');
        sb.append(", partnerUserID='").append(partnerUserID).append('\'');
        sb.append(", userCode='").append(userCode).append('\'');
        sb.append(", demands=").append(demands);
        sb.append('}');
        return sb.toString();
    }
}
