package c3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class bai_6 implements Callable<String> {
    private final String fileName;

    public bai_6(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String call() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file: " + fileName);
        }
        return content.toString();
    }

    public static void main(String[] args) {
        String[] inputFiles = {"input.txt", "input1.txt", "input2.txt"}; 
        String outputFile = "output.txt"; 

        ExecutorService es = Executors.newFixedThreadPool(inputFiles.length);
        List<Future<String>> futures = new ArrayList<>();

        for (String file : inputFiles) {
            futures.add(es.submit(new bai_6(file)));
        }

        // Ghi vào file output.txt
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (Future<String> future : futures) {
                bw.write(future.get());
            }
            System.out.println("Ghi hoàn tất vào " + outputFile);
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        es.shutdown();
    }
}
