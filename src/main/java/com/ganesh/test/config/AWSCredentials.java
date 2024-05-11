package com.ganesh.test.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.s3")
public record AWSCredentials(String accessKey, String secretKey, String bucket, String region) {
}
