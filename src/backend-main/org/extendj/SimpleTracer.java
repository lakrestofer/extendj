package org.extendj;

// import org.extendj.ast.ASTState;
import org.extendj.ast.ASTNode;
import org.extendj.ast.ASTState;


import java.util.Map;
import java.util.HashMap;

import java.io.PrintStream;

public class SimpleTracer implements ASTState.Trace.Receiver {

    private Map<ASTState.Trace.Event, Integer> counter = new HashMap<ASTState.Trace.Event, Integer>();

    public void accept(ASTState.Trace.Event event, ASTNode node, String attribute, Object params, Object value) {
        // recv.accept(new Object[] { event, node, attribute, params, value });
        // System.err.println("hi there");
        counter.merge(event, 1, Integer::sum);
    }

    public void generateReport(PrintStream out) {
        for (Map.Entry<ASTState.Trace.Event, Integer> entry : counter.entrySet()) {
            out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
