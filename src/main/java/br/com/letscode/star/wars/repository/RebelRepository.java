package br.com.letscode.star.wars.repository;

import br.com.letscode.star.wars.domain.Rebel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebelRepository extends JpaRepository<Rebel, Long> {
}
