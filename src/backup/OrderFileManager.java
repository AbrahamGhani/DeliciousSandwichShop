package backup;
import com.pluralsight.core.Priceable;
import com.pluralsight.order.Order;
import com.pluralsight.persistence.FileManager;

public class OrderFileManager {

    /*
     was never used. just stuck with transaction logger class instead
     */

    private FileManager fm;
    private String orderFilename;

    public OrderFileManager(FileManager fm, String orderFilename) {
        this.fm = fm;
        this.orderFilename = orderFilename;
    }

    public void saveOrder(Order<? extends Priceable> order) {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(order.getId()).append("\n");
        sb.append("Timestamp: ").append(order.getTimestamp()).append("\n");
        order.getItems().forEach(item ->
                sb.append(item.getClass().getSimpleName())
                        .append(" - ")
                        .append(item.getPrice())
                        .append("\n")
        );
        sb.append("Total: ").append(order.getTotalPrice());

        fm.writeToFile(orderFilename, sb.toString());
    }

    public String loadOrder(String id) {
        return fm.readFromFile(orderFilename);
    }


}
