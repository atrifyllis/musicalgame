package gr.alx.game.ejb;

import gr.alx.game.model.GamePoll;
import gr.alx.game.model.PollEntry;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: alx
 * Date: 2/8/2013
 * Time: 7:24 μμ
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class GamePollDao {

    @PersistenceContext
    private EntityManager em;


    public void create(GamePoll poll) {
        em.persist(poll);
    }

    public List<GamePoll> findAllOrderedByDateDesc() {
        Query query = em.createQuery("select p from GamePoll p order by date desc ");
        return query.getResultList();
    }

    /**
     * Retrieves only the most recent poll date, if any, stored in the GamePoll table
     * @return Date the poll ran
     */
    public Date findLatestDate() {
        Date latestDate = null;
        Query query = em.createQuery("select p from GamePoll p order by date desc ");
        List list = query.getResultList();
        if (list.size() > 0) {
            GamePoll poll = (GamePoll) list.get(0);
            latestDate = poll.getDate();
        }
        return latestDate;
    }

    public GamePoll findByDate(Date date){
        Query query = em.createQuery("select p from GamePoll p where date= :date ");
        query.setParameter("date", date);
        return (GamePoll) query.getSingleResult();
    }

    public void delete(GamePoll poll) {
        em.remove(poll);
    }

    public List<GamePoll> keepPollsWithOnlyOneWinner(List<GamePoll> polls) {
        List<GamePoll> pollsWIthOneWinner = new ArrayList<>();
        for (GamePoll poll : polls) {
            List<PollEntry> entries = poll.getEntries();
            Collections.sort(entries, new Comparator<PollEntry>() {
                @Override
                public int compare(PollEntry o1, PollEntry o2) {
                    if (o1.getVotes() < o2.getVotes()) {
                        return 1;
                    } else if (o1.getVotes() > o2.getVotes()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
            int mostVotes = 0;
            for (PollEntry entry : entries) {

                int votes = entry.getVotes();
                if(mostVotes == votes){
                     break;
                }
                else if(votes < mostVotes){
                   pollsWIthOneWinner.add(poll);
                    break;
                }
                else{
                     mostVotes = votes;
                }
            }
        }
        return pollsWIthOneWinner;
    }
}
