package task1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Margo on 19.09.2015.
 */
public class GenerateCharsTask implements Runnable {

    private File file;
    private int n;

    public GenerateCharsTask(File file, int n) {
        this.file = file;
        this.n = n;
    }

    //33 - 126
    @Override
    public void run() {
        System.out.println("Entered to generator");

        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        stringBuffer.append("Generator\n");

        for (int i = 0; i < n; i++) {
            stringBuffer.append(random.nextInt(33) + '!');
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter(file, true);
            writer.write(stringBuffer.toString());
            SharedResource.setIsWritten(true);
            System.out.println("Finished generating chars");
            synchronized (SharedResource.getLock()) {
                SharedResource.getLock().notifyAll();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Quited from thread1\n");
    }
}
