package com.att.kata.azure.rollingqueuemessage;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.QueueOutput;
import com.microsoft.azure.functions.annotation.QueueTrigger;

public class QueueTrigger1 {

    @FunctionName("QueueTrigger1")
    public void run(
            @QueueTrigger(name = "message", queueName = "kata-rolling-queue-1", connection = "storageConnectionString") String messageIn,
            @QueueOutput(name = "message2", queueName = "kata-rolling-queue-2", connection = "storageConnectionString") OutputBinding<String> messageOut,
            final ExecutionContext context
    ) throws InterruptedException {
        context.getLogger().info("Java Queue trigger function processed a message: " + messageIn);
        int i = Integer.parseInt(messageIn);
        Thread.sleep(5000);
        messageOut.setValue(Integer.toString(i + 1));
    }
}
