package org.basenio.io.session.state;

import org.apache.log4j.Logger;
import org.basenio.service.IoHandler;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractStateSession implements IoStateSession{
    private final Logger logger = Logger.getLogger(getClass());
    private SocketChannel socketChannel;
    private SelectionKey selectionKey;
    private long sessionId;
    private static AtomicLong idGenerator = new AtomicLong(0);
    private Object runnableProcessor;
    protected byte[] readBuffer;
    protected Queue<byte[]> writableQueue = new ConcurrentLinkedQueue<>();
    private int bufferPosition = 0;
    //private IoFilter ioFilter;
    private IoHandler ioHandler;
    private long lastActiveTimeStamp = System.currentTimeMillis();
    private long timeoutCount = 0;
    private long lastTimeoutTimeStamp = System.currentTimeMillis();

    private int DEFAULT_BUF_SIZE = Short.MAX_VALUE + 4;
    private int MIN_BUF_SIZE = 256 + 4;
    private int MAX_BUF_SIZE = Integer.MAX_VALUE + 4;
    private int DEFAULT_TIMEOUT_COUNT = 5;
    private int DEFAULT_TIMEOUT_SPAN_MS = 30 * 1000;
    private static long DEFAULT_MAX_IDLE_SPAN_MS = Long.MAX_VALUE;

    /**
     * Write operating for processor
     */
    public abstract void writeLocal();

}
