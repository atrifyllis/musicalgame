package gr.alx.game.ejb;

import gr.alx.game.model.GameUser;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserDao {
    @PersistenceContext
    private EntityManager em;

    public GameUser find(Long id) {
        return em.find(GameUser.class, id);
    }

    public void create(GameUser user) {
        em.persist(user);
    }

    public GameUser findByUsername(String username) {
        Query query = em.createQuery("select u from GameUser u where u.username=:username");
        query.setParameter("username", username);
        return (GameUser) query.getSingleResult();
    }

    public List<GameUser> findAll() {
        return em.createQuery("select u from GameUser u", GameUser.class).getResultList();
    }

    public void delete(GameUser user){
        em.remove(user);
    }

}