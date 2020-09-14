package de.juplo.boot.data.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener
{
    private static final Logger LOG = LoggerFactory.getLogger(UserEventListener.class);


    @EventListener
    public void onUserEvent(UserEvent event)
    {
        LOG.info("{}: {} - {}", event.getTime(), event.getStatus(), event.getKey());
    }
}
