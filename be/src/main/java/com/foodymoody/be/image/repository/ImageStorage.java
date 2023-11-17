package com.foodymoody.be.image.repository;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageStorage {
    //FIXME 아직 구현이 완료되지 않았습니다.

    private final String region;
    private final String bucketName;
    private final AmazonS3 s3Client;

    public ImageStorage(
            @Value("${aws.iam.s3.accesskey}") String accessKey,
            @Value("${aws.iam.s3.secretkey}") String secretKey,
            @Value("${aws.s3.region}") String region,
            @Value("${aws.s3.bucket}") String bucketName
    ) {
        this.region = region;
        this.bucketName = bucketName;
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();
    }

    public String upload(MultipartFile file, String key) {
        try {
            ObjectMetadata metadata = getObjectMetadata(file);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file.getInputStream(), metadata);
            s3Client.putObject(putObjectRequest);
            return createS3Url(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(String key) {
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, key);
        s3Client.deleteObject(deleteObjectRequest);
    }

    private ObjectMetadata getObjectMetadata(MultipartFile file) {
        String fileType = getMIMEType(file);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(fileType);
        metadata.setContentLength(file.getSize());
        return metadata;
    }

    private String createS3Url(String key) {
        return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key;
    }

    private String getMIMEType(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String fileType = originalFilename.split("\\.")[1];
        return "image/" + fileType;
    }

}
