package org.openapitools.dockersupport;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DockerComposeSupportRule implements BeforeAllCallback, AfterAllCallback {

    private final DockerComposeSupport dockerComposeRule = DockerComposeSupport.getInstance();

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        dockerComposeRule.start();
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        // we do nothing here, because #DockerComposeSupport.start registers a shutdownhook, which stops the system after all tests
    }
}
