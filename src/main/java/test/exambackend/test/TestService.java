package test.exambackend.test;

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
        Test test = new Test();
        test.setName(testDTO.getName());
        test.setAge(testDTO.getAge());
        testRepository.save(test);
        return toDTO(test);
    }

    public TestDTO deleteTest(Long id) {
        Test test = testRepository.findById(id).orElseThrow(() -> new NotFoundException("Test not found"));
        testRepository.deleteById(id);
        return toDTO(test);
    }

    public TestDTO updateTest(Long id, TestDTO testDTO) {
        Test test = testRepository.findById(id).orElseThrow(() -> new NotFoundException("Test not found"));
        if (testDTO.getName() == null || testDTO.getName().isEmpty() || testDTO.getAge() < 0) {
            throw new ValidationException("Name and age must be provided");
        }
        test.setName(testDTO.getName());
        test.setAge(testDTO.getAge());
        testRepository.save(test);
        return toDTO(test);
    }

    private TestDTO toDTO(Test test) {
        TestDTO testDTO = new TestDTO();
        testDTO.setId(test.getId());
        testDTO.setName(test.getName());
        testDTO.setAge(test.getAge());
        return testDTO;
    }
}
