package hei.school.sarisary.endpoint.rest.controller.health;

import hei.school.sarisary.file.BucketComponent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BlackAndWhiteController {
  private final String IMAGE_BUCKET_KEY = "images/";
  BucketComponent bucketComponent;

  @PutMapping("/black-and-white/{id}")
  public ResponseEntity<String> uploadImage(
      @PathVariable("id") String id, @RequestBody byte[] fileBytes) throws IOException {
    String fileSuffix = ".png";
    var fileBucketKey = IMAGE_BUCKET_KEY + id + fileSuffix;

    File file = convertByteArrayToFile(fileBytes, "./");
    if (file != null) {
      System.out.println("File created: " + file.getAbsolutePath());
    } else {
      System.out.println("Failed to create file.");
    }

    assert file != null;
    bucketComponent.upload(file, fileBucketKey);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

  public static File convertByteArrayToFile(byte[] byteArray, String filePath) {
    FileOutputStream fos = null;
    File file = null;
    try {
      file = new File(filePath);
      fos = new FileOutputStream(file);
      fos.write(byteArray);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (fos != null) {
          fos.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return file;
  }
}
