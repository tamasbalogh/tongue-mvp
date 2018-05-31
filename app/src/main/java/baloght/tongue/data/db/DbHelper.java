package baloght.tongue.data.db;

import java.util.List;

public interface DbHelper {
    Statistics getStatisticsById(long id);
    List<Statistics> getAllStatistics();
    int getNumberOfGamesByDate(String date);
    long insertStatistics(Statistics statistics);
    void deleteAllStatistics();
}
