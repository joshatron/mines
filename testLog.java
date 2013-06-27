import java.io.*;

public class testLog
{
    public static void main(String[] args) throws IOException
    {
        log l = new log();

        l.addStats("Josh", 1, true, 14);
        l.toString();
        l.addStats("Josh", 2, true, 87);
        l.toString();
        l.addStats("Alex", 2, false, 1024);
        l.toString();
        l.addStats("Josh", 3, true, 318);
        l.toString();
        l.toFile();
    }
}
