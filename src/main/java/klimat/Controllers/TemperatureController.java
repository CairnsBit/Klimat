package klimat.Controllers;

import klimat.Models.temp;
import org.springframework.web.bind.annotation.*;
import klimat.Models.response;
import klimat.DAO.*;

import java.util.List;

@RestController
public class TemperatureController {

    ITempDao temperatureDao = new TempDao();
    List<temp> temperatureList = temperatureDao.getAllTemperatures();

    @RequestMapping("/temperatures")
    public List<temp> index() {
        return temperatureList;
    }

    @RequestMapping("/temperature")
    public temp oneTemperature() {
        return new temp(1, 25, 25, 1, 1);
    }

    @RequestMapping("/temperature/{id}")
    public temp getTemperatureById(@PathVariable int id){
        temp res = new temp();
        for (temp b : temperatureList){
            if (b.getId() == id){
                res = b;
            }
        }
        return res;
    }

    @RequestMapping("/temperature/{id}/delete")
    public response deleteTemperatureById(@PathVariable("id") int id){
        response res = new response("Temperature deleted", Boolean.FALSE);
        int indexToRemove = -1;
        for (int i = 0; i < temperatureList.size(); i++){
            if (temperatureList.get(i).getId() == id){
                indexToRemove = i;
            }
        }

        if (indexToRemove != -1){
            temperatureList.remove(indexToRemove);
            res.setStatus(Boolean.TRUE);
        }
        temperatureDao.persistTemperatures(temperatureList);
        return res;
    }

    @PostMapping("/temperature/add")
    public response addTemperature(@RequestBody temp b){
        System.out.println("Id: " + b.getId()+" Temperature: "+b.getTemp() + " Humidity: " + b.getHum()
                + " Lum: " + b.getLum());
        response res = new response("Temperature added", Boolean.FALSE);
        temperatureList.add(b);
        res.setStatus(Boolean.TRUE);
        temperatureDao.persistTemperatures(temperatureList);
        return res;
    }

    @PostMapping("/temperature/update")
    public response upsertTemperature(@RequestBody temp b){
        response res = new response("Temperature updated", Boolean.FALSE);

        int indexToUpdate = -1;
        for (int i = 0; i < temperatureList.size(); i++){
            if (temperatureList.get(i).getId() == b.getId()){
                indexToUpdate = i;
            }
        }

        if (indexToUpdate == -1){
            temperatureList.add(b);
            res.setMessage("Temperature inserted");
            res.setStatus(Boolean.TRUE);
        }
        else{
            temperatureList.set(indexToUpdate, b);
            res.setStatus(Boolean.TRUE);
        }
        temperatureDao.persistTemperatures(temperatureList);
        return res;
    }
}