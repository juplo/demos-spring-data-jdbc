package de.juplo.kafka.outbox;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.time.ZonedDateTime;


@ToString
@EqualsAndHashCode
public class OutboxEvent extends ApplicationEvent
{
  @Getter
  private final String key;
  @Getter
  private final Object value;
  @Getter
  private final ZonedDateTime time;


  public OutboxEvent(Object source, String key, Object value, ZonedDateTime time)
  {
    super(source);
    this.key = key;
    this.value = value;
    this.time = time;
  }
}
