package de.juplo.kafka.outbox;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.ZonedDateTime;


@Repository
@AllArgsConstructor
public class OutboxRepository
{
  private static final String SQL_UPDATE =
      "INSERT INTO outbox (key, value, issued) VALUES (:key, :value, :issued)";

  private final NamedParameterJdbcTemplate jdbcTemplate;


  public void save(String key, String value, ZonedDateTime issued)
  {
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("key", key);
    parameters.addValue("value", value);
    parameters.addValue("issued", Timestamp.from(issued.toInstant()));
    jdbcTemplate.update(SQL_UPDATE, parameters);
  }
}
