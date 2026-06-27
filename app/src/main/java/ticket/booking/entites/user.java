package ticket.booking.entites;


import java.util.List;

public class user {

    private String name;

    private String password;

    private String hashPassword;

    private List<ticket> ticketsBooked;

    private String userId;


    public user(String name, String password, String hashPassword, List<ticket> ticketsBooked, String userId){
        this.name = name;
        this.password = password;
        this.hashPassword = hashPassword;
        this.ticketsBooked = ticketsBooked;
        this.userId = userId;
    }
    public user(){}

    public String getName() {
        return name;
    }

    public String getPassword(){
        return password;
    }

    public String gethashPassword() {
        return hashPassword;
    }

    public List<ticket> getTicketsBooked() {
        return ticketsBooked;
    }

    public void printTickets(){
        for (int i = 0; i<ticketsBooked.size(); i++){
            System.out.println(ticketsBooked.get(i).getTicketInfo());
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public void setTicketsBooked(List<ticket> ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
