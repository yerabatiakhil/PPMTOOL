package akhilyerabati.ppmtool.services;
import akhilyerabati.ppmtool.domain.Backlog;
import akhilyerabati.ppmtool.domain.project;
import akhilyerabati.ppmtool.domain.User;
import akhilyerabati.ppmtool.exceptions.ProjectIdException;
import akhilyerabati.ppmtool.exceptions.ProjectNotFoundException;
import akhilyerabati.ppmtool.repositories.BacklogRepository;
import akhilyerabati.ppmtool.repositories.ProjectRepository;
import akhilyerabati.ppmtool.repositories.UserRepository;
//import jdk.nashorn.internal.ir.debug.PrintVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired  //logic should not be focussed on controller rather controller should be router.
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private UserRepository userRepository;

    public project saveOrUpdateProject(project project, String username){
        if(project.getId() != null){
            project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
            if(existingProject !=null &&(!existingProject.getProjectLeader().equals(username))){
                throw new ProjectNotFoundException("Project not found in your account");
            }else if(existingProject == null){
                throw new ProjectNotFoundException("Project with ID: '"+project.getProjectIdentifier()+"' cannot be updated because it doesn't exist");
            }
        }
        //Logic
        try{
            User user = userRepository.findByUsername(username);
            project.setUser(user);
            project.setProjectLeader(user.getUsername());
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            //Backlog backlog = new Backlog();
            if(project.getId()==null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }
            if(project.getId() != null){
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase() ));
            }
            return projectRepository.save(project);

        }catch(Exception e){
            throw new ProjectIdException("Project ID ' "+project.getProjectIdentifier().toUpperCase()+" ' already exists");
        }

        //return projectRepository.save(project);
    }
    public project findProjectByIdentifier(String projectId, String username){
        project project=projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if( project == null){
            throw new ProjectIdException("Project ID ' "+projectId+" ' does not  exists");

        }
        if(!project.getProjectLeader().equals(username)){
            throw new ProjectNotFoundException("Project not found in your account");
        }
        return project;
    }
    public Iterable<project> findAllProjects(String username){
        return projectRepository.findAllByProjectLeader(username);
    }

    public void deleteProjectByIdentifier(String projectid, String username){


        projectRepository.delete(findProjectByIdentifier(projectid, username));
    }
}
