package org.sample.news.domain.event;

/**
 * The event publisher that will publish the event outside the domain through a transport layer when an action occur.
 *
 * Usually this class is implemented in the application with the implementation of transport layer in the infrastructure
 * (how the message will be transmitted?). Each method will have a class definition in the same package, which
 * encapsulate the event context that need to be published.
 */
public interface Publisher {
}
