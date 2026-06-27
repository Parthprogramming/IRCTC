package ticket.booking.entites;

import java.util.Date;

public class ticket {

    private String ticketId;

    private String userId;

    private String source;

    private String destination;

    private Date dateoftravel;

    private train train;

    public ticket(){}

    public ticket(String ticketId, String userId, String source, String destination, Date dateoftravel, train train){
        this.ticketId = ticketId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.dateoftravel = dateoftravel;
        this.train = train;
    }

    public String getTicketInfo(){
        return String.format("Ticket ID: %s belongs to User %s from %s to %s on %s", ticketId, userId, source, destination, dateoftravel);
    }

    public String getTicketId(){
        return ticketId;
    }

    public void setTicketId(String ticketId){
        this.ticketId = ticketId;
    }

    public String getSource(){
        return source;
    }

    public void setSource(String source){
        this.source = source;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getDestination(){
        return destination;
    }

    public void setDestination(String destination){
        this.destination = destination;
    }

    public Date getDateOfTravel(){
        return dateoftravel;
    }

    public void setDateOfTravel(Date dateoftravel){
        this.dateoftravel = dateoftravel;
    }

    public train getTrain(){
        return train;
    }

    public void setTrain(train train){
        this.train = train;
    }

}




