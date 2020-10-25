package de.juplo.kafka.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;


@Component
@AllArgsConstructor
public class OutboxListener
{
  private final OutboxRepository repository;
  private final ObjectMapper mapper;


  @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
  public void onUserEvent(OutboxEvent event)
  {
    try
    {
      repository.save(
          event.getKey(),
          mapper.writeValueAsString(event.getValue()),
          event.getTime());
    }
    catch (JsonProcessingException e)
    {
      throw new RuntimeException(e);
    }
  }
}
