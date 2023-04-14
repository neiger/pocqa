package general;

import org.testng.Reporter;

public class ErrorHandler {
        public static void handle(Exception exception) {
            String errorMessage = "Error: " + exception.getMessage();
            System.err.println(errorMessage);
            Reporter.log(errorMessage);

            // Add custom logic based on the exception type, e.g., retry, exit, etc.
            if (exception instanceof org.openqa.selenium.NoSuchElementException) {
                String message = "Element not found. Please check your locators.";
                System.err.println(message);
                Reporter.log(message);
                // Add your custom logic here
            } else if (exception instanceof org.openqa.selenium.TimeoutException) {
                String message = "Operation timed out. Retrying...";
                System.err.println(message);
                Reporter.log(message);
                // Add your custom logic here
            } else if (exception instanceof org.openqa.selenium.WebDriverException) {
                String message = "WebDriverException occurred.";
                System.err.println(message);
                Reporter.log(message);
                // Add your custom logic here
            } else {
                String message = "Unknown error occurred.";
                System.err.println(message);
                Reporter.log(message);
                // Add your custom logic here
            }
        }
    }
