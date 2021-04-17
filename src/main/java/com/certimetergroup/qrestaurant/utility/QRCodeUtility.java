package com.certimetergroup.qrestaurant.utility;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.springframework.core.io.FileSystemResource;

public class QRCodeUtility {
    public static String generateQRCode(String UUID) {
        return UUID + "_" + System.currentTimeMillis();
    }

    public static FileSystemResource generateImageQRCode(String qrcode) {
        return new FileSystemResource(QRCode.from(qrcode).to(ImageType.JPG).withSize(375, 375).file());
    }
}
