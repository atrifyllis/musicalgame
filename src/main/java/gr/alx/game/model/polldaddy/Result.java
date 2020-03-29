package gr.alx.game.model.polldaddy;

/**
 * Created with IntelliJ IDEA.
 * User: alx
 * Date: 26/7/2013
 * Time: 12:51 πμ
 * To change this template use File | Settings | File Templates.
 */
public class Result {
    private Answers answers;
    private String id;
    public Answers getAnswers() {
        return answers;
    }

    public void setAnswers(Answers answers) {
        this.answers = answers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Result{");
        sb.append("answers=").append(answers);
        sb.append(", id='").append(id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
