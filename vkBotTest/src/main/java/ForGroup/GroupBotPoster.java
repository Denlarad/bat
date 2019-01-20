package ForGroup;

import com.vk.api.sdk.actions.Wall;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.photos.PhotoUpload;
import com.vk.api.sdk.objects.photos.responses.WallUploadResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GroupBotPoster {
    public void groupBotPoster(VkApiClient vk, UserActor userActor, GroupActor groupActor) throws ClientException, ApiException, IOException {

        GetPhotoToGroupBot getPhotoToGroupBot = new GetPhotoToGroupBot();
        File file = new File(getPhotoToGroupBot.getPhotoToGroupBot(vk,userActor));
        if (file.getPath() != "D:\\404.jpg") {
            PhotoUpload serverResponse = vk.photos().getWallUploadServer(userActor).execute();
            WallUploadResponse uploadResponse = vk.upload().photoWall(serverResponse.getUploadUrl(), file).execute();
            List<Photo> photoList = vk.photos().saveWallPhoto(userActor, uploadResponse.getPhoto())
                    .server(uploadResponse.getServer())
                    .hash(uploadResponse.getHash())
                    .execute();

            Photo photo = photoList.get(0);
            String attachId = "photo" + photo.getOwnerId() + "_" + photo.getId();

            Wall wall = new Wall(vk);
            wall.post(userActor).ownerId(-173453365).fromGroup(true).postId(0).message("asdf").attachments(attachId).execute();
            file.renameTo(new File("D:\\Do not use this", file.getName()));
        }
    }
}
