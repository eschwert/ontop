package it.unibz.krdb.obda.ontology.impl;

/*
 * #%L
 * ontop-obdalib-core
 * %%
 * Copyright (C) 2009 - 2014 Free University of Bozen-Bolzano
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import it.unibz.krdb.obda.model.OBDADataFactory;
import it.unibz.krdb.obda.model.Predicate;
import it.unibz.krdb.obda.model.impl.OBDADataFactoryImpl;
import it.unibz.krdb.obda.ontology.AnnotationProperty;

/**
 * Represents DataPropertyExpression from the OWL 2 QL Specification
 * 
 * DataPropertyExpression := DataProperty
 * 
 * Support for owl:topDataProperty and owl:bottomDataProperty
 * 
 * @author Roman Kontchakov
 *
 */

public class AnnotationPropertyImpl implements AnnotationProperty {

	private static final long serialVersionUID = 500873858691854474L;

	private final Predicate predicate;
	private final String name;

	private static final OBDADataFactory ofac = OBDADataFactoryImpl.getInstance();


	AnnotationPropertyImpl(String name) {
		//defining annotation property we still don't know if the values that it will assume, will be an object or a data property
		this.predicate = ofac.getPredicate(name, new Predicate.COL_TYPE[]{Predicate.COL_TYPE.OBJECT, Predicate.COL_TYPE.NULL});
		this.name = name;		

	}

	@Override
	public Predicate getPredicate() {
		return predicate;
	}

	@Override
	public String getName() {
		return name;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		
		if (obj instanceof AnnotationPropertyImpl) {
			AnnotationPropertyImpl other = (AnnotationPropertyImpl) obj;
			return name.equals(other.name);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public String toString() {
		return name;
	}
}
