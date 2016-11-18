package train.tcrn.c21;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MappedChannelWrite {
  static String PATH = "mapped channel write.txt";

  public static void main(String[] args) {
    try (FileChannel channel =
        (FileChannel) Files.newByteChannel(Paths.get(PATH), StandardOpenOption.WRITE,
          StandardOpenOption.CREATE, StandardOpenOption.READ)) {
      MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_WRITE, 0, 26);
      for (int i = 0; i < 26; i++) {
        buf.put((byte) ('A' + i));
      }
    } catch (IOException e) {
    }
  }
}
