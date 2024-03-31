package com.att.kata.azure.functions;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Azure Functions with Cosmos DB trigger.
 */
public class MyFirstCosmosDbFunction {
    /**
     * This function will be invoked when there are inserts or updates in the specified database and collection.
     */
    @FunctionName("MyFirstCosmosDbFunction")
    public void run(
        @CosmosDBTrigger(
            name = "items",
            databaseName = "ToDoList",
            containerName = "ItemsIn",
            connection = "cosmosConnectionString",
            createLeaseContainerIfNotExists = true
        )
        Object inputItem,
        @CosmosDBOutput(name = "database",
                databaseName = "ToDoList",
                containerName = "ItemsOut",
                connection = "cosmosConnectionString")
        OutputBinding<String> outputItem,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java Cosmos DB trigger function executed.");
        ArrayList<?> inputItems = (ArrayList<?>) inputItem;
        context.getLogger().info("Documents modified: " + inputItems.size());

        var id = UUID.randomUUID().toString();

        // Generate document
        final String jsonDocument = "{\"id\":\"" + id + "\", " +
                "\"description\": \"" + "itemAdded" + "\"}";

        context.getLogger().info("Document to be saved: " + jsonDocument);

        // Set outputItem's value to the JSON document to be saved
        outputItem.setValue(jsonDocument);
    }
}
