package com.callbackcats.memeow.service;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StorageService {
    private final String AZURE_STORAGE_CONNECTION_STRING =
            "DefaultEndpointsProtocol=https;" +
            "AccountName=memeowstorage;" +
            "AccountKey=llQxGeVBUkHK623VEkDkAMpasfSGcf42JLZkZPTzNiWGaAUbpCV2vGfw3oyz/YKkIgZ7cjufzdgzTxxMVLBptA==";
    private final String MEME_STORAGE_CONTAINER = "memetemplates";
    private BlobServiceClient blobServiceClient;
    private BlobContainerClient containerClient;

    public StorageService(){
        blobServiceClient = new BlobServiceClientBuilder().connectionString(AZURE_STORAGE_CONNECTION_STRING).buildClient();
        containerClient = blobServiceClient.getBlobContainerClient(MEME_STORAGE_CONTAINER);
    }

}
