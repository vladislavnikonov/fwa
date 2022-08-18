package edu.school21.cinema.services;

import edu.school21.cinema.models.Image;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ImageService {
    private final String imagesPath;

    public ImageService(String imagesPath) {
        this.imagesPath = imagesPath;
    }

    public String getHashPhoto(Integer userId, String pathInfo) throws IOException {
        File image = new File(imagesPath + userId + pathInfo);
        if (!image.exists())
            throw new FileNotFoundException("file not exists");
        byte[] file = FileUtils.readFileToByteArray(image);
        return Base64.getEncoder().encodeToString(file);
    }

    public String getNameLastPhoto(List<Image> images) {
        return images.stream().map(Image::getFile).sorted((o1, o2) -> {
            if (o1 == o2)
                return 1;
            return o1.lastModified() > o2.lastModified() ? 1 : -1;
        }).reduce((e1, e2) -> e2).get().getName();
    }

    public List<Image> getImages(Integer userId) throws FileNotFoundException {
        File dir = new File(imagesPath + userId);
        if (!dir.exists())
            dir.mkdir();
        File[] arrFiles = dir.listFiles();
        if (arrFiles.length == 0) {
            throw new FileNotFoundException("Upload photo");
        }
        return Arrays.stream(arrFiles).map(Image::new).collect(Collectors.toList());
    }

    public void saveImage(Part part, Integer userId) throws IOException {
        String header = part.getHeader("content-disposition");
        String fileName = new String(
                (header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getBytes(),
                StandardCharsets.UTF_8
        );
        if (!fileName.isEmpty()) {
            String image = imagesPath + userId + File.separator + UUID.randomUUID() + fileName;
            part.write(image);
        }
    }
}
