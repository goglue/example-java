package org.sample.news.domain.event;

/**
 * Event Listeners/Subscribers are list of events that the domain is interested in, and need to take an action in regard
 *
 * Usually (but not always) the same transport layer used in the publisher will be used to listen to events coming from
 * other domains, to take an action regarding them, mostly this is implemented in the application and the transport in
 * the infrastructure.
 */
public interface Listeners {
    // List of events that are related to the domain
}
