package gr.alx.game.ejb;

import gr.alx.game.model.GamePoll;
import gr.alx.game.model.PollEntry;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Stateless
public class PollEntryDao {

    @PersistenceContext
    private EntityManager em;

    public void create(PollEntry historyRecord) {
        em.persist(historyRecord);
    }

    public List<PollEntry> findByDate(Date date) {
        Query query = em.createQuery("select e from PollEntry e where e.gamePoll.date=:date");
        query.setParameter("date", date, TemporalType.DATE);
        return query.getResultList();
    }

    public PollEntry findByUrl(String url) throws NoResultException {
        Query query = em.createQuery("select e from PollEntry e where e.status=:url");
        query.setParameter("url", url);
        return (PollEntry) query.getSingleResult();
    }

    public PollEntry findByTitle(String title) throws NoResultException {
        Query query = em.createQuery("select e from PollEntry e where e.title=:title");
        query.setParameter("title", title);
        return (PollEntry) query.getSingleResult();
    }

    public void delete(PollEntry pollEntry) {
        em.remove(pollEntry);
    }
}