package org.basenio.io.session.state;

import org.basenio.exception.NioBaseWriteException;
import org.basenio.service.IoHandler;

import java.io.IOException;

public interface IoStateSession {
    /**
     * Inner identifier of this session
     *
     * @return
     */
    public long getSessionId();

    /**
     * Callback main handler
     *
     * @return
     */
    public IoHandler getHandler();

    /**
     * Write bytes<no protocol length> to special session
     *
     * @param message
     * @throws NioBaseWriteException
     * @throws IOException
     */
    public void write(final byte[] message) throws NioBaseWriteException, IOException;

    /**
     * Last active timestamp in this session
     *
     * @return
     */
    public long getLastActiveTimeStamp();

    /**
     * The status<closing-session> True-the session will be closed or
     * that is in unregister queue.
     *
     * @return
     */
    public boolean isClosing();

    /**
     * to close this session
     */
    public void close();

    /**
     * unique String identifier of current session eg.
     * ScoketSession@1:192.168.1.1:8080
     *
     * @return
     */
    public String toString();

    /**
     * return session IP
     *
     * @return
     */
    public String getIP();
}
