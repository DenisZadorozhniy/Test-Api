import org.testng.annotations.Test;

public class TestSite {

    private final TestSteps testSteps = new TestSteps();

    @Test
    public void restApiTest(){
        testSteps.getAllPosts();
        testSteps.getPostWithId();
        testSteps.getEmptyBody();
        testSteps.createUser();
        testSteps.getUsers();
        testSteps.getUser();
    }
}