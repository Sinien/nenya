//
// $Id$
//
// Nenya library - tools for developing networked games
// Copyright (C) 2002-2006 Three Rings Design, Inc., All Rights Reserved
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

package com.threerings.media;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Window;
import javax.swing.JApplet;

import com.threerings.media.Log;

/**
 * When using the {@link FrameManager} in an Applet, one must use this
 * top-level class.
 */
public class ManagedJApplet extends JApplet
{
    // from interface FrameManager.ManagedRoot
    public void init (FrameManager fmgr)
    {
        _fmgr = fmgr;
    }

    // from interface FrameManager.ManagedRoot
    public Window getWindow ()
    {
        Component parent = getParent();
        while (!(parent instanceof Window) && parent != null) {
            parent = parent.getParent();
        }
        return (Window)parent;
    }

    /**
     * Returns the frame manager managing this frame.
     */
    public FrameManager getFrameManager ()
    {
        return _fmgr;
    }

    @Override // from Component
    public void paint (Graphics g)
    {
        // we catch paint requests and forward them on to the repaint
        // infrastructure
        update(g);
    }

    @Override // from Component
    public void update (Graphics g)
    {
        Shape clip = g.getClip();
        Rectangle dirty;
        if (clip != null) {
            dirty = clip.getBounds();
        } else {
            dirty = getRootPane().getBounds();
            // account for our frame insets
            Insets insets = getInsets();
            dirty.x += insets.left;
            dirty.y += insets.top;
        }

        if (_fmgr != null) {
            _fmgr.restoreFromBack(dirty);
        } else {
            Log.info("Dropping AWT dirty rect " + dirty + " (" + clip + ").");
        }
    }

    protected FrameManager _fmgr;
}
