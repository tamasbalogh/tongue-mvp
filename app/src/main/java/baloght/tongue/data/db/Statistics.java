package baloght.tongue.data.db;

public class Statistics {

    public static final String TABLE_NAME = "statistics";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_STAR = "star";
    public static final String COLUMN_PERCENTAGE = "percentage";

    private Long id;
    private String date;
    private int star;
    private int percentage;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_DATE + " TEXT,"
                    + COLUMN_STAR + " INTEGER,"
                    + COLUMN_PERCENTAGE + " INTEGER"
                    + ")";

    public Statistics() {
    }

    public Statistics(Long id, String date, int star, int percentage) {
        this.id = id;
        this.date = date;
        this.star = star;
        this.percentage = percentage;
    }

    public Statistics(String date, int star, int percentage) {
        this.date = date;
        this.star = star;
        this.percentage = percentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", star=" + star +
                ", percentage=" + percentage +
                '}';
    }
}
