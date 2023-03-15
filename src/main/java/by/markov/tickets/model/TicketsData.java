package by.markov.tickets.model;

import java.util.List;

public class TicketsData {

    private List<Ticket> tickets;

    public TicketsData() {
    }

    public TicketsData(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "TicketsData{" +
                "tickets=" + tickets +
                '}';
    }
}
