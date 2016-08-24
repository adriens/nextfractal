/*
 * NextFractal 1.2.1
 * https://github.com/nextbreakpoint/nextfractal
 *
 * Copyright 2015-2016 Andrea Medeghini
 *
 * This file is part of NextFractal.
 *
 * NextFractal is an application for creating fractals and other graphics artifacts.
 *
 * NextFractal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NextFractal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NextFractal.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.grammar;

import com.nextbreakpoint.nextfractal.contextfree.grammar.ast.ASTCompiledPath;
import com.nextbreakpoint.nextfractal.contextfree.grammar.ast.ASTPathCommand;

import java.awt.geom.GeneralPath;

public class CommandInfo {
    private GeneralPath path;
    private Long pathUID;
    private int index;

    public CommandInfo() {
        // TODO Auto-generated method stub
    }

    public CommandInfo(PrimShape primShape) {
        // TODO Auto-generated method stub
    }

    public GeneralPath getPath() {
        return path;
    }

    public void setPath(GeneralPath path) {
        this.path = path;
    }

    public void tryInit(int index, ASTCompiledPath currentPath, double width, ASTPathCommand astPathCommand) {
    }

    public Long getPathUID() {
        return pathUID;
    }

    public void setPathUID(Long pathUID) {
        this.pathUID = pathUID;
    }

    public int getIndex() {
        return index;
    }
}
