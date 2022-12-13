package polmustdie;

import java.io.*;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.*;

public class LogChecker {

    FileReader reader;

    {
        try {
            reader = new FileReader("/Users/soulfade/Desktop/text_5.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    Closeable resource = reader;
    public void Run() throws IOException {
        try {
            BufferedReader buffered = new BufferedReader(reader);
            resource = buffered;
            Map<String, Log> dictionaryLog = new HashMap<>();

//            BufferedReader reader = new BufferedReader(
//                    new FileReader("/Users/soulfade/Desktop/text_5.txt"));

            String line = buffered.readLine();

            while (line != null) {
                String[] lines = line.split("ID = ");
                String id = lines[1];
                String data = lines[0].substring(lines[0].indexOf('.') + 2, lines[0].indexOf('–') - 1);

                Log currentLog = dictionaryLog.get(id);

                if (currentLog == null) {
                    dictionaryLog.put(id, new Log(id, data));
                }
                else {
                    currentLog.addEnd(data);
                }
                line = buffered.readLine();
            }

            ArrayList<Log> listOfKeys = new ArrayList<>(dictionaryLog.values());

//            int i = listOfKeys.size()/2;
//            long median = listOfKeys.get(listOfKeys.size()/2).getDelta();
            ArrayList<Long> list = new ArrayList<>();
            for (int i = 0; i < listOfKeys.size();i++) {
                list.add(listOfKeys.get(i).getDelta());
            }
            Collections.sort(list);
            long median = list.get(listOfKeys.size()/2);
//            long median = listOfKeys.get(listOfKeys.size()/2).getDelta();

            System.out.println("\nInput delta [seconds]: ");
            System.out.println(listOfKeys);
            Scanner scanner = new Scanner(System.in);
            long delta = scanner.nextInt() * 1000L;
            if (delta == 0)
                delta = 3000;

            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, Log> entry : dictionaryLog.entrySet()) //checking each entry
            {
                if (entry.getValue().getDelta() > median + delta)
                {
                    builder.append(entry.getValue().toString()).append("\n");
                }
            }
            System.out.println("\nMedian: " + median/1000 + " [seconds]");
            System.out.println("These logs are fluctuated:");
            System.out.println(builder);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        finally {
            resource.close();
        }

    }
}