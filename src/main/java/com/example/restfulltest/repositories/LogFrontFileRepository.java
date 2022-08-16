package com.example.restfulltest.repositories;


import com.example.restfulltest.models.FrontLog;
import org.springframework.stereotype.Repository;
import java.io.*;

@Repository
public class LogFrontFileRepository  {
    private final String FILE_LOG_NAME = "FrontLog.txt";

    public Integer save(FrontLog frontLog) throws Exception {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_LOG_NAME, true)));
            out.println(frontLog.toString());
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return frontLog.getId();
    }

}
