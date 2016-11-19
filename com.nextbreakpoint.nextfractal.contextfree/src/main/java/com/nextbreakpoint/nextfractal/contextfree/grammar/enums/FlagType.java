/*
 * NextFractal 1.3.0
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
package com.nextbreakpoint.nextfractal.contextfree.grammar.enums;

public enum FlagType {
	CF_NONE(0), 
	CF_MITER_JOIN(1), 
	CF_ROUND_JOIN(2), 
	CF_BEVEL_JOIN(3), 
	CF_JOIN_MASK(0x7), 
	CF_JOIN_PRESENT(8), 
	CF_BUTT_CAP(1 << 4), 
	CF_ROUND_CAP(2 << 4), 
	CF_SQUARE_CAP(3 << 4), 
	CF_CAP_MASK(0x7 << 4), 
	CF_CAP_PRESENT(8 << 4),
	CF_ARC_CW(1 << 8), 
	CF_ARC_LARGE(1 << 9), 
	CF_CONTINUOUS(1 << 10), 
	CF_ALIGN(1 << 11), 
	CF_EVEN_ODD(1 << 12), 
	CF_ISO_WIDTH(1 << 13),
	CF_FILL(1 << 14), 
	CF_CYCLIC(31 << 15), 
	CF_DIHEDRAL(1 << 15), 
	CF_P11G(2 << 15), 
	CF_P11M(3 << 15), 
	CF_P1M1(4 << 15), 
	CF_P2(30 << 15), 
	CF_P2MG(6 << 15), 
	CF_P2MM(7 << 15),
	CF_PM(8 << 15),
	CF_PG(9 << 15), 
	CF_CM(10 << 15), 
	CF_PMM(11 << 15), 
	CF_PMG(12 << 15), 
	CF_PGG(13 << 15), 
	CF_CMM(14 << 15), 
	CF_P4(15 << 15), 
	CF_P4M(16 << 15), 
	CF_P4G(17 << 15), 
	CF_P3(18 << 15), 
	CF_P3M1(19 << 15), 
	CF_P31M(20 << 15), 
	CF_P6(21 << 15), 
	CF_P6M(22 << 15); 
	
	private long mask;

	private FlagType(long mask) {
		this.mask = mask;
	}

	public long getMask() {
		return mask;
	}

	public static FlagType fromMask(long mask) {
		for (FlagType value : FlagType.values()) {
			if (value.getMask() == mask) {
				return value;
			}
		}
		return CF_NONE;
	}

	public static FlagType byName(String name) {
		String normalizedName = name.replace(":","_").toUpperCase();
		for (FlagType value : FlagType.values()) {
			if (value.name() == normalizedName) {
				return value;
			}
		}
		return CF_NONE;
	}
}
