package gr.alx.game.model.polldaddy;

/**
 * Created with IntelliJ IDEA.
 * User: alx
 * Date: 23/7/2013
 * Time: 9:11 μμ
 * To change this template use File | Settings | File Templates.
 */
public class Demand {
    private String start;
    private String end;
    private String id;

    private String folder_id;
    private Polls polls; // for GetPolls request
    private Poll poll; //for GetPollResult request
    private Result result;

    public Demand() {
    }

    public Demand(String id) {
        this.id = id;
    }

    public Demand(String id, Poll poll) {
        this.id = id;
        this.poll = poll;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFolder_id() {
        return folder_id;
    }

    public void setFolder_id(String folder_id) {
        this.folder_id = folder_id;
    }

    public Polls getPolls() {
        return polls;
    }

    public void setPolls(Polls polls) {
        this.polls = polls;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Demand{");
        sb.append("start='").append(start).append('\'');
        sb.append(", end='").append(end).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", folder_id='").append(folder_id).append('\'');
        sb.append(", polls=").append(polls);
        sb.append(", poll=").append(poll);
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}
