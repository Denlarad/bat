package ForGroup;

import com.vk.api.sdk.actions.Wall;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.wall.WallPostFull;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import sun.reflect.misc.FieldUtil;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.vk.api.sdk.queries.wall.WallGetFilter.OWNER;

public class PhotoGetter {
    public String photoGetter(VkApiClient vk, UserActor userActor) throws ClientException, ApiException, IOException {
        Wall wall = new Wall(vk);

        int ownerId = -96591297;

        FileWriter writer = new FileWriter("D:\\Wrong Photos.txt",true);

        FileInputStream fstream = new FileInputStream("D:\\Wrong Photos.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String hashLine;



        List<WallPostFull> wallPostFull
                = wall.get(userActor).ownerId(ownerId).filter(OWNER).count(10).execute().getItems();

        List<PostsForBot> goodPosts = new ArrayList<>();



        for (int i = 1; i < wallPostFull.size(); i++) {
            if (wallPostFull.get(i).getAttachments().get(0).getPhoto().getOwnerId() != ownerId) {
                System.out.println("pahol v jopy s reklamoi");
            } else {
                PostsForBot postsForBot = new PostsForBot();

                Integer likes = wallPostFull.get(i).getLikes().getCount();
                postsForBot.setLikesInfo(likes);
                postsForBot.setWallPostFull(wallPostFull.get(i));
                goodPosts.add(postsForBot);

            }

        }

        Collections.sort(goodPosts);

        String target = "D:\\photos";
        for(int i = 0; i < br.lines().count(); i++) {
            String a = goodPosts.get(i).getWallPostFull().getAttachments().get(0).getPhoto().getPhoto1280();

            URL website = new URL(a);
            try (InputStream in = website.openStream()) {
                String hash = DigestUtils.md5Hex(in);


                if (br.readLine().equals(hash)) {
                    System.out.println("hah");
                } else {
                    writer.write(hash);
                    writer.append("\r\n");
                    writer.flush();
                    System.out.println(goodPosts.get(i).getWallPostFull().getAttachments().get(0).getPhoto().getPhoto1280());
                }
            }
        }


        return null;
    }
}

class PostsForBot implements Comparable<PostsForBot>{
    private Integer likesInfo;
    private WallPostFull wallPostFull;

    public Integer getLikesInfo() {
        return likesInfo;
    }

    public void setLikesInfo(Integer likesInfo) {
        this.likesInfo = likesInfo;
    }

    public WallPostFull getWallPostFull() {
        return wallPostFull;
    }

    public void setWallPostFull(WallPostFull wallPostFull) {
        this.wallPostFull = wallPostFull;
    }

    @Override
    public int compareTo(PostsForBot o) {
        return Integer.compare(o.getLikesInfo(), this.likesInfo);
    }
}
