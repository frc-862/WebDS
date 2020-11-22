package lightning.webds.service;

import lightning.webds.entity.User;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import javax.annotation.PostConstruct;
import java.io.FileInputStream;

import org.springframework.stereotype.Service;

@Service
public class FireBaseInitializer{
    private FirebaseDatabase userColumn;

    @PostConstruct
public void initialize() {
    System.out.println("firebaseapp initialized");
    try {
        FileInputStream serviceAccount =
                new FileInputStream("./serviceAccount.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://webds-83ad8.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}