package gr.alx.game.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class GamePoll implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Temporal(TemporalType.DATE)
   @Column(unique = true)
   private Date date;

   @OneToMany(fetch = FetchType.EAGER, mappedBy = "gamePoll")   //mappedBy is used to avoid join table (intermediate)
   private List<PollEntry> entries = new ArrayList<PollEntry>();

    public GamePoll(Date date) {
        this.date = date;
    }

    public GamePoll() {
    }

    public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object that)
   {
      if (this == that)
      {
         return true;
      }
      if (that == null)
      {
         return false;
      }
      if (getClass() != that.getClass())
      {
         return false;
      }
      if (id != null)
      {
         return id.equals(((GamePoll) that).id);
      }
      return super.equals(that);
   }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GamePoll{");
        sb.append("id=").append(id);
        sb.append(", version=").append(version);
        sb.append(", date=").append(date);
        sb.append(", entries=").append(entries);
        sb.append('}');
        return sb.toString();
    }

    @Override
   public int hashCode()
   {
      if (id != null)
      {
         return id.hashCode();
      }
      return super.hashCode();
   }

   public Date getDate()
   {
      return this.date;
   }

   public void setDate(final Date date)
   {
      this.date = date;
   }

   public List<PollEntry> getEntries()
   {
      return this.entries;
   }

   public void setEntries(final List<PollEntry> entries)
   {
      this.entries = entries;
   }
}