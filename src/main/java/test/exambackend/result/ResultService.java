package test.exambackend.result;

import org.springframework.stereotype.Service;

@Service
public class ResultService {
    ResultRepository resultRepository;


    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;

    }



}
