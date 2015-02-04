package com.tanjarine.automation.utilityhelpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by ypolshchykau on 27.01.2015.
 */
public class AutoItScriptExecutor {
    public final static Logger log = LoggerFactory.getLogger(AutoItScriptExecutor.class);
    /**
     * method involving 3rd party script execution -  taking  relative path to file  and making it work
     *
     * @param relativePath
     * @param fileName
     * @return
     * @throws InterruptedException
     */
    public static boolean autoItScriptExecutor(String relativePath, String fileName) throws InterruptedException {
        // commenting due to execution this test on a remote machine: 192.168.0.69
        String exeName = "fileUpload.exe";
        // commenting due to execution this test on a remote machine: 192.168.0.69
        String finalRes = "file://" + Paths.get("").toAbsolutePath().toString() + File.separator;

        String[] parts = relativePath.split("/");
        for (int count = 0; count < parts.length; count++) {
            finalRes += parts[count] + File.separator;
        }
        finalRes += fileName;
        log.info("file " + fileName + " to upload PATH  :" + finalRes);

        log.info("execution of AutoIt script:  automatic attachment of " + fileName);

        fileUploadAutoItExeCall(exeName, finalRes);

        Thread.sleep(3300);
        return true;
    }

    public static void fileUploadAutoItExeCall(String exeName, String filePathToUpload) {
        try {
            String[] dialog = new String[]{exeName, filePathToUpload};
            Runtime.getRuntime().exec(dialog);
        } catch (IOException e) {
        }
    }
}
