package c3;
import java.io.*;
import java.util.concurrent.*;
public class bai_7 implements Callable<byte[]>{
	 private final String filePath;
	    private final long start;
	    private final int size;

	    public bai_7(String filePath, long start, int size) {
	        this.filePath = filePath;
	        this.start = start;
	        this.size = size;
	    }
	    

	    @Override
	    public byte[] call() throws Exception {
	        try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
	            byte[] buffer = new byte[size];
	            file.seek(start);
	            file.read(buffer);
	            return buffer;
	        }
	    }
	    
	    private static final int CHUNK_SIZE = 1024 * 1024; // 1MB

	    public static void main(String[] args) throws Exception {
	        String inputFilePath = "input.txt";
	        String outputFilePath = "output.txt";

	        File inputFile = new File(inputFilePath);
	        long fileSize = inputFile.length();
	        int numChunks = (int) Math.ceil((double) fileSize / CHUNK_SIZE);

	        ExecutorService executor = Executors.newFixedThreadPool(4);
	        Future<byte[]>[] futures = new Future[numChunks];

	        for (int i = 0; i < numChunks; i++) {
	            long start = (long) i * CHUNK_SIZE;
	            int size = (int) Math.min(CHUNK_SIZE, fileSize - start);
	            futures[i] = executor.submit(new bai_7(inputFilePath, start, size));
	        }

	        executor.shutdown();

	        try (FileOutputStream outputStream = new FileOutputStream(outputFilePath)) {
	            for (Future<byte[]> future : futures) {
	                outputStream.write(future.get());
	            }
	        }

	        System.out.println("File read and merged successfully!");
	    }
	}



   
    


