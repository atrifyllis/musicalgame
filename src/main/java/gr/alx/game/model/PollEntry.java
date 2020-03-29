package gr.alx.game.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "gamePoll_id"}))
public class PollEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = null;
    @Version
    @Column(name = "version")
    private int version = 0;

    @OneToOne
    private GameUser user;

    @Column
    private String title;

    @Column(unique = true)
    private String status;

    @Column
    private String lyrics;

    @ManyToOne
    //bidirectional relation with PollEntry to avoid join table (intermediate)
    private GamePoll gamePoll;

    @Column
    private int votes;

    public PollEntry() {
    }

    public PollEntry(GameUser user, String title, String status, GamePoll poll, int votes) {
        this.user = user;
        this.title = title;
        this.status = status;
        this.gamePoll = poll;
        this.votes = votes;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    public GameUser getUser() {
        return this.user;
    }

    public void setUser(final GameUser user) {
        this.user = user;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getLyrics() {
        return this.lyrics;
    }

    public void setLyrics(final String lyrics) {
        this.lyrics = lyrics;
    }

    public GamePoll getGamePoll() {
        return this.gamePoll;
    }

    public void setGamePoll(final GamePoll gamePoll) {
        this.gamePoll = gamePoll;
    }

    public int getVotes() {
        return this.votes;
    }

    public void setVotes(final int votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PollEntry pollEntry = (PollEntry) o;

        if (version != pollEntry.version) return false;
        if (votes != pollEntry.votes) return false;
        if (!id.equals(pollEntry.id)) return false;
        if (lyrics != null ? !lyrics.equals(pollEntry.lyrics) : pollEntry.lyrics != null) return false;
        if (!gamePoll.equals(pollEntry.gamePoll)) return false;
        if (status != null ? !status.equals(pollEntry.status) : pollEntry.status != null) return false;
        if (title != null ? !title.equals(pollEntry.title) : pollEntry.title != null) return false;
        if (!user.equals(pollEntry.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + version;
        result = 31 * result + user.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (lyrics != null ? lyrics.hashCode() : 0);
        result = 31 * result + gamePoll.hashCode();
        result = 31 * result + votes;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PollEntry{");
        sb.append("id=").append(id);
        sb.append(", version=").append(version);
        sb.append(", user=").append(user);
        sb.append(", title='").append(title).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", lyrics='").append(lyrics).append('\'');
//        sb.append(", gamePoll=").append(gamePoll);
        sb.append(", votes=").append(votes);
        sb.append('}');
        return sb.toString();
    }

}