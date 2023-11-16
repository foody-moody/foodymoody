package com.foodymoody.be.image.repository;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.IOException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageStorage {

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

    @Transactional
    public String upload(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            String fileType = originalFilename.split("\\.")[1];
            ObjectMetadata data = new ObjectMetadata();
            data.setContentType("image/" + fileType);
            data.setContentLength(file.getSize());
            String uuid = UUID.randomUUID().toString();
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucketName, uuid, file.getInputStream(), data);
            s3Client.putObject(putObjectRequest);
            return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + uuid;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
