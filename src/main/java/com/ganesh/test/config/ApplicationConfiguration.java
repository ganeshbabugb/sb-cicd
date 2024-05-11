package com.ganesh.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class ApplicationConfiguration {

    private final AWSCredentials awsCredentials;

    public ApplicationConfiguration(AWSCredentials awsCredentials) {
        this.awsCredentials = awsCredentials;
    }

    @Bean("s3Client")
    public S3Client getS3Client() {
        Region region = Region.of(awsCredentials.region());
        return S3Client.builder()
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(awsCredentials.accessKey(), awsCredentials.secretKey())
                        )
                )
                .region(region)
                .build();
    }

}
