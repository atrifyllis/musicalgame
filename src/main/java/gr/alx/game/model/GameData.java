package gr.alx.game.model;

import java.util.Date;
import java.util.Map;

public class GameData {

	Map<String, GameUser> users;
	Date date;

	public GameData(Map<String, GameUser> users, Date date) {
		this.users = users;
		this.date = date;
	}

	public Map<String, GameUser> getUsers() {
		return users;
	}

	public void setUsers(Map<String, GameUser> users) {
		this.users = users;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GameData [users=").append(users).append(", date=")
				.append(date).append("]");
		return builder.toString();
	}

}
