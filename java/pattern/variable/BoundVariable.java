/*
 * Copyright (C) 2020 Grakn Labs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package graql.lang.pattern.variable;

import graql.lang.common.exception.GraqlException;
import graql.lang.pattern.Conjunctable;
import graql.lang.pattern.Pattern;

import java.util.Arrays;
import java.util.List;

import static grakn.common.util.Objects.className;
import static graql.lang.common.exception.ErrorMessage.INVALID_CASTING;

public abstract class BoundVariable extends Variable implements Conjunctable {

    BoundVariable(Reference reference) {
        super(reference);
    }

    @Override
    public boolean isBound() {
        return true;
    }

    @Override
    public BoundVariable asBound() {
        return this;
    }

    public UnboundVariable toUnbound() {
        return new UnboundVariable(reference);
    }

    public ConceptVariable asConcept() {
        throw GraqlException.of(INVALID_CASTING.message(className(this.getClass()), className(ConceptVariable.class)));
    }

    public TypeVariable asType() {
        throw GraqlException.of(INVALID_CASTING.message(className(this.getClass()), className(TypeVariable.class)));
    }

    public ThingVariable<?> asThing() {
        throw GraqlException.of(INVALID_CASTING.message(className(this.getClass()), className(ThingVariable.class)));
    }

    @Override
    public BoundVariable normalise() { return this; }

    @Override
    public boolean isVariable() { return true; }

    @Override
    public BoundVariable asVariable() { return this; }

    public List<? extends Pattern> patterns() {
        return Arrays.asList(this);
    }
}
