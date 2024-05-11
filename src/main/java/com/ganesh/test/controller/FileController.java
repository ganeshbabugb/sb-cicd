package com.ganesh.test.controller;

import com.ganesh.test.config.AWSCredentials;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class FileController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);
    private final S3Client s3Client;
    private final AWSCredentials awsCredentials;

    @GetMapping("/downloadFile")
    public ResponseEntity<?> downloadFile(@RequestParam(defaultValue = "ganesh.txt", required = false, name = "fileName")
                                          String objectKey) {

        log.info("Downloading file: {}", objectKey);

        GetObjectRequest objectRequest = GetObjectRequest.builder()
                .bucket(awsCredentials.bucket())
                .key(objectKey)
                .build();
        ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(objectRequest);

        byte[] data = objectBytes.asByteArray();

        try {
            // Set the headers for the response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", objectKey);

            // Stream the byte[] data directly to the response
            return new ResponseEntity<>(data, headers, HttpStatus.OK);

        } catch (S3Exception e) {
            // Handle any exceptions
            return ResponseEntity.status(e.statusCode())
                    .body("Failed to download file: " + e.awsErrorDetails().errorMessage());
        }
    }
}
