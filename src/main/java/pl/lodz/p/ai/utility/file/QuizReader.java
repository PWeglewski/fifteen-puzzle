package pl.lodz.p.ai.utility.file;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.lodz.p.ai.FifteenPuzzleApp;
import pl.lodz.p.ai.array.Array2D;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * Created by piotr on 05.10.16.
 */
public class QuizReader {
    private static final String ENCODING = "UTF-8";
    static Logger log = Logger.getLogger(QuizReader.class.getName());

    public static Array2D read(String fileName) {
        Array2D resultArray = null;

        InputStream resourceAsStream = FifteenPuzzleApp.class.getClassLoader().getResourceAsStream(fileName);

        if (resourceAsStream == null) {
            log.severe("File not found");
            return null;
        }

        try {
            String JsonString = IOUtils.toString(resourceAsStream, ENCODING);
            JSONObject jsonObject = new JSONObject(JsonString);
            JSONArray rows = jsonObject.getJSONArray("board");
            int resultArraySize = rows.length();
            resultArray = new Array2D(resultArraySize);
            for (int rowIndex = 0; rowIndex < rows.length(); rowIndex++) {
                JSONArray row = (JSONArray) rows.get(rowIndex);
                for (int columnIndex = 0; columnIndex < row.length(); columnIndex++) {
                    resultArray.setValue(columnIndex, rowIndex, (int) row.get(columnIndex));
                }
            }

        } catch (IOException e) {
            log.severe(e.getLocalizedMessage());
        }

        return resultArray;
    }


}
