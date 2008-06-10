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

package com.threerings.flex {

import mx.controls.Spacer;

/**
 * Wraps a non-Flex component for use in Flex. 
 */
public class FlexUtil
{
    /**
     * How hard would it have been for them make Spacer accept two optional arguments?
     */
    public static function createSpacer (width :int = 0, height :int = 0) :Spacer
    {
        var spacer :Spacer = new Spacer();
        spacer.width = width;
        spacer.height = height;
        return spacer;
    }
}
}
