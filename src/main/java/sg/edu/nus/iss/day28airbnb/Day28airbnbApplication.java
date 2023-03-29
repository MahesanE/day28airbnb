package sg.edu.nus.iss.day28airbnb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day28airbnbApplication{

	// @Autowired
	// private AirbnbRepo airRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day28airbnbApplication.class, args);
	}

	// @Override
	// public void run(String... args) throws Exception {
	// 	List<Airbnb> result = airRepo.findByText("kitchen");
	// 	for(List<Airbnb> d : result){
	// 		System.out.println(">>> " + d.toString());
	// 		}
	// }

}
