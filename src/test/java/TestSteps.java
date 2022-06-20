import io.qameta.allure.Step;
import model.PostDTO;
import org.testng.Assert;
import utils.LoadFromProperties;

public class TestSteps {

    private final RequestApiUtils request = new RequestApiUtils();

    private final String url = LoadFromProperties.getProperties("url");
    private final String path = LoadFromProperties.getProperties("path");
    private final String allPostJsonFile = LoadFromProperties.getDataProperties("allPostFile");
    private final String path99 = LoadFromProperties.getProperties("path99");
    private final String ninetyNinthJsonFile = LoadFromProperties.getDataProperties("ninetyNinthFile");
    private final String path150 = LoadFromProperties.getProperties("path150");
    private final String emptyJsonFile = LoadFromProperties.getDataProperties("emptyFile");
    private final String postRequestJsonFile = LoadFromProperties.getDataProperties("postRequestFile");
    private final String pathUser = LoadFromProperties.getProperties("pathUsers");
    private final String allUserJsonFile = LoadFromProperties.getDataProperties("allUserFile");
    private final String pathUserWithId5 = LoadFromProperties.getProperties("path5user");
    private final String fifthUserJsonFile = LoadFromProperties.getDataProperties("fifthUserFile");

    @Step("Send GET Request to get all posts (/posts).")
    public void getAllPosts() {
        PostDTO postDTO = request.sentPostGetRequest(url, path, allPostJsonFile);
        Assert.assertEquals(postDTO.getStatus(),200,"the status did not match");
        Assert.assertEquals(postDTO.getType(),"application/json; charset=utf-8","type did not match");
        Assert.assertTrue(request.checkSortingID(),"the list is not sorted in ascending order");
    }

    @Step("Send GET request to get post with id=99 (/posts/99)")
    public void getPostWithId(){
        PostDTO postDTO = request.sentPostGetRequest(url, path99, ninetyNinthJsonFile);
        Assert.assertEquals(postDTO.getStatus(),200,"the status did not match");
        Assert.assertEquals(postDTO.getPosts().get(0).getUserId(),10,"userId did not match");
        Assert.assertEquals(postDTO.getPosts().get(0).getId(),99,"Id did not match");
        Assert.assertNotNull(postDTO.getPosts().get(0).getTitle(),"title did not match");
        Assert.assertNotNull(postDTO.getPosts().get(0).getBody(),"body did not match");
    }

    @Step("Send GET request to get post with id=150 (/posts/150")
    public void getEmptyBody(){
        PostDTO postDTO = request.sentPostGetRequest(url, path150, emptyJsonFile);
        Assert.assertEquals(postDTO.getStatus(),404,"the status did not match");
        Assert.assertNull(request.getValueFromJsonPostFile(emptyJsonFile).get(0).getBody());
    }

    @Step("Send POST request to create post with userId=1 and random body and random title (/posts).")
    public void createUser(){
        PostDTO postDTO = request.sentPostRequest(url, path, postRequestJsonFile);
        Assert.assertEquals(postDTO.getStatus(),201,"The status did not match");
        Assert.assertEquals(postDTO.getPosts().get(0).getTitle(),
                request.getValueFromJsonPostFile(postRequestJsonFile).get(0).getTitle(),"title did not match");
    }

    @Step("Send GET request to get users (/users).")
    public void getUsers(){
        PostDTO postDTO = request.sentUserGetRequest(url, pathUser, allUserJsonFile);
        Assert.assertEquals(postDTO.getStatus(),200,"the status did not match");
        Assert.assertEquals(postDTO.getType(),"application/json; charset=utf-8","types do not match");
        Assert.assertEquals(postDTO.getUsers().get(4).getId(),
                request.getValueFromJsonUserFile(allUserJsonFile).get(4).getId(),"ids types do not match");
    }

    @Step("Send GET request to get user with id=5 (/users/5).")
    public void getUser(){
        PostDTO postDTO = request.sentUserGetRequest(url, pathUserWithId5, fifthUserJsonFile);
        Assert.assertEquals(postDTO.getUsers().get(0).getId(),
                request.getValueFromJsonUserFile(fifthUserJsonFile).get(0).getId(),"ids types do not match" );

    }
}