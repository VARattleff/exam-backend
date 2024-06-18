package test.exambackend.test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestClass, Long> {
    TestClass findByName(String name);
}
