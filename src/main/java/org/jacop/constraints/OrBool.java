/*
 * OrBool.java
 * This file is part of JaCoP.
 * <p>
 * JaCoP is a Java Constraint Programming solver.
 * <p>
 * Copyright (C) 2000-2008 Krzysztof Kuchcinski and Radoslaw Szymanek
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * Notwithstanding any other provision of this License, the copyright
 * owners of this work supplement the terms of this License with terms
 * prohibiting misrepresentation of the origin of this work and requiring
 * that modified versions of this work be marked in reasonable ways as
 * different from the original version. This supplement of the license
 * terms is in accordance with Section 7 of GNU Affero General Public
 * License version 3.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.jacop.constraints;

import java.util.Arrays;
import java.util.List;

import org.jacop.core.IntVar;
import org.jacop.core.Store;

/**
 * OrBool constraint implements logic and operation on its arguments
 * and returns result.
 *
 * @author Krzysztof Kuchcinski and Radoslaw Szymanek
 * @version 4.5
 */

public class OrBool extends DecomposedConstraint<PrimitiveConstraint> {

    PrimitiveConstraint c = null;

    /**
     * It constructs and constraint on variables.
     * @param a parameters
     * @param result result variable.
     */
    public OrBool(IntVar[] a, IntVar result) {

	if (a.length == 1)
            c = new XeqY(a[0], result);	
        else if (a.length == 2)
            c = new OrBoolSimple(a[0], a[1], result);
        else
            c = new OrBoolVector(a, result);
    }

    /**
     * It constructs and constraint on variables.
     * @param a parameters
     * @param result result variable.
     */
    public OrBool(List<IntVar> a, IntVar result) {
	this(a.toArray(new IntVar[a.size()]), result);
    }

    /**
     * It constructs and constraint on variables.
     * @param a a parameter
     * @param b b parameter
     * @param result result variable.
     */
    public OrBool(IntVar a, IntVar b, IntVar result) {

        c = new OrBoolSimple(a, b, result);

    }

    @Override public void imposeDecomposition(Store store) {

        store.impose(c);

    }

    @Override public List<PrimitiveConstraint> decompose(Store store) {
        return Arrays.asList(c);
    }

    public String toString() {
        return c.toString();
    }
}
