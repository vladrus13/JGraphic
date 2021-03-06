package ru.vladrus13.jgraphic.resources;

import ru.vladrus13.jgraphic.exception.AppException;
import ru.vladrus13.jgraphic.property.MainProperty;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Image loader
 */
public class ImageLoader {

    /**
     * Images
     */
    private final static Map<String, BufferedImage> images = new HashMap<>();
    /**
     * Resources path
     */
    private final static Path resourcesPath = Path.of(MainProperty.get("resources.path")).resolve("graphic");

    /**
     * Return image from resources
     *
     * @param name {@link Path} FROM resources
     * @return {@link BufferedImage} with image
     * @throws AppException If we can't get image
     */
    public static BufferedImage load(Path name) throws AppException {
        if (!images.containsKey(name.toString())) {
            try {
                images.put(name.toString(), ImageIO.read(Files.newInputStream(resourcesPath.resolve(name))));
            } catch (IOException e) {
                throw new AppException("Can't load image: " + name, e);
            }
        }
        return images.get(name.toString());
    }
}
