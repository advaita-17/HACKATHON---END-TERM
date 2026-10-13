public class CinemaBookingApp {

    static class CinemaBooking {
        private static final double DEFAULT_PRICE = 200.0;
        private static final int MAX_TICKETS = 10;

        private String customerName;
        private String movieName;
        private double ticketPrice;
        private int numberOfTickets;
        private boolean priceSpecified;
        private String status = "Not Booked";
        private double total, discount, finalAmount;

      
        public CinemaBooking(String customerName, String movieName, double ticketPrice, int numberOfTickets) {
            this.customerName = customerName.trim();
            this.movieName = movieName.trim();
            this.ticketPrice = ticketPrice;
            this.numberOfTickets = numberOfTickets;
            this.priceSpecified = true;
        }

     
        public CinemaBooking(String customerName, String movieName, int numberOfTickets) {
            this(customerName, movieName, DEFAULT_PRICE, numberOfTickets);
            this.priceSpecified = false;
        }

        public CinemaBooking(String customerName, String movieName) {
            this(customerName, movieName, 1);
        }

        // Overloaded booking methods
        public void bookTickets() {
            process();
        }

        public void bookTickets(int tickets) {
            this.numberOfTickets = tickets;
            process();
        }

        public void bookTickets(int tickets, double price) {
            this.numberOfTickets = tickets;
            this.ticketPrice = price;
            this.priceSpecified = true;
            process();
        }

        private void process() {
            if (numberOfTickets < 1 || numberOfTickets > MAX_TICKETS) {
                status = "Booking Failed (tickets must be 1-" + MAX_TICKETS + ")";
                total = discount = finalAmount = 0;
                return;
            }
            total = ticketPrice * numberOfTickets;
            discount = (numberOfTickets >= 5) ? total * 0.10 : 0.0;
            finalAmount = total - discount;
            status = "Booking Successful";
        }

        
        public boolean verifyCustomer(String enteredName) {
            return customerName.equalsIgnoreCase(enteredName.trim());
        }

        public void displaySummary() {
            System.out.println();
            System.out.printf("Customer Name      : %s%n", customerName.toUpperCase());
            System.out.printf("Movie Name         : %s%n", movieName.toUpperCase());
            if (priceSpecified) {
                System.out.printf("Ticket Price       : Rs. %.2f%n", ticketPrice);
            }
            System.out.printf("Number of Tickets  : %d%n", numberOfTickets);
            System.out.println();
            System.out.printf("Booking Status     : %s%n", status);
            System.out.printf("Total Amount       : Rs. %.2f%n", total);
            System.out.printf("Discount           : Rs. %.2f%n", discount);
            System.out.printf("Final Amount       : Rs. %.2f%n", finalAmount);
        }
    }

    public static void main(String[] args) {
        System.out.println("========== CINEMA TICKET BOOKING ==========");

        CinemaBooking b1 = new CinemaBooking("Sneha", "Avatar", 200.0, 5);
        b1.bookTickets();
        b1.displaySummary();

        
        CinemaBooking b2 = new CinemaBooking("Arjun", "Avatar");
        b2.bookTickets(2);
        b2.displaySummary();

        System.out.println();
        String result = b2.verifyCustomer("  arjun ") ? "Names Match" : "Names Do Not Match";
        System.out.println("Customer Verification: " + result);
        System.out.println("=============================================");
    }
}