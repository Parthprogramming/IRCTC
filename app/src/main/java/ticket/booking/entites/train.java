package ticket.booking.entites;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public class train {


    private String trainId;

    private String trainNo;

    private List<List<Integer>> seats;

    private Map<String, Time> stationTimes;

    private List<String> stations;
}
