package by.markov.tickets;

import by.markov.tickets.service.TicketService;

public class Main {

    public static void main(String[] args) {
        TicketService ticketService = new TicketService();
        ticketService.getResultData();
    }
}
