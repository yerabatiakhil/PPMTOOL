package akhilyerabati.ppmtool.web;

import akhilyerabati.ppmtool.domain.project;
import akhilyerabati.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @PostMapping("")
    public ResponseEntity<project> createNewProject(@RequestBody project project){
        project project1=projectService.saveOrUpdateProject(project);
        return new ResponseEntity<project>(project, HttpStatus.CREATED);

    }
}
