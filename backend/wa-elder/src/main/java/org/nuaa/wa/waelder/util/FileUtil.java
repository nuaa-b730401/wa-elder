package org.nuaa.wa.waelder.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Scanner;

/**
 * @Author: ToMax
 * @Description:
 * @Date: Created in 2018/12/30 16:41
 */
public class FileUtil {
    /**
     * create new folder(not exist)
     * @param path folder path
     * @return create result
     */
    public static boolean createFolder(String path) {
        File file = new File(path);
        return file.exists() || file.mkdirs();
    }

    /**
     * save file(upload)
     * @param file file(upload)
     * @param path save path
     * @param fileName file name
     * @return save result
     * @throws IOException file save exception
     */
    public static boolean saveFile(MultipartFile file, String path, String fileName) throws IOException {
        // first step : check folder
        if (createFolder(path)) {
            // folder exist and then save file
            file.transferTo(new File(path + "/" + fileName));
            return true;
        }
        return false;
    }

    /**
     * save markdown content to file
     * @param content markdown article content
     * @param path save folder path
     * @param fileName save file name
     * @return save result
     * @throws IOException content save exception
     */
    public static boolean saveMarkdownFile(String content, String path, String fileName) throws IOException {
        if (createFolder(path)) {
            File file1 = new File(path + "/" + fileName);
            Writer writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(file1), "UTF-8"));
            writer.write(content);
            writer.flush();
            writer.close();
            return true;
        }
        return false;
    }

    /**
     * read content from markdown file
     * @param path markdown file path
     * @return markdown file content
     * @throws IOException file read exception
     */
    public static String readMarkdownFile(String path) throws IOException {
        FileInputStream inputStream = new FileInputStream(path);
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        StringBuilder markdownContentBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            markdownContentBuilder.append(scanner.nextLine());
            markdownContentBuilder.append("\n");
        }
        inputStream.close();
        scanner.close();
        return markdownContentBuilder.toString();
    }

    /**
     * file rename
     * @param path origin file path
     * @param oName origin file name
     * @param nName new file name
     * @return file rename result, false represents new file exists
     */
    public static boolean renameFile(String path, String oName, String nName) {
        File oFile = new File(path + "/" + oName);
        File nFile = new File(path + "/" + nName);

        // target file name exist
        return !nFile.exists() && oFile.renameTo(nFile);
    }

    /**
     * delete folder
     * @param file @NotNull
     * @return delete result
     */
    public static boolean deleteFolder(File file) {
        if (file.exists()) {
            for (File children : file.listFiles()) {
                if (children.isFile()) {
                    deleteFile(children);
                } else if (children.isDirectory()) {
                    deleteFolder(children);
                }
            }
            return file.delete();
        }
        return false;
    }

    /**
     * delete single file
     * @param file file
     * @return delete result
     */
    public static boolean deleteFile(File file) {
        return file.exists() && file.isFile() && file.delete();
    }

    /**
     * save image in static folder
     * @param file
     * @param path
     * @throws IOException
     */
    public static void generateVisitableImage(MultipartFile file, String path) throws IOException {
        File image = new File(path);
        if (!image.getParentFile().exists()) {
            image.getParentFile().mkdirs();
        }
        file.transferTo(image);
    }

    /**
     * copy file
     * @param sourcePath
     * @param destPath
     * @throws IOException
     */
    public static void copyFile(String sourcePath, String destPath) throws IOException {
        FileUtils.copyFile(new File(sourcePath), new File(destPath));
    }

    /**
     * get image resource and output to stream
     * @param inputStream
     * @param outputStream
     * @throws IOException
     */
    public static void getImageResource(InputStream inputStream, OutputStream outputStream) {
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        int n = 0;
        byte[] buffer = new byte[1024];
        try {
            while ((n = inputStream.read(buffer, 0, buffer.length)) != -1) {
                bos.write(buffer, 0, buffer.length);
            }
        } catch (IOException e) {
            // TODO : log
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
