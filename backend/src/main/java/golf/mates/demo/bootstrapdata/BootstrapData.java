package golf.mates.demo.bootstrapdata;

import golf.mates.demo.entities.GolfClub;
import golf.mates.demo.entities.User;
import golf.mates.demo.repository.GolfClubRepository;
import golf.mates.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Path;


@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final GolfClubRepository golfClubRepository;

    @Override
    public void run(String... args) throws Exception {

        loadGolfClubData();
        loadUserData();

    }

    private void loadGolfClubData() throws IOException {

        File file = ResourceUtils.getFile("classpath:csvdata/golfklubbar_lista.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] golfClubData = line.split(";");
                golfClubRepository.save(new GolfClub(golfClubData[0],golfClubData[1]));
            }

        }

    }



    private void loadUserData() {

        User user1 = new User("user1", encoder.encode("password"));
        User user2 = new User("user2", encoder.encode("password"));
        User user3 = new User("user3", encoder.encode("password"));

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);


    }




}