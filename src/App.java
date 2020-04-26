import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("\nPlease input arguments: file path, minSize, threads\n");
            System.out.println("Example: ./test/syslog10k.log 4 4\n");
            System.exit(0);
        }
        String filename = args[0];
        int minSize = Integer.parseInt(args[1]);
        if (minSize < 1) {
            System.out.println("\nmin should greater than or equal to 1\n");
            System.exit(0);
        }
        int threads = Integer.parseInt(args[2]);
        if (threads < 1) {
            System.out.println("\nthreads number should greater than or equal to 1\n");
            System.exit(0);
        }
        Path path = Paths.get(filename);
        List<String> loglists = Files.readAllLines(path);
        String[] logs = new String[loglists.size()];
        int i = 0;
        for (String s : loglists) {
            logs[i++] = s;
        }
        Parser parser = new Parser();
        LogTime[] times = parser.parse(logs);

        long start = System.currentTimeMillis();

        ParallelMergeSort parallelMergeSort = new ParallelMergeSort(times, minSize, threads);
        parallelMergeSort.parallelMergeSort();

        long end = System.currentTimeMillis();

        String[] res = new String[times.length];
        for (i = 0; i < times.length; i++) {
            LogTime logTime = times[i];
            int index = logTime.index;
            res[i] = logs[index];
        }

        for (i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }

        System.out.println("time: " + (end - start) + " ms");

    }
}
