package de.juplo.boot.data.jdbc;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.time.ZonedDateTime;


@Getter
@EqualsAndHashCode
@ToString
public class UserEvent extends ApplicationEvent
{
  private final String key;
  private final UserStatus status;
  private final ZonedDateTime time;


  public UserEvent(Object source, String key, UserStatus status, ZonedDateTime time)
  {
    super(source);
    this.key = key;
    this.status = status;
    this.time = time;
  }
}
