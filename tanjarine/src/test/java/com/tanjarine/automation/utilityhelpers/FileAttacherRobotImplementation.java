package com.tanjarine.automation.utilityhelpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * Created by ypolshchykau on 30.01.2015.
 */
public class FileAttacherRobotImplementation {
    public final static Logger log = LoggerFactory.getLogger(FileAttacherRobotImplementation.class);

    /**
     * this method implements file attachment using Robot mechanism
     *
     * @param filePathToImage
     */

    public static void fileAttachmentUsingRobot(String filePathToImage) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        setClipboardData(filePathToImage);
        robot.delay(1000);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    /**
     * method for copying file in order to attach file in File open window
     *
     * @param str
     */
    public static void setClipboardData(String str) {
        StringSelection stringSelection = new StringSelection(str);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

}
