package org.openapitools.dockersupport;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Stream;
public class ClassPathUtils {
    private ClassPathUtils() {
    }
    private static List<String> resourceProvider(Path path, String name, Integer maxDepth) {
        ExceptionThrowingSupplier<Stream<Path>, IOException> streamSupplier = () -> {
            if (maxDepth == null) {
                return Files.walk(path);
            } else {
                return Files.walk(path, maxDepth);
            }
        };
        try (Stream<Path> walk = streamSupplier.get()) {
            int index = 0;
            List<String> resources = new ArrayList<>();
            for (Iterator<Path> it = walk.iterator(); it.hasNext(); ) {
                Path resourcePath = it.next();
                String resource = resourcePath.toString();
                if (resources.isEmpty()) {
                    index = resource.lastIndexOf(name.replaceAll("/", Matcher.quoteReplacement(File.separator)));
                }
                resource = resource.substring(index);
                resource += Files.isDirectory(resourcePath) ? "/" : "";
                resources.add(resource);
            }
            return resources;
        } catch (IOException e) {
            throw new IllegalStateException("Unable to walk path", e);
        }
    }
    public static void extractResources(String name, Path toPath) {
        for (String resource : getResources(name)) {
            resource = resource.substring(1);
            if (resource.endsWith("/")) {
                try {
                    Files.createDirectories(toPath.resolve(resource));
                } catch (IOException e) {
                    throw new IllegalStateException("Unable to create directories", e);
                }
            } else {
                try {
                    Path path = Files.createFile(toPath.resolve(resource));
                    try (InputStream in = new ClassPathResource(resource).getInputStream();
                         OutputStream out = new FileOutputStream(path.toFile(), true)) {
                        IOUtils.copy(in, out);
                    }
                } catch (IOException e) {
                    throw new IllegalStateException("Unable to load Docker Compose file", e);
                }
            }
        }
    }
    public static List<String> getResources(String name) {
        return getResources(name, null);
    }
    public static List<String> getResources(String name, Integer maxDepth) {
        try {
            URI uri = ClassPathUtils.class.getResource(name).toURI();
            Path myPath;

            if (uri.getScheme().equals("jar")) {
                try (FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
                    myPath = fileSystem.getPath(name);
                    return resourceProvider(myPath, name, maxDepth);
                }
            } else {
                myPath = Paths.get(uri);
                return resourceProvider(myPath, name, maxDepth);
            }
        } catch (IOException | URISyntaxException e) {
            throw new IllegalStateException("Unable to create temp path", e);
        }
    }
}
