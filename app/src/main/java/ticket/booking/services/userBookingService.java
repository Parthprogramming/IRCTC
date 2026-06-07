package ticket.booking.services;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entites.user;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


public class userBookingService {
    private user user ;

    private List<user> userList;

    // Serializing - Putting the user to the JSON File
    // Deserialize - Fetching the user from the JSON File
    private ObjectMapper objectMapper = new ObjectMapper();

    public static final String USERS_PATH="../localDB/user.json";

    public userBookingService(user user1) throws IOException {
        this.user = user1;
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<user>>() {});
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user){
        try{
            userList.add(user);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

}
