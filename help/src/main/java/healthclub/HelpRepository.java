package healthclub;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="helps", path="helps")
public interface HelpRepository extends PagingAndSortingRepository<Help, Long>{


}
