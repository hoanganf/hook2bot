package com.anit.hook2bot.infrastructure.repository;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author anit
 */
public class FileRepository {
    private String readFromInputStream()
            throws IOException {
        FileInputStream inputStream = new FileInputStream("/Users/anit/workspace/tmp.txt");
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        //log.info("FILE {}", resultStringBuilder.toString());
        return resultStringBuilder.toString();
    }
}
