import { HttpStatusCode } from "@Infrastructure/httpClient/HttpStatusCode";

export function generateErrorMessage(httpStatusCode: HttpStatusCode | null, message: string) {
    return (httpStatusCode? (httpStatusCode + ": ") : "") 
            + message;
}