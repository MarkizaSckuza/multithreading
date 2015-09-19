package task1;

import java.io.*;

/**
 * Created by Margo on 19.09.2015.
 */
public class WriteTask implements Runnable {

    private File file;

    public WriteTask(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        System.out.println("Entered to writer");
        while (SharedResource.getResource() == null || SharedResource.getResource().isEmpty()) {
            synchronized (SharedResource.getLock()) {
                try {
                    SharedResource.getLock().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        FileWriter writer = null;

        try {
            writer = new FileWriter(file, true);
            writer.write("\n\nWriter\n");
            writer.write(SharedResource.getResource());
            System.out.println("Finished writing");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Quited from thread3");
    }
}
