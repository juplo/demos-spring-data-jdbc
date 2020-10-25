package de.juplo.boot.data.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UserEventListener
{
    private static final Logger LOG = LoggerFactory.getLogger(UserEventListener.class);


    @TransactionalEventListener
    public void onUserEvent(UserEvent event)
    {
        LOG.info("{}: {} - {}", event.getTime(), event.getValue(), event.getKey());
    }
}
