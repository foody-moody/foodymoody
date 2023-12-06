package com.foodymoody.be.image.domain;

import org.springframework.http.MediaType;
import org.springframework.util.MimeType;

public enum SupportedImageFormat implements ImageFormat {

    JPEG(MediaType.IMAGE_JPEG, new byte[] {(byte) 0xFF, (byte) 0xD8}),
    PNG(MediaType.IMAGE_PNG, new byte[] {(byte) 0x89,(byte) 0x50,(byte) 0x4E,(byte) 0x47,(byte) 0x0D,(byte) 0x0A,(byte) 0x1A,(byte) 0x0A});

    private final String mimeType;
    private final byte[] signature;

    SupportedImageFormat(MimeType mimeType, byte[] signature) {
        this.mimeType = String.join("/", mimeType.getType(), mimeType.getSubtype());
        this.signature = signature;
    }

    @Override
    public String getMimeType() { return mimeType; }

    @Override
    public byte[] getSignature() {
        return signature;
    }

}
