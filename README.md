# parallelMergeSort

An implementation of parallel merge sort using [ForkJoinPool](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ForkJoinPool.html).

The goal is to sort the log files by date time.

#### How to run

To run merge sort on syslog1Mb.log file with misSize = 10, threads = 4

`./ParallelMergeSort syslog1Mb.log 10 4`

#### Test cases

* test file: syslog1Mb.log
* number of physical cores: 4
* number of logical cores: 8

| misSize | threads | time (ms) |
| ------  | ------- | --------  |
|10       |1        |359        |
|10       |2        |271        |
|10       |3        |245        |
|10       |4        |217        |
|10       |5        |275        |
|10       |6        |254        |
|10       |7        |237        |
|10       |8        |236        |
|10       |16       |240        |
|10       |32       |237        |

#### Libraries

[org.joda.time.DateTime](https://www.joda.org/joda-time/)  -- To compare date time