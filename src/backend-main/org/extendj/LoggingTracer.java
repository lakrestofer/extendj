package org.extendj;

// import org.extendj.ast.ASTState;
import org.extendj.ast.ASTNode;
import org.extendj.ast.ASTState;


import java.util.Map;
import java.util.HashMap;

import java.io.BufferedWriter;
import java.io.IOException;

public class LoggingTracer implements ASTState.Trace.Receiver {

    private BufferedWriter out;
    private long startTime;

    public LoggingTracer(BufferedWriter out) {
        this.out = out;
        this.startTime = System.nanoTime();
    }

    public void accept(ASTState.Trace.Event event, ASTNode node, String attribute, Object params, Object value) {
        try {
            String eventName = event.toString();
            String astNodeName = ASTNode.nodeToString(node);
            int astNodeId = System.identityHashCode(node);
            out.write(String.format("%d\t%s\t%s\t%d\n", astNodeId, eventName, astNodeName, System.nanoTime() - startTime));
        } catch(IOException e) {
            // e.printStackTrace();
            System.err.println("uh oh");
        }
    }
}
