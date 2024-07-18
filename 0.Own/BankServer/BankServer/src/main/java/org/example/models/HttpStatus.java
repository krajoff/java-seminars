package org.example.models;

public class HttpStatus {
    public static String getStatusMessage(int statusCode) {
        switch (statusCode) {
            case 100:
                return "100 Continue: The server has received the request headers and the client should proceed to send the request body.";
            case 101:
                return "101 Switching Protocols: The requester has asked the server to switch protocols and the server has agreed to do so.";
            case 200:
                return "200 OK: The request has succeeded.";
            case 201:
                return "201 Created: The request has been fulfilled and resulted in a new resource being created.";
            case 202:
                return "202 Accepted: The request has been accepted for processing, but the processing has not been completed.";
            case 203:
                return "203 Non-Authoritative Information: The server is a transforming proxy that received a 200 OK from its origin, but is returning a modified version of the origin's response.";
            case 204:
                return "204 No Content: The server successfully processed the request, but is not returning any content.";
            case 205:
                return "205 Reset Content: The server successfully processed the request, but is not returning any content and requires that the requester reset the document view.";
            case 206:
                return "206 Partial Content: The server is delivering only part of the resource due to a range header sent by the client.";
            case 300:
                return "300 Multiple Choices: There are multiple options for the resource from which the client may choose.";
            case 301:
                return "301 Moved Permanently: The resource has been moved permanently to a new URI.";
            case 302:
                return "302 Found: The resource has been found at a different URI and should be retrieved from there.";
            case 303:
                return "303 See Other: The response to the request can be found under another URI using a GET method.";
            case 304:
                return "304 Not Modified: The resource has not been modified since the version specified by the request headers.";
            case 305:
                return "305 Use Proxy: The requested resource must be accessed through the proxy given by the Location field.";
            case 307:
                return "307 Temporary Redirect: The resource resides temporarily under a different URI.";
            case 400:
                return "400 Bad Request: The server could not understand the request due to invalid syntax.";
            case 401:
                return "401 Unauthorized: The client must authenticate itself to get the requested response.";
            case 402:
                return "402 Payment Required: This code is reserved for future use.";
            case 403:
                return "403 Forbidden: The client does not have access rights to the content.";
            case 404:
                return "404 Not Found: The server can not find the requested resource.";
            case 405:
                return "405 Method Not Allowed: The request method is known by the server but is not supported by the target resource.";
            case 406:
                return "406 Not Acceptable: The server cannot produce a response matching the list of acceptable values defined in the request's proactive content negotiation headers.";
            case 407:
                return "407 Proxy Authentication Required: The client must authenticate itself with the proxy.";
            case 408:
                return "408 Request Timeout: The server would like to shut down this unused connection.";
            case 409:
                return "409 Conflict: The request conflicts with the current state of the server.";
            case 410:
                return "410 Gone: The requested resource is no longer available and will not be available again.";
            case 411:
                return "411 Length Required: The request did not specify the length of its content, which is required by the requested resource.";
            case 412:
                return "412 Precondition Failed: The server does not meet one of the preconditions that the requester put on the request.";
            case 413:
                return "413 Payload Too Large: The request is larger than the server is willing or able to process.";
            case 414:
                return "414 URI Too Long: The URI requested by the client is longer than the server is willing to interpret.";
            case 415:
                return "415 Unsupported Media Type: The media format of the requested data is not supported by the server.";
            case 416:
                return "416 Range Not Satisfiable: The range specified by the Range header field in the request cannot be fulfilled.";
            case 417:
                return "417 Expectation Failed: The server cannot meet the requirements of the Expect request-header field.";
            case 418:
                return "418 I'm a teapot: This code was defined in 1998 as one of the traditional IETF April Fools' jokes.";
            case 426:
                return "426 Upgrade Required: The client should switch to a different protocol.";
            case 500:
                return "500 Internal Server Error: The server has encountered a situation it doesn't know how to handle.";
            case 501:
                return "501 Not Implemented: The request method is not supported by the server and cannot be handled.";
            case 502:
                return "502 Bad Gateway: The server, while acting as a gateway or proxy, received an invalid response from the upstream server.";
            case 503:
                return "503 Service Unavailable: The server is not ready to handle the request.";
            case 504:
                return "504 Gateway Timeout: The server is acting as a gateway or proxy and did not receive a timely response from the upstream server.";
            case 505:
                return "505 HTTP Version Not Supported: The HTTP version used in the request is not supported by the server.";
            default:
                return statusCode + " Unknown Status Code";
        }
    }
}
