package com.foodymoody.be.image.infra.persistence;

import com.foodymoody.be.common.exception.InvalidImageUrlException;
import com.foodymoody.be.common.util.ids.BaseId;
import com.foodymoody.be.image.domain.ImageCategory;
import com.foodymoody.be.image.domain.ImageResource;
import com.foodymoody.be.image.domain.ImageStorage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Delete;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectsRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectsResponse;
import software.amazon.awssdk.services.s3.model.ObjectIdentifier;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class S3Storage implements ImageStorage {

    private static final int MAX_DELETE_BATCH_SIZE = 1000;
    private final String s3EndPoint;
    private final String bucketName;
    private final String rootPrefix;
    private final S3Client s3Client;

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
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(accessKey, secretKey))
                )
                .build();
    }

    @Override
    public String upload(String key, ImageResource file) {
        return upload(key, file.getInputStream(), file.getContentType(), file.getContentLength());
    }

    @Override
    public boolean deleteAll(List<String> imageKeys) {
        List<ObjectIdentifier> s3Keys = imageKeys.stream()
                .map(key -> ObjectIdentifier.builder().key(key).build())
                .collect(Collectors.toUnmodifiableList());
        boolean hasNotError = true;

        List<ObjectIdentifier> buffer = new ArrayList<>();

        for (ObjectIdentifier s3Key : s3Keys) {
            buffer.add(s3Key);
            if (buffer.size() > MAX_DELETE_BATCH_SIZE) {
                hasNotError = hasNotError && deleteInBatch(buffer);
                buffer.clear();
            }
        }
        return hasNotError && deleteInBatch(buffer);
    }

    @Override
    public void delete(String key) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.deleteObject(deleteObjectRequest);
    }

    @Override
    public String generateKey(ImageCategory category, BaseId resourceId, String uuid, String fileName) {
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

    private String upload(String key, InputStream inputStream, String contentType, long contentLength) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(contentType)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, contentLength));

        return String.join("/", s3EndPoint, key);
    }

    private boolean deleteInBatch(List<ObjectIdentifier> s3Keys) {
        Delete delete = Delete.builder()
                .objects(s3Keys)
                .build();
        DeleteObjectsRequest deleteObjectsRequest = DeleteObjectsRequest.builder()
                .bucket(bucketName)
                .delete(delete)
                .build();
        DeleteObjectsResponse deleteObjectsResponse = s3Client.deleteObjects(deleteObjectsRequest);
        return !deleteObjectsResponse.hasErrors();
    }

}
