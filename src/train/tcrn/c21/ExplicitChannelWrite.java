package train.tcrn.c21;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ExplicitChannelWrite {
  static String PATH = "channel write.txt";

  public static void main(String[] args) {
    try (FileChannel channel =
        (FileChannel) Files.newByteChannel(Paths.get(PATH), StandardOpenOption.WRITE,
          StandardOpenOption.CREATE)) {
      ByteBuffer buf = ByteBuffer.allocate(26);
      for (int i = 0; i < 26; i++) {
        buf.put((byte) ('A' + i));
      }
      buf.rewind();
      channel.write(buf);
    } catch (IOException e) {
    }
  }
}
