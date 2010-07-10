//
// Nenya library - tools for developing networked games
// Copyright (C) 2002-2010 Three Rings Design, Inc., All Rights Reserved
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

package com.threerings.miso.client {

import flash.display.DisplayObject;

import as3isolib.core.IsoDisplayObject;
import as3isolib.display.IsoSprite;
import as3isolib.display.primitive.IsoBox;
import as3isolib.graphics.SolidColorFill;

import com.threerings.media.tile.Tile;
import com.threerings.media.tile.TileSet;
import com.threerings.media.tile.TileUtil;
import com.threerings.util.Log;
import com.threerings.miso.util.MisoSceneMetrics;

public class TileIsoSprite extends IsoSprite
    implements PriorityIsoDisplayObject
{
    private static var log :Log = Log.getLog(TileIsoSprite);

    public function TileIsoSprite (x :int, y :int, tileId :int, tile :Tile, priority :int,
        metrics :MisoSceneMetrics)
    {
        _tileId = tileId;
        _metrics = metrics;
        _priority = priority;

        layout(x, y, tile);

        setSize(tile.getBaseWidth(), tile.getBaseHeight(), 1);

        if (tile.getImage() == null) {
            tile.notifyOnLoad(function() :void {
                gotTileImage(tile);
            });
        } else {
            gotTileImage(tile);
        }
    }

    public function layout (x :int, y :int, tile :Tile) :void
    {
        moveTo(x, y, 0);
    }

    protected function gotTileImage (tile :Tile) :void
    {
        var image :DisplayObject = tile.getImage();
        // as3isolib uses top instead of bottom.
        image.x = -tile.getOriginX() + _metrics.tilewid *
            ((tile.getBaseWidth() - tile.getBaseHeight())/2);;
        image.y = -tile.getOriginY() + _metrics.tilehei *
            ((tile.getBaseWidth() + tile.getBaseHeight())/2);
        sprites = [image];
        render();
    }

    public function getPriority () :int
    {
        return _priority;
    }

    protected var _tileId :int;

    protected var _priority :int;

    protected var _metrics :MisoSceneMetrics;
}
}