package com.callbackcats.memeow.service;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StorageService {
    private final String AZURE_STORAGE_CONNECTION_STRING = System.getenv("AZURE_STORAGE_CONNECTION_STRING");
    private final String STORAGE_CONTAINER = System.getenv("AZURE_STORAGE_MEME_CONTAINER");
    private BlobServiceClient blobServiceClient;
    private BlobContainerClient containerClient;

    public StorageService(){
        blobServiceClient = new BlobServiceClientBuilder().connectionString(AZURE_STORAGE_CONNECTION_STRING).buildClient();
        containerClient = blobServiceClient.getBlobContainerClient(STORAGE_CONTAINER);
//
//        for (BlobItem blobItem : containerClient.listBlobs()) {
//            System.out.println("\t" + blobItem.getName());
//        }
    }

}
