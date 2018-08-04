package com.example;

import com.example.media.MediaQualifier;
import com.example.media.MediaType;
import com.example.media.MediaTypeFilenameFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class MediaUploader {

    private static final String jdbcUrl = "jdbc:derby://localhost:1527/MediaDB";
    private static final String user = "oracle";
    private static final String pwd = "oracle";
    private static final String defaultPath = "/home/oracle/labs/resources/webmedia";

    public static void main(String[] args) {
        String directory;

        if (args.length < 1) {
            System.out.println("Usage: MediaLoader <dir of media items>");
            System.out.println("Trying with default argument of: " + defaultPath);
            directory = defaultPath;
        } else {
            directory = args[0];
        }

        try {
            MediaQualifier mq = new MediaQualifier();
            // Ordinarily, this code would be set depending on the browser type
            mq.setTypes(MediaType.IMAGE, MediaType.MP4_VIDEO, MediaType.OGV_VIDEO);
            uploadItems(new File(directory), mq);
        } catch (SQLException | IOException ex) {
            System.out.println("Exception processing the directory " + directory + ": " + ex);
            System.exit(-1);
        }
    }

    public static void uploadItems(File dir, MediaQualifier mq) throws SQLException, IOException {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, user, pwd);
                PreparedStatement media = conn.prepareStatement("INSERT INTO MediaContent (id, media) VALUES (?, ?)");
                PreparedStatement data = conn.prepareStatement("INSERT INTO MediaInfo (id, title, mediaDate, tags) VALUES (?, ?, ?, ?)")) {

            File[] files = dir.listFiles(new MediaTypeFilenameFilter(mq));

            if (files != null) {
                for (File f : files) {
                    System.out.println("Found file: " + f.getAbsolutePath());

                    String id = f.getName();
                    Date date = new Date(f.lastModified());
                    // Generate an id and title string for the database
                    int periodIndex = id.lastIndexOf(".");
                    String fileExtension = id.substring(periodIndex);
                    String title = id.substring(0, periodIndex);

                    // Store the media for this item
                    media.setString(1, id);  // Generated id string
                    try (FileInputStream fis = new FileInputStream(f)) {
                        media.setBinaryStream(2, fis, f.length());
                        System.out.println("Storing data for: " + title + ", " + id);
                        media.execute();

                        // Store the metadata for this item
                        data.setString(1, id);  // Generated id string
                        data.setString(2, title);  // Generated title string
                        data.setDate(3, new java.sql.Date(date.getTime()));
                        data.setString(4, "");
                        System.out.println("Storing meta data for: " + title + ", " + id);
                        data.execute();
                    } catch (IOException io) {
                        System.out.println("Failed to store media item: " + title + ". Continuing...");
                    }
                }

            }
        }
        System.out.println("Complete!");
    }
}