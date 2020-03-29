package gr.alx.game.controllers;


import gr.alx.game.ejb.GamePollDao;
import gr.alx.game.ejb.UserDao;
import gr.alx.game.model.GamePoll;
import gr.alx.game.model.GameUser;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * GameUser: alx
 * Date: 2/7/2013
 * Time: 9:19 μμ
 * To change this template use File | Settings | File Templates.
 */
@Named
@RequestScoped
public class WelcomeController {

    @Inject
    UserDao userDao;

    @Inject
    GamePollDao gamePollDao;

    List<GameUser> userList;
    List<GamePoll>  pollList;

    public String doWelcome(){

//        GameUser user = new GameUser("test", "test");
//
//        try {
//            userDao.create(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        userList = userDao.findAll();
        pollList = gamePollDao.keepPollsWithOnlyOneWinner(gamePollDao.findAllOrderedByDateDesc());

        return "Users in database: "+userList.size();
    }

    public List<GameUser> getUserList() {
        return userList;
    }

    public List<GamePoll> getPollList() {
        return pollList;
    }
}
