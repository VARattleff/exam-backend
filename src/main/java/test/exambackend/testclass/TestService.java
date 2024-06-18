package test.exambackend.testclass;

import org.springframework.stereotype.Service;
import test.exambackend.errorhandling.exception.NotFoundException;
import test.exambackend.errorhandling.exception.ValidationException;

import java.util.Optional;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class TestService {
    TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<TestDTO> findAll() {
        return testRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<TestDTO> findById(Long id) {

        if (id == null || id < 0) {
            throw new ValidationException("Id must be provided");
        }

        return testRepository.findById(id).map(this::toDTO);
    }

    public TestDTO createTest(TestDTO testDTO) {
        if (testDTO.getName() == null || testDTO.getName().isEmpty() || testDTO.getAge() < 0) {
            throw new ValidationException("Name and age must be provided");
        }
        TestClass testClass = new TestClass();
        testClass.setName(testDTO.getName());
        testClass.setAge(testDTO.getAge());
        testRepository.save(testClass);
        return toDTO(testClass);
    }

    public TestDTO deleteTest(Long id) {
        TestClass testClass = testRepository.findById(id).orElseThrow(() -> new NotFoundException("Test not found"));
        testRepository.deleteById(id);
        return toDTO(testClass);
    }

    public TestDTO updateTest(Long id, TestDTO testDTO) {
        TestClass testClass = testRepository.findById(id).orElseThrow(() -> new NotFoundException("Test not found"));
        if (testDTO.getName() == null || testDTO.getName().isEmpty() || testDTO.getAge() < 0) {
            throw new ValidationException("Name and age must be provided");
        }
        testClass.setName(testDTO.getName());
        testClass.setAge(testDTO.getAge());
        testRepository.save(testClass);
        return toDTO(testClass);
    }

    private TestDTO toDTO(TestClass testClass) {
        TestDTO testDTO = new TestDTO();
        testDTO.setId(testClass.getId());
        testDTO.setName(testClass.getName());
        testDTO.setAge(testClass.getAge());
        return testDTO;
    }
}
