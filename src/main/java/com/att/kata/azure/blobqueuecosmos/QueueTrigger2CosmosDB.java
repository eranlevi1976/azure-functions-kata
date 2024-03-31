package com.att.kata.azure.blobqueuecosmos;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.CosmosDBOutput;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.QueueTrigger;

import java.util.UUID;

/**
 * Azure Functions with Azure Storage Queue trigger.
 */
public class QueueTrigger2CosmosDB {
    /**
     * This function will be invoked when a new message is received at the specified path. The message contents are provided as input to this function.
     */
    @FunctionName("QueueTrigger2CosmosDB")
    public void run(
        @QueueTrigger(name = "message", queueName = "kata-queue-3", connection = "storageConnectionString") String messageIn,
        @CosmosDBOutput(name = "database",
                databaseName = "ToDoList",
                containerName = "ItemsOut",
                connection = "cosmosConnectionString")
        OutputBinding<String> outputItem,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java Queue trigger function processed a message: " + messageIn);

        int i = Integer.parseInt(messageIn);
        i++;
        var id = UUID.randomUUID().toString();
        // Generate document
        final String jsonDocument = "{\"id\":\"" + id + "\", " +
                "\"value\": \"" + i + "\"}";

        context.getLogger().info("Document to be saved: " + jsonDocument);

        // Set outputItem's value to the JSON document to be saved
        outputItem.setValue(jsonDocument);
    }
}
