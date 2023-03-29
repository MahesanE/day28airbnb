package sg.edu.nus.iss.day28airbnb.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day28airbnb.model.Airbnb;
import sg.edu.nus.iss.day28airbnb.repository.AirbnbRepo;

@Service
public class AirbnbService {
    @Autowired
    private AirbnbRepo airRepo;


   public List<Airbnb> findByText(String text){
    return airRepo.findByText(text);
   } 
}
