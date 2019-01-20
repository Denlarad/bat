import ForGroup.PhotoGetter;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.omg.CORBA.INTERNAL;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws ClientException, ApiException, InterruptedException, IOException {
            TransportClient transportClient = HttpTransportClient.getInstance();
            VkApiClient vk = new VkApiClient(transportClient);

        int botType = 0; //      0 - User bot.    1 - Group bot.

        PhotoGetter photoGetter = new PhotoGetter();


        GroupActor groupActor = new GroupActor(173453365,"3aaad040710cdc3ee7339e02b55341c67c5285540e8c97ab7cb64b0a3a9f42239e99b6746c544b42585e6");
        UserActor userActor = new UserActor(495812590,"38f79c7d734445c7855e56f9ae36d8c654ce4b8f97d6d0846989ea35f268e57152011e2368e5918d0d2fd");


        photoGetter.photoGetter(vk,userActor);
        photoGetter.photoGetter(vk,userActor);

        VkBot vkBot = new VkBot();

        vkBot.botManager(vk,userActor,groupActor,botType);




    }

}


