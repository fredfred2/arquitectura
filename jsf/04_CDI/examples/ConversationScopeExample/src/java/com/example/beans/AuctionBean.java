import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

@Named

@ConversationScoped
public class AuctionBean implements Serializable {

   
    private List<String> items;
    @Inject
    private Conversation conversation;

    public AuctionBean() {
        items = new ArrayList<String>();
    }

    @PostConstruct
    private void initialize() {
        conversation.begin();
        items.add("Clay Vase");
        items.add("Pesrian Carpet");
        items.add("Diamond studded Handset");
        items.add("Party Slippers");
        items.add("Golden Watch");
        items.add("Emerald Purse");
    }

    public List<String> getitems() {
        return items;
    }

    public void setitems(List<String> items) {
        this.items = items;
    }

    public void delete(String itemName) {
        items.remove(itemName);
    }

    public String end() {
        conversation.end();
        return "index";
    }

    public void destroy(ActionEvent event) {
        conversation.end();
    }
}
