package lightning.webds.service;

import java.util.HashMap;

import lightning.webds.controller.MainController;
import lightning.webds.entity.User;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.util.concurrent.ExecutionException;
import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    /* FireBase database structure:
        Collections
         |_ Documents
             |_Key-value data
    */
    private final String COLLECTION_NAME = "users";

    public List<User> getAllUser(){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Map<String, Object> userMap = null;
        try{
        userMap = dbFirestore.collection(COLLECTION_NAME).document("userlist").get().get().getData();
        }
        catch(InterruptedException in){}
        catch(ExecutionException ex){}

        ArrayList<User> userList = new ArrayList<User>();
        
        if(userMap != null){
            userMap.forEach((key, value) -> {
                try{
                userList.add(getUserDetails(key));
                }
                catch(InterruptedException in){}
                catch(ExecutionException ex){}
            });
        }

        return userList;
    }

    public User getUserDetails(String email) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        // get instance of collection named "users" and get the document "email" under the collection
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(email);
        // get the content of the document
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        return document.exists() ? document.toObject(User.class) : null;
    }

    public void updateUser(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore
        .collection(COLLECTION_NAME) // get instance of the collection named "users"
        .document(user.getEmail()) // get instance of the document under the "users" collection
        .set(user); // set content to the document

        dbFirestore
        .collection(COLLECTION_NAME)
        .document("userlist")
        .update(new HashMap<String,Object>()
        {
            {put(user.getEmail(), "");}
        }
        );
    }

    public void deleteUser(String email) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection(COLLECTION_NAME).document(email).delete();
    }

    public Boolean userExist(String email){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        try{
          return dbFirestore.collection(COLLECTION_NAME).document(email).get().get().exists();
        }
        catch(InterruptedException in){}
        catch(ExecutionException ex){}
        return false;
    }
}