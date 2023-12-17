package com.foodymoody.be.image.infra.persistence;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.foodymoody.be.common.exception.InvalidImageUrlException;
import com.foodymoody.be.image.domain.ImageCategory;
import com.foodymoody.be.image.domain.ImageResource;
import com.foodymoody.be.image.domain.ImageStorage;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class S3Storage implements ImageStorage {

    private final String s3EndPoint;
    private final String bucketName;
    private final String rootPrefix;
    private final AmazonS3 s3Client;

    public S3Storage(
            @Value("${aws.iam.s3.accesskey}") String accessKey,
            @Value("${aws.iam.s3.secretkey}") String secretKey,
            @Value("${aws.s3.region}") String region,
            @Value("${aws.s3.bucket}") String bucketName,
            @Value("${aws.s3.endpoint}") String s3EndPoint,
            @Value("${aws.s3.root-prefix.image}") String rootPrefix
    ) {
        this.s3EndPoint = s3EndPoint;
        this.bucketName = bucketName;
        this.rootPrefix = rootPrefix;
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();
    }

    @Override
    public String upload(String key, ImageResource file) {
        return upload(key, file.getInputStream(), file.getObjectMetadata());
    }

    @Override
    public void delete(String key) {
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, key);
        s3Client.deleteObject(deleteObjectRequest);
    }

    @Override
    public String generateKey(ImageCategory category, String resourceId, String uuid, String fileName) {
        return String.join("/", rootPrefix, category.getPrefix(resourceId), uuid, fileName);
    }

    @Override
    public String getKey(String url) {
        String rootPath = s3EndPoint.concat("/");
        if (!url.startsWith(rootPath.concat(rootPrefix))) {
            throw new InvalidImageUrlException();
        }
        return url.replace(rootPath, "");
    }

    private String upload(String key, InputStream inputStream, ObjectMetadata metadata) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, metadata);
        s3Client.putObject(putObjectRequest);
        return s3Client.getUrl(bucketName, key).toString();
    }

}
