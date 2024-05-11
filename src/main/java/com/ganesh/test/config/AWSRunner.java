package com.ganesh.test.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AWSRunner implements CommandLineRunner {

    private final S3Client s3Client;

    @Override
    public void run(String... args) throws Exception {
        List<Bucket> buckets = s3Client.listBuckets().buckets();
        buckets.forEach(bucket -> System.out.println("bucket = " + bucket.name()));
    }
}
