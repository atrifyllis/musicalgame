package gr.alx.game.ejb;

import gr.alx.game.model.GameUser;
import gr.alx.game.util.DatabaseUsersGenerator;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
@Transactional
public class UserDaoTest {
    @Inject
    private UserDao userdao;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(UserDao.class, DatabaseUsersGenerator.class)
                .addPackage(GameUser.class.getPackage())
                .addAsManifestResource("test-persistence.xml",
                        ArchivePaths.create("persistence.xml"))
                    .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testIsDeployed() {
        assertNotNull(userdao);
    }

    @Test
    public void testListUsers() {
        List<GameUser> users = userdao.findAll();
        assertNotNull(users);
        assertThat(users.size(), is(12));
    }

    @Test(expected =EJBTransactionRolledbackException.class)
    @Transactional
    public void testAddUser() {
        userdao.create(new GameUser("test3", "test@test.com"));
        GameUser user = userdao.findByUsername("test3");
        assertNotNull(user);
        assertThat(user.getEmail(), is("test@test.com"));
        userdao.delete(user);
        userdao.findByUsername("test3");
    }
}
