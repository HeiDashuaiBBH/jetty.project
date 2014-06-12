//
//  ========================================================================
//  Copyright (c) 1995-2014 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package org.eclipse.jetty.http2.frames;

import java.nio.ByteBuffer;

public class DataFrame extends Frame
{
    private final int streamId;
    private final ByteBuffer data;
    private final boolean endStream;
    private final int length;

    public DataFrame(int streamId, ByteBuffer data, boolean endStream)
    {
        this(streamId, data, endStream, 0);
    }

    public DataFrame(int streamId, ByteBuffer data, boolean endStream, int padding)
    {
        super(FrameType.DATA);
        this.streamId = streamId;
        this.data = data;
        this.endStream = endStream;
        this.length = data.remaining() + padding;
    }

    public int getStreamId()
    {
        return streamId;
    }

    public ByteBuffer getData()
    {
        return data;
    }

    public boolean isEndStream()
    {
        return endStream;
    }

    @Override
    public int getFlowControlledLength()
    {
        return length;
    }

    @Override
    public String toString()
    {
        return String.format("%s{length:%d/%d}", super.toString(), data.remaining(), length);
    }
}
