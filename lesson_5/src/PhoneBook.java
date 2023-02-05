import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    private Map<String, String> map;

    PhoneBook(){
        Map<String, String> map = new HashMap<>();
    }

    void put(String name, String phone){
        map.put(name, phone);
    }

    void entrySet() {

    }


}
