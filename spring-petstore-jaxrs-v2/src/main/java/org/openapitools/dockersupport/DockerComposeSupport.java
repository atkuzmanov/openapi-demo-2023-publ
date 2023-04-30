package org.openapitools.dockersupport;

import com.palantir.docker.compose.DockerComposeExtension;
import com.palantir.docker.compose.connection.waiting.HealthChecks;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.lang.reflect.Field;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class DockerComposeSupport {

    private final DockerComposeSupport.Container MONGODB = new DockerComposeSupport.Container("mongodb", 27017);

    private static final DockerComposeSupport INSTANCE = new DockerComposeSupport();

    public static DockerComposeSupport getInstance() {
        return INSTANCE;
    }

    private final DockerComposeExtension dockerComposeRule;

    private final AtomicBoolean running = new AtomicBoolean(false);

    private final Path dockerTempPath;

    public DockerComposeSupport() {
        DockerComposeExtension.Builder builder = DockerComposeExtension.builder();

        try {
            URI resource = ClassLoader.getSystemClassLoader().getResource(".").toURI();
            Path jarPath = Paths.get(Paths.get(resource).toString());
            log.info("Using path {} as temporary Docker Compose base", jarPath.toString());
            dockerTempPath = Files.createTempDirectory(jarPath, "docker");
        } catch (Exception e) {
            throw new IllegalStateException("Unable to create temp path", e);
        }
        ClassPathUtils.extractResources("/docker", dockerTempPath);
        builder.file(dockerTempPath.resolve("docker/docker-compose-test.yml").toString());

        builder.waitingForService(MONGODB.getName(), HealthChecks.toHaveAllPortsOpen());
        dockerComposeRule = builder.build();
    }

    public void start() throws Exception {
        if (running.compareAndSet(false, true)) {
            log.info("Starting Docker Compose...");
            dockerComposeRule.before();
            setEnv();
            log.info("Succesfully started Docker Compose");
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    log.info("Stopping Docker Compose...");
                    dockerComposeRule.after();
                    log.info("Succesfully stopped Docker Compose");
                    FileUtils.deleteDirectory(dockerTempPath.toFile());
                    log.info("Succesfully docker-tmp-dir: {}", dockerTempPath.toString());
                } catch (Exception e) {
                    throw new IllegalStateException("Unable to do a clean shutdown", e);
                }

            }));
        }
    }

    private void setEnv() throws IllegalAccessException {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.getType().equals(DockerComposeSupport.Container.class)) {
                DockerComposeSupport.Container container = (DockerComposeSupport.Container) field.get(this);
                container.setPortExternal(dockerComposeRule);
                log.info("Docker container '{}' with ports internal {} and external {}",
                        container.getName(),
                        container.getPortInternal(),
                        container.getPortExternal());
            }
        }

        System.setProperty("ecm.service.mongodb.uri", String.format("mongodb://localhost:%d", MONGODB.getPortExternal()));
    }

    @Getter
    public static final class Container {

        private final String name;
        private final int portInternal;
        private int portExternal;

        public Container(String name, int portInternal) {
            this.name = name;
            this.portInternal = portInternal;
        }

        public int setPortExternal(DockerComposeExtension dockerComposeRule) {
            this.portExternal = dockerComposeRule.containers()
                    .container(name)
                    .port(portInternal)
                    .getExternalPort();
            return portExternal;
        }
    }
}
