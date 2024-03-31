package com.att.kata.azure.blobqueuecosmos;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.BlobTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.QueueOutput;
import com.microsoft.azure.functions.annotation.StorageAccount;

/**
 * Azure Functions with Azure Blob trigger.
 */
public class BlobTrigger2Queue {
    /**
     * This function will be invoked when a new or updated blob is detected at the specified path. The blob contents are provided as input to this function.
     */
    @FunctionName("BlobTrigger2Queue")
    @StorageAccount("storageConnectionString")
    public void run(
        @BlobTrigger(name = "content", path = "kata-blob-container-3/{name}", dataType = "binary")
        byte[] content,
        @QueueOutput(name = "message2", queueName = "kata-queue-3", connection = "storageConnectionString")
        OutputBinding<String> messageOut,
        @BindingName("name") String name,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java Blob trigger function processed a blob. Name: " + name + "\n  Size: " + content.length + " Bytes");

        String fileContent = new String(content);
        int i = Integer.parseInt(fileContent);
        i++;
        messageOut.setValue(Integer.toString(i));

    }
}
