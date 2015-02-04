package com.tanjarine.automation.utilityhelpers;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Eugene Polschikov on 13.05.2014.
 * class, implementing file operations
 */
public class FileOperations {
    public FileOperations(String oldFilePath, String newFilePath) {

        File initialFile = new File(oldFilePath);

        File newFile = new File(newFilePath);
        oldFile = initialFile;
        newProcessedFile = newFile;
    }
    public FileOperations() {

    }

    /*  method returning Line count in given txt file
     *
     * NOTE: this method is working for WIN only
     */
    public int countLinesFastest(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        /*try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        }*/

        /*
         * solution above has an off by one error for multi line files which don't end in newline.
         * A one line file ending without a newline would return 1, but a two line file ending without
         * a newline would return 1 too. Here's an implementation of the accepted solution which fixes this.
         */
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean endsWithoutNewLine = false;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n')
                        ++count;
                }
                endsWithoutNewLine = (c[readChars - 1] != '\n');
            }
            if (endsWithoutNewLine) {
                ++count;
            }
            return count;
        } finally {
            is.close();
        }
    }

    /*
    *
    * shortest in implementation one
     */

    public int countLines(String filePath) throws IOException {
        LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(filePath)));
        lnr.skip(Long.MAX_VALUE);
        int num = lnr.getLineNumber();
        // Finally, the LineNumberReader object should be closed to prevent resource leak
        lnr.close();
        return num;
    }

    public String readFirstLineInFile(String filePath) throws IOException {
        File namefile = new File(filePath);
        FileReader namereader = new FileReader(namefile);
        BufferedReader in = new BufferedReader(namereader);
        String res = in.readLine();
        return res;
    }
    /*
     * method is response for appending analyzed result to new File
     */
    public void appendStringToFile(String filePath, String lineToAppend) throws FileNotFoundException, IOException {
        //        File dir = new File(".");
        //        String loc = dir.getCanonicalPath() + File.separator + "Code.txt";

        File appended = new File(filePath);
        FileWriter fstream = new FileWriter(appended, true);
        BufferedWriter out = new BufferedWriter(fstream);

        out.write(lineToAppend);
        out.newLine();

        //close buffer writer
        out.close();
    }

    /*
     * removeNthLineOnTheSpot method is responsible for deleting nth line of the file selected
     */


    public void removeNthLineOnTheSpot(String filePath, int indexLineToRemove) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(filePath, "rw");

        // Leave the n first lines unchanged.
        for (int i = 0; i < indexLineToRemove; i++)
            raf.readLine();

        // Shift remaining lines upwards.
        long writePos = raf.getFilePointer();
        raf.readLine();
        long readPos = raf.getFilePointer();

        byte[] buf = new byte[1024];
        int n;
        while (-1 != (n = raf.read(buf))) {
            raf.seek(writePos);
            raf.write(buf, 0, n);
            readPos += n;
            writePos += n;
            raf.seek(readPos);
        }

        raf.setLength(writePos);
        raf.close();
    }

    public String  readNthLineFromTxtFile(String filePath, int lineNumber) throws IOException {
        File file = new File(filePath);
        String line = FileUtils.readLines(file).get(lineNumber);
        return line;
    }


    /**
     * method reading values from CSV file  using java.util.Scanner
     * @return
     */
    public List<String> readFromCsvFile(String filePath) {
        File fileCSV = new File(filePath);

        List<String> parsedValuesFromFile = new ArrayList<String>();
        //Get scanner instance
        Scanner scanner = null;
        try {
            scanner = new Scanner(fileCSV);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Set the delimiter used in file
        scanner.useDelimiter(",");

        //Get all tokens and store them in some data structure
        //I am just printing them
        while (scanner.hasNext()) {
            // performing cast to lower case as we need to compare data loaded from file to actual text labels which are on elements;
            //            trimming in order to cut out spaces; \n \t  \r   symbols
            parsedValuesFromFile.add(scanner.next().toString().toLowerCase().trim());
        }

        //Do not forget to close the scanner
        scanner.close();


        return parsedValuesFromFile;
    }





    private File oldFile;
    private File newProcessedFile;

}
