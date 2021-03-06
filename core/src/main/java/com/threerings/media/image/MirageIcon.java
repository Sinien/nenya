//
// Nenya library - tools for developing networked games
// Copyright (C) 2002-2012 Three Rings Design, Inc., All Rights Reserved
// https://github.com/threerings/nenya
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

package com.threerings.media.image;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

/**
 * Implements the Swing {@link Icon} interface with a mirage providing the
 * image information.
 */
public class MirageIcon implements Icon
{
    public MirageIcon (Mirage mirage)
    {
        _mirage = mirage;
    }

    // documentation inherited from interface
    public void paintIcon (Component c, Graphics g, int x, int y)
    {
        _mirage.paint((Graphics2D)g, x, y);
    }

    // documentation inherited from interface
    public int getIconWidth()
    {
        return _mirage.getWidth();
    }

    // documentation inherited from interface
    public int getIconHeight()
    {
        return _mirage.getHeight();
    }

    protected Mirage _mirage;
}
