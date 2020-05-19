package klimat.DAO;

import klimat.Models.temp;

import java.util.List;

public interface ITempDao {
   public List<temp> getAllTemperatures();
   public void persistTemperatures(List<temp> temperatureList);
}

