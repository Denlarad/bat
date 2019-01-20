package ForGroup;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;


public class GetPhotoToGroupBot {
    public String getPhotoToGroupBot(VkApiClient vk, UserActor userActor) throws ClientException, ApiException, IOException {
        Random random = new Random();
        List<String > memes = new ArrayList<>();
        PhotoGetter photoGetter = new PhotoGetter();
        memes.add(photoGetter.photoGetter(vk,userActor));
        return "D:\\2.jpg";
    }
}
