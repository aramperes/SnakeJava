package dev.momo.snake.io;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StatsLoader {

    private final File directory = new File("C:\\SnakeJava");
    private final File nameFile = new File("C:\\SnakeJava\\name");
    private final File hsFile = new File("C:\\SnakeJava\\hs");

    public StatsLoader() {

        try {

            directory.mkdir();

            if (!nameFile.exists())
                nameFile.createNewFile();


            if (!hsFile.exists())
                hsFile.createNewFile();


        } catch (Exception ignored) {

        }
    }

    public void test() {

    }

    public void setHighScore(int hs) {
        try {
            Files.write(hsFile.toPath(), ("" + hs).getBytes());
            URL yahoo = new URL("http://csgoforge.com/snake/SnakeHighScore.php?user=" + getName() + "&hs=" + hs);
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public int getHighScore() {

        try {
            return Integer.valueOf(Files.readAllLines(hsFile.toPath(), Charset.forName("UTF-8")).get(0));
        } catch (Exception e) {

        }

        return 0;
    }

    public void setName(String name) {
        try {
            Files.write(nameFile.toPath(), name.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {

        try {
            return Files.readAllLines(nameFile.toPath(), Charset.forName("UTF-8")).get(0);
        } catch (Exception e) {

        }

        return null;
    }
}
