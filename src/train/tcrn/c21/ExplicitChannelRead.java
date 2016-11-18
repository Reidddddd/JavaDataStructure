package train.tcrn.c21;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExplicitChannelRead {
  public static void main(String[] args) {
    int count;
    Path filePath = null;
    filePath =
        Paths
            .get("D:\\development\\workspace\\java-training\\src\\train\\tcrn\\c11\\CurrentThreadDemo.java");

    try (SeekableByteChannel fChan = Files.newByteChannel(filePath)) {
      ByteBuffer mBuf = ByteBuffer.allocate(128);
      do {
        count = fChan.read(mBuf);
        if (count != -1) {
          mBuf.rewind();
          for (int i = 0; i < count; i++) {
            System.out.print((char) mBuf.get());
          }
        }
      } while (count != -1);
    } catch (IOException e) {
      System.out.println("I/O Error " + e);
    }
  }
}
