package com.att.kata.azure.functions;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with Azure Blob trigger.
 */
public class MyFirstBlobTrigger {
    /**
     * This function will be invoked when a new or updated blob is detected at the specified path. The blob contents are provided as input to this function.
     */
    @FunctionName("MyFirstBlobTrigger")
    @StorageAccount("storageConnectionString")
    public void run(
        @BlobTrigger(name = "content", path = "kata-blob-container-1/{name}", dataType = "binary") byte[] content,
        @BlobOutput(name = "target",path = "kata-blob-container-2/{name}-Copy.txt")OutputBinding<byte[]> outputItem,
        @BindingName("name") String name,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java Blob trigger function processed a blob. Name: " + name + "\n  Size: " + content.length + " Bytes");

        outputItem.setValue(content);


    }
}
