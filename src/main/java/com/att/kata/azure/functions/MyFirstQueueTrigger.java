package com.att.kata.azure.functions;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with Azure Storage Queue trigger.
 */
public class MyFirstQueueTrigger {
    /**
     * This function will be invoked when a new message is received at the specified path. The message contents are provided as input to this function.
     */
    @FunctionName("MyFirstQueueTrigger")
    public void run(
        @QueueTrigger(name = "message", queueName = "kata-queue-1", connection = "storageConnectionString") String messageIn,
        @QueueOutput(name = "message2", queueName = "kata-queue-2", connection = "storageConnectionString") OutputBinding<String> messageOut,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java Queue trigger function processed a message: " + messageIn);
        messageOut.setValue(messageIn);
    }
}
