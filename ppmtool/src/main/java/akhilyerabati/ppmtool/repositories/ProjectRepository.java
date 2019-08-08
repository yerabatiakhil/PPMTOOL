package akhilyerabati.ppmtool.repositories;

import akhilyerabati.ppmtool.domain.project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<project, Long> {
  //  @Override
  //  Iterable<project> findAllById(Iterable<Long> longs);
    project findByProjectIdentifier(String projectId);

    @Override
    Iterable<project> findAll();
}
