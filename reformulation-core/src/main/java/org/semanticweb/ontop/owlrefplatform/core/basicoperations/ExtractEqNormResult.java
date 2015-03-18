package org.semanticweb.ontop.owlrefplatform.core.basicoperations;

import fj.data.List;
import org.semanticweb.ontop.model.Function;

/**
 * TODO: explain
 * TODO: find a better name
 *
 * Beware: immutable data structures (from the Functional Java library).
 */
public class ExtractEqNormResult {
    private final List<Function> nonPushableAtoms;
    private final List<Function> pushableAtoms;
    private final Substitution currentSubstitution;

    public ExtractEqNormResult(List<Function> nonPushableAtoms, List<Function> pushableAtoms, Substitution substitution) {
        this.nonPushableAtoms = nonPushableAtoms;
        this.pushableAtoms = pushableAtoms;
        this.currentSubstitution = substitution;
    }

    public List<Function> getNonPushableAtoms() {
        return nonPushableAtoms;
    }

    public List<Function> getPushableAtoms() {
        return pushableAtoms;
    }

    public Substitution getCurrentSubstitution() {
        return currentSubstitution;
    }

    public List<Function> getAllAtoms() {
        return pushableAtoms.append(nonPushableAtoms);
    }

    public static ExtractEqNormResult constructFromNonPushableAtom(Function nonPushableAtom, Substitution substitution) {
        List<Function> nonPushableAtoms = List.cons(nonPushableAtom, List.<Function>nil());
        return new ExtractEqNormResult(nonPushableAtoms, List.<Function>nil(), substitution);
    }

    public static ExtractEqNormResult constructFromPushableAtom(Function pushableAtom, Substitution substitution) {
        List<Function> pushableAtoms = List.cons(pushableAtom, List.<Function>nil());
        return new ExtractEqNormResult(List.<Function>nil(), pushableAtoms, substitution);
    }
}
