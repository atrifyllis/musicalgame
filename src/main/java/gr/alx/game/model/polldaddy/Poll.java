package gr.alx.game.model.polldaddy;

/**
 * Created with IntelliJ IDEA.
 * User: alx
 * Date: 23/7/2013
 * Time: 9:14 μμ
 * To change this template use File | Settings | File Templates.
 */
public class Poll {
    private String folderID;
    private String content;
    private String id;
    private String created;
    private String responses;
    private String owner;
    private String closed;

    public Poll() {
    }

    public Poll(String id) {
        this.id = id;
    }

    public String getFolderID() {
        return folderID;
    }

    public void setFolderID(String folderID) {
        this.folderID = folderID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getResponses() {
        return responses;
    }

    public void setResponses(String responses) {
        this.responses = responses;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getClosed() {
        return closed;
    }



    public void setClosed(String closed) {
        this.closed = closed;
    }
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GamePoll{");
        sb.append("folderID='").append(folderID).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", created='").append(created).append('\'');
        sb.append(", responses='").append(responses).append('\'');
        sb.append(", owner='").append(owner).append('\'');
        sb.append(", closed='").append(closed).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
