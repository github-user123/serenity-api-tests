package model;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class DataHelper {

    private static final String URL_DATA_PATH = "src/test/resources/BaseURL";

    UserData userData;

    public DataHelper() throws FileNotFoundException {
        Gson gson = new Gson();

        userData = gson.fromJson(getBufferedReaderForPath(URL_DATA_PATH), UserData.class);
    }

    public BufferedReader getBufferedReaderForPath(final String path) throws FileNotFoundException {
        return new BufferedReader(new FileReader(path));
    }

    public UserData getUserData() {
        return userData;
    }
}
