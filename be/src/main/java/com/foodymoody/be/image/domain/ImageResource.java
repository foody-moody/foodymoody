package com.foodymoody.be.image.domain;

import com.foodymoody.be.common.exception.InvalidImageFileException;
import com.foodymoody.be.common.exception.UnsupportedImageFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public class ImageResource {

    private final Resource resource;
    private final String mimeType;
    private final long contentLength;

    public ImageResource(MultipartFile file) {
        validateFile(file);
        this.mimeType = getMimeTypeIfSupported(file)
                .orElseThrow(UnsupportedImageFormatException::new);
        this.resource = file.getResource();
        this.contentLength = file.getSize();
    }

    public String getFilename() {
        return resource.getFilename();
    }

    public String getContentType() {
        return this.mimeType;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public InputStream getInputStream() {
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            throw new InvalidImageFileException();
        }
    }

    private void validateFile(MultipartFile file) {
        if (Objects.isNull(file) || Objects.isNull(file.getOriginalFilename())
                || file.isEmpty() || file.getOriginalFilename().isBlank()) {
            throw new InvalidImageFileException();
        }
    }

    private Optional<String> getMimeTypeIfSupported(MultipartFile file) {
        for (ImageFormat format : SupportedImageFormat.values()) {
            if (isSignatureMatch(file, format)) {
                return Optional.of(format.getMimeType());
            }
        }
        return Optional.empty();
    }

    private boolean isSignatureMatch(MultipartFile file, ImageFormat format) {
        byte[] signature = getSignature(file, format.getSignature().length);
        return Arrays.equals(signature, format.getSignature());
    }

    private byte[] getSignature(MultipartFile file, int signatureLength) {
        byte[] signature = new byte[signatureLength];

        try {
            file.getInputStream().read(signature);
        } catch (IOException e) {
            throw new InvalidImageFileException();
        }

        return signature;
    }
}
