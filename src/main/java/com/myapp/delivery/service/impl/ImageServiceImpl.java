package com.myapp.delivery.service.impl;

import com.myapp.delivery.domain.menu_item.MenuItemImage;
import com.myapp.delivery.service.ImageService;
import com.myapp.delivery.service.props.MinioProperties;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

  private final MinioClient minioClient;
  private final MinioProperties minioProperties;

  @Override
  public String upload(MenuItemImage menuItemImage) {
    System.out.println("Зашел в метод upload");
    try {
      createBucket();
    } catch (Exception e) {
      throw new RuntimeException("Image upload failed create bucket " + e.getMessage());
    }
    MultipartFile file = menuItemImage.getFile();
    if (file.isEmpty() || file.getOriginalFilename() == null) {
      throw new RuntimeException("Image must have name");
    }
    String fileName = generateFileName(file);
    InputStream inputStream;
    try {
      inputStream = file.getInputStream();
    } catch (Exception e) {
      throw new RuntimeException("Image upload failed file.getInputStream() " + e.getMessage());
    }
    saveImage(inputStream, fileName);
    return fileName;
  }

  @SneakyThrows
  @Override
  public InputStream getImage(String fileName) {
    return minioClient.getObject(GetObjectArgs.builder()
            .bucket(minioProperties.getBucket())
            .object(fileName)
            .build());
  }

  @SneakyThrows
  private void createBucket() {
    boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
            .bucket(minioProperties.getBucket())
            .build());
    System.out.println(found);
    if (!found) {
      minioClient.makeBucket(MakeBucketArgs.builder()
              .bucket(minioProperties.getBucket())
              .build());
    }
  }

  private String generateFileName(MultipartFile file) {
    String extension = getExtension(file);
    return UUID.randomUUID() + "." + extension;
  }

  private String getExtension(MultipartFile file) {
    return file.getOriginalFilename()
            .substring(file.getOriginalFilename().lastIndexOf(".") + 1);
  }

  @SneakyThrows
  private void saveImage(InputStream inputStream, String fileName) {
    minioClient.putObject(PutObjectArgs.builder()
            .stream(inputStream, inputStream.available(), -1)
            .bucket(minioProperties.getBucket())
            .object(fileName)
            .build());
  }
}
