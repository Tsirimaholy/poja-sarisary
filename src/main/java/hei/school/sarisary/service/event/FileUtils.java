package hei.school.sarisary.service.event;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FileUtils {
  public static File convertBytesToFile(byte[] bytes) throws IOException {
    File tempFile = File.createTempFile("temp", UUID.randomUUID().toString());
    try (FileOutputStream fos = new FileOutputStream(tempFile)) {
      fos.write(bytes);
    }

    return tempFile;
  }
}
