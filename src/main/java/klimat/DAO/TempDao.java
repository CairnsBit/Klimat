package klimat.DAO;

import klimat.Models.temp;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class TempDao implements ITempDao {

    String temperatureListPath = "src/main/java/klimat/Reports/simData.json";
    Gson gson = new Gson();

    public List<temp> getAllTemperatures(){
        String json = new String();
        List<temp> temperatureList = new ArrayList<temp>();

        try (BufferedReader br = new BufferedReader(
                new FileReader(temperatureListPath));) {
            Type listType = new TypeToken<ArrayList<temp>>(){}.getType();
            temperatureList = new Gson().fromJson(br, listType);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return temperatureList;
    }

    public void persistTemperatures(List <temp> temperatureList){
        String json = gson.toJson(temperatureList);

        try (FileWriter writer = new FileWriter(temperatureListPath);) {
            writer.write(json);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
