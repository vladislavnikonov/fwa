package edu.school21.cinema.models;

import java.io.File;
import java.net.URLConnection;

public class Image {
    private final String imageName;
    private final String MIME;
    private final long size;
    private final File file;

    public Image(File file) {
        this.file = file;
        this.imageName = file.getName().substring(36);
        this.size = file.length() / 1024;
        this.MIME = URLConnection.guessContentTypeFromName(file.getName());
    }

    public String getImageName() {
        return imageName;
    }

    public String getMIME() {
        return MIME;
    }

    public long getSize() {
        return size;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "Image{" +
                "name='" + imageName + '\'' +
                ", MIME='" + MIME + '\'' +
                ", size=" + size +
                ", file=" + file +
                '}';
    }
}
