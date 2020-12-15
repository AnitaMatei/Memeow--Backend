package com.callbackcats.memeow.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
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

    public void uploadImage(ByteArrayInputStream inputStream, String fileName){
        BlobClient blob = memeContainerClient.getBlobClient(fileName);

        blob.upload(inputStream,inputStream.available());

        BlobHttpHeaders blobHttpHeaders = new BlobHttpHeaders();
        blobHttpHeaders.setContentType("image/jpeg");
        blob.setHttpHeaders(blobHttpHeaders);
    }

}
