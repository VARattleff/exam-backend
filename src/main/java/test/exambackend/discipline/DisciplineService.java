package test.exambackend.discipline;

import org.springframework.stereotype.Service;

@Service
public class DisciplineService {
    DiciplineRepository diciplineRepository;

    public DisciplineService(DiciplineRepository diciplineRepository) {
        this.diciplineRepository = diciplineRepository;
    }

}


