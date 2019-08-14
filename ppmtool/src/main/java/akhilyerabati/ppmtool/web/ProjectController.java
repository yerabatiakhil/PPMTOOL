package akhilyerabati.ppmtool.web;

import akhilyerabati.ppmtool.domain.project;
import akhilyerabati.ppmtool.services.MapValidationErrorService;
import akhilyerabati.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;
    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody project project, BindingResult result){
        //when binding result is validated it i gives us a list if errors
        ResponseEntity<?> errorMap= mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;

        project project1=projectService.saveOrUpdateProject(project);
        return new ResponseEntity<project>(project, HttpStatus.CREATED);

    }
    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId){
        project project=projectService.findProjectByIdentifier(projectId);
       return new ResponseEntity<project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<project> getAllProjects(){
        return projectService.fIndAllProjects();}

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        projectService.deleteProjectByIdentifier(projectId);
        return new ResponseEntity<String>("Project with ID'"+projectId+"' was deleted", HttpStatus.OK);
    }
}
