package ma.adnan;

import ma.adnan.entite.Patient;
import ma.adnan.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Test2Application {

    public static void main(String[] args) {
        SpringApplication.run(Test2Application.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            Patient p1=new Patient(null,"adnan",new Date(2001-11-24),11,false);
            Patient p2=new Patient(null,"ali",new Date(2000-10-3),12,true);
            Patient p3=new Patient(null,"othman",new Date(2002-1-4),20,false);
            Patient p4=new Patient(null,"ahmed",new Date(2005-3-4),30,true);

            List<Patient> patients= Arrays.asList(p1,p2,p3,p4);
            patientRepository.saveAll(patients);
        };
    }

}
