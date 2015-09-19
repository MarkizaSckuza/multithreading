package task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Margo on 19.09.2015.
 */
public class ReadAndClassifyTask implements Runnable {

    private File file;

    public ReadAndClassifyTask(File file) {
        this.file = file;
    }

    @Override
    public void run() {

        System.out.println("Entered to classifier");
        while (!SharedResource.getIsWritten()) {
            synchronized (SharedResource.getLock()) {
                try {
                    SharedResource.getLock().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));

            String s;
            while ((s = reader.readLine()) != null) {
                stringBuilder.append(s);
            }

            SharedResource.setResource(this.sort(stringBuilder.toString().toCharArray()));
            SharedResource.setIsSorted(true); //???
            System.out.println("Finished classification");
            synchronized (SharedResource.getLock()) {
                SharedResource.getLock().notifyAll();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Quited from thread 2\n");
    }

    private String sort(char[] chars) {

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length - i - 1; j++) {
                if (chars[j] > chars[j + 1]) {
                    char temp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = temp;
                }
            }
        }
        return new String(chars);
    }
}
