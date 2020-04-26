public class Parser {

    public LogTime[] parse(String[] logs) {
        LogTime[] res = new LogTime[logs.length];
        for (int i = 0; i < logs.length; ++i) {
            String log = logs[i];
            int index = log.indexOf(" ");
            res[i] = new LogTime(log.substring(0, index), i);
        }
        return res;
    }
}