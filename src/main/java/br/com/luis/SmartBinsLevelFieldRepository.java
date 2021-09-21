package br.com.luis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartBinsLevelFieldRepository extends JpaRepository<SmartBinsLevelField, Long> {

}
