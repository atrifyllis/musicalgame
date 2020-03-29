package gr.alx.game.model.polldaddy;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alx
 * Date: 26/7/2013
 * Time: 12:52 πμ
 * To change this template use File | Settings | File Templates.
 */
public class Answers {
    private List<Answer> answer;

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Answers{");
        sb.append("answer=").append(answer);
        sb.append('}');
        return sb.toString();
    }
}
