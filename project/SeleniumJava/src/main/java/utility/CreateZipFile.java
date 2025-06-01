package utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZipFile {

	public static void createZipFromFolder(String sourceFolderPath, String zipFilePath) throws IOException {
		// Convert the folder path to a Path object for easier file operations
		Path sourceFolder = Paths.get(sourceFolderPath);

		// Create a ZipOutputStream to write the zip file
		try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFilePath))) {

			// Walk through the directory recursively (including subdirectories)
			Files.walk(sourceFolder)
					// Only process files, not directories
					.filter(path -> !Files.isDirectory(path)).forEach(path -> {
						// Get the relative path of the file to store in the zip entry
						String zipEntryName = sourceFolder.relativize(path).toString();

						try (InputStream inputStream = Files.newInputStream(path)) {
							// Create a new zip entry (file name inside the zip)
							ZipEntry zipEntry = new ZipEntry(zipEntryName);
							zipOut.putNextEntry(zipEntry);

							// Read the file and write it to the zip output stream
							byte[] buffer = new byte[1024]; // 1KB buffer
							int length;
							while ((length = inputStream.read(buffer)) >= 0) {
								zipOut.write(buffer, 0, length);
							}

							// Close the current zip entry
							zipOut.closeEntry();

						} catch (IOException e) {
							// Print error if file couldn't be added
							System.err.println("Failed to add file: " + path + " -> " + e.getMessage());
						}
					});

		} // zipOut is automatically closed here due to try-with-resources
	}
}
