package de.juplo.boot.data.jdbc;

import de.juplo.kafka.outbox.postage.OutboxEvent;

import java.time.ZonedDateTime;


public class UserEvent extends OutboxEvent
{
  public UserEvent(Object source, String key, UserStatus status, ZonedDateTime time)
  {
    super(source, key, status, time);
  }
}
