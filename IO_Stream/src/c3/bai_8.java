package c3;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class bai_8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập từ khóa cần tìm: ");
        String keyword = scanner.nextLine();

        List<String> filePaths = Arrays.asList("input.txt", "input1.txt", "input2.txt"); 

        ExecutorService executor = Executors.newFixedThreadPool(filePaths.size());
        List<Future<Integer>> results = new ArrayList<>();

        for (String filePath : filePaths) {
            results.add(executor.submit(() -> searchInFile(filePath, keyword)));
        }

        executor.shutdown();
        
        int totalOccurrences = 0;
        for (Future<Integer> result : results) {
            try {
                totalOccurrences += result.get(); 
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Tổng số lần xuất hiện của từ khóa '" + keyword + "': " + totalOccurrences);
    }

    private static int searchInFile(String filePath, String keyword) {
        int count = 0;
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                count += countOccurrences(line, keyword);
            }
            System.out.println("File: " + filePath + " - Số lần xuất hiện: " + count);
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file: " + filePath);
        }
        return count;
    }

    private static int countOccurrences(String line, String keyword) {
        int count = 0;
        int index = 0;
        while ((index = line.indexOf(keyword, index)) != -1) {
            count++;
            index += keyword.length();
        }
        return count;
    }
}
