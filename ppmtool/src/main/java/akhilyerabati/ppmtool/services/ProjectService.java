package akhilyerabati.ppmtool.services;
import akhilyerabati.ppmtool.domain.project;
import akhilyerabati.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired  //logic should not be focussed on controller rather controller should be router.
    private ProjectRepository projectRepository;

    public project saveOrUpdateProject(project project){
        //Logic

        return projectRepository.save(project);
    }
}
