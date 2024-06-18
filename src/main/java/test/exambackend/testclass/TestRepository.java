package test.exambackend.testclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestClass, Long> {
}
