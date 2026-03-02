import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Demo that shows immutability in action.
 *
 * - tickets are built via Builder
 * - service "updates" return a new ticket, original stays unchanged
 * - external tag modifications have no effect on the ticket
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        // service updates return new tickets, original stays unchanged
        IncidentTicket assigned = service.assign(t, "agent@example.com");
        IncidentTicket escalated = service.escalateToCritical(assigned);

        System.out.println("\nOriginal after updates : " + t);
        System.out.println("Assigned               : " + assigned);
        System.out.println("Escalated              : " + escalated);

        // external tag mutation has no effect
        List<String> tags = escalated.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nExternal tag mutation blocked (unmodifiable list)");
        }
        System.out.println("Tags after hack attempt: " + escalated.getTags());
    }
}
