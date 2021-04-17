package com.certimetergroup.qrestaurant.client.controller;

import com.certimetergroup.qrestaurant.client.request.PatchFirebaseTokenRequest;
import com.certimetergroup.qrestaurant.client.service.DeviceClientService;
import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.model.DeviceClient;
import com.certimetergroup.qrestaurant.response.Response;
import com.certimetergroup.qrestaurant.utility.QRCodeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@Validated
public class DeviceClientController {

    @Autowired
    private DeviceClientService deviceClientService;

    /* ENDPOINT USED TO DOWNLOAD QRCODE
     * @param registrationToken is client's registration token
     * @return image of QRCode
     */
    @GetMapping(value = "/qrcode/download", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<FileSystemResource> downloadQRCode(@RequestHeader("Authorization-Client-Token") @NotEmpty String registrationToken) {
        DeviceClient deviceClient = deviceClientService.getDeviceClientByRegistrationToken(registrationToken);
        FileSystemResource image = QRCodeUtility.generateImageQRCode(deviceClient.getQRCode());
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=" + image.getFilename())
                .body(image);
    }

    /*
     * ENDPOINT USED TO LOGOUT
     * @param registrationToken is client's registration token
     * @return success if the client is logged out
     */
    @PostMapping("/logout")
    public ResponseEntity<Response> logout(@RequestHeader("Authorization-Client-Token") @NotEmpty String registrationToken) {
        DeviceClient deviceClient = deviceClientService.getDeviceClientByRegistrationToken(registrationToken);
        deviceClientService.deleteDevice(deviceClient);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(ResponseType.SUCCESS));
    }

    /*
     * ENDPOINT USED TO PATCH CLIENT'S FIREBASE TOKEN
     * @param registrationToken is client's registration token
     * @param patchFirebaseTokenRequest contains the firebaseRegistrationToken
     * @return success
     */
    @PatchMapping("/device/firebase/registration/token")
    public ResponseEntity<Response> patchFirebaseToken(@RequestHeader("Authorization-Client-Token") @NotEmpty String registrationToken,
                                                       @RequestBody @Valid PatchFirebaseTokenRequest patchFirebaseTokenRequest) {
        DeviceClient deviceClient = deviceClientService.getDeviceClientByRegistrationToken(registrationToken);
        deviceClient.setFirebaseRegistrationToken(patchFirebaseTokenRequest.getFirebaseRegistrationToken());
        deviceClientService.updateDevice(deviceClient);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(ResponseType.SUCCESS));
    }
}
