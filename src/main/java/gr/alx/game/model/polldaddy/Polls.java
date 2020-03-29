package gr.alx.game.model.polldaddy;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alx
 * Date: 23/7/2013
 * Time: 9:16 μμ
 * To change this template use File | Settings | File Templates.
 */
public class Polls {
    List<Poll> poll;
    private String total;

    public Polls() {
    }

    public List<Poll> getPoll() {
        return poll;
    }

    public void setPoll(List<Poll> poll) {
        this.poll = poll;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Polls{");
        sb.append("poll=").append(poll);
        sb.append(", total='").append(total).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
