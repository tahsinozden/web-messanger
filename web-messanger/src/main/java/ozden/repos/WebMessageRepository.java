package ozden.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ozden.entities.WebMessage;

public interface WebMessageRepository extends JpaRepository<WebMessage, Integer>{
	List<WebMessage> findBySenderAndReceiverOrderByCreateDateDesc(String sender, String receiver);
}
