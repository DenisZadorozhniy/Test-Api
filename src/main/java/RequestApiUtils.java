import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.PostDTO;
import model.PostPojo;
import model.UsersPajo;
import org.apache.commons.lang3.RandomStringUtils;
import utils.ApiUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class RequestApiUtils {

    private static final String PRIMARY_PATH = "src/main/resources/jsonFiles/";
    private static final String JSON_EXTENSION = ".json";

    private final PostDTO postDTO = new PostDTO();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<PostPojo> postPojos = new ArrayList<>();
    private List<UsersPajo> usersPajos = new ArrayList<>();

    public PostDTO sentPostGetRequest(String url, String path, String titleFile) {
        String pathJSONFile = PRIMARY_PATH + titleFile + JSON_EXTENSION;
        objectMapperReadAndWriteValue(url, path, pathJSONFile);

        postDTO.setType(ApiUtils.getRequest(url, path).getContentType());
        postDTO.setStatus(ApiUtils.getRequest(url, path).getStatusCode());
        postDTO.setPosts(postPojos);
        return postDTO;
    }

    public PostDTO sentUserGetRequest(String url, String path, String titleFile) {
        String pathJSONFile = PRIMARY_PATH + titleFile + JSON_EXTENSION;

        objectMapperReadAndWriteValueByUsers(url, path, pathJSONFile);
        postDTO.setType(ApiUtils.getRequest(url, path).getContentType());
        postDTO.setStatus(ApiUtils.getRequest(url, path).getStatusCode());
        postDTO.setUsers(usersPajos);

        return postDTO;
    }

    public PostDTO sentPostRequest(String url, String path, String titleFile) {
        String pathJSONFile = PRIMARY_PATH + titleFile + JSON_EXTENSION;
        Random random = new Random();

        HashMap<String, String> values = new HashMap<>();
        values.put("title", RandomStringUtils.randomAlphabetic(10));
        values.put("body", RandomStringUtils.randomAlphabetic(10));
        values.put("userId", String.valueOf(random.nextInt(1000)));

        ApiUtils.postRequest(url, path, values);
        List<PostPojo> list = new ArrayList<>();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            list = objectMapper.readValue(ApiUtils.postRequest(url, path, values).body().asString(), new TypeReference<List<PostPojo>>() {
            });
            objectMapper.writeValue(Paths.get(pathJSONFile).toFile(),
                    objectMapper.readValue(ApiUtils.postRequest(url, path, values).body().asString(), new TypeReference<Object>() {
                    }));
        } catch (IOException e) {
            e.printStackTrace();
        }

        postDTO.setType(ApiUtils.postRequest(url, path, values).getContentType());
        postDTO.setStatus(ApiUtils.postRequest(url, path, values).getStatusCode());
        postDTO.setPosts(list);

        return postDTO;
    }

    public List<PostPojo> getValueFromJsonPostFile(String titleFile) {
        String pathJSONFile = PRIMARY_PATH + titleFile + JSON_EXTENSION;
        File jsonFile = new File(pathJSONFile);
        List<PostPojo> postPojoList = new ArrayList<>();
        return objectMapperReadValueFromPostFile(jsonFile, postPojoList);
    }

    public List<UsersPajo> getValueFromJsonUserFile(String titleFile) {
        String pathJSONFile = PRIMARY_PATH + titleFile + JSON_EXTENSION;
        List<UsersPajo> userList = new ArrayList<>();
        UsersPajo[] usersPajosArray = null;
        File jsonFile = new File(pathJSONFile);

        try {
            usersPajosArray = objectMapper.readValue(jsonFile, UsersPajo[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (usersPajosArray != null) {
            Collections.addAll(userList, usersPajosArray);
        }

        return userList;
    }

    public Boolean checkSortingID() {
        List<PostPojo> sortedList = postPojos.stream().sorted(Comparator.comparing(PostPojo::getId)).collect(Collectors.toList());
        return sortedList.equals(postPojos);
    }

    private void objectMapperReadAndWriteValue(String apiUrl, String path, String pathJSONFile) {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            postPojos = objectMapper.readValue(ApiUtils.getRequest(apiUrl, path).body().asString(), new TypeReference<List<PostPojo>>() {
            });
            objectMapper.writeValue(Paths.get(pathJSONFile).toFile(), objectMapper.readValue(ApiUtils.getRequest(apiUrl, path).body().asString(), new TypeReference<Object>() {
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void objectMapperReadAndWriteValueByUsers(String apiUrl, String path, String pathJSONFile) {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            usersPajos = objectMapper.readValue(ApiUtils.getRequest(apiUrl, path).body().asString(),
                    new TypeReference<List<UsersPajo>>() {
                    });
            objectMapper.writeValue(Paths.get(pathJSONFile).toFile(),
                    objectMapper.readValue(ApiUtils.getRequest(apiUrl, path).body().asString(),
                            new TypeReference<Object>() {
                            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<PostPojo> objectMapperReadValueFromPostFile(File jsonFile, List<PostPojo> list) {
        try {
            PostPojo postPojo = objectMapper.readValue(jsonFile, PostPojo.class);
            list.add(postPojo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}