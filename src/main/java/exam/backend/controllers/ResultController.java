package exam.backend.controllers;

import exam.backend.dto.ResultDto;
import exam.backend.entities.Result;
import exam.backend.services.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {
    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    public List<Result> getAllResults() {
        return resultService.getAllResults();
    }

    @PostMapping
    public ResponseEntity<Result> addResult(@RequestBody ResultDto request) {
        System.out.println(request);
        return ResponseEntity.ok(resultService.addResult(request));
    }
}
