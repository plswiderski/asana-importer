package org.bitbucket.pablo127.asana_importer;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        final String pathname = args[0];
        try {
            List<String> newLines = new ArrayList<>();

            List<String> lines = FileUtils.readLines(new File(pathname), "UTF-8");
            boolean first = true;
            for (String line : lines) {
                if (first) {
                    first = false;
                    newLines.add(line);
                    continue;
                }
                String[] columns = line.split(",");
                if (!isInteger(columns[0]))
                    newLines.set(newLines.size() - 1, newLines.get(newLines.size() - 1) + line);
                else
                    newLines.add(line);
            }
            FileUtils.write(new File(pathname + "2"), createTextData(newLines), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static CharSequence createTextData(List<String> lines) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            stringBuilder.append(line)
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    private static boolean isInteger(String value) {
        try {
            Long.parseLong(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
