package polmustdie;

import java.text.ParseException;
import java.text.SimpleDateFormat;

class Log
{
    String id;
    java.util.Date start;
    java.util.Date end;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Log(String id, String start) throws ParseException
    {
        this.id = id;
        this.start = dateFormat.parse(start);
        this.end = null;
    }
    public void addEnd(String end) throws ParseException
    {
        this.end = dateFormat.parse(end);
    }
    public long getDelta()
    {
        return end.getTime() - start.getTime();
    }

    @Override
    public String toString() {
        return "ID = " + this.id + " start: "+ dateFormat.format(start) +
                " end: " + dateFormat.format(end);
    }
}