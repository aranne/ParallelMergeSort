import org.joda.time.DateTime;

public class LogTime implements Comparable<LogTime> {
    DateTime datetime;
    int index;

    public LogTime(String s, int i) {
        index = i;
        datetime = new DateTime(s);
    }

    @Override
    public int compareTo(LogTime log) {
        return this.datetime.compareTo(log.datetime);
    }

    @Override
    public String toString() {
        return datetime.toString();
    }
}
