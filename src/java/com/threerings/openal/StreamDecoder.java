//
// $Id$
//
// Nenya library - tools for developing networked games
// Copyright (C) 2002-2007 Three Rings Design, Inc., All Rights Reserved
// http://www.threerings.net/code/nenya/
//
// This library is free software; you can redistribute it and/or modify it
// under the terms of the GNU Lesser General Public License as published
// by the Free Software Foundation; either version 2.1 of the License, or
// (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

package com.threerings.openal;

import java.util.HashMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.nio.ByteBuffer;

/**
 * Decodes audio streams from data read from an {@link InputStream}.
 */
public abstract class StreamDecoder
{
    /**
     * Registers a class of {@link StreamDecoder} for the specified file extension.
     */
    public static void registerExtension (String extension, Class clazz)
    {
        _extensions.put(extension, clazz);
    }

    /**
     * Creates and initializes a stream decoder for the specified file.
     */
    public static StreamDecoder createInstance (File file)
        throws IOException
    {
        String path = file.getPath();
        int idx = path.lastIndexOf('.');
        if (idx == -1) {
            Log.warning("Missing extension for file [file=" + path + "].");
            return null;
        }
        String extension = path.substring(idx+1);
        Class clazz = _extensions.get(extension);
        if (clazz == null) {
            Log.warning("No decoder registered for extension [extension=" + extension +
                ", file=" + path + "].");
            return null;
        }
        StreamDecoder decoder;
        try {
            decoder = (StreamDecoder)clazz.newInstance();
        } catch (Exception e) {
            Log.warning("Error instantiating decoder [file=" + path + ", error=" + e + "].");
            return null;
        }
        decoder.init(new FileInputStream(file));
        return decoder;
    }

    /**
     * Initializes the decoder with its input stream.
     */
    public abstract void init (InputStream in)
        throws IOException;

    /**
     * Returns the sound format (see {@link Stream#getFormat}).
     */
    public abstract int getFormat ();

    /**
     * Returns the sound frequency (see {@link Stream#getFrequency}).
     */
    public abstract int getFrequency ();

    /**
     * Reads as much data as will fit into the specified buffer.
     *
     * @return the number of bytes read.  If less than or equal to zero, the decoder has reached
     * the end of the stream.
     */
    public abstract int read (ByteBuffer buf)
        throws IOException;

    /** Maps file extensions to decoder classes. */
    protected static HashMap<String, Class> _extensions = new HashMap<String, Class>();
    static {
        registerExtension("ogg", OggStreamDecoder.class);
        registerExtension("mp3", Mp3StreamDecoder.class);
    }
}
