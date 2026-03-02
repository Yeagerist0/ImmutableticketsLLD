package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer that creates tickets using the Builder.
 * No mutation after creation — updates return a new ticket.
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        return new IncidentTicket.Builder(id, reporterEmail, title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .addTag("NEW")
                .build();
    }

    public IncidentTicket escalateToCritical(IncidentTicket t) {
        return t.toBuilder()
                .priority("CRITICAL")
                .addTag("ESCALATED")
                .build();
    }

    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        return t.toBuilder()
                .assigneeEmail(assigneeEmail)
                .build();
    }
}
