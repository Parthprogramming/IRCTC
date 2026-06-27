package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entites.train;
import ticket.booking.entites.user;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class userBookingService {

    private user user;

    private List<user> userList;

    // Serializing - Putting the user to the JSON File
    // Deserialize - Fetching the user from the JSON File
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_FILE_PATH;

    static {
        try {
            USERS_FILE_PATH = new File(
                    userBookingService.class
                            .getClassLoader()
                            .getResource("localDB/user.json")
                            .toURI()
            ).getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException("Cannot resolve user.json path", e);
        }
    }

    public userBookingService(user user1) throws IOException {
        this.user = user1;
        loadUsers();
    }

    public userBookingService() throws IOException {
        loadUsers();
    }

    public List<user> loadUsers() throws IOException {
        File usersFile = new File(USERS_FILE_PATH);
        if (!usersFile.exists()) {
            userList = new ArrayList<>();
            return userList;
        }
        userList = objectMapper.readValue(usersFile, new TypeReference<List<user>>() {
        });
        return userList;
    }

    public Boolean loginUser() {
        Optional<user> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.gethashPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(user user) {
        try {
            userList.add(user);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USERS_FILE_PATH);
        usersFile.getParentFile().mkdirs(); // creates localDB/ if it doesn't exist
        objectMapper.writeValue(usersFile, userList);
    }

    public void fetchBooking() {
        user.printTickets();
    }

    public Boolean cancelBooking(String ticketId) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the ticketId to cancel");
        ticketId = s.next();

        if (ticketId == null || ticketId.isEmpty()) {
            System.out.println("Ticket ID cannot be null or empty");
        }

        String finalTicketId1 = ticketId;
        boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketId1));

        String finalTicketId = ticketId;
        user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketId));
        if (removed) {
            System.out.println("Ticket with ID " + ticketId + " has been canceled.");
            return Boolean.TRUE;
        } else {
            System.out.println("No ticket found with ID " + ticketId);
            return Boolean.FALSE;
        }
    }

    public List<train> getTrains(String source, String destination) {
        try {
            trainService trainService = new trainService();
            return trainService.searchTrains(source, destination);
        } catch (IOException ex) {
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeats(train train) {
        return train.getSeats();
    }

    public Boolean bookTrainSeat(train train, int row, int seat) {
        try {
            trainService trainService = new trainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }
}
