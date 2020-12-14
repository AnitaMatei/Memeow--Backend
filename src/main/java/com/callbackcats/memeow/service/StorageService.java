package com.callbackcats.memeow.service;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
@Slf4j
public class StorageService {
    private final String AZURE_STORAGE_CONNECTION_STRING = System.getenv("AZURE_STORAGE_CONNECTION_STRING");
    @Getter
    private final String TEMPLATE_STORAGE_CONTAINER_URL = "memetemplates/";
    @Getter
    private final String MEME_STORAGE_CONTAINER_URL = "usermemes/";
    @Getter
    private final String STORAGE_BASE_URL = "https://memeowstorage.blob.core.windows.net/";
    private BlobServiceClient blobServiceClient;
    private BlobContainerClient memeContainerClient;

    public StorageService(){
        blobServiceClient = new BlobServiceClientBuilder().connectionString(AZURE_STORAGE_CONNECTION_STRING).buildClient();
        memeContainerClient = blobServiceClient.getBlobContainerClient(MEME_STORAGE_CONTAINER_URL);
//
//        for (BlobItem blobItem : containerClient.listBlobs()) {
//            System.out.println("\t" + blobItem.getName());
//        }
    }

    public void uploadMeme(ByteArrayInputStream inputStream, String fileName){
        memeContainerClient.getBlobClient(fileName).upload(inputStream,inputStream.available());
    }

}
