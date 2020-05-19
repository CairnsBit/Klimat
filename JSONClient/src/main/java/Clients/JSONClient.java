package Clients;

import Models.response;
import org.springframework.web.client.RestTemplate;
import Models.temp;

import java.util.*;

public class JSONClient {

    private static temp getTemp(){
        final String uri = "http://localhost:8080/temperature.json";
        RestTemplate restTemplate = new RestTemplate();
        temp result = restTemplate.getForObject(uri, temp.class);
        System.out.println("**************************");
        System.out.println(" Id: "+result.getId()+" Temperatur: "+result.getTemp()+" Luftfuktighet: "+result.getHum()+" Lumen: "+result.getLum()+" Energi Förbrukning: "+result.getUsage());
        return result;
    }
    private static temp getTempById(int id){
        final String uri = "http://localhost:8080/temperature/{id}";
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("id", id);
        RestTemplate restTemplate = new RestTemplate();
        temp result = restTemplate.getForObject(uri, temp.class, params);
        System.out.println("**************************");
        System.out.println(" Id: "+result.getId()+" Temperatur: "+result.getTemp()+" Luftfuktighet: "+result.getHum()+" Lumen: "+result.getLum()+" Energi Förbrukning: "+result.getUsage());
        return result;
    }
    private static List<temp> getTemps(){
        final String uri = "http://localhost:8080/temperatures.json";
        float temp = 0;
        float hum = 0;
        int lum = 0;
        int usage = 0;
        RestTemplate restTemplate = new RestTemplate();
        temp[] resultArray = restTemplate.getForObject(uri, temp[].class);
        List<temp> result = Arrays.asList(resultArray);
        for (temp b : result){
            temp += b.getTemp();
            hum += b.getHum();
            lum += b.getLum();
            usage += b.getUsage();
            System.out.println("**************************");
            System.out.println(" Id: " + b.getId()+" Temperatur: "+b.getTemp()+" Luftfuktighet: "+b.getHum()+" Lumen: "+b.getLum() + " Energi Förbrukning: " +b.getUsage());
        }
        System.out.println("**************************");
        System.out.println("Medelvärdet (Temperatur) : "+temp/result.size()+" Medelvärdet (Luftfuktighet): "+hum /result.size()+" Medelvärdet (Lumen): "+lum/result.size());
        System.out.println("Energi förbrukning totalt: "+usage);
        return result;
    }

    public static void updateTemp(int id, float temp, float hum, int lum, int usage){
        final String uri = "http://localhost:8080/temperature/update";
        temp upTemp = new temp(id, temp, hum, lum, usage);
        RestTemplate restTemplate = new RestTemplate();
        response result = restTemplate.postForObject(uri, upTemp, response.class);
        System.out.println(result.getMessage());
    }


    private static List<temp> getPrice(int price){
        final String uri = "http://localhost:8080/temperatures.json";
        int usage = 0;

        RestTemplate restTemplate = new RestTemplate();
        temp[] resultArray = restTemplate.getForObject(uri, temp[].class);
        List<temp> result = Arrays.asList(resultArray);
        for (temp b : result){
            usage += b.getUsage();
        }
        System.out.println("**************************");
        System.out.println("Energi Förbrukning Totalt: " + usage * price + "kr");
        return result;
    }


    public static void main(String[] args){

        int choice;

        Scanner sc = new Scanner(System.in);

        while(true){
            try{
                System.out.println("**************************");
                System.out.println("1. Visa nu");
                System.out.println("2. Uppdatera en dag");
                System.out.println("3. Hämta Temperatur från en viss dag");
                System.out.println("4. Hämta all data och beräkna medelvärden");
                System.out.println("5. Beräkna priset för energin");
                System.out.println("6. Exit");
                System.out.println("**************************");
                System.out.println("Ditt val: ");
                choice = sc.nextInt();

                if(choice==1) {
                    getTemp();
                }
                else if(choice==2) {
                    System.out.println("Skriv");
                    int id = sc.nextInt();
                    float temp = sc.nextFloat();
                    float hum = sc.nextFloat();
                    int lum = sc.nextInt();
                    int usage = sc.nextInt();
                    updateTemp(id, temp, hum, lum, usage);
                }
                else if(choice==3) {
                    System.out.println("Ange ID: ");
                    int ind = sc.nextInt();
                    getTempById(ind);
                }
                else if(choice==4) {
                    getTemps();
                }
                else if(choice==5){
                    System.out.println("Ange kostnad per kWH: ");
                    int price = sc.nextInt();
                    getPrice(price);
                }
                else if(choice==6) {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}