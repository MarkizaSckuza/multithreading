package task1;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Margo on 19.09.2015.
 */
public class Test {

    public static void main(String[] args) {

        String name = "result.txt";
        File file = new File(name);

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(new GenerateCharsTask(file, 20000));
        executorService.execute(new ReadAndClassifyTask(file));
        executorService.execute(new WriteTask(file));

        executorService.shutdown();
    }
}
