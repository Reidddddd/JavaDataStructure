package train.tcrn.c21;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MappedChannelRead {
  public static void main(String[] args) {
    try (FileChannel channel =
        (FileChannel) Files
            .newByteChannel(Paths
                .get("D:\\development\\workspace\\java-training\\src\\train\\tcrn\\c11\\CurrentThreadDemo.java"))) {
      long size = channel.size();
      MappedByteBuffer mbuf = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
      for (int i = 0; i < size; i++) {
        System.out.print((char) mbuf.get());
      }
      System.out.println();
    } catch (IOException e) {
    }
  }
}
