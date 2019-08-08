package akhilyerabati.ppmtool.services;
import akhilyerabati.ppmtool.domain.project;
import akhilyerabati.ppmtool.exceptions.ProjectIdException;
import akhilyerabati.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired  //logic should not be focussed on controller rather controller should be router.
    private ProjectRepository projectRepository;

    public project saveOrUpdateProject(project project){
        //Logic
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);

        }catch(Exception e){
            throw new ProjectIdException("Project ID ' "+project.getProjectIdentifier().toUpperCase()+" ' already exists");
        }

        //return projectRepository.save(project);
    }
    public project findProjectByIdentifier(String projectId){
        project project=projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if( project == null){
            throw new ProjectIdException("Project ID ' "+projectId+" ' does not  exists");
        }
        return project;
    }
    public Iterable<project> fIndAllProjects(){
        return projectRepository.findAll();
    }
}
