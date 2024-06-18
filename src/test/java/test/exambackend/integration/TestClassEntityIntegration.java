package test.exambackend.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import test.exambackend.test.TestClass;
import test.exambackend.test.TestRepository;

import java.util.NoSuchElementException;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
public class TestClassEntityIntegration {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRepository testRepository;

    @Test
    public void testCreateEntity() throws Exception {
        String json = """
                {
                    "name":"Test Name",
                    "age":25
                }
            """;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/tests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(201);

        TestClass testClass = testRepository.findByName("Test Name");
        assertThat(testClass).isNotNull();
        assertThat(testClass.getName()).isEqualTo("Test Name");
        assertThat(testClass.getAge()).isEqualTo(25);
    }

    @Test
    public void testGetEntity() throws Exception {
        TestClass testClass = new TestClass();
        testClass.setName("Existing Entity");
        testRepository.save(testClass);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/tests/" + testClass.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        String jsonResponse = result.getResponse().getContentAsString();
        assertThat(jsonResponse).contains("Existing Entity");
    }

    @Test
    public void testDeleteEntity() throws Exception {
        TestClass testClass = new TestClass();
        testClass.setName("Entity to delete");
        testRepository.save(testClass);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/tests/" + testClass.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(testRepository.findByName("Entity to delete")).isNull();
    }

    @Test
    public void testUpdateEntity() throws Exception {
        TestClass testClass = new TestClass();
        testClass.setName("Entity to update");
        testRepository.save(testClass);

        String json = """
                {
                    "name":"Updated Entity",
                    "age":30
                }
            """;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/tests/" + testClass.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        TestClass updatedEntity = testRepository.findById(testClass.getId())
                .orElseThrow(() -> new NoSuchElementException("Entity not found with id: " + testClass.getId()));
        assertThat(updatedEntity.getName()).isEqualTo("Updated Entity");
        assertThat(updatedEntity.getAge()).isEqualTo(30);
    }
}