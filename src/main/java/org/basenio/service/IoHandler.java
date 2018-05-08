package org.basenio.service;

import org.basenio.io.session.state.IoStateSession;

public interface IoHandler {
    /**
     * The callback interface when a new session create a connection
     *
     * @param session
     */
    public void socketCreated(final IoStateSession session);

    /**
     * The callback interface when the session has been cosed
     *
     * @param session
     */
    public void socketClosed(final IoStateSession session);

    /**
     * The callback interface when the session received byte[] data
     *
     * @param session
     * @param message
     */
    public void messageReceived(final IoStateSession session, final byte[] message);
}
