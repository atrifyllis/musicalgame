package gr.alx.game.model.polldaddy;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alx
 * Date: 23/7/2013
 * Time: 9:06 μμ
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "demands")
public class Demands {

    List<Demand> demand;


    public Demands(List<Demand> demand) {
        this.demand = demand;
    }

    public Demands() {
    }

    public List<Demand> getDemand() {
        return demand;
    }

    public void setDemand(List<Demand> demand) {
        this.demand = demand;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Demands{");
        sb.append("demand=").append(demand);
        sb.append('}');
        return sb.toString();
    }
}
