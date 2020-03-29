package gr.alx.game.model.polldaddy;

/**
 * Created with IntelliJ IDEA.
 * User: alx
 * Date: 26/7/2013
 * Time: 12:52 πμ
 * To change this template use File | Settings | File Templates.
 */
public class Answer {
    private String text;
    private String id;
    private int total;
    private float percent;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Answer{");
        sb.append("text='").append(text).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", total=").append(total);
        sb.append(", percent=").append(percent);
        sb.append('}');
        return sb.toString();
    }
}
