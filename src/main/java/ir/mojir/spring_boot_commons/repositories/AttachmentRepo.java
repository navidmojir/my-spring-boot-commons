package ir.mojir.spring_boot_commons.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ir.mojir.spring_boot_commons.entities.Attachment;

@Repository
public interface AttachmentRepo extends CrudRepository<Attachment, Long> {

}
