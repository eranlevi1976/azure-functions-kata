package com.att.kata.azure.rollingqueuemessage;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.QueueOutput;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class HttpTriggerFunction {
    /**
     * This function listens at endpoint "/api/HttpTriggerFunction". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpTriggerFunction
     * 2. curl {your host}/api/HttpTriggerFunction?number=1
     */
    @FunctionName("HttpTriggerFunction")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.FUNCTION) HttpRequestMessage<Optional<String>> request,
            @QueueOutput(name = "message", queueName = "kata-rolling-queue-1", connection = "storageConnectionString") OutputBinding<String> messageOut,

            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter
        String query = request.getQueryParameters().get("number");
        String number = request.getBody().orElse(query);

        if (number == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a number on the query string or in the request body").build();
        }

        int i;
        try{
            i = Integer.parseInt(number);
        }catch(NumberFormatException nfe){
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a valid number on the query string").build();
        }
        messageOut.setValue(Integer.toString(i+1));
        return request.createResponseBuilder(HttpStatus.OK).body("Thanks!").build();

    }
}
